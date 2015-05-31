/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import generator.ejb.ExperimentBeanLocal;
import generator.util.ExcelExport;
import generator.utils.PagerLink;
import generator.utils.RequestAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maks
 */
@WebServlet(name = "ExperimentServlet", urlPatterns = {"/exp", "/index.html"})
public class ExperimentServlet extends HttpServlet {

//    @EJB
//    private ExperimentFacadeLocal experimentFacade;
//    
//    @EJB
//    private SpectrumFacadeLocal spectrumFacade;
    
    @EJB
    private ExperimentBeanLocal experimentBean;

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

        String action = request.getParameter("action");
        if ("results-to-excel".equals(action)) {
            int experimentId = toInt(request.getParameter("expid"));
            File exelFile = new ExcelExport().exportSpectrum(experimentBean.getResults(experimentId));
            sendFile("spectrum.xls", exelFile, response);
        } else if ("exp-to-xls".equals(action)){
            File exelFile = new ExcelExport().exportExperiments(experimentBean.getAllExperiments());
            sendFile("experiments.xls",exelFile, response);
        } else if ("add-exp".equals(action)){
            request.getRequestDispatcher("/WEB-INF/jsp/add-experiment.jsp").forward(request, response);
//        } else if("search-by-freq".equals(action)){
            
        } else if ("results".equals(action)) {
            viewResults(request, response);
        } else {
            viewExperiments(request, response);
        }
    }
    
    private void viewExperiments (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            int paginationStep = (int) toLong(request.getParameter("paginationstep"));
            if (paginationStep < 5) {
                paginationStep = 5;
            } else if (paginationStep > 100) {
                paginationStep = 100;
            }

            
            int page = toInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
            
            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter("paginationstep", "" + paginationStep);
            request.setAttribute(RequestAttribute.PAGER.getName(),
                    experimentBean.getPager(page, paginationStep));
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);  
            request.setAttribute("experiments", experimentBean.getExperimentPage(page, paginationStep));
                        
            request.getRequestDispatcher("/WEB-INF/jsp/experiments.jsp?paginationstep=" + paginationStep).forward(request, response);
//            request.getRequestDispatcher("/WEB-INF/jsp/experiments.jsp?page=" + page
//                    + "&paginationstep=" + paginationStep).forward(request, response);
        
    }
    
        
    private void viewResults (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            int paginationStep = (int) toLong(request.getParameter("paginationstep"));
            if (paginationStep < 5) {
                paginationStep = 5;
            } else if (paginationStep > 100) {
                paginationStep = 100;
            }
            int page = toInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
            int experimentId = toInt(request.getParameter("expid"));
            
            //if (experimentId < 1)
            
            PagerLink pagerLink = new PagerLink();
            pagerLink.addParameter("paginationstep", "" + paginationStep);
            pagerLink.addParameter("action", "results");
            pagerLink.addParameter("expid", "" + experimentId);
            request.setAttribute(RequestAttribute.PAGER.getName(),
                    experimentBean.getResultsPager(experimentId, page, paginationStep));
            request.setAttribute(RequestAttribute.PAGER_LINK.getName(), pagerLink);
            
            Collection <Spectrum> spectrum = experimentBean.getResults(experimentId, page, paginationStep);
            request.setAttribute("spectrum", spectrum);
            request.getRequestDispatcher("/WEB-INF/jsp/spectrum.jsp?page=" + page
                    + "&paginationstep=" + paginationStep).forward(request, response);
        
    }



    
        @Deprecated
    private int pageValidation(String pageString, int paginationStep, int amount) {
        int page = (int) toLong(pageString);

        if ("last".equals(pageString)) {

            page = (int) ((amount - 1) / paginationStep) + 1;

        } else if (page < 1) {
            page = 1;
        } else {

            int lastPage = (int) ((amount - 1) / paginationStep) + 1;
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
    
        
    private int toInt(String intString) {
        int number;
        try {
            number = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            number = -1;
        } catch (NullPointerException e) {
            number = -1;
        }
        return number;
    }
    
    
        


    private void sendFile(String fileName, File excelFile, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
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
        processPostRequest(request, response);
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
