/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoServlet;

import br.com.gestint.administrativo.Combustivel;
import br.com.gestint.administrativo.Infracao;
import br.com.gestint.administrativo.Manutencao;
import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamentoDAO.FiltroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
public class ServletFiltroNumerosInfracao extends HttpServlet {

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

        DecimalFormat df = new DecimalFormat("###,###,###,##0.00");
        df.setRoundingMode(RoundingMode.UP);

        String nomeplaca = request.getParameter("idplaca");
        String nomecident = request.getParameter("idcident");
        String nomecondutor = request.getParameter("idcondutor");
        String nomelocadora = request.getParameter("idlocadora");

        StringBuilder sb = new StringBuilder();
        List<Infracao> infracoes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, SUM(VALOR) AS VALORES, AVG(VALOR) AS AVGVALOR, SUM(PONTOS) AS PONTOS, AVG(PONTOS) AS AVGPONTOS FROM `infracoes` WHERE "
                    + "`PLACA` LIKE '%" + nomeplaca
                    + "' AND `CONDUTOR_IDENTIFICADO` LIKE '%" + nomecident
                    + "' AND `LOCADORA` LIKE '%" + nomelocadora
                    + "' AND `CONDUTOR` LIKE '%" + nomecondutor
                    + "'");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Infracao infracao = new Infracao();
                infracao.setId(rs.getInt("ID"));
                infracao.setValor(rs.getDouble("VALORES"));
                infracao.setValores(rs.getDouble("AVGVALOR"));
                infracao.setPontos(rs.getInt("PONTOS"));
                infracao.setSomapontos(rs.getInt("AVGPONTOS"));
                infracoes.add(infracao);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        for (int i = 0; i < infracoes.size(); i++) {
            sb.append(infracoes.get(i).getId() + "%" + infracoes.get(i).getValor() + "%" + infracoes.get(i).getValores()
                    + "%" + infracoes.get(i).getPontos() + "%" + infracoes.get(i).getSomapontos() + ";");
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
