/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import generator.util.ExcelExport;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author maks
 */
@WebServlet(name = "Controller", urlPatterns = {"/controller", "/add"})
@MultipartConfig
public class Controller extends HttpServlet {

    @EJB
    private SpectrumFacadeLocal spectrumFacade;

    private final static Logger LOGGER
            = Logger.getLogger(Controller.class.getCanonicalName());

//        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet TestServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            
//            String file = request.getParameter("file");
//            
//                    
//
////		out.println("<h1>findall=" + spectrumFacade.findAll()+ "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
//    }
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Create path components to save the file
        final String path = File.separator +"home" + File.separator + "maks";// request.getParameter("destination");
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                writer.print(read);
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + path);
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                    new Object[]{fileName, path});
        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                    new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

//        String action = request.getParameter("action");
//        if ("excel".equals(action)) {
//            File exelFile = new ExcelExport().exportSpectrum(spectrumFacade.findAll());
//            sendFile(exelFile, response);
//        } else {
//
//            int paginationStep = (int) toLong(request.getParameter("paginationstep"));
//            if (paginationStep < 5) {
//                paginationStep = 5;
//            } else if (paginationStep > 100) {
//                paginationStep = 100;
//            }
//
//            int page = pageValidation(request.getParameter("page"), paginationStep);
//
//            request.setAttribute("spectrum", spectrumFacade.findPage(page, paginationStep));
//            request.getRequestDispatcher("/WEB-INF/jsp/spectrum.jsp?page=" + page
//                    + "&paginationstep=" + paginationStep).forward(request, response);
//        }
    }

    private Date dateParser(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm_dd-MM-yyyy", Locale.US);
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Can't covert to Date", ex);
            return Calendar.getInstance().getTime();
        }
    }

    private int pageValidation(String pageString, int paginationStep) {
        int page = (int) toLong(pageString);

        if ("last".equals(pageString)) {

            page = (int) ((spectrumFacade.count() - 1) / paginationStep) + 1;

        } else if (page < 1) {
            page = 1;
        } else {

            int lastPage = (int) ((spectrumFacade.count() - 1) / paginationStep) + 1;
            if (page > lastPage) {
                page = lastPage;
            }

        }
        return page;
    }

    private long toLong(String intString) {
        long number;
        try {
            number = Long.parseLong(intString);
        } catch (NumberFormatException e) {
            number = -1;
        } catch (NullPointerException e) {
            number = -1;
        }
        return number;
    }

    private void sendFile(File excelFile, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=spectrum.xls");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(excelFile);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        excelFile.delete();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processGetRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void processPostRequest(HttpServletRequest request, HttpServletResponse response) {

    }

}
