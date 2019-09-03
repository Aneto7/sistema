/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.Analise;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author rosaan
 */
public class ServletAnalisePendente extends HttpServlet {

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

        String nomearea = request.getParameter("idarea");
        String nomeregiao = request.getParameter("idregiao");
        String nomeplataforma = request.getParameter("idplataforma");
        String nomefilial = request.getParameter("idfilial");
        String nomemagnitude = request.getParameter("idmagnitude");
        String nomecentrodecusto = request.getParameter("idcentrodecusto");
        String nomeconta = request.getParameter("idconta");
        String nomeid = request.getParameter("idid");

        HttpSession session = request.getSession(false);
        String usuariolog = (String) session.getAttribute("usuariolog");
        StringBuilder sb = new StringBuilder();
        List<Analise> analises = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `analiseorcamento` WHERE"
                    + "`SITUACAO` LIKE '%PENDENTE"
                    + "' AND `RESPONSAVEL` LIKE '%" + usuariolog
                    + "' AND `ID` = " + nomeid
                    + " ORDER BY ID");
            ResultSet rs = st.getResultSet();
            int i = 0;
            while (rs.next()) {
                Analise analise = new Analise();
                i++;
                analise.setId(rs.getInt("ID"));
                analise.setMagnitude(rs.getString("MAGNITUDE_CONTA"));
                analise.setRegiao(rs.getString("REGIAO"));
                analise.setFilial(rs.getString("DESCRICAO_FILIAL"));
                analise.setConta(rs.getString("CONTA"));
                analise.setDescconta(rs.getString("DESCRICAO_CONTA"));
                analise.setCentrocusto(rs.getString("CENTRO_DE_CUSTO"));
                analise.setDesccentrocusto(rs.getString("DESCRICAO_CC"));
                analise.setPlataforma(rs.getString("PLATAFORMA"));
                analise.setMes(rs.getString("MES"));
                analise.setBorr(rs.getString("BORR"));
                analise.setRealmes(rs.getDouble("REALIZADO"));
                analise.setBdgmes(rs.getDouble("BDG"));
                analise.setDifmes((rs.getDouble("BDG")) - (rs.getDouble("REALIZADO")));
                analise.setRealacum(rs.getDouble("REAL_ACUM"));
                analise.setBdgacum(rs.getDouble("BDG_ACUM"));
                analise.setDifacum((rs.getDouble("BDG_ACUM")) - (rs.getDouble("REAL_ACUM")));
                analise.setAnalise(rs.getString("ANALISE"));
                analise.setUser(rs.getString("USUARIO"));
                analise.setDatareg(rs.getDate("DATA"));
                analise.setResponsavel(rs.getString("RESPONSAVEL"));
                analise.setSituacao(rs.getString("SITUACAO"));
                analise.setTipoanalise(rs.getString("TIPOJUS"));
                analise.setArea(rs.getString("AREA"));
                analise.setNumeroanalises(i);
                analises.add(analise);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        for (int i = 0; i < analises.size(); i++) {
            sb.append(analises.get(i).getId() + "#" + analises.get(i).getPlataforma() + "#" + analises.get(i).getRegiao()
                    + "#" + analises.get(i).getFilial() + "#" + analises.get(i).getMagnitude() + "#" + analises.get(i).getCentrocusto()
                    + "#" + analises.get(i).getConta() + "#" + analises.get(i).getNumeroanalises() + "#" + analises.get(i).getAnalise()
                    + "#" + analises.get(i).getBdgmes() + "#" + analises.get(i).getRealmes() + "#" + analises.get(i).getDifmes()
                    + "#" + analises.get(i).getBdgacum() + "#" + analises.get(i).getRealacum() + "#" + analises.get(i).getDifacum() + ";");
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
