/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoDAO;

import br.com.gestint.orcamento.Analise;
import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.DadosFiltro;
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

/**
 *
 * @author rosaan
 */
public class AnaliseDAO {

    public AnaliseDAO() {
    }

    public List<Analise> listarAnalise() {

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

        List<Analise> analises = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `analiseorcamento` WHERE "
                    + "`DESCRICAO_FILIAL` LIKE '%" + filial
                    + "' AND ((`MAGNITUDE_CONTA` LIKE 'Employment Cost' AND `RESPONSAVEL` LIKE '" + area + "') "
                    + "OR (`MAGNITUDE_CONTA` NOT LIKE 'Employment Cost'))"
                    + " AND `REGIAO` LIKE '%" + regiao
                    + "' AND `CONTA` LIKE '%" + conta
                    + "' AND `CENTRO_DE_CUSTO` LIKE '%" + centro
                    + "' AND `PLATAFORMA` LIKE '%" + plataforma
                    + "' AND `MES` LIKE '%" + mes
//                    + "' AND YEAR(`DATA`) = '" + ano
                    + "' ORDER BY ID");
            ResultSet rs = st.getResultSet();
            int i = 0;
            while (rs.next()) {
                i++;
                Analise analise = new Analise();

                analise.setId(rs.getInt("ID"));
                analise.setMagnitude(rs.getString("MAGNITUDE_CONTA"));
                analise.setRegiao(rs.getString("REGIAO"));
                analise.setFilial(rs.getString("DESCRICAO_FILIAL"));
                analise.setConta(rs.getString("CONTA"));
                analise.setDescconta(rs.getString("DESCRICAO_CONTA"));
                analise.setCentrocusto(rs.getString("CENTRO_DE_CUSTO"));
                analise.setDesccentrocusto(rs.getString("DESCRICAO_CC"));
                analise.setPlataforma(rs.getString("PLATAFORMA"));
                analise.setMes(rs.getString("MES"));
                analise.setBorr(rs.getString("BORR"));
                analise.setRealmes(rs.getDouble("REALIZADO"));
                analise.setBdgmes(rs.getDouble("BDG"));
                analise.setDifmes((rs.getDouble("BDG"))-(rs.getDouble("REALIZADO")));
                analise.setRealacum(rs.getDouble("REAL_ACUM"));
                analise.setBdgacum(rs.getDouble("BDG_ACUM"));
                analise.setDifacum((rs.getDouble("BDG_ACUM"))-(rs.getDouble("REAL_ACUM")));
                analise.setAnalise(rs.getString("ANALISE"));
                analise.setUser(rs.getString("USUARIO"));
                analise.setDatareg(rs.getDate("DATA"));
                analise.setResponsavel(rs.getString("RESPONSAVEL"));
                analise.setSituacao(rs.getString("SITUACAO"));
                analise.setTipoanalise(rs.getString("TIPOJUS"));
                analise.setArea(rs.getString("AREA"));
                analise.setNumeroanalises(i);
                analises.add(analise);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AnaliseDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return analises;
    }

    
    public List<Analise> listarAnalisePendente() {

        PreparedStatement stmt = null;
        
        String plataforma = DadosFiltro.getPlataforma();
        String area = DadosFiltro.getArea();
        String regiao = DadosFiltro.getRegiao();
        String filial = DadosFiltro.getFilial();
        String magnitude = DadosFiltro.getMagnitude();
        String centro = DadosFiltro.getCentrodecusto();
        String conta = DadosFiltro.getConta();
        String usuario = DadosFiltro.getUsuario();

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
        if (usuario == null) {
            usuario = "";
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

        List<Analise> analises = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `analiseorcamento` WHERE "
                    + "`SITUACAO` LIKE '%PENDENTE"
                    + "' AND `RESPONSAVEL` LIKE '%" + usuario
                    + "' ORDER BY ID");
            ResultSet rs = st.getResultSet();
            int i = 0;
            while (rs.next()) {
                i++;
                Analise analise = new Analise();

                analise.setId(rs.getInt("ID"));
                analise.setMagnitude(rs.getString("MAGNITUDE_CONTA"));
                analise.setRegiao(rs.getString("REGIAO"));
                analise.setFilial(rs.getString("DESCRICAO_FILIAL"));
                analise.setConta(rs.getString("CONTA"));
                analise.setDescconta(rs.getString("DESCRICAO_CONTA"));
                analise.setCentrocusto(rs.getString("CENTRO_DE_CUSTO"));
                analise.setDesccentrocusto(rs.getString("DESCRICAO_CC"));
                analise.setPlataforma(rs.getString("PLATAFORMA"));
                analise.setMes(rs.getString("MES"));
                analise.setBorr(rs.getString("BORR"));
                analise.setRealmes(rs.getDouble("REALIZADO"));
                analise.setBdgmes(rs.getDouble("BDG"));
                analise.setDifmes((rs.getDouble("BDG"))-(rs.getDouble("REALIZADO")));
                analise.setRealacum(rs.getDouble("REAL_ACUM"));
                analise.setBdgacum(rs.getDouble("BDG_ACUM"));
                analise.setDifacum((rs.getDouble("BDG_ACUM"))-(rs.getDouble("REAL_ACUM")));
                analise.setAnalise(rs.getString("ANALISE"));
                analise.setUser(rs.getString("USUARIO"));
                analise.setDatareg(rs.getDate("DATA"));
                analise.setResponsavel(rs.getString("RESPONSAVEL"));
                analise.setSituacao(rs.getString("SITUACAO"));
                analise.setTipoanalise(rs.getString("TIPOJUS"));
                analise.setArea(rs.getString("AREA"));
                analise.setNumeroanalises(i);
                analises.add(analise);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AnaliseDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return analises;
    }
}
