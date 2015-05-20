
package generator;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maks
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experiment.findAll", query = "SELECT e FROM Experiment e ORDER BY e.datetime DESC"),
    @NamedQuery(name = "Experiment.findByDatetime", query = "SELECT e FROM Experiment e WHERE e.datetime = :datetime"),
    @NamedQuery(name = "Experiment.findByComment", query = "SELECT e FROM Experiment e WHERE e.comment = :comment"),
    @NamedQuery(name = "Experiment.findById", query = "SELECT e FROM Experiment e WHERE e.id = :id")})
public class Experiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Size(max = 250)
    private String comment;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "device", referencedColumnName = "id")
    @ManyToOne
    private Measurementdevice device;
    @OneToMany(mappedBy = "experiment")
    private Collection<Spectrum> spectrumCollection;

    public Experiment() {
    }

    public Experiment(Integer id) {
        this.id = id;
    }

    public Experiment(Integer id, Date datetime) {
        this.id = id;
        this.datetime = datetime;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Measurementdevice getDevice() {
        return device;
    }

    public void setDevice(Measurementdevice device) {
        this.device = device;
    }

    @XmlTransient
    public Collection<Spectrum> getSpectrumCollection() {
        return spectrumCollection;
    }

    public void setSpectrumCollection(Collection<Spectrum> spectrumCollection) {
        this.spectrumCollection = spectrumCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Experiment)) {
            return false;
        }
        Experiment other = (Experiment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generator.Experiment[ id=" + id + " ]";
    }
    
}
