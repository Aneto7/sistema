/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.cadastroDAO;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.cadastro.CartaoCar;
import br.com.gestint.cadastro.Veiculo;
import br.com.gestint.orcamentoDAO.OrcamentoDAO;
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
public class VeiculoDAO {

    Connection con;

    public VeiculoDAO() {
    }

    public List<Veiculo> listarVeiculo() {

        PreparedStatement stmt = null;

        List<Veiculo> veiculos = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `veiculos`");
            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("ID"));
                veiculo.setPlaca(rs.getString("PLACA"));
                veiculo.setLocador(rs.getString("LOCADOR"));
                veiculo.setValorloc(rs.getDouble("VALOR_LOCACAO"));
                veiculo.setIniciocont(rs.getDate("INICIO_CONTRATO"));
                veiculo.setFimcont(rs.getDate("FIM_CONTRATO"));
                veiculo.setUnidade(rs.getString("UNIDADE"));
                veiculo.setModelo(rs.getString("MODELO"));
                veiculo.setTipo(rs.getString("TIPO"));
                veiculo.setClassificacao(rs.getString("CLASSIFICACAO"));
                veiculo.setEmpresa(rs.getString("EMPRESA"));
                veiculo.setCcsoja(rs.getString("CC_SOJA"));
                veiculo.setCcmilho(rs.getString("CC_MILHO"));
                veiculo.setCartao(rs.getString("CARTAO"));
                veiculo.setCondutor(rs.getString("CONDUTOR"));
                veiculo.setDevolvido(rs.getString("DEVOLVIDO"));

                veiculos.add(veiculo);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return veiculos;
    }
}
