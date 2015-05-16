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
public interface ExperimentFacadeLocal {

    void create(Experiment experiment);

    void edit(Experiment experiment);

    void remove(Experiment experiment);

    Experiment find(Object id);

    List<Experiment> findAll();

    List<Experiment> findRange(int[] range);

    int count();
    
    List<Experiment> findPage(int pageNumber, int paginationStep);
    
}
