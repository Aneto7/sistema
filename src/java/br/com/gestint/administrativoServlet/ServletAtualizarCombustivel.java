/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamentoDAO.FiltroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author rosaan
 */
public class ServletAtualizarCombustivel extends HttpServlet {

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
        FiltroDAO fdao = new FiltroDAO();

        String nomeplaca = request.getParameter("idplaca");
        String nomecartao = request.getParameter("idcartao");
        String nomecondutor = request.getParameter("idcondutor");
        String nomecentro = request.getParameter("idcentro");
        Double nomekm = (Double.parseDouble(request.getParameter("idkm")));
        Double nomevalor = (Double.parseDouble(request.getParameter("idvalor")));
        Double nomelitros = (Double.parseDouble(request.getParameter("idlitros")));
        String nomedata = request.getParameter("iddata");
        int id = (Integer.parseInt(request.getParameter("idid")));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = con.createStatement();

            st.executeUpdate("UPDATE combustivel SET PLACA='"
                    + nomeplaca + "',CARTAO='"
                    + nomecartao + "',DATA='"
                    + nomedata + "',VALOR='"
                    + nomevalor + "',LITROS='"
                    + nomelitros + "',KM='"
                    + nomekm + "',CENTRO_CUSTO='"
                    + nomecentro + "',CONDUTOR='"
                    + nomecondutor
                    + "' WHERE ID='" + id + "'");
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            ConexaoMySQL.FecharConexao();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nomeplaca + "#" + nomecartao + "#" + nomedata
                + "#" + nomevalor + "#" + nomelitros + "#" + nomekm
                + "#" + nomecentro + "#" + nomecondutor + ";");
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
