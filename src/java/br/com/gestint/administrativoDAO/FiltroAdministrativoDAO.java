/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativoDAO;

import br.com.gestint.administrativo.Cartao;
import br.com.gestint.administrativo.Centro;
import br.com.gestint.administrativo.Condutor;
import br.com.gestint.administrativo.Contagens;
import br.com.gestint.administrativo.IdCombustivel;
import br.com.gestint.administrativo.Locador;
import br.com.gestint.administrativo.Placa;
import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.orcamento.DadosFiltro;
import br.com.gestint.orcamentoDAO.FiltroDAO;
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
public class FiltroAdministrativoDAO {

    public FiltroAdministrativoDAO() {
    }

    public List<Placa> listarPlaca() {
        PreparedStatement stmt = null;
        List<Placa> placas = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, PLACA FROM `combustivel` GROUP BY PLACA ORDER BY PLACA");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Placa placa = new Placa();
                placa.setId(rs.getInt("ID"));
                placa.setPlaca(rs.getString("PLACA"));
                placas.add(placa);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return placas;
    }

    public List<Placa> listarPlacaInfracao() {
        PreparedStatement stmt = null;
        List<Placa> placas = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, PLACA FROM `infracoes` GROUP BY PLACA ORDER BY PLACA");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Placa placa = new Placa();
                placa.setId(rs.getInt("ID"));
                placa.setPlaca(rs.getString("PLACA"));
                placas.add(placa);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return placas;
    }

    public List<Cartao> listarCartao() {
        PreparedStatement stmt = null;
        List<Cartao> cartoes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, CARTAO FROM `combustivel` GROUP BY CARTAO ORDER BY CARTAO");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Cartao cartao = new Cartao();
                cartao.setId(rs.getInt("ID"));
                cartao.setCartao(rs.getString("CARTAO"));
                cartoes.add(cartao);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return cartoes;
    }

    public List<Condutor> listarCondutor() {
        PreparedStatement stmt = null;
        List<Condutor> condutores = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, CONDUTOR FROM `combustivel` GROUP BY CONDUTOR ORDER BY CONDUTOR");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Condutor condutor = new Condutor();
                condutor.setId(rs.getInt("ID"));
                condutor.setCondutor(rs.getString("CONDUTOR"));
                condutores.add(condutor);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return condutores;
    }

    public List<Centro> listarCentro() {
        PreparedStatement stmt = null;
        List<Centro> centros = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, CENTRO_CUSTO FROM `combustivel` GROUP BY CENTRO_CUSTO ORDER BY CENTRO_CUSTO");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Centro centro = new Centro();
                centro.setId(rs.getInt("ID"));
                centro.setCentro(rs.getString("CENTRO_CUSTO"));
                centros.add(centro);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return centros;
    }

    public List<IdCombustivel> listarIdCombustivel() {
        PreparedStatement stmt = null;
        List<IdCombustivel> idscombs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID FROM `combustivel` GROUP BY ID ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                IdCombustivel id = new IdCombustivel();
                id.setId(rs.getInt("ID"));
                idscombs.add(id);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return idscombs;
    }

    public List<Locador> listarLocadora() {
        PreparedStatement stmt = null;
        List<Locador> locadoras = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, LOCADOR FROM `veiculos` GROUP BY LOCADOR ORDER BY LOCADOR");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Locador locador = new Locador();
                locador.setId(rs.getInt("ID"));
                locador.setLocadora(rs.getString("LOCADOR"));
                locadoras.add(locador);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return locadoras;
    }

    public List<Contagens> somarValores() {
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
            st.executeQuery("SELECT SUM(BDG_" + mes + ") AS BDG_JAN, SUM(REAL_" + mes + ") AS REAL_JAN, " + bdgacumuladomes + " AS BDG_ACUM, " + realacumuladomes + " AS REAL_ACUM FROM `orcamentocontrole`");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Contagens cont = new Contagens();
                cont.setBdgmes(rs.getDouble("BDG_JAN"));
                cont.setRealmes(rs.getDouble("REAL_JAN"));
                cont.setBdgacum(rs.getDouble("BDG_ACUM"));
                cont.setRealacum(rs.getDouble("REAL_ACUM"));
                cont.setDifmes((rs.getDouble("BDG_JAN")) - (rs.getDouble("REAL_JAN")));
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

    public List<Contagens> somarValoresAnalises() {
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
            st.executeQuery("SELECT SUM(BDG) AS BDG_SOMA, SUM(REALIZADO) AS REALIZADO_SOMA, SUM(BDG_ACUM) AS BDG_ACUM, SUM(REAL_ACUM) AS REAL_ACUM FROM `analiseorcamento`");
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

    public List<Contagens> contarInfracoes() {
        PreparedStatement stmt = null;
        List<Contagens> conts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT COUNT(ID) AS ID FROM `infracoes` ORDER BY ID");
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

    public List<Contagens> contarManutencoes() {
        PreparedStatement stmt = null;
        List<Contagens> conts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT COUNT(ID) AS ID FROM `manutencao` ORDER BY ID");
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

    public List<IdCombustivel> listarIdInfracoes() {
        PreparedStatement stmt = null;
        List<IdCombustivel> idscombs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID FROM `infracoes` GROUP BY ID ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                IdCombustivel id = new IdCombustivel();
                id.setId(rs.getInt("ID"));
                idscombs.add(id);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return idscombs;
    }

    public List<IdCombustivel> listarIdManutencoes() {
        PreparedStatement stmt = null;
        List<IdCombustivel> idscombs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID FROM `manutencao` GROUP BY ID ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                IdCombustivel id = new IdCombustivel();
                id.setId(rs.getInt("ID"));
                idscombs.add(id);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return idscombs;
    }
    
    public List<Contagens> contarRevisao() {
        PreparedStatement stmt = null;
        List<Contagens> conts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT COUNT(ID) AS ID FROM `revisoes` ORDER BY ID");
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
    
    public List<IdCombustivel> listarIdRevisao() {
        PreparedStatement stmt = null;
        List<IdCombustivel> idscombs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID FROM `revisoes` GROUP BY ID ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                IdCombustivel id = new IdCombustivel();
                id.setId(rs.getInt("ID"));
                idscombs.add(id);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return idscombs;
    }

}
