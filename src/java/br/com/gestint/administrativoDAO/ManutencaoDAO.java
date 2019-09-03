/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoDAO;

import br.com.gestint.administrativo.Manutencao;
import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.Analise;
import br.com.gestint.orcamentoDAO.OrcamentoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Antonio
 */
public class ManutencaoDAO {

    Connection con;

    public ManutencaoDAO() {
    }

    public List<Manutencao> listarManutencao() {

        PreparedStatement stmt = null;

        int i = 0;

        int id = 0;
        String placa = "";
        String grupomanutencao = "";
        String responsavel = "";
        String detalhe = "";
        String carroreserva = "";

        List<Manutencao> manutencoes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `manutencao`");
            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                i++;
                Manutencao manutencao = new Manutencao();
                manutencao.setId(rs.getInt("ID"));
                manutencao.setPlaca(rs.getString("PLACA"));
                manutencao.setDatainicio(rs.getDate("DATA_INICIO"));
                manutencao.setDatafim(rs.getDate("DATA_FIM"));
                manutencao.setGrupomanutencao(rs.getString("GRUPO_MANUTENCAO"));
                manutencao.setValor(rs.getDouble("VALOR"));
                manutencao.setResponsavel(rs.getString("RESPONSAVEL"));
                manutencao.setOrcamento(rs.getString("ORCAMENTO"));
                manutencao.setDetalhe(rs.getString("DETALHE"));
                manutencao.setCarroreserva(rs.getString("CARRO_RESERVA"));
                manutencao.setNumeromanutencoes(i);
                manutencoes.add(manutencao);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return manutencoes;
    }

    public List<Analise> readForDesc(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Analise> analises = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
            stmt.setString(1, "%" + desc + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Analise analise = new Analise();

                analise.setId(rs.getInt("SEGME"));
                analise.setMagnitude(rs.getString("MAGNITUDE_CONTA"));
                analise.setRegiao(rs.getString("REGIAO"));
                analise.setFilial(rs.getString("DESCRICAO_FILIAL"));
                analises.add(analise);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoMySQL.FecharConexao();
        }

        return analises;

    }

    public void update(Analise analise) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET descricao = ? ,qtd = ?,preco = ? WHERE id = ?");
//            stmt.setString(1, analise.getDescricao());
//            stmt.setInt(2, analise.getQtd());
//            stmt.setDouble(3, analise.getPreco());
//            stmt.setInt(4, analise.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConexaoMySQL.FecharConexao();
        }

    }

    public void delete(Analise analise) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setInt(1, analise.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConexaoMySQL.FecharConexao();
        }

    }

}
