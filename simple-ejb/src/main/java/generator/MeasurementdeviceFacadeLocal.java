/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maks
 */
@Local
public interface MeasurementdeviceFacadeLocal {

    void create(Measurementdevice measurementdevice);

    void edit(Measurementdevice measurementdevice);

    void remove(Measurementdevice measurementdevice);

    Measurementdevice find(Object id);

    List<Measurementdevice> findAll();

    List<Measurementdevice> findRange(int[] range);

    int count();

    Measurementdevice findBySerialNumber (String serialNumber);
}
