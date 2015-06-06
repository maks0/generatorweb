package generator;

import generator.ejb.ExperimentBeanLocal;
import generator.ejb.SpectrumParserLocal;

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
 * @author maks
 */
@WebServlet(name = "Controller", urlPatterns = {"/controller", "/add"})
@MultipartConfig
public class AddExperiment extends HttpServlet {

    @EJB
    private SpectrumParserLocal spectrumParserLocal;

    @EJB
    private ExperimentBeanLocal experimentBeanLocal;

    @EJB
    private SpectrumSiteUserFacadeLocal userFacadeLocal;

    private final static Logger LOGGER
            = Logger.getLogger(AddExperiment.class.getCanonicalName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            SpectrumSiteUser user = userFacadeLocal.find(request.getParameter("email").trim().toLowerCase());
            if (user != null && request.getParameter("password").equals(user.getPassword())) {
                String deviceModel = request.getParameter("device");
                final Part filePart = request.getPart("file");
                Date begin = dateParser(request.getParameter("begin"));
                System.out.println("d" + deviceModel);
                if (deviceModel == null || deviceModel.equals("")){
                    experimentBeanLocal.addExperiment(begin, request.getParameter("device_sn"),
                            spectrumParserLocal.parseSpectrum(filePart), request.getParameter("comment"));
                } else {
                    experimentBeanLocal.addExperiment(begin, deviceModel, request.getParameter("device_sn"),
                            spectrumParserLocal.parseSpectrum(filePart), request.getParameter("comment"));
                }
                response.sendRedirect("exp");
            } else {
                request.setAttribute("errorMessage",
                        "Access denied! Incorrect email or password (check your Caps Lock)! (Неправильный email чи пароль)");
                request.getRequestDispatcher("WEB-INF/jsp/add-experiment.jsp").forward(request, response);
            }
        } catch (InvalidEntityException e){
            request.setAttribute("errorMessage", "Sorry!" + e.getMessage() + ". Please, correct it and try again.");
            request.getRequestDispatcher("WEB-INF/jsp/add-experiment.jsp").forward(request, response);
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
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("exp");
    }

    private Date dateParser(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm_dd-MM-yyyy", Locale.US);
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(AddExperiment.class.getName()).log(Level.SEVERE, "Can't covert to Date", ex);
            return Calendar.getInstance().getTime();
        }
    }


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processGetRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
    }

}
