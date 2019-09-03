/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoDAO;

import br.com.gestint.administrativo.Combustivel;
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
 * @author rosaan
 */
public class CombustivelDAO {

    Connection con;

    public CombustivelDAO() {
    }

    public List<Combustivel> listarCombustivel() {

        PreparedStatement stmt = null;

        int id = 0;
        String placa = "";
        String cartao = "";
        String condutor = "";
        double km = 0;
        double valor = 0;
        double litros = 0;
        String centro = "";

        List<Combustivel> combustiveis = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `combustivel`");
            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                Combustivel combustivel = new Combustivel();
                combustivel.setId(rs.getInt("ID"));
                combustivel.setPlaca(rs.getString("PLACA"));
                combustivel.setCartao(rs.getString("CARTAO"));
                combustivel.setCondutor(rs.getString("CONDUTOR"));
                combustivel.setKM(rs.getDouble("KM"));
                combustivel.setValor(rs.getDouble("VALOR"));
                combustivel.setLitros(rs.getDouble("LITROS"));
                combustivel.setCentro(rs.getString("CENTRO_CUSTO"));
                combustivel.setData(rs.getDate("DATA"));

                combustiveis.add(combustivel);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return combustiveis;
    }
}
