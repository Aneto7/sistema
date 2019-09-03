/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoServlet;

import br.com.gestint.administrativo.Manutencao;
import br.com.gestint.administrativo.Revisao;
import br.com.gestint.atividades.ConexaoMySQL;
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
 * @author Antonio
 */
public class ServletRevisao extends HttpServlet {

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

        String nomeplaca = request.getParameter("idplaca");
        String nomelocadora = request.getParameter("idlocadora");

        StringBuilder sb = new StringBuilder();
        List<Revisao> revisoes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ADDDATE( DATA, INTERVAL 60 DAY) AS PROREV, ID, PLACA, LOCADORA, BASE, KM, DATA, OBSERVACAO FROM `revisoes` WHERE "
                    + "`PLACA` LIKE '%" + nomeplaca
                    + "' AND `LOCADORA` LIKE '%" + nomelocadora
                    + "' ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Revisao revisao = new Revisao();
                revisao.setId(rs.getInt("ID"));
                revisao.setPlaca(rs.getString("PLACA"));
                revisao.setLocadora(rs.getString("LOCADORA"));
                revisao.setBase(rs.getString("BASE"));
                revisao.setKm(rs.getDouble("KM"));
                revisao.setData(rs.getDate("DATA"));
                revisao.setDetalhe(rs.getString("OBSERVACAO"));
                revisao.setProximarevisao(rs.getDate("PROREV"));
                revisoes.add(revisao);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        for (int i = 0; i < revisoes.size(); i++) {
            sb.append(revisoes.get(i).getId() + "#" + revisoes.get(i).getPlaca() + "#" + revisoes.get(i).getLocadora()
                    + "#" + revisoes.get(i).getBase() + "#" + revisoes.get(i).getKm() + "#" + revisoes.get(i).getData()
                    + "#" + revisoes.get(i).getDetalhe() + "#" + revisoes.get(i).getProximarevisao() + ";");
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
