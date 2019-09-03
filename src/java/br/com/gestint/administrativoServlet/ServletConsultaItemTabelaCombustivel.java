/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoServlet;

import br.com.gestint.administrativo.Combustivel;
import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.cadastro.CondutorCad;
import br.com.gestint.orcamentoDAO.FiltroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ServletConsultaItemTabelaCombustivel extends HttpServlet {

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
        HttpSession session = request.getSession(false);

        StringBuilder sb = new StringBuilder();
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("idid"));

        PreparedStatement stmt = null;
        List<Combustivel> combs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `combustivel` WHERE ID =" + id);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Combustivel comb = new Combustivel();
                comb.setPlaca(rs.getString("PLACA"));
                comb.setCartao(rs.getString("CARTAO"));
                comb.setCondutor(rs.getString("CONDUTOR"));
                comb.setCentro(rs.getString("CENTRO_CUSTO"));
                comb.setKM(rs.getDouble("KM"));
                comb.setValor(rs.getDouble("VALOR"));
                comb.setLitros(rs.getDouble("LITROS"));
                comb.setData(rs.getDate("DATA"));
                comb.setId(rs.getInt("ID"));
                combs.add(comb);
            }
            for (int i = 0; i < combs.size(); i++) {
                sb.append(combs.get(i).getPlaca() + "%" + combs.get(i).getCartao() + "%" + combs.get(i).getCondutor()
                        + "%" + combs.get(i).getCentro() + "%" + combs.get(i).getKM() + "%" + combs.get(i).getValor()
                        + "%" + combs.get(i).getLitros() + "%" + combs.get(i).getData() + "%" + combs.get(i).getId());
            }
            out.write(sb.toString());
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
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
