package generator.ejb;

import generator.Experiment;
import generator.ExperimentFacadeLocal;
import generator.Spectrum;
import generator.SpectrumFacadeLocal;
import generator.dto.ExperimentDTO;
import generator.util.PageCalculator;
import generator.util.Pager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.Part;

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

    public void addExperiment(Part filePart) throws IOException {

        OutputStream out = null;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(filePart.getInputStream()));

            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                addResult(line);
            }
        } finally {
            if (out != null) {
                out.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

    private File createNewFile() {
        String directory = "mytemp";
        if (!(Files.isDirectory(Paths.get(directory)))) {
            new File(directory).mkdirs();
        }

        String time = ((Long) Calendar.getInstance().getTimeInMillis()).toString();
        String filePath = directory + File.separator + "Uploaded" + time + ".txt";
        return new File(filePath);
    }
    
//    private double[][] convertToArray(String[] lines){
//        return convertToArray(lines, 0);
//    }

//    private double[][] convertToArray(String[] lines, int fromIndex) {
//
//        ArrayList<double[]> sig = new ArrayList<double[]>();
//        Pattern patt = Pattern.compile("^\\s*(\\S+)\\s+(\\S+)\\s+(.*)$");
//        Matcher match;
//        for (int i = fromIndex; i < lines.length; i++) {
//            try {
//                match = patt.matcher(lines[i]); // ////
//                match.find();
//                sig.add(new double[]{Double.parseDouble(match.group(1)),
//                    Double.parseDouble(match.group(2))});
//            } catch (NumberFormatException e) {
//                continue; //it's ok
//            } 
//            
//            
////IllegalStateException e
////                                }    catch (IndexOutOfBoundsException e ){
////                                
//
//        }
//        double[][] signal = new double[sig.size()][2];
//        return sig.toArray(signal);
//    }
    
    private boolean addResult (String line){
        try {
        Pattern pattern = Pattern.compile("^\\s*([0-9Ee,.+-]{1,40})\\s+([0-9Ee,.+-]{1,40})\\s+(.*)$");
        Matcher match = pattern.matcher(line);
        if (match.find()){
            double x = Double.parseDouble(match.group(1));
            double y = Double.parseDouble(match.group(2));
            System.out.println("MATCH!!! X = " + x + " Y = " + y);
            return true;
        }
        } catch (NumberFormatException e){
            // it's ok
            return false;
        }
        return false;
    }

//    public int countResults(int experimentId) {
//        Experiment experiment = experimentFacade.find(experimentId);
//
//        return 1;
//    }
}
