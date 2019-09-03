/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoServlet;

import br.com.gestint.atividades.ConexaoMySQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author rosaan
 */
public class ServletAtualizarInfracao extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String idvalor = request.getParameter("idvalor");
        if (idvalor.equals("")) {
            idvalor = "0";
        }

        int id = (Integer.parseInt(request.getParameter("idid")));
        String nomeplaca = request.getParameter("idplaca");
        String nomelocadora = request.getParameter("idlocadora");
        String nomecondutor = request.getParameter("idcondutor");
        int nomepontos = (Integer.parseInt(request.getParameter("idpontos")));
        String nomecident = request.getParameter("idcident");
        String nomecodigo = request.getParameter("idcodigo");
        String nomedata = request.getParameter("iddata");
        Double nomevalor = (Double.parseDouble(idvalor));
        String nomeauto = request.getParameter("idauto");
        String nomeprazo = request.getParameter("idprazo");
        String nomechamado = request.getParameter("idchamado");
        String nomereembolso = request.getParameter("idreembolso");
        String nomedescmulta = request.getParameter("iddescmulta");
        String nomeobsmulta = request.getParameter("idobsmulta");

        String nomegravidade = "";
        switch (nomepontos) {
            case 3:
                nomegravidade = "Leve";
                break;
            case 4:
                nomegravidade = "Média";
                break;
            case 5:
                nomegravidade = "Grave";
                break;
            case 7:
                nomegravidade = "Gravíssima";
                break;
            default:
                nomegravidade = "";
                break;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = con.createStatement();

            st.executeUpdate("UPDATE infracoes SET DATA='"
                    + nomedata + "',PLACA='"
                    + nomeplaca + "',LOCADORA='"
                    + nomelocadora + "',PONTOS='"
                    + nomepontos + "',GRAVIDADE='"
                    + nomegravidade + "',CODIGO='"
                    + nomecodigo + "',DESCRICAO='"
                    + nomedescmulta + "',VALOR='"
                    + nomevalor + "',AUTO_DE_INFRACAO='"
                    + nomeauto + "',PRAZO_RECURSO='"
                    + nomeprazo + "',CONDUTOR='"
                    + nomecondutor + "',N_CHAMADO='"
                    + nomechamado + "',CONDUTOR_IDENTIFICADO='"
                    + nomecident + "',REEMBOLSO='"
                    + nomereembolso + "',OBSERVACAO='"
                    + nomeobsmulta
                    + "' WHERE ID='" + id + "'");
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            ConexaoMySQL.FecharConexao();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nomeplaca + "#" + nomecodigo + "#" + nomelocadora
                + "#" + nomegravidade + "#" + nomeprazo + "#" + nomecondutor + ";");
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
