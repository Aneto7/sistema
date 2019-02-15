/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoServlet;

import br.com.gestint.administrativo.Infracao;
import br.com.gestint.administrativo.Locador;
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
 * @author rosaan
 */
public class ServletInfracao extends HttpServlet {

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
        String nomecondutor = request.getParameter("idcondutor");
        String nomelocadora = request.getParameter("idlocadora");
        String nomecident = request.getParameter("idcident");

        StringBuilder sb = new StringBuilder();
        List<Infracao> infracoes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, LOCADORA, PLACA, CONDUTOR, AUTO_DE_INFRACAO, GRAVIDADE, "
                    + "PRAZO_RECURSO, OBSERVACAO, DESCRICAO FROM `infracoes` WHERE "
                    + "`PLACA` LIKE '%" + nomeplaca
                    + "' AND `LOCADORA` LIKE '%" + nomelocadora
                    + "' AND `CONDUTOR` LIKE '%" + nomecondutor
                    + "' AND `CONDUTOR_IDENTIFICADO` LIKE '%" + nomecident
                    + "' ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Infracao infracao = new Infracao();
                infracao.setId(rs.getInt("ID"));
                infracao.setLocadora(rs.getString("LOCADORA"));
                infracao.setPlaca(rs.getString("PLACA"));
                infracao.setCondutor(rs.getString("CONDUTOR"));
                infracao.setAuto(rs.getString("AUTO_DE_INFRACAO"));
                infracao.setGravidade(rs.getString("GRAVIDADE"));
                infracao.setPrazo(rs.getDate("PRAZO_RECURSO"));
                infracao.setObservacao(rs.getString("OBSERVACAO"));
                infracao.setDescricao(rs.getString("DESCRICAO"));
                infracoes.add(infracao);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        for (int i = 0; i < infracoes.size(); i++) {
            sb.append(infracoes.get(i).getId() + "#" + infracoes.get(i).getLocadora() + "#" + infracoes.get(i).getPlaca()
                    + "#" + infracoes.get(i).getCondutor() + "#" + infracoes.get(i).getAuto() + "#" + infracoes.get(i).getGravidade()
                    + "#" + infracoes.get(i).getPrazo() + "#" + infracoes.get(i).getObservacao() + "#" + infracoes.get(i).getDescricao() + ";");
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
