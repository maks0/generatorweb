/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maks
 */
@Entity
@Table(name = "spectrum_site_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SpectrumSiteUser.findAll", query = "SELECT s FROM SpectrumSiteUser s"),
    @NamedQuery(name = "SpectrumSiteUser.findByEmail", query = "SELECT s FROM SpectrumSiteUser s WHERE s.email = :email"),
    @NamedQuery(name = "SpectrumSiteUser.findByPassword", query = "SELECT s FROM SpectrumSiteUser s WHERE s.password = :password"),
    @NamedQuery(name = "SpectrumSiteUser.findByUsername", query = "SELECT s FROM SpectrumSiteUser s WHERE s.username = :username")})
public class SpectrumSiteUser implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Недопустимый адрес электронной почты")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String email;
    @Size(max = 100)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    public SpectrumSiteUser() {
    }

    public SpectrumSiteUser(String email) {
        this.email = email;
    }

    public SpectrumSiteUser(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpectrumSiteUser)) {
            return false;
        }
        SpectrumSiteUser other = (SpectrumSiteUser) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generator.SpectrumSiteUser[ email=" + email + " ]";
    }
    
}
