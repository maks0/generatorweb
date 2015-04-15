/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author maks
 */
@Stateless
public class SpectrumFacade extends AbstractFacade<Spectrum> implements SpectrumFacadeLocal {
    @PersistenceContext(unitName = "generator_generator-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SpectrumFacade() {
        super(Spectrum.class);
    }
    
    public List<Spectrum> findPage(int pageNumber, int paginationStep) {
        TypedQuery<Spectrum> query = em.createNamedQuery("Forummessages.findAll", Spectrum.class);
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        List<Spectrum> answers = query.getResultList();
        return answers;
    }
    
}
