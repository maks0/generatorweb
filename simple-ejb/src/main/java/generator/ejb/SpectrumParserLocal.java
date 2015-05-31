package generator.ejb;

import generator.Spectrum;

import javax.ejb.Local;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

/**
 * Created by maks on 30.05.15.
 */
@Local
public interface SpectrumParserLocal {
    Spectrum parseLine (String line);
    List<Spectrum> parseSpectrum(Part filePart) throws IOException;
}
