
package generator.dto;

import java.util.Date;

/**
 *
 * @author maks
 */
public class ExperimentDTO {
    
    private Date beginTime;
    
    private String comment;
    
    private int id;
    
    private String MeasurementDeviceModel;
    
    public ExperimentDTO () {};

    public ExperimentDTO(int id, Date beginTime, String comment, String MeasurementDeviceModel) {
        this.beginTime = (Date) beginTime.clone();
        this.comment = comment;
        this.id = id;
        this.MeasurementDeviceModel = MeasurementDeviceModel;
    }
    
    

    public Date getBeginTime() {
        return (Date) beginTime.clone();
    }

    public String getComment() {
        return comment;
    }

    public int getId() {
        return id;
    }

    public String getMeasurementDeviceModel() {
        return MeasurementDeviceModel;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = (Date) beginTime.clone();
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMeasurementDeviceModel(String MeasurementDeviceModel) {
        this.MeasurementDeviceModel = MeasurementDeviceModel;
    }
    
    
    
    
}
