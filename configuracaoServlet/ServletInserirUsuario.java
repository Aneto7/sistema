/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.configuracaoServlet;

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
 * @author rosaan
 */
public class ServletInserirUsuario extends HttpServlet {

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

        String nomeusuario = request.getParameter("idusuario");
        String nomenome = request.getParameter("idnome");
        String nomeemail = request.getParameter("idemail");
        String nomesetor = request.getParameter("idsetor");
        String nomeperfil = request.getParameter("idperfil");
        String nomeregiao = request.getParameter("idregiao");
        String nomesenha = request.getParameter("idsenha");
        String nomefilial = request.getParameter("idfilial");
        String nomearea = request.getParameter("idarea");
        int nomeid = (Integer.parseInt(request.getParameter("idid")));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = con.createStatement();

            st.executeUpdate("INSERT INTO `usuario` (`ID`, `USUARIO`, `FILIAL`, `REGIAO`, `SENHA`, "
                    + "`PERFIL`, `EMAIL`, `NOME`, `SETOR`, `AREA`) VALUES (NULL, '" + nomeusuario + "', '" + nomefilial + "', '" + nomeregiao + "', "
                    + "'" + nomesenha + "', '" + nomeperfil + "', '" + nomeemail + "', '" + nomenome + "', '" + nomesetor + "', '" + nomearea + "');");
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            ConexaoMySQL.FecharConexao();
        }
//        StringBuilder sb = new StringBuilder();
//
//        sb.append(nomematricula + "#" + nomenome + "#" + nomecentro
//                + "#" + nomeibutton + "#" + nomepoll + "#" + nometrab + ";");
//        out.write(sb.toString());
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
