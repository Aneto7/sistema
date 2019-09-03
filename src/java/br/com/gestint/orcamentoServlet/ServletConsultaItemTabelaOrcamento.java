/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.Contagens;
import br.com.gestint.orcamento.Orcamento;
import br.com.gestint.orcamentoDAO.FiltroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author rosaan
 */
public class ServletConsultaItemTabelaOrcamento extends HttpServlet {

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
        HttpSession session = request.getSession(false);

        StringBuilder sb = new StringBuilder();
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("idid"));

        PreparedStatement stmt = null;
        List<Orcamento> orcs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `orcamentocontrole` WHERE ID =" + id);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Orcamento orc = new Orcamento();
                orc.setResponsavel(rs.getString("RESPONSAVEL"));
                orc.setSegmento(rs.getString("SEGMENTO"));
                orc.setRegiao(rs.getString("REGIAO"));
                orc.setFilial(rs.getString("DESCRICAO_FILIAL"));
                orc.setMagnitude(rs.getString("MAGNITUDE_CONTA"));
                orc.setCentrodecusto(rs.getString("CENTRO_DE_CUSTO"));
                orc.setConta(rs.getString("CONTA"));
                orcs.add(orc);
            }
            for (int i = 0; i < orcs.size(); i++) {
                sb.append(orcs.get(i).getResponsavel() + "%" + orcs.get(i).getSegmento() + "%" + orcs.get(i).getRegiao()
                        + "%" + orcs.get(i).getFilial() + "%" + orcs.get(i).getMagnitude()+ "%" + orcs.get(i).getCentrodecusto()
                        + "%" + orcs.get(i).getConta());
            }
            out.write(sb.toString());
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
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
