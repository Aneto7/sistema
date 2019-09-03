/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.DadosFiltro;
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
public class ServletSalvarAnalisePendente extends HttpServlet {

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

        int nomeid = Integer.parseInt(request.getParameter("idid"));
        String nomearea = "";
        String nomeplataforma = "";
        String nomefilial = "";
        String nomeregiao = "";
        String nomemagnitude = "";
        String nomecentrodecusto = "";
        String nomedesccentrodecusto = "";
        String nomeconta = "";
        String nomedescconta = "";
        String nomemes = "";
        String nometipojus = "";
        String nomeresponsavel = "";
        Double nomebdgmes = 0.00;
        Double nomerealmes = 0.00;
        Double nomebdgacum = 0.00;
        Double nomerealacum = 0.00;
        String nomejustificativa = "";
        String nomeboor = "BDG";
        String nomesituacao = "ANALISADO";

        Date dataatual = new Date();
        SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datas = forma.format(dataatual);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = con.createStatement();

            st.executeQuery("SELECT * FROM `analiseorcamento` WHERE"
                    + "`ID` LIKE '%" + nomeid
                    + "' ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                nomearea = rs.getString("AREA");
                nomeplataforma = rs.getString("PLATAFORMA");
                nomefilial = rs.getString("DESCRICAO_FILIAL");
                nomeregiao = rs.getString("REGIAO");
                nomemagnitude = rs.getString("MAGNITUDE_CONTA");
                nomecentrodecusto = rs.getString("CENTRO_DE_CUSTO");
                nomedesccentrodecusto = rs.getString("DESCRICAO_CC");
                nomeconta = rs.getString("CONTA");
                nomedescconta = rs.getString("DESCRICAO_CONTA");
                nomemes = rs.getString("MES");
                nometipojus = rs.getString("TIPOJUS");
                nomeresponsavel = rs.getString("RESPONSAVEL");
                nomebdgmes = rs.getDouble("BDG");
                nomerealmes = rs.getDouble("REALIZADO");
                nomebdgacum = rs.getDouble("BDG_ACUM");
                nomerealacum = rs.getDouble("REAL_ACUM");
                nomejustificativa = rs.getString("ANALISE");
                nomeboor = rs.getString("BORR");

            }

            HttpSession session = request.getSession(false);
            st.executeUpdate("INSERT INTO `analiseorcamento` (`ID`, `MAGNITUDE_CONTA`, `REGIAO`, `DESCRICAO_FILIAL`, `CONTA`, `DESCRICAO_CONTA`, `CENTRO_DE_CUSTO`, `DESCRICAO_CC`, `PLATAFORMA`, `MES`, `BORR`, `REALIZADO`, `BDG`, `REAL_ACUM`, `BDG_ACUM`, `ANALISE`, `USUARIO`, `DATA`, `RESPONSAVEL`, `SITUACAO`, `TIPOJUS`, `AREA`) VALUES (NULL, '" + nomemagnitude + "', '" + nomeregiao + "', '" + nomefilial + "', '" + nomeconta + "', '" + nomedescconta + "', '" + nomecentrodecusto + "', '" + nomedesccentrodecusto + "', '" + nomeplataforma + "', '" + nomemes + "', '" + nomeboor + "', '" + nomerealmes + "', '" + nomebdgmes + "', '" + nomerealacum + "', '" + nomebdgacum + "', '" + nomejustificativa + "', '" + session.getAttribute("usuariolog") + "', '" + datas + "', '" + nomeresponsavel + "', '" + nomesituacao + "', '" + nometipojus + "', '" + nomearea + "');");

            st.executeUpdate("UPDATE analiseorcamento SET SITUACAO='ANALISADO' WHERE ID='" + nomeid + "'");

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
