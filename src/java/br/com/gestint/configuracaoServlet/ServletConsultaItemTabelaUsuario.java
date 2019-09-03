/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.configuracaoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.cadastro.CondutorCad;
import br.com.gestint.configuracao.Usuario;
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
public class ServletConsultaItemTabelaUsuario extends HttpServlet {

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
        List<Usuario> users = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `usuario` WHERE ID =" + id);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setUsuario(rs.getString("USUARIO"));
                user.setNome(rs.getString("NOME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setSetor(rs.getString("SETOR"));
                user.setPerfil(rs.getString("PERFIL"));
                user.setRegiao(rs.getString("REGIAO"));
                user.setFilial(rs.getString("FILIAL"));
                user.setArea(rs.getString("AREA"));
                user.setIdusuario(rs.getInt("ID"));
                users.add(user);
            }
            for (int i = 0; i < users.size(); i++) {
                sb.append(users.get(i).getUsuario() + "%" + users.get(i).getNome() + "%" + users.get(i).getEmail()
                        + "%" + users.get(i).getSetor() + "%" + users.get(i).getPerfil() + "%" + users.get(i).getRegiao()
                        + "%" + users.get(i).getFilial() + "%" + users.get(i).getArea() + "%" + users.get(i).getIdusuario());
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
