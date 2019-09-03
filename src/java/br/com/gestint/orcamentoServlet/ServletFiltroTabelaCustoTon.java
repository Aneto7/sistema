/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.DashboardInicial;
import br.com.gestint.orcamentoDAO.FiltroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rosaan
 */
public class ServletFiltroTabelaCustoTon extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String nomeplataforma = request.getParameter("idplataforma");
        int nomeano = Integer.parseInt(request.getParameter("idano"));
        if (nomeano < 2018) {
            nomeano = 2018;
        }

        StringBuilder sb = new StringBuilder();
        List<DashboardInicial> orcs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(orcamentocontrole.ID) AS ID, orcamentocontrole.DESCRICAO_FILIAL AS FILIAL, 'ORÇAMENTO' AS SEG, ((orcamentocontrole.REAL_JAN)+(orcamentocontrole.REAL_FEV)+(orcamentocontrole.REAL_MAR)+(orcamentocontrole.REAL_ABR)+(orcamentocontrole.REAL_MAI)+(orcamentocontrole.REAL_JUN)+(orcamentocontrole.REAL_JUL)+(orcamentocontrole.REAL_AGO)+(orcamentocontrole.REAL_SET)+(orcamentocontrole.REAL_OUT)+(orcamentocontrole.REAL_NOV)+(orcamentocontrole.REAL_DEZ)) AS REAL_ACUM, SUM(IF((apoioorcamento.TIPO='VOLUME' AND apoioorcamento.PLANOUREAL='REALIZADO'), apoioorcamento.VALOR, 0)) AS VOLUMEREAL FROM `orcamentocontrole` LEFT JOIN `apoioorcamento` ON orcamentocontrole.DESCRICAO_FILIAL=apoioorcamento.FILIAL  WHERE orcamentocontrole.DESCRICAO_FILIAL <> '' GROUP BY orcamentocontrole.FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                DashboardInicial orcamento = new DashboardInicial();
                orcamento.setId(rs.getInt("ID"));
                orcamento.setFilial(rs.getString("FILIAL"));
                orcamento.setSomaorc((Double.parseDouble(rs.getString("REAL_ACUM"))));
                orcamento.setMes1((Double.parseDouble(rs.getString("VOLUMEREAL"))));
                orcamento.setSoma((Double.parseDouble(rs.getString("REAL_ACUM"))) / (Double.parseDouble(rs.getString("VOLUMEREAL"))));

                orcs.add(orcamento);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        for (int i = 0; i < orcs.size(); i++) {
            sb.append(orcs.get(i).getId() + "#" + orcs.get(i).getSomaorc() + "#" + orcs.get(i).getMes1() + "#" + orcs.get(i).getSoma()
                    + "#" + orcs.get(i).getFilial() + ";");
        }
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
