/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.Date;
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
public class ExperimentFacade extends AbstractFacade<Experiment> implements ExperimentFacadeLocal {
    @PersistenceContext(unitName = "generator_generator-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExperimentFacade() {
        super(Experiment.class);
    }
    
        
    @Override
    public List<Experiment> findPage(int pageNumber, int paginationStep) {
        TypedQuery<Experiment> query = em.createNamedQuery("Experiment.findAll", Experiment.class);
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public Experiment find(Date begin, Measurementdevice device, String comment) {
        TypedQuery<Experiment> query = em.createNamedQuery("Experiment.findByBeginDeviceComment", Experiment.class);
        query.setParameter("begin", begin);
        query.setParameter("comment", comment);
        query.setParameter("device", device);
        return query.getSingleResult();
    }


}
