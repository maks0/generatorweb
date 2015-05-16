
package generator.ejb;

import generator.Experiment;
import generator.ExperimentFacadeLocal;
import generator.Spectrum;
import generator.SpectrumFacadeLocal;
import generator.dto.ExperimentDTO;
import generator.util.PageCalculator;
import generator.util.Pager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author maks
 */
@Stateless
public class ExperimentBean implements ExperimentBeanLocal {

    @EJB
    ExperimentFacadeLocal experimentFacade;
    
    @EJB
    SpectrumFacadeLocal spectrumFacadeLocal;

    public Collection<Spectrum> getResults(int experimentId, int page, int pStep) {
        Experiment experiment = experimentFacade.find(experimentId);
        Collection<Spectrum> spectrum = spectrumFacadeLocal.findPage(experiment, page, pStep);
        return new ArrayList <Spectrum> (spectrum);
    }
    
        
    public Collection<ExperimentDTO> getExperimentPage(int page, int pStep) {
        Collection<Experiment> experiments = experimentFacade.findPage(page, pStep);
        return toExperimentDTO(experiments);
    }
    public Pager getResultsPager (int experimentId, int page, int pStep){
        Experiment experiment = experimentFacade.find(experimentId);
        return PageCalculator.getInstance().calculatePages(page, pStep, spectrumFacadeLocal.count(experiment));
    }
        
    public Pager getPager (int page, int pStep){
        return PageCalculator.getInstance().calculatePages(page, pStep, experimentFacade.count());
    }
    
    private List <ExperimentDTO> toExperimentDTO (Collection <Experiment> experiments ) {
        List <ExperimentDTO> dtoList = new ArrayList<ExperimentDTO>();
        Iterator <Experiment>  experimentIterator = experiments.iterator();
        while (experimentIterator.hasNext()){
            Experiment exp = experimentIterator.next();
            String model = exp.getDevice().getModel();
            System.out.println("==========" + exp.getDevice().getModel());
            dtoList.add(new ExperimentDTO(exp.getId(), exp.getDatetime(), 
                    exp.getComment(), model));
        }
        return dtoList;
    }
        
//    public int countResults(int experimentId) {
//        Experiment experiment = experimentFacade.find(experimentId);
//
//        return 1;
//    }
}
