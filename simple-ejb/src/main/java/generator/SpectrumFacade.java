/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maks
 */
@Stateless
public class SpectrumFacade extends AbstractFacade<Spectrum> implements SpectrumFacadeLocal {
//    @PersistenceContext(unitName = "generator_generator-ejb_ejb_1.0PU")
    private EntityManager em = null;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SpectrumFacade() {
 //       super(Spectrum.class);
    }
    
}
