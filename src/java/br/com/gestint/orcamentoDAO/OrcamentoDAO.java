/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoDAO;

import br.com.gestint.orcamento.Analise;
import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.DadosFiltro;
import br.com.gestint.orcamento.Orcamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rosaan
 */
public class OrcamentoDAO {

    Connection con;

    public OrcamentoDAO() {
    }

    public List<Orcamento> listarOrcamento() {

        PreparedStatement stmt = null;

        String plataforma = DadosFiltro.getPlataforma();
        String area = DadosFiltro.getArea();
        String regiao = DadosFiltro.getRegiao();
        String filial = DadosFiltro.getFilial();
        String magnitude = DadosFiltro.getMagnitude();
        String centro = DadosFiltro.getCentrodecusto();
        String conta = DadosFiltro.getConta();
        int ano = DadosFiltro.getAno();
        if (ano < 2017) {
            ano = 2017;
        }

        if (plataforma == null) {
            plataforma = "";
        }
        if (area == null) {
            area = "";
        }
        if (regiao == null) {
            regiao = "";
        }
        if (filial == null) {
            filial = "";
        }
        if (magnitude == null) {
            magnitude = "";
        }
        if (centro == null) {
            centro = "";
        }
        if (conta == null) {
            conta = "";
        }

        int numeromes = 0;
        Date data = new Date();
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        numeromes = dataCal.get(Calendar.MONTH);
        String[] meses = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"};
        if (DadosFiltro.getMes() == null) {
            DadosFiltro.setNumeromes(numeromes - 1);
            DadosFiltro.setMes(meses[numeromes - 1]);
        }

        String mes = DadosFiltro.getMes();
        int nmes = DadosFiltro.getNumeromes();
        String orcanual = "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO)+(BDG_SET)+(BDG_OUT)+(BDG_NOV)+(BDG_DEZ))";
        String realanual = "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO)+(REAL_SET)+(REAL_OUT)+(REAL_NOV)+(REAL_DEZ))";

        String realacumuladomes = "";
        String[] mesesrealacumulados = {"SUM(REAL_JAN)", "SUM((REAL_JAN)+(REAL_FEV))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO)+(REAL_SET))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO)+(REAL_SET)+(REAL_OUT))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO)+(REAL_SET)+(REAL_OUT)+(REAL_NOV))", "SUM((REAL_JAN)+(REAL_FEV)+(REAL_MAR)+(REAL_ABR)+(REAL_MAI)+(REAL_JUN)+(REAL_JUL)+(REAL_AGO)+(REAL_SET)+(REAL_OUT)+(REAL_NOV)+(REAL_DEZ))"};
        realacumuladomes = mesesrealacumulados[nmes];

        String bdgacumuladomes = "";
        String[] mesesbdgacumulados = {"SUM(BDG_JAN)", "SUM((BDG_JAN)+(BDG_FEV))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO)+(BDG_SET))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO)+(BDG_SET)+(BDG_OUT))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO)+(BDG_SET)+(BDG_OUT)+(BDG_NOV))", "SUM((BDG_JAN)+(BDG_FEV)+(BDG_MAR)+(BDG_ABR)+(BDG_MAI)+(BDG_JUN)+(BDG_JUL)+(BDG_AGO)+(BDG_SET)+(BDG_OUT)+(BDG_NOV)+(BDG_DEZ))"};
        bdgacumuladomes = mesesbdgacumulados[nmes];

        List<Orcamento> orcamentos = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, SEGMENTO, REGIAO, CENTRO_DE_CUSTO, DESCRICAO_FILIAL, MAGNITUDE_CONTA, CONTA, ROUND(SUM(BDG_" + mes + ")) AS BDG_JAN_GROUP, ROUND(SUM(REAL_" + mes + ")) AS REAL_JAN_GROUP, ROUND(" + bdgacumuladomes + ") AS BDG_ACUM_GROUP, ROUND(" + realacumuladomes + ") AS REAL_ACUM_GROUP, ROUND(" + orcanual + ") AS BDG_ANUAL, ROUND(" + realanual + ") AS REAL_ANUAL FROM `orcamentocontrole` WHERE "
                    + "`REGIAO` LIKE '%" + regiao
                    + "' AND `SEGMENTO` LIKE '%" + plataforma
                    + "' AND `DESCRICAO_FILIAL` LIKE '%" + filial
                    + "' AND ((`MAGNITUDE_CONTA` LIKE 'Employment Cost' AND `RESPONSAVEL` LIKE '" + area + "') "
                    + "OR (`MAGNITUDE_CONTA` NOT LIKE 'Employment Cost'))"
                    + " AND `CENTRO_DE_CUSTO` LIKE '%" + centro
                    + "' AND `CONTA` LIKE '%" + conta
                    + "' AND ANO = " + ano
                    + " GROUP BY ID");
            ResultSet rs = st.getResultSet();

            while (rs.next()) {

                Orcamento orcamento = new Orcamento();
                orcamento.setId(rs.getInt("ID"));
                orcamento.setSegmento(rs.getString("SEGMENTO"));
                orcamento.setRegiao(rs.getString("REGIAO"));
                orcamento.setDescricaocentrodecusto(rs.getString("CENTRO_DE_CUSTO"));
                orcamento.setFilial(rs.getString("DESCRICAO_FILIAL"));
                orcamento.setMagnitude(rs.getString("MAGNITUDE_CONTA"));
                orcamento.setDescricaoconta(rs.getString("CONTA"));
                orcamento.setBdgmes(rs.getDouble("BDG_JAN_GROUP"));
                orcamento.setRealmes(rs.getDouble("REAL_JAN_GROUP"));
                orcamento.setDiferencames((rs.getDouble("BDG_JAN_GROUP")) - (rs.getDouble("REAL_JAN_GROUP")));
                orcamento.setBdgacum(rs.getDouble("BDG_ACUM_GROUP"));
                orcamento.setRealacum(rs.getDouble("REAL_ACUM_GROUP"));
                orcamento.setDiferencaacum((rs.getDouble("BDG_ACUM_GROUP")) - (rs.getDouble("REAL_ACUM_GROUP")));
                orcamento.setBdganual(rs.getDouble("BDG_ANUAL"));
                orcamento.setRealanual(rs.getDouble("REAL_ANUAL"));
                orcamento.setDiferencaanual((rs.getDouble("BDG_ANUAL")) - (rs.getDouble("REAL_ANUAL")));
                orcamentos.add(orcamento);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return orcamentos;
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
