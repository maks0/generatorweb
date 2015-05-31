package generator;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maks
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Spectrum.findAll", query = "SELECT s FROM Spectrum s ORDER BY s.frequency"),
    @NamedQuery(name = "Spectrum.findById", query = "SELECT s FROM Spectrum s WHERE s.id = :id"),
    @NamedQuery(name = "Spectrum.findByMagnitude", query = "SELECT s FROM Spectrum s WHERE s.magnitude = :magnitude"),
    @NamedQuery(name = "Spectrum.findByExperiment", query = "SELECT s FROM Spectrum s WHERE s.experiment = :experiment ORDER BY s.frequency"),
    @NamedQuery(name = "Spectrum.findByFrequency", query = "SELECT s FROM Spectrum s WHERE s.frequency = :frequency")})
public class Spectrum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "magnitude")
    private double magnitude;
    @Basic(optional = false)
//    @NotNull
    private double frequency;
    @JoinColumn(name = "experiment", referencedColumnName = "id")
    @ManyToOne
    private Experiment experiment;

    public Spectrum() {
    }

    public Spectrum(Long id) {
        this.id = id;
    }

    public Spectrum(Long id, double frequency, double magnitude) {
        this.id = id;
        this.magnitude = magnitude;
        this.frequency = frequency;
    }
    public Spectrum(double frequency, double magnitude) {
        this.magnitude = magnitude;
        this.frequency = frequency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
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
        if (!(object instanceof Spectrum)) {
            return false;
        }
        Spectrum other = (Spectrum) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generator.Spectrum[ id=" + id + " ]";
    }
    
}
