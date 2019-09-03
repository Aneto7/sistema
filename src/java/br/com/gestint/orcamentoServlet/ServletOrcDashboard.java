/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.DashboardOrcamento;
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
public class ServletOrcDashboard extends HttpServlet {

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
        String nomeano = request.getParameter("idano");

        StringBuilder sb = new StringBuilder();
        List<DashboardOrcamento> dorcs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MES, SUM(REALIZADO) AS REALI, SUM(BDG) AS BDGMES, "
                    + "SUM(IF(`TIPOJUS` = 'DENTRO DO ORCAMENTO ANUAL', 1, 0)) AS DENTRO, "
                    + "SUM(IF(`TIPOJUS` = 'ORCAMENTO SUBESTIMADO', 1, 0)) AS SUBESTIMADO, "
                    + "SUM(IF(`TIPOJUS` = 'ORCAMENTO SUPERESTIMADO', 1, 0)) AS SUPERESTIMADO, "
                    + "SUM(IF(`TIPOJUS` = 'SEM ORCAMENTO', 1, 0)) AS SEMORCAMENTO, "
                    + "SUM(IF(`AREA` = '115', 1, 0)) AS A115, "
                    + "SUM(IF(`AREA` = '201', 1, 0)) AS A201, "
                    + "SUM(IF(`AREA` = '322', 1, 0)) AS A322, "
                    + "SUM(IF(`AREA` = '333', 1, 0)) AS A333, "
                    + "SUM(IF(`PLATAFORMA` = 'SOJA', 1, 0)) AS SOJA, "
                    + "SUM(IF(`PLATAFORMA` = 'MILHO', 1, 0)) AS MILHO, "
                    + "CASE WHEN MES = 'JAN' THEN 1 "
                    + "WHEN MES = 'FEV' THEN 2 WHEN MES = 'MAR' THEN 3 WHEN MES = 'ABR' THEN 4 "
                    + "WHEN MES = 'MAI' THEN 5 WHEN MES = 'JUN' THEN 6 WHEN MES = 'JUL' THEN 7 "
                    + "WHEN MES = 'AGO' THEN 8 WHEN MES = 'SET' THEN 9 WHEN MES = 'OUT' THEN 10 "
                    + "WHEN MES = 'NOV' THEN 11 WHEN MES = 'DEZ' THEN 12 END AS NMES "
                    + "FROM `analiseorcamento` WHERE PLATAFORMA LIKE '%" + nomeplataforma
                    + "' AND REGIAO LIKE '%" + nomeregiao
                    + "' AND DESCRICAO_FILIAL LIKE '%" + nomefilial
                    + "' AND MAGNITUDE_CONTA LIKE '%" + nomemagnitude
                    + "' AND AREA LIKE '%" + nomearea
                    + "' AND YEAR(`DATA`) = '" + nomeano
                    + "' GROUP BY MES ORDER BY NMES DESC");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                DashboardOrcamento dorc = new DashboardOrcamento();
                dorc.setNmes(rs.getInt("NMES"));
                dorc.setMes(rs.getString("MES"));
                dorc.setBdgmes(rs.getDouble("BDGMES"));
                dorc.setRealizado(rs.getDouble("REALI"));
                dorc.setJusdentro(rs.getInt("DENTRO"));
                dorc.setJussub(rs.getInt("SUBESTIMADO"));
                dorc.setJussuper(rs.getInt("SUPERESTIMADO"));
                dorc.setJussem(rs.getInt("SEMORCAMENTO"));
                dorc.setJusa115(rs.getInt("A115"));
                dorc.setJusa201(rs.getInt("A201"));
                dorc.setJusa322(rs.getInt("A322"));
                dorc.setJusa333(rs.getInt("A333"));
                dorc.setJussoja(rs.getInt("SOJA"));
                dorc.setJusmilho(rs.getInt("MILHO"));
                dorcs.add(dorc);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        for (int i = 0; i < dorcs.size(); i++) {
            sb.append(dorcs.get(i).getNmes() + "%" + dorcs.get(i).getMes() + "%"
                    + dorcs.get(i).getBdgmes() + "%" + dorcs.get(i).getRealizado() + "%"
                    + dorcs.get(i).getJusdentro() + "%" + dorcs.get(i).getJussub() + "%"
                    + dorcs.get(i).getJussuper() + "%" + dorcs.get(i).getJussem() + "%"
                    + dorcs.get(i).getJusa115() + "%" + dorcs.get(i).getJusa201() + "%"
                    + dorcs.get(i).getJusa322() + "%" + dorcs.get(i).getJusa333() + "%"
                    + dorcs.get(i).getJusa322() + "%" + dorcs.get(i).getJusa333() + "%"
                    + dorcs.get(i).getJusa322() + "%" + dorcs.get(i).getJusa333() + ";");
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
