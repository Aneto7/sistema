/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.cadastroServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.cadastro.CondutorCad;
import br.com.gestint.orcamento.Orcamento;
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
public class ServletConsultaItemTabelaCondutor extends HttpServlet {

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
        List<CondutorCad> conds = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `condutor` WHERE ID =" + id);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                CondutorCad cond = new CondutorCad();
                cond.setNome(rs.getString("NOME"));
                cond.setMatricula(rs.getString("MATRICULA"));
                cond.setCentro(rs.getString("CENTRO"));
                cond.setIbutton(rs.getString("IBUTTON"));
                cond.setNcnh(rs.getString("N_CNH"));
                cond.setCargo(rs.getString("CARGO"));
                cond.setCategoriacnh(rs.getString("CATEGORIA_CNH"));
                cond.setGestorsubregional(rs.getString("GESTOR_SUB_REGIONAL"));
                cond.setGestorregional(rs.getString("GESTOR_REGIONAL"));
                cond.setGestordivisional(rs.getString("GESTOR_DIVISIONAL"));
                cond.setUnidade(rs.getString("UNIDADE"));
                cond.setEmpresa(rs.getString("EMPRESA"));
                cond.setArea(rs.getString("AREA"));
                cond.setStatus(rs.getString("ATIVO"));
                cond.setDataadminissao(rs.getString("DATA_ADMISSAO"));
                cond.setDatadefensiva(rs.getString("DATA_REALIZACAO_DEFENSIVA"));
                cond.setVencimentocnh(rs.getString("VENCIMENTO_CNH"));
                cond.setPoll(rs.getBoolean("POLL"));
                cond.setTrab(rs.getBoolean("TRAB"));
                cond.setDesig(rs.getBoolean("DESIG"));
                cond.setForm31(rs.getBoolean("FORM_31"));
                cond.setForm32(rs.getBoolean("FORM_32"));
                cond.setAprovado(rs.getBoolean("APROVADA"));
                cond.setId(rs.getInt("ID"));
                conds.add(cond);
            }
            for (int i = 0; i < conds.size(); i++) {
                sb.append(conds.get(i).getNome() + "%" + conds.get(i).getMatricula() + "%" + conds.get(i).getCentro()
                        + "%" + conds.get(i).getIbutton() + "%" + conds.get(i).getNcnh() + "%" + conds.get(i).getCargo()
                        + "%" + conds.get(i).getCategoriacnh() + "%" + conds.get(i).getGestorsubregional()
                        + "%" + conds.get(i).getGestorregional() + "%" + conds.get(i).getGestordivisional()
                        + "%" + conds.get(i).getUnidade() + "%" + conds.get(i).getEmpresa() + "%" + conds.get(i).getArea()
                        + "%" + conds.get(i).getStatus() + "%" + conds.get(i).getDataadminissao() + "%" + conds.get(i).getDatadefensiva()
                        + "%" + conds.get(i).getVencimentocnh() + "%" + conds.get(i).isPoll() + "%" + conds.get(i).isTrab()
                        + "%" + conds.get(i).isDesig()+ "%" + conds.get(i).isForm31()+ "%" + conds.get(i).isForm32()
                        + "%" + conds.get(i).isAprovado()+ "%" + conds.get(i).getId());
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
