/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamentoDAO;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.Area;
import br.com.gestint.orcamento.CentrodeCusto;
import br.com.gestint.orcamento.Conta;
import br.com.gestint.orcamento.Contagens;
import br.com.gestint.orcamento.DadosFiltro;
import br.com.gestint.orcamento.Filial;
import br.com.gestint.orcamento.Magnitude;
import br.com.gestint.orcamento.Plataforma;
import br.com.gestint.orcamento.Regiao;
import br.com.gestint.orcamento.Responsavel;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
public class FiltroDAO {

    public FiltroDAO() {
    }

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

    public List<Plataforma> listarPlataforma() {
        PreparedStatement stmt = null;
        List<Plataforma> plataformas = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, SEGMENTO FROM `orcamentocontrole` GROUP BY SEGMENTO ORDER BY SEGMENTO");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Plataforma plataforma = new Plataforma();
                plataforma.setId(rs.getInt("ID"));
                plataforma.setPlataforma(rs.getString("SEGMENTO"));
                plataformas.add(plataforma);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return plataformas;
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

    public List<Magnitude> listarMagnitude() {
        PreparedStatement stmt = null;
        List<Magnitude> magnitudes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, MAGNITUDE_CONTA FROM `orcamentocontrole` GROUP BY MAGNITUDE_CONTA ORDER BY MAGNITUDE_CONTA");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Magnitude magnitude = new Magnitude();
                magnitude.setId(rs.getInt("ID"));
                magnitude.setMagnitude(rs.getString("MAGNITUDE_CONTA"));
                magnitudes.add(magnitude);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return magnitudes;
    }

    public List<CentrodeCusto> listarCentrodeCusto() {
        PreparedStatement stmt = null;
        List<CentrodeCusto> centros = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, CENTRO_DE_CUSTO FROM `orcamentocontrole` GROUP BY CENTRO_DE_CUSTO ORDER BY CENTRO_DE_CUSTO");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                CentrodeCusto centro = new CentrodeCusto();
                centro.setId(rs.getInt("ID"));
                centro.setCentrodecusto(rs.getString("CENTRO_DE_CUSTO"));
                centros.add(centro);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return centros;
    }

    public List<Conta> listarConta() {
        PreparedStatement stmt = null;
        List<Conta> contas = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, CONTA FROM `orcamentocontrole` GROUP BY CONTA ORDER BY CONTA");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getInt("ID"));
                conta.setConta(rs.getString("CONTA"));
                contas.add(conta);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return contas;
    }

    public List<Responsavel> listarResponsavel() {
        PreparedStatement stmt = null;
        List<Responsavel> responsaveis = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT DISTINCT USUARIO FROM `usuario` ORDER BY NOME");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Responsavel resp = new Responsavel();
                resp.setResponsavel(rs.getString("USUARIO"));
                responsaveis.add(resp);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return responsaveis;
    }

    public List<Contagens> contarAnalises() {
        int ano = DadosFiltro.getAno();
        if (ano < 2017) {
            ano = 2017;
        }

        PreparedStatement stmt = null;
        List<Contagens> conts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT COUNT(ID) AS ID FROM `analiseorcamento` WHERE YEAR(`DATA`) = '" + ano + "' ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Contagens cont = new Contagens();
                cont.setNumemrodeanalises(rs.getInt("ID"));
                conts.add(cont);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return conts;
    }

    public List<Contagens> somarValores() {
        DecimalFormat df = new DecimalFormat("###,###,###,##0.00");
        df.setRoundingMode(RoundingMode.UP);
        
        int ano = DadosFiltro.getAno();
        if (ano < 2017) {
            ano = 2017;
        }

        int numeromes = 0;
        Date data = new Date();
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        numeromes = dataCal.get(Calendar.MONTH);
        String[] meses = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"};
        if (DadosFiltro.getMes() == null) {
            DadosFiltro.setNumeromes(numeromes);
            DadosFiltro.setMes(meses[numeromes]);
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

        PreparedStatement stmt = null;
        List<Contagens> conts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT SUM(BDG_" + mes + ") AS BDG_JAN, SUM(REAL_" + mes + ") AS REAL_JAN, " + bdgacumuladomes + " AS BDG_ACUM, " + realacumuladomes + " AS REAL_ACUM, " + orcanual + " AS BDG_ANUAL, " + realanual + " AS REAL_ANUAL FROM `orcamentocontrole` WHERE YEAR(`DATA`) = '" + ano + "'");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Contagens cont = new Contagens();
                cont.setBdgmes(rs.getDouble("BDG_JAN"));
                cont.setRealmes(rs.getDouble("REAL_JAN"));
                cont.setBdgacum(rs.getDouble("BDG_ACUM"));
                cont.setRealacum(rs.getDouble("REAL_ACUM"));
                cont.setBdganual(rs.getDouble("BDG_ANUAL"));
                cont.setRealanual(rs.getDouble("REAL_ANUAL"));
                cont.setDifmes((rs.getDouble("BDG_JAN")) - (rs.getDouble("REAL_JAN")));
                cont.setDifacum((rs.getDouble("BDG_ACUM")) - (rs.getDouble("REAL_ACUM")));
                cont.setDifanual((rs.getDouble("BDG_ANUAL")) - (rs.getDouble("REAL_ANUAL")));
                conts.add(cont);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return conts;
    }

    public List<Contagens> somarValoresAnalises() {
        int ano = DadosFiltro.getAno();
        if (ano < 2017) {
            ano = 2017;
        }
        
        DecimalFormat df = new DecimalFormat("###,###,###,##0.00");
        df.setRoundingMode(RoundingMode.UP);

        int numeromes = 0;
        Date data = new Date();
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        numeromes = dataCal.get(Calendar.MONTH);
        String[] meses = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"};
        if (DadosFiltro.getMes() == null) {
            DadosFiltro.setNumeromes(numeromes);
            DadosFiltro.setMes(meses[numeromes]);
        }

        String mes = DadosFiltro.getMes();
        int nmes = DadosFiltro.getNumeromes();

        PreparedStatement stmt = null;
        List<Contagens> conts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT SUM(BDG) AS BDG_SOMA, SUM(REALIZADO) AS REALIZADO_SOMA, SUM(BDG_ACUM) AS BDG_ACUM, SUM(REAL_ACUM) AS REAL_ACUM FROM `analiseorcamento` WHERE YEAR(`DATA`) = '" + ano + "'");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Contagens cont = new Contagens();
                cont.setBdgmes(rs.getDouble("BDG_JAN"));
                cont.setRealmes(rs.getDouble("REAL_JAN"));
                cont.setBdgacum(rs.getDouble("BDG_ACUM"));
                cont.setRealacum(rs.getDouble("REAL_ACUM"));
                cont.setDifmes((rs.getDouble("BDG_SOMA")) - (rs.getDouble("REAL_SIMA")));
                cont.setDifacum((rs.getDouble("BDG_ACUM")) - (rs.getDouble("REAL_ACUM")));
                conts.add(cont);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return conts;
    }

    public List<Contagens> somarValoresMensais() {
        DecimalFormat df = new DecimalFormat("###,###,###,##0.00");
        df.setRoundingMode(RoundingMode.UP);
        
        int ano = DadosFiltro.getAno();
        if (ano < 2017) {
            ano = 2017;
        }

        int numeromes = 0;
        Date data = new Date();
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        numeromes = dataCal.get(Calendar.MONTH);
        String[] meses = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"};
        if (DadosFiltro.getMes() == null) {
            DadosFiltro.setNumeromes(numeromes);
            DadosFiltro.setMes(meses[numeromes]);
        }

        String mes = DadosFiltro.getMes();
        int nmes = DadosFiltro.getNumeromes();

        PreparedStatement stmt = null;
        List<Contagens> conts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT SUM(BDG) AS BDG_SOMA, SUM(REALIZADO) AS REALIZADO_SOMA, SUM(BDG_ACUM) AS BDG_ACUM, SUM(REAL_ACUM) AS REAL_ACUM FROM `analiseorcamento` WHERE YEAR(`DATA`) = '" + ano + "'");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Contagens cont = new Contagens();
                cont.setBdgmes(rs.getDouble("BDG_JAN"));
                cont.setRealmes(rs.getDouble("REAL_JAN"));
                cont.setBdgacum(rs.getDouble("BDG_ACUM"));
                cont.setRealacum(rs.getDouble("REAL_ACUM"));
                cont.setDifmes((rs.getDouble("BDG_SOMA")) - (rs.getDouble("REALIZADO_SOMA")));
                cont.setDifacum((rs.getDouble("BDG_ACUM")) - (rs.getDouble("REAL_ACUM")));
                conts.add(cont);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return conts;
    }
}
