package generator.util;

import generator.Spectrum;
import generator.dto.ExperimentDTO;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * @author maks
 */
public class ExcelExport {
    
        
    public File exportSpectrum (Collection<Spectrum> rows) throws IOException {
        return exportSpectrum("Spectrum", rows);
    }

    public File exportSpectrum (String header, Collection<Spectrum> rows) throws IOException {
        return writeSpectrum(header, createNewFile(), rows);
    }
        
//    public File exportSpectrum (String header, Collection<Spectrum> rows) throws IOException {
//        return writeSpectrum(header, createNewFile(), rows);
//    }
        
    public File exportExperiments (Collection<ExperimentDTO> rows) throws IOException {
        return writeExperiments(createNewFile(), rows);
    }
    
        
    private File createNewFile () {
        String directory = "mytemp";
        if (!(Files.isDirectory(Paths.get(directory)))) {
            new File(directory).mkdirs();
        }

        String time = ((Long) Calendar.getInstance().getTimeInMillis()).toString();
        String filePath = directory + File.separator + "Report" + time + ".xls"; 
        return new File(filePath);
    }

    public File writeSpectrum(String header, File f, Collection <Spectrum> report) throws IOException {

        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;

        if (f.exists()) {
            f.delete();
            f.createNewFile();
        }

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Spectrum");
        writeSpectrumTable(header, sheet, report);

        return writeExcelFile(f, workbook);
    }
        
    public File writeExperiments(File f, Collection<ExperimentDTO> report) throws IOException {

        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;

        if (f.exists()) {
            f.delete();
            f.createNewFile();
        }

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Experiments");
        writeExperimentsTable(sheet, report);

        return writeExcelFile(f, workbook);
    }
    
    
    private File writeExcelFile (File file, HSSFWorkbook workbook) throws IOException{
                
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            workbook.write(out);
            return file;
        } finally {
            if (out != null) {
                try {
                out.close();
                } catch (IOException e){
                    //TODO 
                    
                }
            }
        }
    }

    private void writeSpectrumTable(String header, Sheet sheet, Collection<Spectrum> spectrum) {
        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue(header);
        firstRow.createCell(4).setCellValue("File was created by System : ");
        firstRow.createCell(8).setCellValue(dateToString(Calendar.getInstance().getTime()));
        
        Row secRow = sheet.createRow(1);
        secRow.createCell(0).setCellValue("Спектр");
        
        Row tableHeader = sheet.createRow(2);
        tableHeader.createCell(0).setCellValue("Frequency, Hz");
        tableHeader.createCell(1).setCellValue("Voltage, V");
                
        Row tableHeaderUkr = sheet.createRow(3);
        tableHeaderUkr.createCell(0).setCellValue("Частота, Гц");
        tableHeaderUkr.createCell(1).setCellValue("Напруга, В");
        int rowNum = 4;
        for (Iterator<Spectrum> it = spectrum.iterator(); it.hasNext();) {
            Spectrum dataRow = it.next();
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(dataRow.getFrequency());
            row.createCell(1).setCellValue(dataRow.getVoltage());
            rowNum++;
        }
    }
        
    private void writeExperimentsTable(Sheet sheet, Collection<ExperimentDTO> experiments) {
        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("Experiments Report");
        firstRow.createCell(4).setCellValue("File was created by System : ");
        firstRow.createCell(8).setCellValue(dateToString(Calendar.getInstance().getTime()));
        
        Row secRow = sheet.createRow(1);
        secRow.createCell(0).setCellValue("Експерименти");
        
        Row tableHeader = sheet.createRow(2);
        tableHeader.createCell(0).setCellValue("Start date and time");
        tableHeader.createCell(1).setCellValue("Measurement Device");
        tableHeader.createCell(2).setCellValue("Comment");
        Row tableHeaderUkr = sheet.createRow(3);
        tableHeaderUkr.createCell(0).setCellValue("Початок експеременту");
        tableHeaderUkr.createCell(1).setCellValue("Вимірювальний пристрій");
        tableHeaderUkr.createCell(2).setCellValue("Коментар");
        int rowNum = 4;
        for (Iterator<ExperimentDTO> it = experiments.iterator(); it.hasNext();) {
            ExperimentDTO dataRow = it.next();
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(dateToString(dataRow.getBeginTime()));
            row.createCell(1).setCellValue(dataRow.getMeasurementDeviceModel());
            row.createCell(2).setCellValue(dataRow.getComment());
            rowNum++;
        }
    }

    public String dateToString(Date date) { //maybe private?
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US);
        return dateFormat.format(date);
    }

    private void setNullableData(Object data, Cell cell) {
        if (data == null) {
            cell.setCellValue("not set");
        } else {
            if (data instanceof Date) {
                cell.setCellValue((Date) data);
            } else if (data instanceof Boolean) {
                cell.setCellValue((Boolean) data);
            } else if (data instanceof String) {
                cell.setCellValue((String) data);
            } else if (data instanceof Number) {
                cell.setCellValue(((Number) data).doubleValue());
            }
        }
    }

//    public File write(String path, List<List> data) throws IOException {
//
//        HSSFWorkbook workbook = null;
//        HSSFSheet sheet = null;
//        File f = new File(path);
//
//        if (!f.exists()) {
//            workbook = new HSSFWorkbook();
//            sheet = workbook.createSheet("Sorting time");
//            formTable(sheet, data);
//
//        } else {
//            try (FileInputStream in = new FileInputStream(new File(path));) {
//
//                workbook = new HSSFWorkbook(in);
//                sheet = workbook.getSheetAt(0);
//                formTable(sheet, data);
//            }
//        }
//        try (FileOutputStream out = new FileOutputStream(f)) {
//            workbook.write(out);
//            return f;
//        }
//    }
//    private void writeTable(Sheet sheet, List<List> data) {
//        int rownum = 0;
//        for (List dataRow : data) {
//            Row row = sheet.getRow(rownum);
//            if (row == null) {
//                row = sheet.createRow(rownum);
//            }
//            rownum++;
//
//            int cellnum = 0;
//            for (Object obj : dataRow) {
//                Cell cell = row.getCell(cellnum);
//                if (cell == null) {
//                    cell = row.createCell(cellnum);
//                }
//                cellnum++;
//
//                if (obj instanceof Date)
//                    cell.setCellValue((Date) obj);
//                else if (obj instanceof Boolean)
//                    cell.setCellValue((Boolean) obj);
//                else if (obj instanceof String)
//                    cell.setCellValue((String) obj);
//                else if (obj instanceof Number) {
//                    cell.setCellValue(((Number) obj).doubleValue());
//                }
//            }
//        }
//    }
}
