/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Measurementdevice.findAll", query = "SELECT m FROM Measurementdevice m"),
    @NamedQuery(name = "Measurementdevice.findById", query = "SELECT m FROM Measurementdevice m WHERE m.id = :id"),
    @NamedQuery(name = "Measurementdevice.findByModel", query = "SELECT m FROM Measurementdevice m WHERE m.model = :model"),
    @NamedQuery(name = "Measurementdevice.findBySerialnumber", query = "SELECT m FROM Measurementdevice m WHERE m.serialnumber = :serialnumber")})
public class Measurementdevice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 100)
    private String model;
    @Size(max = 100)
    private String serialnumber;
    @OneToMany(mappedBy = "device")
    private Collection<Experiment> experimentCollection;

    public Measurementdevice() {
    }

    public Measurementdevice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    @XmlTransient
    public Collection<Experiment> getExperimentCollection() {
        return experimentCollection;
    }

    public void setExperimentCollection(Collection<Experiment> experimentCollection) {
        this.experimentCollection = experimentCollection;
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
        if (!(object instanceof Measurementdevice)) {
            return false;
        }
        Measurementdevice other = (Measurementdevice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generator.Measurementdevice[ id=" + id + " ]";
    }
    
}
