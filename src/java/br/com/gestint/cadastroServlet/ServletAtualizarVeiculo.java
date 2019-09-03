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
public class ServletAtualizarVeiculo extends HttpServlet {

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

        String valor = "0";
        if (request.getParameter("idvalor") == "") {
            valor = "0";
        } else {
            valor = request.getParameter("idvalor");
        }

        int nomeid = (Integer.parseInt(request.getParameter("idid")));
        String nomeplaca = request.getParameter("idplaca");
        String nomelocado = request.getParameter("idlocador");
        Double nomevalor = (Double.parseDouble(valor));
        String nomefilial = request.getParameter("idfilial");
        String nomeempresa = request.getParameter("idempresa");
        String nomeccsoja = request.getParameter("idccsoja");
        String nomeccmilho = request.getParameter("idccmilho");
        String nomedatainicio = request.getParameter("iddatainicio");
        String nomedatafim = request.getParameter("iddatafim");
        String nometipo = request.getParameter("idtipo");
        String nomemodelo = request.getParameter("idmodelo");
        String nomeclassificacao = request.getParameter("idclassificacao");
        String nomecartao = request.getParameter("idcartao");
        String nomecondutor = request.getParameter("idcondutor");
        String nomestatus = request.getParameter("idstatus");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = con.createStatement();

            st.executeUpdate("UPDATE veiculos SET PLACA='"
                    + nomeplaca + "',LOCADOR='"
                    + nomelocado + "',VALOR_LOCACAO='"
                    + nomevalor + "',INICIO_CONTRATO='"
                    + nomedatainicio + "',FIM_CONTRATO='"
                    + nomedatafim + "',UNIDADE='"
                    + nomefilial + "',MODELO='"
                    + nomemodelo + "',TIPO='"
                    + nometipo + "',CLASSIFICACAO='"
                    + nomeclassificacao + "',EMPRESA='"
                    + nomeempresa + "',CC_SOJA='"
                    + nomeccsoja + "',CARTAO='"
                    + nomecartao + "',CONDUTOR='"
                    + nomecondutor + "',CC_MILHO='"
                    + nomeccmilho + "',DEVOLVIDO='"
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
