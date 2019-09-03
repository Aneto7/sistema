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
public class ServletFiltroCustoTon extends HttpServlet {

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
        String nomegrupo = "";
        if (request.getParameter("idgrupo") != null) {
            nomegrupo = request.getParameter("idgrupo");
        }

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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, SUM(BDG_JAN) AS BDG_JAN, SUM(BDG_FEV) AS BDG_FEV, SUM(BDG_MAR) AS BDG_MAR, SUM(BDG_ABR) AS BDG_ABR, SUM(BDG_MAI) AS BDG_MAI, SUM(BDG_JUN) AS BDG_JUN, SUM(BDG_JUL) AS BDG_JUL, SUM(BDG_AGO) AS BDG_AGO, SUM(BDG_SET) AS BDG_SET, SUM(BDG_OUT) AS BDG_OUT, SUM(BDG_NOV) AS BDG_NOV, SUM(BDG_DEZ) AS BDG_DEZ, SUM(REAL_JAN) AS REAL_JAN, SUM(REAL_FEV) AS REAL_FEV, SUM(REAL_MAR) AS REAL_MAR, SUM(REAL_ABR) AS REAL_ABR, SUM(REAL_MAI) AS REAL_MAI, SUM(REAL_JUN) AS REAL_JUN, SUM(REAL_JUL) AS REAL_JUL, SUM(REAL_AGO) AS REAL_AGO, SUM(REAL_SET) AS REAL_SET, SUM(REAL_OUT) AS REAL_OUT, SUM(REAL_NOV) AS REAL_NOV, SUM(REAL_DEZ) AS REAL_DEZ, " + bdgacumuladomes + " AS BDG_ACUM, " + realacumuladomes + " AS REAL_ACUM FROM `orcamentocontrole` WHERE "
                    + "`RESPONSAVEL` LIKE '%" + nomearea
                    + "' AND `REGIAO` LIKE '%" + nomeregiao
                    + "' AND `SEGMENTO` LIKE '%" + nomeplataforma
                    + "' AND `DESCRICAO_FILIAL` LIKE '%" + nomefilial
                    + "' AND `MAGNITUDE_CONTA` LIKE '%" + nomemagnitude
                    + "' AND `GRUPO` LIKE '%" + nomegrupo
                    + "'");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                DashboardInicial orcamento = new DashboardInicial();
                orcamento.setId(rs.getInt("ID"));
                orcamento.setMes1orc(rs.getDouble("BDG_JAN"));
                orcamento.setMes2orc(rs.getDouble("BDG_FEV"));
                orcamento.setMes3orc(rs.getDouble("BDG_MAR"));
                orcamento.setMes4orc(rs.getDouble("BDG_ABR"));
                orcamento.setMes5orc(rs.getDouble("BDG_MAI"));
                orcamento.setMes6orc(rs.getDouble("BDG_JUN"));
                orcamento.setMes7orc(rs.getDouble("BDG_JUL"));
                orcamento.setMes8orc(rs.getDouble("BDG_AGO"));
                orcamento.setMes9orc(rs.getDouble("BDG_SET"));
                orcamento.setMes10orc(rs.getDouble("BDG_OUT"));
                orcamento.setMes11orc(rs.getDouble("BDG_NOV"));
                orcamento.setMes12orc(rs.getDouble("BDG_DEZ"));
                orcamento.setSomaorc(rs.getDouble("BDG_ACUM"));
                orcamento.setMes1(rs.getDouble("REAL_JAN"));
                orcamento.setMes2(rs.getDouble("REAL_FEV"));
                orcamento.setMes3(rs.getDouble("REAL_MAR"));
                orcamento.setMes4(rs.getDouble("REAL_ABR"));
                orcamento.setMes5(rs.getDouble("REAL_MAI"));
                orcamento.setMes6(rs.getDouble("REAL_JUN"));
                orcamento.setMes7(rs.getDouble("REAL_JUL"));
                orcamento.setMes8(rs.getDouble("REAL_AGO"));
                orcamento.setMes9(rs.getDouble("REAL_SET"));
                orcamento.setMes10(rs.getDouble("REAL_OUT"));
                orcamento.setMes11(rs.getDouble("REAL_NOV"));
                orcamento.setMes12(rs.getDouble("REAL_DEZ"));
                orcamento.setSoma(rs.getDouble("REAL_ACUM"));

                orcs.add(orcamento);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        for (int i = 0; i < orcs.size(); i++) {
            sb.append(orcs.get(i).getId() + "%" + orcs.get(i).getMes1orc() + "%" + orcs.get(i).getMes2orc()
                    + "%" + orcs.get(i).getMes3orc() + "%" + orcs.get(i).getMes4orc() + "%" + orcs.get(i).getMes5orc()
                    + "%" + orcs.get(i).getMes5orc() + "%" + orcs.get(i).getMes5orc() + "%" + orcs.get(i).getMes8orc()
                    + "%" + orcs.get(i).getMes9orc() + "%" + orcs.get(i).getMes10orc() + "%" + orcs.get(i).getMes11orc()
                    + "%" + orcs.get(i).getMes12orc() + "%" + orcs.get(i).getMes1() + "%" + orcs.get(i).getMes2()
                    + "%" + orcs.get(i).getMes3() + "%" + orcs.get(i).getMes4() + "%" + orcs.get(i).getMes5()
                    + "%" + orcs.get(i).getMes6() + "%" + orcs.get(i).getMes7() + "%" + orcs.get(i).getMes8()
                    + "%" + orcs.get(i).getMes9() + "%" + orcs.get(i).getMes10() + "%" + orcs.get(i).getMes11()
                    + "%" + orcs.get(i).getMes12() + "%" + orcs.get(i).getSomaorc() + "%" + orcs.get(i).getSoma() + ";");
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
