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
import javax.persistence.Query;
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
    
    public List<Spectrum> findPage(Experiment experiment, int pageNumber, int paginationStep) {
        TypedQuery<Spectrum> query = em.createNamedQuery("Spectrum.findByExperiment", Spectrum.class);
        query.setParameter("experiment", experiment);
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        List<Spectrum> answers = query.getResultList();
        return answers;
    }
    
        
    public List<Spectrum> findByExperiment(Experiment experiment) {
        TypedQuery<Spectrum> query = em.createNamedQuery("Spectrum.findByExperiment", Spectrum.class);
        query.setParameter("experiment", experiment);
        List<Spectrum> answers = query.getResultList();
        return answers;
    }
    
    public int count (Experiment experiment){
        Query query = em.createQuery("SELECT count(s) FROM Spectrum s WHERE (s.experiment = :experiment)");
        query.setParameter("experiment", experiment);
        return ((Long) query.getSingleResult()).intValue();
    }
}
