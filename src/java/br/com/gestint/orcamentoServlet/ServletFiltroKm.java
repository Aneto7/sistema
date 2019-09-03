/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.DadosFiltro;
import br.com.gestint.orcamento.DashboardInicial;
import br.com.gestint.orcamentoDAO.FiltroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class ServletFiltroKm extends HttpServlet {

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
        String nomegrupo = request.getParameter("idgrupo");

        int nomeano = 2017;

        DadosFiltro.setAno(nomeano);
        DadosFiltro.setNumeromes(Integer.parseInt(request.getParameter("idmes")) - 1);
        int numeromes = DadosFiltro.getNumeromes();
        Date data = new Date();
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        String[] meses = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"};
        DadosFiltro.setMes(meses[numeromes]);
        if (DadosFiltro.getMes() == null) {
            numeromes = dataCal.get(Calendar.MONTH);
            DadosFiltro.setNumeromes(numeromes);
            DadosFiltro.setMes(meses[numeromes]);
        }

        String mes = DadosFiltro.getMes();
        int nmes = DadosFiltro.getNumeromes();

        String realacumuladomes = "";
        String[] mesesrealacumulados = {"SUM(REAL_JAN)", "SUM((REAL_JAN)+(REAL_FEV))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO)+(REAL_SET))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO)+(REAL_SET)+(REAL_OUT))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO)+(REAL_SET)+(REAL_OUT)+(REAL_NOV))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO)+(REAL_SET)+(REAL_OUT)+(REAL_NOV)+(REAL_DEZ))"};
        realacumuladomes = mesesrealacumulados[nmes];

        String bdgacumuladomes = "";
        String[] mesesbdgacumulados = {"SUM(BDG_JAN)", "SUM((BDG_JAN)+(BDG_FEV))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO)+(BDG_SET))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO)+(BDG_SET)+(BDG_OUT))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO)+(BDG_SET)+(BDG_OUT)+(BDG_NOV))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO)+(BDG_SET)+(BDG_OUT)+(BDG_NOV)+(BDG_DEZ))"};
        bdgacumuladomes = mesesbdgacumulados[nmes];

        StringBuilder sb = new StringBuilder();
        List<DashboardInicial> orcs = new ArrayList<>();

        int idorc = 0;
        Double mes1orc = 0.00;
        Double mes2orc = 0.00;
        Double mes3orc = 0.00;
        Double mes4orc = 0.00;
        Double mes5orc = 0.00;
        Double mes6orc = 0.00;
        Double mes7orc = 0.00;
        Double mes8orc = 0.00;
        Double mes9orc = 0.00;
        Double mes10orc = 0.00;
        Double mes11orc = 0.00;
        Double mes12orc = 0.00;
        Double acumorc = 0.00;
        Double mes1 = 0.00;
        Double mes2 = 0.00;
        Double mes3 = 0.00;
        Double mes4 = 0.00;
        Double mes5 = 0.00;
        Double mes6 = 0.00;
        Double mes7 = 0.00;
        Double mes8 = 0.00;
        Double mes9 = 0.00;
        Double mes10 = 0.00;
        Double mes11 = 0.00;
        Double mes12 = 0.00;
        Double acum = 0.00;

        int idovol = 0;
        Double mes1volplan = 0.00;
        Double mes2volplan = 0.00;
        Double mes3volplan = 0.00;
        Double mes4volplan = 0.00;
        Double mes5volplan = 0.00;
        Double mes6volplan = 0.00;
        Double mes7volplan = 0.00;
        Double mes8volplan = 0.00;
        Double mes9volplan = 0.00;
        Double mes10volplan = 0.00;
        Double mes11volplan = 0.00;
        Double mes12volplan = 0.00;
        Double acumvolplan = 0.00;
        Double mes1volreal = 0.00;
        Double mes2volreal = 0.00;
        Double mes3volreal = 0.00;
        Double mes4volreal = 0.00;
        Double mes5volreal = 0.00;
        Double mes6volreal = 0.00;
        Double mes7volreal = 0.00;
        Double mes8volreal = 0.00;
        Double mes9volreal = 0.00;
        Double mes10volreal = 0.00;
        Double mes11volreal = 0.00;
        Double mes12volreal = 0.00;
        Double acumvolreal = 0.00;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, SUM(BDG_JAN) AS BDG_JAN, SUM(BDG_FEV) AS BDG_FEV, SUM(BDG_MAR) AS BDG_MAR, SUM(BDG_ABR) AS BDG_ABR, SUM(BDG_MAI) AS BDG_MAI, SUM(BDG_JUN) AS BDG_JUN, SUM(BDG_JUL) AS BDG_JUL, SUM(BDG_AGO) AS BDG_AGO, SUM(BDG_SET) AS BDG_SET, SUM(BDG_OUT) AS BDG_OUT, SUM(BDG_NOV) AS BDG_NOV, SUM(BDG_DEZ) AS BDG_DEZ, SUM(REAL_JAN) AS REAL_JAN, SUM(REAL_FEV) AS REAL_FEV, SUM(REAL_MAR) AS REAL_MAR, SUM(REAL_ABR) AS REAL_ABR, SUM(REAL_MAI) AS REAL_MAI, SUM(REAL_JUN) AS REAL_JUN, SUM(REAL_JUL) AS REAL_JUL, SUM(REAL_AGO) AS REAL_AGO, SUM(REAL_SET) AS REAL_SET, SUM(REAL_OUT) AS REAL_OUT, SUM(REAL_NOV) AS REAL_NOV, SUM(REAL_DEZ) AS REAL_DEZ, " + bdgacumuladomes + " AS BDG_ACUM, " + realacumuladomes + " AS REAL_ACUM FROM `orcamentocontrole` WHERE "
                    + "`RESPONSAVEL` LIKE '%" + nomearea
                    + "' AND `REGIAO` LIKE '%" + nomeregiao
                    + "' AND `SEGMENTO` LIKE '%" + nomeplataforma
                    + "' AND `DESCRICAO_FILIAL` LIKE '%" + nomefilial
                    + "' AND `ANO` LIKE '%" + nomeano
                    + "' AND `MAGNITUDE_CONTA` LIKE 'Gas"
                    + "'");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                idorc = rs.getInt("ID");
                mes1orc = rs.getDouble("BDG_JAN");
                mes2orc = rs.getDouble("BDG_FEV");
                mes3orc = rs.getDouble("BDG_MAR");
                mes4orc = rs.getDouble("BDG_ABR");
                mes5orc = rs.getDouble("BDG_MAI");
                mes6orc = rs.getDouble("BDG_JUN");
                mes7orc = rs.getDouble("BDG_JUL");
                mes8orc = rs.getDouble("BDG_AGO");
                mes9orc = rs.getDouble("BDG_SET");
                mes10orc = rs.getDouble("BDG_OUT");
                mes11orc = rs.getDouble("BDG_NOV");
                mes12orc = rs.getDouble("BDG_DEZ");
                acumorc = rs.getDouble("BDG_ACUM");
                mes1 = rs.getDouble("REAL_JAN");
                mes2 = rs.getDouble("REAL_FEV");
                mes3 = rs.getDouble("REAL_MAR");
                mes4 = rs.getDouble("REAL_ABR");
                mes5 = rs.getDouble("REAL_MAI");
                mes6 = rs.getDouble("REAL_JUN");
                mes7 = rs.getDouble("REAL_JUL");
                mes8 = rs.getDouble("REAL_AGO");
                mes9 = rs.getDouble("REAL_SET");
                mes10 = rs.getDouble("REAL_OUT");
                mes11 = rs.getDouble("REAL_NOV");
                mes12 = rs.getDouble("REAL_DEZ");
                acum = rs.getDouble("REAL_ACUM");
            }

            st.executeQuery("SELECT MIN(ID) AS ID, SUM(IF((MES='JAN' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_JAN, SUM(IF((MES='FEV' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_FEV, SUM(IF((MES='MAR' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_MAR, SUM(IF((MES='ABR' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_ABR, SUM(IF((MES='MAI' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_MAI, SUM(IF((MES='JUN' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_JUN, SUM(IF((MES='JUL' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_JUL, SUM(IF((MES='AGO' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_AGO, SUM(IF((MES='SET' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_SET, SUM(IF((MES='OUT' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_OUT, SUM(IF((MES='NOV' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_NOV, SUM(IF((MES='DEZ' AND TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL_DEZ, SUM(IF((TIPO='QUILOMETRAGEM' AND PLANOUREAL='REALIZADO'), VALOR, 0)) AS QUILOMETRAGEMREAL FROM `apoioorcamento` WHERE "
                    + "`REGIAO` LIKE '%" + nomeregiao
                    + "' AND `FILIAL` LIKE '%" + nomefilial
                    + "' AND `ANO` = " + nomeano
                    + " AND `AREA` LIKE '%" + nomearea
                    + "'");
            ResultSet rs1 = st.getResultSet();
            while (rs1.next()) {
                idovol = rs1.getInt("ID");
                mes1volreal = rs1.getDouble("QUILOMETRAGEMREAL_JAN");
                mes2volreal = rs1.getDouble("QUILOMETRAGEMREAL_FEV");
                mes3volreal = rs1.getDouble("QUILOMETRAGEMREAL_MAR");
                mes4volreal = rs1.getDouble("QUILOMETRAGEMREAL_ABR");
                mes5volreal = rs1.getDouble("QUILOMETRAGEMREAL_MAI");
                mes6volreal = rs1.getDouble("QUILOMETRAGEMREAL_JUN");
                mes7volreal = rs1.getDouble("QUILOMETRAGEMREAL_JUL");
                mes8volreal = rs1.getDouble("QUILOMETRAGEMREAL_AGO");
                mes9volreal = rs1.getDouble("QUILOMETRAGEMREAL_SET");
                mes10volreal = rs1.getDouble("QUILOMETRAGEMREAL_OUT");
                mes11volreal = rs1.getDouble("QUILOMETRAGEMREAL_NOV");
                mes12volreal = rs1.getDouble("QUILOMETRAGEMREAL_DEZ");
                acumvolreal = rs1.getDouble("QUILOMETRAGEMREAL");

            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        sb.append(idorc + "%" + mes1orc + "%" + mes2orc + "%" + mes3orc + "%" + mes4orc + "%" + mes5orc + "%" + mes6orc + "%"
                + mes7orc + "%" + mes8orc + "%" + mes9orc + "%" + mes10orc + "%" + mes11orc + "%" + mes12orc + "%"
                + mes1 + "%" + mes2 + "%" + mes3 + "%" + mes4 + "%" + mes5 + "%" + mes6 + "%" + mes7 + "%" + mes8 + "%"
                + mes9 + "%" + mes10 + "%" + mes11 + "%" + mes12 + "%" + acumorc + "%" + acum + "%"
                + mes1volplan + "%" + mes2volplan + "%" + mes3volplan + "%" + mes4volplan + "%" + mes5volplan + "%" + mes6volplan + "%"
                + mes7volplan + "%" + mes8volplan + "%" + mes9volplan + "%" + mes10volplan + "%" + mes11volplan + "%" + mes12volplan + "%"
                + mes1volreal + "%" + mes2volreal + "%" + mes3volreal + "%" + mes4volreal + "%" + mes5volreal + "%" + mes6volreal + "%"
                + mes7volreal + "%" + mes8volreal + "%" + mes9volreal + "%" + mes10volreal + "%" + mes11volreal + "%" + mes12volreal + "%"
                + acumvolplan + "%" + acumvolreal
                + ";");
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
