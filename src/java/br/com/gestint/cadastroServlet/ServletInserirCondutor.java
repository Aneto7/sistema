/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.cadastroServlet;

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
public class ServletInserirCondutor extends HttpServlet {

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

        String nomenome = request.getParameter("idnome");
        String nomematricula = request.getParameter("idmatricula");
        String nomecentro = request.getParameter("idcentro");
        String nomeibutton = request.getParameter("idibutton");
        String nomecnh = request.getParameter("idcnh");
        String nomecargo = request.getParameter("idcargo");
        String nomecategoria = request.getParameter("idcategoria");
        String nomegsub = request.getParameter("idgsub");
        String nomegreg = request.getParameter("idgreg");
        String nomegdiv = request.getParameter("idgdiv");
        String nomeunidade = request.getParameter("idunidade");
        String nomeempresa = request.getParameter("idempresa");
        String nomearea = request.getParameter("idarea");
        String nomestatus = request.getParameter("idstatus");
        Date nomeadminissao = (Date.valueOf(request.getParameter("idadmissao")));
        Date nomedefensiva = (Date.valueOf(request.getParameter("iddefensiva")));
        Date nomevencimento = (Date.valueOf(request.getParameter("idvencimento")));
        String nomepoll = request.getParameter("idpoll");
        String nometrab = request.getParameter("idtrab");
        String nomedesig = request.getParameter("iddesig");
        String nome31 = request.getParameter("id31");
        String nome32 = request.getParameter("id32");
        String nomeaprovado = request.getParameter("idaprovado");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = con.createStatement();

            st.executeUpdate("INSERT INTO `condutor` (`ID`, `MATRICULA`, `NOME`, `CENTRO`, `IBUTTON`, `POLL`, "
                    + "`TRAB`, `DESIG`, `EMPRESA`, `AREA`, `CARGO`, `UNIDADE`, `REGIONAL`, `DATA_ADMISSAO`, "
                    + "`DATA_REALIZACAO_DEFENSIVA`, `N_CNH`, `VENCIMENTO_CNH`, `CATEGORIA_CNH`, `FORM_31`, `FORM_32`, "
                    + "`GESTOR_SUB_REGIONAL`, `GESTOR_REGIONAL`, `GESTOR_DIVISIONAL`, `APROVADA`, `OBSERVACAO`, `ATIVO`) "
                    + "VALUES (NULL, '" + nomematricula + "', '" + nomenome + "', '" + nomecentro + "', '" + nomeibutton + "', "
                    + "'" + nomepoll + "', '" + nometrab + "', '" + nomedesig + "', '" + nomeempresa + "', '" + nomearea + "', '" + nomecargo + "', "
                    + "'" + nomeunidade + "', '" + nomeunidade + "', '" + nomeadminissao + "', '" + nomedefensiva + "', '" + nomecnh + "', '" 
                    + nomevencimento + "', '" + nomecategoria + "', '" + nome31 + "', '" + nome32 + "', '" + nomegsub + "', '" 
                    + nomegreg + "', '" + nomegdiv + "', '" + nomeaprovado + "', '" + nomeaprovado + "', '" + nomestatus + "');");
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            ConexaoMySQL.FecharConexao();
        }
        StringBuilder sb = new StringBuilder();

        sb.append(nomematricula + "#" + nomenome + "#" + nomecentro
                + "#" + nomeibutton + "#" + nomepoll + "#" + nometrab + ";");
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
