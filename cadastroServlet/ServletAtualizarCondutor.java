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
 * @author rosaan
 */
public class ServletAtualizarCondutor extends HttpServlet {

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
        int nomeid = (Integer.parseInt(request.getParameter("idid")));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = con.createStatement();

            st.executeUpdate("UPDATE condutor SET MATRICULA='"
                    + nomematricula + "',NOME='"
                    + nomenome + "',CENTRO='"
                    + nomecentro + "',IBUTTON='"
                    + nomeibutton + "',POLL='"
                    + nomepoll + "',TRAB='"
                    + nometrab + "',DESIG='"
                    + nomedesig + "',EMPRESA='"
                    + nomeempresa + "',AREA='"
                    + nomearea + "',CARGO='"
                    + nomecargo + "',UNIDADE='"
                    + nomeunidade + "',REGIONAL='"
                    + nomeunidade + "',DATA_ADMISSAO='"
                    + nomeadminissao + "',DATA_REALIZACAO_DEFENSIVA='"
                    + nomedefensiva + "',N_CNH='"
                    + nomecnh + "',VENCIMENTO_CNH='"
                    + nomevencimento + "',CATEGORIA_CNH='"
                    + nomecategoria + "',FORM_31='"
                    + nome31 + "',FORM_32='"
                    + nome32 + "',GESTOR_SUB_REGIONAL='"
                    + nomegsub + "',GESTOR_REGIONAL='"
                    + nomegreg + "',GESTOR_DIVISIONAL='"
                    + nomegdiv + "',APROVADA='"
                    + nomeaprovado + "',OBSERVACAO='"
                    + nomeaprovado + "',ATIVO='"
                    + nomestatus
                    + "' WHERE ID='" + nomeid + "'");
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            ConexaoMySQL.FecharConexao();
        }
//        StringBuilder sb = new StringBuilder();
//        sb.append(nomeplaca + "#" + nomecodigo + "#" + nomelocadora
//                + "#" + nomegravidade + "#" + nomeprazo + "#" + nomecondutor + ";");
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
