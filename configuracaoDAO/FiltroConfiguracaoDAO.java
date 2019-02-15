/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.configuracaoDAO;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.configuracao.Usuario;
import br.com.gestint.orcamento.Area;
import br.com.gestint.orcamento.Filial;
import br.com.gestint.orcamento.Regiao;
import br.com.gestint.orcamentoDAO.FiltroDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class FiltroConfiguracaoDAO {

    public List<Area> listarArea() {
        PreparedStatement stmt = null;
        List<Area> areas = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, RESPONSAVEL FROM `orcamentocontrole` GROUP BY RESPONSAVEL ORDER BY RESPONSAVEL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Area area = new Area();
                area.setId(rs.getInt("ID"));
                area.setArea(rs.getString("RESPONSAVEL"));
                areas.add(area);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return areas;
    }
    
    public List<Regiao> listarRegiao() {
        PreparedStatement stmt = null;
        List<Regiao> regioes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, REGIAO FROM `orcamentocontrole` GROUP BY REGIAO ORDER BY REGIAO");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Regiao regiao = new Regiao();
                regiao.setId(rs.getInt("ID"));
                regiao.setRegiao(rs.getString("REGIAO"));
                regioes.add(regiao);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return regioes;
    }

    public List<Filial> listarFilial() {
        PreparedStatement stmt = null;
        List<Filial> filiais = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, DESCRICAO_FILIAL FROM `orcamentocontrole` GROUP BY DESCRICAO_FILIAL ORDER BY DESCRICAO_FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Filial filial = new Filial();
                filial.setId(rs.getInt("ID"));
                filial.setFilial(rs.getString("DESCRICAO_FILIAL"));
                filiais.add(filial);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return filiais;
    }
    
    public List<Usuario> listarUsuario() {
        PreparedStatement stmt = null;
        List<Usuario> users = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `usuario`");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("ID"));
                usuario.setUsuario(rs.getString("USUARIO"));
                usuario.setFilial(rs.getString("FILIAL"));
                usuario.setRegiao(rs.getString("REGIAO"));
                usuario.setSenha(rs.getString("SENHA"));
                usuario.setPerfil(rs.getString("PERFIL"));
                usuario.setEmail(rs.getString("EMAIL"));
                usuario.setNome(rs.getString("NOME"));
                usuario.setSetor(rs.getString("SETOR"));
                usuario.setArea(rs.getString("AREA"));
                users.add(usuario);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return users;
    }
}
