
package generator;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maks
 */
@Local
public interface MeasurementDeviceFacadeLocal {

    void create(Measurementdevice measurementdevice);

    void edit(Measurementdevice measurementdevice);

    void remove(Measurementdevice measurementdevice);

    Measurementdevice find(Object id);

    List<Measurementdevice> findAll();

    List<Measurementdevice> findRange(int[] range);

    int count();

    Measurementdevice findBySerialNumber (String serialNumber);
}
