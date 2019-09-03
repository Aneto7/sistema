/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Antonio
 */
public class ServletAtualizarRevisao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String idkm = request.getParameter("idkm");
        if (idkm.equals("")) {
            idkm = "0";
        }

        String nomeplaca = request.getParameter("idplaca");
        String nomelocadora = request.getParameter("idlocadora");
        String nomebase = request.getParameter("idbase");
        String nomedata = request.getParameter("iddata");
        Double km = Double.parseDouble(idkm);
        String nomedetalhe = request.getParameter("iddetalhe");
        int nomeid = (Integer.parseInt(request.getParameter("idid")));
        Double nomevalor = (Double.parseDouble(idkm));

        Date data = Date.valueOf(nomedata);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = con.createStatement();

            st.executeUpdate("UPDATE revisoes SET PLACA='"
                    + nomeplaca + "',LOCADORA='"
                    + nomelocadora + "',BASE='"
                    + nomebase + "',KM='"
                    + km + "',DATA='"
                    + data + "',OBSERVACAO='"
                    + nomedetalhe
                    + "' WHERE ID='" + nomeid + "'");
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            ConexaoMySQL.FecharConexao();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nomeplaca + "#" + nomelocadora + "#" + nomebase
                + "#" + km + "#" + data + "#" + nomedetalhe + ";");
        out.write(sb.toString());
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

}
