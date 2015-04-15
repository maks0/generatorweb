/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maks
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Spectrum.findAll", query = "SELECT s FROM Spectrum s"),
    @NamedQuery(name = "Spectrum.findByTime", query = "SELECT s FROM Spectrum s WHERE s.time = :time"),
    @NamedQuery(name = "Spectrum.findByVoltage", query = "SELECT s FROM Spectrum s WHERE s.voltage = :voltage")})
public class Spectrum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date time;
    private BigInteger voltage;

    public Spectrum() {
    }

    public Spectrum(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigInteger getVoltage() {
        return voltage;
    }

    public void setVoltage(BigInteger voltage) {
        this.voltage = voltage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (time != null ? time.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Spectrum)) {
            return false;
        }
        Spectrum other = (Spectrum) object;
        if ((this.time == null && other.time != null) || (this.time != null && !this.time.equals(other.time))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generator.Spectrum[ time=" + time + " ]";
    }
    
}
