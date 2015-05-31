/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maks
 */
@Local
public interface SpectrumSiteUserFacadeLocal {

    void create(SpectrumSiteUser spectrumSiteUser);

    void edit(SpectrumSiteUser spectrumSiteUser);

    void remove(SpectrumSiteUser spectrumSiteUser);

    SpectrumSiteUser find(Object id);

    List<SpectrumSiteUser> findAll();

    List<SpectrumSiteUser> findRange(int[] range);

    int count();
    
}
