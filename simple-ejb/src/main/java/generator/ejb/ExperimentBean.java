package generator.ejb;

import generator.*;
import generator.dto.ExperimentDTO;
import generator.util.PageCalculator;
import generator.util.Pager;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author maks
 */
@Stateless
public class ExperimentBean implements ExperimentBeanLocal {

    @EJB
    private ExperimentFacadeLocal experimentFacade;

    @EJB
    private SpectrumFacadeLocal spectrumFacadeLocal;

    @EJB
    private MeasurementDeviceFacadeLocal deviceFacade;

    @Override
    public Collection<Spectrum> getResults(int experimentId, int page, int pStep) {
        Experiment experiment = experimentFacade.find(experimentId);
        Collection<Spectrum> spectrum = spectrumFacadeLocal.findPage(experiment, page, pStep);
        return new ArrayList<Spectrum>(spectrum);
    }

    @Override
    public Collection<Spectrum> getResults(int experimentId) {
        Experiment experiment = experimentFacade.find(experimentId);
        Collection<Spectrum> spectrum = spectrumFacadeLocal.findByExperiment(experiment);
        return new ArrayList<Spectrum>(spectrum);
    }

    @Override
    public Collection<ExperimentDTO> getExperimentPage(int page, int pStep) {
        Collection<Experiment> experiments = experimentFacade.findPage(page, pStep);
        return toExperimentDTO(experiments);
    }

    @Override
    public Collection<ExperimentDTO> getAllExperiments() {
        Collection<Experiment> experiments = experimentFacade.findAll();
        return toExperimentDTO(experiments);
    }

    @Override
    public Pager getResultsPager(int experimentId, int page, int pStep) {
        Experiment experiment = experimentFacade.find(experimentId);
        return PageCalculator.getInstance().calculatePages(page, pStep, spectrumFacadeLocal.count(experiment));
    }

    @Override
    public Pager getPager(int page, int pStep) {
        return PageCalculator.getInstance().calculatePages(page, pStep, experimentFacade.count());
    }

    private List<ExperimentDTO> toExperimentDTO(Collection<Experiment> experiments) {
        List<ExperimentDTO> dtoList = new ArrayList<ExperimentDTO>();
        Iterator<Experiment> experimentIterator = experiments.iterator();
        while (experimentIterator.hasNext()) {
            Experiment exp = experimentIterator.next();
            String model = exp.getDevice().getModel();
            dtoList.add(new ExperimentDTO(exp.getId(), exp.getDatetime(),
                    exp.getComment(), model));
        }
        return dtoList;
    }


    public void addExperiment(Date begin, int deviceId, List <Spectrum> spectrumList, String comment){
        //TODO check for not null
        Measurementdevice device = deviceFacade.find(deviceId);
        if (device == null){
            throw new InvalidEntityException("Can't find device with ID = " + deviceId);
        }
        Experiment experiment = new Experiment();
        experiment.setDatetime(begin);
        experiment.setDevice(device);
        experiment.setComment(comment);
        experimentFacade.create(experiment);
        Iterator <Spectrum> spectrumIterator = spectrumList.iterator();
        while (spectrumIterator.hasNext()){
            Spectrum spectrum = spectrumIterator.next();
            spectrum.setExperiment(experiment);
            spectrumFacadeLocal.create(spectrum);
        }
    }

    public void addExperiment(Date begin, String deviceModel,
                              String deviceSerialNumber, List <Spectrum> spectrumList, String comment) {
        //TODO check for not null

        javax.transaction.UserTransaction user_tr =null;
        Measurementdevice device = deviceFacade.findBySerialNumber(deviceSerialNumber);
        if (device == null){
            device = createDevice(deviceModel, deviceSerialNumber);
        }
        Experiment experiment = new Experiment();
        experiment.setDatetime(begin);
        experiment.setDevice(device);
        experiment.setComment(comment);
        experimentFacade.create(experiment);
        Iterator <Spectrum> spectrumIterator = spectrumList.iterator();
        while (spectrumIterator.hasNext()){
            Spectrum spectrum = spectrumIterator.next();
            spectrum.setExperiment(experiment);
            spectrumFacadeLocal.create(spectrum);
        }
    }
    private Measurementdevice createDevice (String deviceModel, String deviceSerialNumber){
        Measurementdevice device = new Measurementdevice();
        device.setSerialnumber(deviceSerialNumber);
        device.setModel(deviceModel);
        deviceFacade.create(device);
        return device;
    }


    public void addExperiment(Date begin,
                              String deviceSerialNumber, List <Spectrum> spectrumList, String comment){
        //TODO check for not null
            Measurementdevice device = deviceFacade.findBySerialNumber(deviceSerialNumber);
            if (device == null){
                throw new InvalidEntityException("Can't find device with SN = " + deviceSerialNumber);
            }
            Experiment experiment = new Experiment();
            experiment.setDatetime(begin);
            experiment.setDevice(device);
            experiment.setComment(comment);
            experimentFacade.create(experiment);
            Iterator<Spectrum> spectrumIterator = spectrumList.iterator();
            while (spectrumIterator.hasNext()) {
                Spectrum spectrum = spectrumIterator.next();
                spectrum.setExperiment(experiment);
                spectrumFacadeLocal.create(spectrum);
            }
    }
}
