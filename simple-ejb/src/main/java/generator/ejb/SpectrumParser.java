package generator.ejb;

import generator.Spectrum;

import javax.ejb.Stateless;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maks on 30.05.15.
 */

@Stateless
public class SpectrumParser implements SpectrumParserLocal {

    public Spectrum parseLine (String line){
        try {
            Pattern pattern = Pattern.compile("^\\s*([0-9Ee,\\.\\+\\-]{1,40})\\s+([0-9Ee,\\.\\+\\-]{1,40})(.*)$");
//            Pattern pattern = Pattern.compile(
//                    "^\\s*([0-9\\+\\-]{1,20},?\\.?[0-9Ee\\+\\-]{0,20})\\s+([0-9\\+\\-]{1,20},?\\.?[0-9Ee\\+\\-]{0,20})(.*)$");
            Matcher match = pattern.matcher(line);
            if (match.find()){
                String firstColumn = match.group(1).replace(',', '.');
                String secondColumn = match.group(2).replace(',', '.');
                double frequecy = Double.parseDouble(firstColumn);
                double magnitude = Double.parseDouble(secondColumn);
//            System.out.println("MATCH!!! X = " + x + " Y = " + y);
                return new Spectrum(frequecy, magnitude);
            }
        } catch (NumberFormatException e){
            // it's ok
            return null;
        }
        return null;
    }

    public List<Spectrum> parseSpectrum(Part filePart) throws IOException {
        List<Spectrum> spectrums = new ArrayList<Spectrum>();
        OutputStream out = null;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(filePart.getInputStream()));

            String line = reader.readLine();
            while (line != null) {
                Spectrum spectrum = parseLine(line);
                if (spectrum != null) {
                    spectrums.add(spectrum);
                }
                line = reader.readLine();
            }
            return spectrums;
        } finally {
            if (out != null) {
                out.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }
}
