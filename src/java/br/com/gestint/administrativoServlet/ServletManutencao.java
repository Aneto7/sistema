/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoServlet;

import br.com.gestint.administrativo.Manutencao;
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
public class ServletManutencao extends HttpServlet {

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
        String nomegrupomanutencao = request.getParameter("idgrupomanutencao");
        String nomeorcamento = request.getParameter("idorcamento");

        StringBuilder sb = new StringBuilder();
        List<Manutencao> manutencoes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, PLACA, DATA_INICIO, DATA_FIM, GRUPO_MANUTENCAO, "
                    + "VALOR, RESPONSAVEL, ORCAMENTO, DETALHE, CARRO_RESERVA FROM `manutencao` WHERE "
                    + "`PLACA` LIKE '%" + nomeplaca
                    + "' AND `GRUPO_MANUTENCAO` LIKE '%" + nomegrupomanutencao
                    + "' AND `ORCAMENTO` LIKE '%" + nomeorcamento
                    + "' ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Manutencao manutencao = new Manutencao();
                manutencao.setId(rs.getInt("ID"));
                manutencao.setPlaca(rs.getString("PLACA"));
                manutencao.setDatainicio(rs.getDate("DATA_INICIO"));
                manutencao.setDatafim(rs.getDate("DATA_FIM"));
                manutencao.setGrupomanutencao(rs.getString("GRUPO_MANUTENCAO"));
                manutencao.setValor(rs.getDouble("VALOR"));
                manutencao.setOrcamento(rs.getString("ORCAMENTO"));
                manutencao.setDetalhe(rs.getString("DETALHE"));
                manutencoes.add(manutencao);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        for (int i = 0; i < manutencoes.size(); i++) {
            sb.append(manutencoes.get(i).getId() + "#" + manutencoes.get(i).getPlaca() + "#" + manutencoes.get(i).getDatainicio()
                    + "#" + manutencoes.get(i).getDatafim() + "#" + manutencoes.get(i).getGrupomanutencao() + "#" + manutencoes.get(i).getValor()
                    + "#" + manutencoes.get(i).getOrcamento() + "#" + manutencoes.get(i).getDetalhe() + ";");
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
