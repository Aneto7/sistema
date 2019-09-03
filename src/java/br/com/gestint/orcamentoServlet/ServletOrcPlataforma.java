/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.Plataforma;
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
public class ServletOrcPlataforma extends HttpServlet {

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
        FiltroDAO fdao = new FiltroDAO();

        String nomearea = request.getParameter("idarea");
        String nomeregiao = request.getParameter("idregiao");
        String nomeplataforma = request.getParameter("idplataforma");
        String nomefilial = request.getParameter("idfilial");
        String nomemagnitude = request.getParameter("idmagnitude");
        String nomecentrodecusto = request.getParameter("idcentrodecusto");
        String nomeconta = request.getParameter("idconta");

        StringBuilder sb = new StringBuilder();
        List<Plataforma> plataformas = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, SEGMENTO FROM `orcamentocontrole` WHERE "
                    + "`RESPONSAVEL` LIKE '%" + nomearea
                    + "' AND `REGIAO` LIKE '%" + nomeregiao
                    + "' AND `DESCRICAO_FILIAL` LIKE '%" + nomefilial
                    + "' AND `MAGNITUDE_CONTA` LIKE '%" + nomemagnitude
                    + "' AND `CENTRO_DE_CUSTO` LIKE '%" + nomecentrodecusto
                    + "' AND `CONTA` LIKE '%" + nomeconta
                    + "' GROUP BY SEGMENTO ORDER BY SEGMENTO");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Plataforma plataforma = new Plataforma();
                plataforma.setId(rs.getInt("ID"));
                plataforma.setPlataforma(rs.getString("SEGMENTO"));
                plataformas.add(plataforma);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        for (int i = 0; i < plataformas.size(); i++) {
            sb.append(plataformas.get(i).getId() + "-" + plataformas.get(i).getPlataforma() + ";");
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
