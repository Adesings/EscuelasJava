/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Alumno;
import modelo.Persona;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Ivan Peters
 */
public class AlumnosController extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
         
         LinkedList<Alumno> listado= Alumno.getAlumnos(); 
        
        for (Alumno alumno : listado) {
            object.put("Run", Persona.agregaDigitoVerificador( alumno.getRun() ));
            object.put("Nombre", alumno.getNombre());
            object.put("Apellido", alumno.getApellido());
            object.put("Sexo", alumno.getSexo());
            object.put("Email", alumno.getMail());
            object.put("Fono", alumno.getFono()); 
            array.add(object);
        }
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
       outputStream.print(array.toString());  
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AlumnosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AlumnosController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
