/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Controller", urlPatterns = {"/controller", "/index.html"})
public class Controller extends HttpServlet {

    @EJB
    private SpectrumFacadeLocal spectrumFacade;

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

        int paginationStep = (int) toLong(request.getParameter("paginationstep"));
        if (paginationStep < 5) {
            paginationStep = 5;
        } else if (paginationStep > 100) {
            paginationStep = 100;
        }
        
        int page = pageValidation(request.getParameter("page"), paginationStep);

        request.setAttribute("spectrum", new TypedContainer<Spectrum>(spectrumFacade.findPage(page, paginationStep)));
        request.getRequestDispatcher("/WEB-INF/jsp/spectrum.jsp").forward(request, response);
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
