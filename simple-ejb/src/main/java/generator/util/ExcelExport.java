package generator.util;

import generator.Spectrum;
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
        String time = ((Long) Calendar.getInstance().getTimeInMillis()).toString();
        String filePath = "mytemp" + File.separator + "Spectrum" + time + ".xls";
        return writeSpectrum(header, createNewFile(), rows);
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

    public File writeSpectrum(String header, File f, Collection<Spectrum> report) throws IOException {

        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;

//        if (f.exists()) {
//            f.delete();
//            f.createNewFile();
//        }

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Spectrum");
        writeSpectrumTable(header, sheet, report);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(f);
            workbook.write(out);
            return f;
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
        Row tableHeader = sheet.createRow(1);
        tableHeader.createCell(0).setCellValue("Frequency");
        tableHeader.createCell(1).setCellValue("Voltage");
        int rowNum = 2;
        for (Iterator<Spectrum> it = spectrum.iterator(); it.hasNext();) {
            Spectrum dataRow = it.next();
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(dataRow.getFrequency());
            row.createCell(1).setCellValue(dataRow.getVoltage());
            rowNum++;
        }
    }

    public File exportReportRows(int allOrders, List<ReportsRow> rows) throws IOException {
        return exportReportRows("TSS report", allOrders, rows);
    }

    public File exportReportRows(String header, int allOrders, List<ReportsRow> rows) throws IOException {
        String time = ((Long) Calendar.getInstance().getTimeInMillis()).toString();
        String filePath = "mytemp" + File.separator + "Report" + time + ".xls";
        return writeReport(header, allOrders, filePath, rows);
    }

    
    
    
    
    public File writeReport(String header, int allOrders, String path, List<ReportsRow> report) throws IOException {

        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;
        File f = new File(path);

        if (f.exists()) {
            f.delete();
            f.createNewFile();
        }

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Report");
        writeReportsRows(header, allOrders, sheet, report);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(f);
            workbook.write(out);
            return f;
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

//    private void writeToFile (File file){
//        
//    }
    private void writeReportsRows(String header, int allOrders, Sheet sheet, List<ReportsRow> reportsRows) {
        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue(header);
        firstRow.createCell(4).setCellValue("Report was created by System : ");
        firstRow.createCell(8).setCellValue(dateToString(Calendar.getInstance().getTime()));
        Row tableHeader = sheet.createRow(1);
        tableHeader.createCell(0).setCellValue("Parameter");
        tableHeader.createCell(1).setCellValue("+");
        tableHeader.createCell(2).setCellValue("-");
        int rowNum = 2;
        for (ReportsRow dataRow : reportsRows) {
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(dataRow.getName());
            row.createCell(1).setCellValue(dataRow.getValue());
            row.createCell(2).setCellValue(allOrders - dataRow.getValue());
            rowNum++;
        }
        Row footer = sheet.createRow(rowNum + 2);
        footer.createCell(0).setCellValue("All analyzed taxi orders : ");
        footer.createCell(4).setCellValue(allOrders);
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
