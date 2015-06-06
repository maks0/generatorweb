/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator.ejb;

import generator.Spectrum;
import generator.dto.ExperimentDTO;
import generator.util.Pager;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maks
 */
@Local
public interface ExperimentBeanLocal {
    
    Collection<Spectrum> getResults(int experimentId, int page, int pStep);
    
    Pager getResultsPager (int experimentId, int page, int pStep);
    
    Pager getPager (int page, int pStep);
    
    Collection<ExperimentDTO> getExperimentPage(int page, int pStep);
    
    Collection<Spectrum> getResults(int experimentId);
    
    Collection<ExperimentDTO> getAllExperiments();

    void addExperiment(Date begin, int deviceId, List <Spectrum> spectrumList, String comment);

    void addExperiment(Date begin, String deviceModel,
                       String deviceSerialNumber, List <Spectrum> spectrumList, String comment);

    void addExperiment(Date begin, String deviceSerialNumber, List <Spectrum> spectrumList, String comment);
}
