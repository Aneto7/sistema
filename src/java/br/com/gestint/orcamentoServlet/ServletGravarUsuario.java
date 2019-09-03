/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.DadosFiltro;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ServletGravarUsuario extends HttpServlet {

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
        StringBuilder sb = new StringBuilder();
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("idusuario");
        String senha = request.getParameter("idsenha");
        String area = "";
        String filial = "";
        String nome = "";
        String perfil = "";
        String regiao = "";
        String setor = "";
        String tela = "";
        String email = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = con.createStatement();

            st.executeQuery("SELECT * FROM `usuario` WHERE "
                    + "`USUARIO` LIKE '" + usuario
                    + "' AND `SENHA` LIKE '" + senha
                    + "'");
            ResultSet rs = st.getResultSet();
            if (rs.first() == false) {
                usuario = "null";
                area = "null";
                filial = "null";
                nome = "null";
                perfil = "null";
                regiao = "null";
                setor = "null";
                email = "null";
            } else {
                usuario = rs.getString("USUARIO");
                area = rs.getString("AREA");
                filial = rs.getString("FILIAL");
                nome = rs.getString("NOME");
                perfil = rs.getString("PERFIL");
                regiao = rs.getString("REGIAO");
                setor = rs.getString("SETOR");
                email = rs.getString("EMAIL");
            }
            HttpSession session = request.getSession();
            session.setAttribute("usuariolog", usuario);
            session.setAttribute("arealog", area);
            session.setAttribute("filiallog", filial);
            session.setAttribute("nomelog", nome);
            session.setAttribute("perfillog", perfil);
            session.setAttribute("regiaolog", regiao);
            session.setAttribute("setorlog", setor);
            session.setAttribute("emaillog", email);

            DadosFiltro.setArea(area);
            DadosFiltro.setFilial(filial);
            DadosFiltro.setRegiao(regiao);
            DadosFiltro.setUsuario(usuario);

            sb.append(usuario + "%" + area + "%" + DadosFiltro.getFilial() + "%" + nome + "%" + perfil + "%" + regiao + "%" + setor + "%" + tela + "%" + email + ";");
            out.write(sb.toString());
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
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
