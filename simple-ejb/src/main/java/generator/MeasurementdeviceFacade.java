/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author maks
 */
@Stateless
public class MeasurementdeviceFacade extends AbstractFacade<Measurementdevice> implements MeasurementdeviceFacadeLocal {
    @PersistenceContext(unitName = "generator_generator-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MeasurementdeviceFacade() {
        super(Measurementdevice.class);
    }

    public Measurementdevice findBySerialNumber (String serialNumber){
        try {
            TypedQuery<Measurementdevice> query = em.createNamedQuery(
                    "Measurementdevice.findBySerialnumber", Measurementdevice.class);
            query.setParameter("serialnumber", serialNumber);
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    
}
