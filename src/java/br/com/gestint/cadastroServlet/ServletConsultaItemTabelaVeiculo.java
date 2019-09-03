/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.cadastroServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.cadastro.CondutorCad;
import br.com.gestint.cadastro.Veiculo;
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
public class ServletConsultaItemTabelaVeiculo extends HttpServlet {

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
        List<Veiculo> veics = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `veiculos` WHERE ID =" + id);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Veiculo veic = new Veiculo();
                veic.setPlaca(rs.getString("PLACA"));
                veic.setLocador(rs.getString("LOCADOR"));
                veic.setValorloc(rs.getDouble("VALOR_LOCACAO"));
                veic.setUnidade(rs.getString("UNIDADE"));
                veic.setEmpresa(rs.getString("EMPRESA"));
                veic.setCcsoja(rs.getString("CC_SOJA"));
                veic.setCcmilho(rs.getString("CC_MILHO"));
                veic.setIniciocont(rs.getDate("INICIO_CONTRATO"));
                veic.setFimcont(rs.getDate("FIM_CONTRATO"));
                veic.setTipo(rs.getString("TIPO"));
                veic.setModelo(rs.getString("MODELO"));
                veic.setClassificacao(rs.getString("CLASSIFICACAO"));
                veic.setCartao(rs.getString("CARTAO"));
                veic.setCondutor(rs.getString("CONDUTOR"));
                veic.setDevolvido(rs.getString("DEVOLVIDO"));
                veic.setId(rs.getInt("ID"));
                veics.add(veic);
            }
            for (int i = 0; i < veics.size(); i++) {
                sb.append(veics.get(i).getPlaca() + "%" + veics.get(i).getLocador() + "%" + veics.get(i).getValorloc()
                        + "%" + veics.get(i).getUnidade() + "%" + veics.get(i).getEmpresa() + "%" + veics.get(i).getCcsoja()
                        + "%" + veics.get(i).getCcmilho() + "%" + veics.get(i).getIniciocont() + "%" + veics.get(i).getFimcont()
                        + "%" + veics.get(i).getTipo() + "%" + veics.get(i).getModelo() + "%" + veics.get(i).getClassificacao()
                        + "%" + veics.get(i).getCartao() + "%" + veics.get(i).getCondutor() + "%" + veics.get(i).getDevolvido() 
                        + "%" + veics.get(i).getId());
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
