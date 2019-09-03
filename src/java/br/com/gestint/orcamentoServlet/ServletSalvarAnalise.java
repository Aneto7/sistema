/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamentoDAO.FiltroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author rosaan
 */
public class ServletSalvarAnalise extends HttpServlet {

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
        String nomeplataforma = request.getParameter("idplataforma");
        String nomefilial = request.getParameter("idfilial");
        String nomeregiao = request.getParameter("idregiao");
        String nomemagnitude = request.getParameter("idmagnitude");
        String nomecentrodecusto = request.getParameter("idcentrodecusto");
        String nomeconta = request.getParameter("idconta");
        String nomemes = request.getParameter("idmes");
        String nometipojus = request.getParameter("idtipojus");
        String nomeresponsavel = request.getParameter("idresponsavel");
        String nomebdgmes = request.getParameter("idbdgmes");
        String nomerealmes = request.getParameter("idrealmes");
        String nomebdgacum = request.getParameter("idbdgacum");
        String nomerealacum = request.getParameter("idrealacum");
        String nomejustificativa = request.getParameter("idjustificativa");
        String nomeboor = "BDG";

        Date dataatual = new Date();
        SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datas = forma.format(dataatual);

        nomerealmes = nomerealmes.replace(".", "");
        nomerealmes = nomerealmes.replace(",", ".");

        nomerealacum = nomerealacum.replace(".", "");
        nomerealacum = nomerealacum.replace(",", ".");

        nomebdgmes = nomebdgmes.replace(".", "");
        nomebdgmes = nomebdgmes.replace(",", ".");

        nomebdgacum = nomebdgacum.replace(".", "");
        nomebdgacum = nomebdgacum.replace(",", ".");

        String desccentro = "";
        String descconta = "";

        String situacao = "ANALISADO";
        if (nomeresponsavel != "") {
            situacao = "PENDENTE";
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = con.createStatement();

            st.executeQuery("SELECT DESCRICAO_CONTA FROM `orcamentocontrole` WHERE "
                    + "`CONTA` LIKE '%" + nomeconta
                    + "' GROUP BY CONTA ORDER BY CONTA");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                descconta = rs.getString("DESCRICAO_CONTA");
            }

            st.executeQuery("SELECT DESCRICAO_CC FROM `orcamentocontrole` WHERE "
                    + "`CENTRO_DE_CUSTO` LIKE '%" + nomecentrodecusto
                    + "' GROUP BY CENTRO_DE_CUSTO ORDER BY CENTRO_DE_CUSTO");
            ResultSet rss = st.getResultSet();
            while (rss.next()) {
                desccentro = rss.getString("DESCRICAO_CC");
            }
            HttpSession session = request.getSession(false);
            st.executeUpdate("INSERT INTO `analiseorcamento` (`ID`, `MAGNITUDE_CONTA`, `REGIAO`, `DESCRICAO_FILIAL`, `CONTA`, `DESCRICAO_CONTA`, `CENTRO_DE_CUSTO`, `DESCRICAO_CC`, `PLATAFORMA`, `MES`, `BORR`, `REALIZADO`, `BDG`, `REAL_ACUM`, `BDG_ACUM`, `ANALISE`, `USUARIO`, `DATA`, `RESPONSAVEL`, `SITUACAO`, `TIPOJUS`, `AREA`) VALUES (NULL, '" + nomemagnitude + "', '" + nomeregiao + "', '" + nomefilial + "', '" + nomeconta + "', '" + descconta + "', '" + nomecentrodecusto + "', '" + desccentro + "', '" + nomeplataforma + "', '" + nomemes + "', '" + nomeboor + "', '" + nomerealmes + "', '" + nomebdgmes + "', '" + nomerealacum + "', '" + nomebdgacum + "', '" + nomejustificativa + "', '" + session.getAttribute("usuariolog") + "', '" + datas + "', '" + nomeresponsavel + "', '" + situacao + "', '" + nometipojus + "', '" + nomearea + "');");
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            ConexaoMySQL.FecharConexao();
        }

        StringBuilder sb = new StringBuilder();

        sb.append(nomearea + "#" + nomeplataforma + "#" + nomefilial
                + "#" + nomeregiao + "#" + nomemagnitude + "#" + nomecentrodecusto
                + "#" + nomeconta + "#" + nomejustificativa + ";");

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
