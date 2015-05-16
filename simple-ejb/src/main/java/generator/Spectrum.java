/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Spectrum.findByVoltage", query = "SELECT s FROM Spectrum s WHERE s.voltage = :voltage"),
    @NamedQuery(name = "Spectrum.findByExperiment", query = "SELECT s FROM Spectrum s WHERE s.experiment = :experiment ORDER BY s.frequency"),
    @NamedQuery(name = "Spectrum.findByFrequency", query = "SELECT s FROM Spectrum s WHERE s.frequency = :frequency")})
public class Spectrum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    private int voltage;
    @Basic(optional = false)
    @NotNull
    private int frequency;
    @JoinColumn(name = "experiment", referencedColumnName = "id")
    @ManyToOne
    private Experiment experiment;

    public Spectrum() {
    }

    public Spectrum(Integer id) {
        this.id = id;
    }

    public Spectrum(Integer id, int voltage, int frequency) {
        this.id = id;
        this.voltage = voltage;
        this.frequency = frequency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
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
