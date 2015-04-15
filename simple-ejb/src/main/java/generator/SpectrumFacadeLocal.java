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
public interface SpectrumFacadeLocal {

    void create(Spectrum spectrum);

    void edit(Spectrum spectrum);

    void remove(Spectrum spectrum);

    Spectrum find(Object id);

    List<Spectrum> findAll();

    List<Spectrum> findRange(int[] range);

    int count();
    List<Spectrum> findPage(int pageNumber, int paginationStep);
    
}
