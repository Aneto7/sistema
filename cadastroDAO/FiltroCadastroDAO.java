/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.cadastroDAO;

import br.com.gestint.atividades.ConexaoMySQL;
import br.com.gestint.cadastro.Apoio;
import br.com.gestint.cadastro.CartaoCar;
import br.com.gestint.cadastro.CondutorCad;
import br.com.gestint.cadastro.ModeloVeiculo;
import br.com.gestint.cadastro.PlacaCadastrada;
import br.com.gestint.cadastro.Unidade;
import br.com.gestint.cadastro.Veiculo;
import br.com.gestint.orcamento.DadosFiltro;
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
public class FiltroCadastroDAO {

    public List<CartaoCar> listarCartaoCadastrado() {
        PreparedStatement stmt = null;
        List<CartaoCar> cartoes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, CARTAO FROM `cartoes_ticketcar` GROUP BY CARTAO ORDER BY CARTAO");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                CartaoCar cartaocar = new CartaoCar();
                cartaocar.setId(rs.getInt("ID"));
                cartaocar.setCartao(rs.getString("CARTAO"));
                cartoes.add(cartaocar);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return cartoes;
    }

    public List<PlacaCadastrada> listarPlacaCadastrada() {
        PreparedStatement stmt = null;
        List<PlacaCadastrada> placas = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT MIN(ID) AS ID, PLACA FROM `veiculos` GROUP BY PLACA ORDER BY PLACA");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                PlacaCadastrada placa = new PlacaCadastrada();
                placa.setId(rs.getInt("ID"));
                placa.setPlaca(rs.getString("PLACA"));
                placas.add(placa);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return placas;
    }

    public List<Unidade> listarUnidade() {
        PreparedStatement stmt = null;
        List<Unidade> unidades = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT FILIAL FROM `filial` GROUP BY FILIAL ORDER BY FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Unidade unidade = new Unidade();
                unidade.setFilial(rs.getString("FILIAL"));
                unidades.add(unidade);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return unidades;
    }

    public List<ModeloVeiculo> listarTipoVeiculo() {
        PreparedStatement stmt = null;
        List<ModeloVeiculo> tipos = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, TIPO FROM `tipoveiculo` GROUP BY TIPO ORDER BY TIPO");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                ModeloVeiculo mv = new ModeloVeiculo();
                mv.setId(rs.getInt("ID"));
                mv.setTipo(rs.getString("TIPO"));
                tipos.add(mv);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return tipos;
    }

    public List<ModeloVeiculo> listarModeloVeiculo() {
        PreparedStatement stmt = null;
        List<ModeloVeiculo> modelos = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, MODELO FROM `tipoveiculo` GROUP BY MODELO ORDER BY MODELO");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                ModeloVeiculo mv = new ModeloVeiculo();
                mv.setId(rs.getInt("ID"));
                mv.setModelo(rs.getString("MODELO"));
                modelos.add(mv);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return modelos;
    }

    public List<CondutorCad> listarCondutorCad() {
        PreparedStatement stmt = null;
        List<CondutorCad> condutores = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, NOME FROM `condutor` GROUP BY NOME ORDER BY NOME");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                CondutorCad cond = new CondutorCad();
                cond.setId(rs.getInt("ID"));
                cond.setNome(rs.getString("NOME"));
                condutores.add(cond);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return condutores;
    }

    public List<Veiculo> listarIdVeiculo() {
        PreparedStatement stmt = null;
        List<Veiculo> veiculos = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, PLACA FROM `veiculos` GROUP BY ID ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("ID"));
                veiculo.setPlaca(rs.getString("PLACA"));
                veiculos.add(veiculo);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return veiculos;
    }

    public List<ModeloVeiculo> listarIdModeloVeiculo() {
        PreparedStatement stmt = null;
        List<ModeloVeiculo> mvs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, MODELO, TIPO FROM `tipoveiculo` GROUP BY ID ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                ModeloVeiculo mv = new ModeloVeiculo();
                mv.setId(rs.getInt("ID"));
                mv.setModelo(rs.getString("MODELO"));
                mv.setTipo(rs.getString("TIPO"));
                mvs.add(mv);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return mvs;
    }

    public List<Unidade> listarIdFilial() {
        PreparedStatement stmt = null;
        List<Unidade> filiais = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `filial` GROUP BY ID ORDER BY ID");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Unidade filial = new Unidade();
                filial.setId(rs.getInt("ID"));
                filial.setFilial(rs.getString("FILIAL"));
                filial.setRegiao(rs.getString("REGIAO"));
                filial.setTipo(rs.getString("TIPO"));
                filial.setStatus(rs.getString("STATUS"));
                filial.setGsub(rs.getString("GESTOR_SUB"));
                filial.setGrg(rs.getString("GESTOR_RG"));
                filial.setGdiv(rs.getString("GESTOR_DIV"));
                filiais.add(filial);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return filiais;
    }

    public List<CondutorCad> listarIdCondutor() {
        PreparedStatement stmt = null;
        List<CondutorCad> conds = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM `condutor`");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                CondutorCad cond = new CondutorCad();
                cond.setId(rs.getInt("ID"));
                cond.setMatricula(rs.getString("MATRICULA"));
                cond.setNome(rs.getString("NOME"));
                cond.setCentro(rs.getString("CENTRO"));
                cond.setIbutton(rs.getString("IBUTTON"));
                cond.setPoll(rs.getBoolean("POLL"));
                cond.setTrab(rs.getBoolean("TRAB"));
                cond.setDesig(rs.getBoolean("DESIG"));
                cond.setEmpresa(rs.getString("EMPRESA"));
                cond.setArea(rs.getString("AREA"));
                cond.setCargo(rs.getString("CARGO"));
                cond.setUnidade(rs.getString("UNIDADE"));
                cond.setRegional(rs.getString("REGIONAL"));
                cond.setDataadminissao(rs.getString("DATA_ADMISSAO"));
                cond.setDatadefensiva(rs.getString("DATA_REALIZACAO_DEFENSIVA"));
                cond.setNcnh(rs.getString("N_CNH"));
                cond.setVencimentocnh(rs.getString("VENCIMENTO_CNH"));
                cond.setCategoriacnh(rs.getString("CATEGORIA_CNH"));
                cond.setForm31(rs.getBoolean("FORM_31"));
                cond.setForm32(rs.getBoolean("FORM_32"));
                cond.setGestorsubregional(rs.getString("GESTOR_SUB_REGIONAL"));
                cond.setGestorregional(rs.getString("GESTOR_REGIONAL"));
                cond.setGestordivisional(rs.getString("GESTOR_DIVISIONAL"));
                cond.setAprovado(rs.getBoolean("APROVADA"));
                cond.setObservacao(rs.getString("OBSERVACAO"));
                cond.setStatus(rs.getString("ATIVO"));
                conds.add(cond);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return conds;
    }

    public List<Apoio> listarHeadcount() {
        PreparedStatement stmt = null;
        List<Apoio> headcounts = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, REGIAO, FILIAL, "
                    + "ROUND(SUM(IF((AREA ='115' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL115, "
                    + "ROUND(SUM(IF((AREA ='201' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL201, "
                    + "ROUND(SUM(IF((AREA ='333' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL333, "
                    + "ROUND(SUM(IF((AREA ='322' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL322, "
                    + "ROUND(SUM(IF((AREA ='115' AND PLANOUREAL ='PLANEJADO'), VALOR, 0))) AS AREAPLAN115, "
                    + "ROUND(SUM(IF((AREA ='201' AND PLANOUREAL ='PLANEJADO'), VALOR, 0))) AS AREAPLAN201, "
                    + "ROUND(SUM(IF((AREA ='333' AND PLANOUREAL ='PLANEJADO'), VALOR, 0))) AS AREAPLAN333, "
                    + "ROUND(SUM(IF((AREA ='322' AND PLANOUREAL ='PLANEJADO'), VALOR, 0))) AS AREAPLAN322, "
                    + "VALOR FROM `apoioorcamento` WHERE "
                    + "TIPO = 'HEADCOUNT' "
                    + "AND MES = '" + DadosFiltro.getMes()
                    + "' AND ANO = " + DadosFiltro.getAno()
                    + " GROUP BY FILIAL ORDER BY REGIAO, FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Apoio hc = new Apoio();
                hc.setId(rs.getInt("ID"));
                hc.setRegiao(rs.getString("REGIAO"));
                hc.setFilial(rs.getString("FILIAL"));
                hc.setReal115(rs.getDouble("AREAREAL115"));
                hc.setReal201(rs.getDouble("AREAREAL201"));
                hc.setReal333(rs.getDouble("AREAREAL333"));
                hc.setReal322(rs.getDouble("AREAREAL322"));
                hc.setRealacum((rs.getDouble("AREAREAL115")) + (rs.getDouble("AREAREAL201")) + (rs.getDouble("AREAREAL333")) + (rs.getDouble("AREAREAL322")));
                hc.setPlan115(rs.getDouble("AREAPLAN115"));
                hc.setPlan201(rs.getDouble("AREAPLAN201"));
                hc.setPlan333(rs.getDouble("AREAPLAN333"));
                hc.setPlan322(rs.getDouble("AREAPLAN322"));
                hc.setPlanacum((rs.getDouble("AREAPLAN115")) + (rs.getDouble("AREAPLAN201")) + (rs.getDouble("AREAPLAN333")) + (rs.getDouble("AREAPLAN322")));
                headcounts.add(hc);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return headcounts;
    }

    public List<Apoio> listarVolume() {
        PreparedStatement stmt = null;
        List<Apoio> volumes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, REGIAO, FILIAL, "
                    + "ROUND(SUM(IF((AREA ='CIF EXP' AND PLATAFORMA ='SOJA'), VALOR, 0))) AS AREASOJACIFEXP, "
                    + "ROUND(SUM(IF((AREA ='CIF REC' AND PLATAFORMA ='SOJA'), VALOR, 0))) AS AREASOJACIFREC, "
                    + "ROUND(SUM(IF((AREA ='FOB' AND PLATAFORMA ='SOJA'), VALOR, 0))) AS AREASOJAFOB, "
                    + "ROUND(SUM(IF((AREA ='CIF EXP' AND PLATAFORMA ='MILHO'), VALOR, 0))) AS AREAMILHOCIFEXP, "
                    + "ROUND(SUM(IF((AREA ='CIF REC' AND PLATAFORMA ='MILHO'), VALOR, 0))) AS AREAMILHOCIFREC, "
                    + "ROUND(SUM(IF((AREA ='FOB' AND PLATAFORMA ='MILHO'), VALOR, 0))) AS AREAMILHOFOB, "
                    + "VALOR FROM `apoioorcamento` WHERE "
                    + "TIPO = 'VOLUME' "
                    + "AND MES = '" + DadosFiltro.getMes()
                    + "' AND ANO = " + DadosFiltro.getAno()
                    + " AND PLANOUREAL = 'REALIZADO'"
                    + " GROUP BY FILIAL ORDER BY REGIAO, FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Apoio volume = new Apoio();
                volume.setId(rs.getInt("ID"));
                volume.setRegiao(rs.getString("REGIAO"));
                volume.setFilial(rs.getString("FILIAL"));
                volume.setRealcifrecsoja(rs.getDouble("AREASOJACIFREC"));
                volume.setRealcifrecmilho(rs.getDouble("AREAMILHOCIFREC"));
                volume.setRealcifrec((rs.getDouble("AREASOJACIFREC")) + (rs.getDouble("AREAMILHOCIFREC")));
                volume.setRealcifexpsoja(rs.getDouble("AREASOJACIFEXP"));
                volume.setRealcifexpmilho(rs.getDouble("AREAMILHOCIFEXP"));
                volume.setRealcifexp((rs.getDouble("AREASOJACIFEXP")) + (rs.getDouble("AREAMILHOCIFEXP")));
                volume.setRealfobsoja(rs.getDouble("AREASOJAFOB"));
                volume.setRealfobmilho(rs.getDouble("AREAMILHOFOB"));
                volume.setRealfob((rs.getDouble("AREASOJAFOB")) + (rs.getDouble("AREAMILHOFOB")));
                volumes.add(volume);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return volumes;
    }

    public List<Apoio> listarVolumePlan() {
        PreparedStatement stmt = null;
        List<Apoio> volumes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, REGIAO, FILIAL, "
                    + "ROUND(SUM(IF((AREA ='CIF EXP' AND PLATAFORMA ='SOJA'), VALOR, 0))) AS AREASOJACIFEXP, "
                    + "ROUND(SUM(IF((AREA ='CIF REC' AND PLATAFORMA ='SOJA'), VALOR, 0))) AS AREASOJACIFREC, "
                    + "ROUND(SUM(IF((AREA ='FOB' AND PLATAFORMA ='SOJA'), VALOR, 0))) AS AREASOJAFOB, "
                    + "ROUND(SUM(IF((AREA ='CIF EXP' AND PLATAFORMA ='MILHO'), VALOR, 0))) AS AREAMILHOCIFEXP, "
                    + "ROUND(SUM(IF((AREA ='CIF REC' AND PLATAFORMA ='MILHO'), VALOR, 0))) AS AREAMILHOCIFREC, "
                    + "ROUND(SUM(IF((AREA ='FOB' AND PLATAFORMA ='MILHO'), VALOR, 0))) AS AREAMILHOFOB, "
                    + "VALOR FROM `apoioorcamento` WHERE "
                    + "TIPO = 'VOLUME' "
                    + "AND MES = '" + DadosFiltro.getMes()
                    + "' AND ANO = " + DadosFiltro.getAno()
                    + " AND PLANOUREAL = 'PLANEJADO'"
                    + " GROUP BY FILIAL ORDER BY REGIAO, FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Apoio volume = new Apoio();
                volume.setId(rs.getInt("ID"));
                volume.setRegiao(rs.getString("REGIAO"));
                volume.setFilial(rs.getString("FILIAL"));
                volume.setRealcifrecsoja(rs.getDouble("AREASOJACIFREC"));
                volume.setRealcifrecmilho(rs.getDouble("AREAMILHOCIFREC"));
                volume.setRealcifrec((rs.getDouble("AREASOJACIFREC")) + (rs.getDouble("AREAMILHOCIFREC")));
                volume.setRealcifexpsoja(rs.getDouble("AREASOJACIFEXP"));
                volume.setRealcifexpmilho(rs.getDouble("AREAMILHOCIFEXP"));
                volume.setRealcifexp((rs.getDouble("AREASOJACIFEXP")) + (rs.getDouble("AREAMILHOCIFEXP")));
                volume.setRealfobsoja(rs.getDouble("AREASOJAFOB"));
                volume.setRealfobmilho(rs.getDouble("AREAMILHOFOB"));
                volume.setRealfob((rs.getDouble("AREASOJAFOB")) + (rs.getDouble("AREAMILHOFOB")));
                volumes.add(volume);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return volumes;
    }

    public List<Apoio> listarKm() {
        PreparedStatement stmt = null;
        List<Apoio> kms = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, REGIAO, FILIAL, "
                    + "ROUND(SUM(IF(TIPO ='QUILOMETRAGEM', VALOR, 0))) AS KM, "
                    + "VALOR FROM `apoioorcamento` WHERE "
                    + "TIPO = 'QUILOMETRAGEM' "
                    + "AND MES = '" + DadosFiltro.getMes()
                    + "' AND ANO = " + DadosFiltro.getAno()
                    + " GROUP BY FILIAL ORDER BY REGIAO, FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Apoio km = new Apoio();
                km.setId(rs.getInt("ID"));
                km.setRegiao(rs.getString("REGIAO"));
                km.setFilial(rs.getString("FILIAL"));
                km.setRealacum(rs.getDouble("KM"));
                kms.add(km);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return kms;
    }

    public List<Apoio> listarWood() {
        PreparedStatement stmt = null;
        List<Apoio> woods = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, REGIAO, FILIAL, "
                    + "ROUND(SUM(IF((TIPO ='WOOD' AND PLATAFORMA ='SOJA'), VALOR, 0))) AS WOODSOJA, "
                    + "ROUND(SUM(IF((TIPO ='WOOD' AND PLATAFORMA ='MILHO'), VALOR, 0))) AS WOODMILHO, "
                    + "VALOR FROM `apoioorcamento` WHERE "
                    + "TIPO = 'WOOD' "
                    + "AND MES = '" + DadosFiltro.getMes()
                    + "' AND ANO = " + DadosFiltro.getAno()
                    + " GROUP BY FILIAL ORDER BY REGIAO, FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Apoio wood = new Apoio();
                wood.setId(rs.getInt("ID"));
                wood.setRegiao(rs.getString("REGIAO"));
                wood.setFilial(rs.getString("FILIAL"));
                wood.setRealsoja(rs.getDouble("WOODSOJA"));
                wood.setRealfob(rs.getDouble("WOODMILHO"));
                wood.setRealacum((rs.getDouble("WOODMILHO")) + (rs.getDouble("WOODSOJA")));
                woods.add(wood);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return woods;
    }

    public List<Apoio> listarDry() {
        PreparedStatement stmt = null;
        List<Apoio> drys = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, REGIAO, FILIAL, "
                    + "ROUND(SUM(IF((TIPO ='SECAGEM' AND PLATAFORMA ='SOJA'), VALOR, 0))) AS SECAGEMSOJA, "
                    + "ROUND(SUM(IF((TIPO ='SECAGEM' AND PLATAFORMA ='MILHO'), VALOR, 0))) AS SECAGEMMILHO, "
                    + "VALOR FROM `apoioorcamento` WHERE "
                    + "TIPO = 'SECAGEM' "
                    + "AND MES = '" + DadosFiltro.getMes()
                    + "' AND ANO = " + DadosFiltro.getAno()
                    + " GROUP BY FILIAL ORDER BY REGIAO, FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Apoio dry = new Apoio();
                dry.setId(rs.getInt("ID"));
                dry.setRegiao(rs.getString("REGIAO"));
                dry.setFilial(rs.getString("FILIAL"));
                dry.setRealsoja(rs.getDouble("SECAGEMSOJA"));
                dry.setRealfob(rs.getDouble("SECAGEMMILHO"));
                dry.setRealacum((rs.getDouble("SECAGEMMILHO")) + (rs.getDouble("SECAGEMSOJA")));
                drys.add(dry);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return drys;
    }

    public List<Apoio> listarPower() {
        PreparedStatement stmt = null;
        List<Apoio> powers = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, REGIAO, FILIAL, "
                    + "ROUND(SUM(IF(TIPO ='PURCHASED POWER', VALOR, 0))) AS POWER, "
                    + "VALOR FROM `apoioorcamento` WHERE "
                    + "TIPO = 'PURCHASED POWER' "
                    + "AND MES = '" + DadosFiltro.getMes()
                    + "' AND ANO = " + DadosFiltro.getAno()
                    + " GROUP BY FILIAL ORDER BY REGIAO, FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Apoio power = new Apoio();
                power.setId(rs.getInt("ID"));
                power.setRegiao(rs.getString("REGIAO"));
                power.setFilial(rs.getString("FILIAL"));
                power.setRealacum(rs.getDouble("POWER"));
                powers.add(power);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return powers;
    }

    public List<Apoio> listarFuel() {
        PreparedStatement stmt = null;
        List<Apoio> fuels = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, REGIAO, FILIAL, "
                    + "ROUND(SUM(IF((TIPO ='FUEL' AND PLATAFORMA ='SOJA'), VALOR, 0))) AS FUELSOJA, "
                    + "ROUND(SUM(IF((TIPO ='FUEL' AND PLATAFORMA ='MILHO'), VALOR, 0))) AS FUELMILHO, "
                    + "VALOR FROM `apoioorcamento` WHERE "
                    + "TIPO = 'FUEL' "
                    + "AND MES = '" + DadosFiltro.getMes()
                    + "' AND ANO = " + DadosFiltro.getAno()
                    + " GROUP BY FILIAL ORDER BY REGIAO, FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Apoio fuel = new Apoio();
                fuel.setId(rs.getInt("ID"));
                fuel.setRegiao(rs.getString("REGIAO"));
                fuel.setFilial(rs.getString("FILIAL"));
                fuel.setRealsoja(rs.getDouble("FUELSOJA"));
                fuel.setRealfob(rs.getDouble("FUELMILHO"));
                fuel.setRealacum((rs.getDouble("FUELSOJA")) + (rs.getDouble("FUELMILHO")));
                fuels.add(fuel);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return fuels;
    }

    public List<Apoio> listarCell() {
        PreparedStatement stmt = null;
        List<Apoio> cells = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, REGIAO, FILIAL, "
                    + "ROUND(SUM(IF((AREA ='115' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL115, "
                    + "ROUND(SUM(IF((AREA ='201' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL201, "
                    + "ROUND(SUM(IF((AREA ='333' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL333, "
                    + "ROUND(SUM(IF((AREA ='322' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL322, "
                    + "VALOR FROM `apoioorcamento` WHERE "
                    + "TIPO = 'CELLPHONE' "
                    + "AND MES = '" + DadosFiltro.getMes()
                    + "' AND ANO = " + DadosFiltro.getAno()
                    + " GROUP BY FILIAL ORDER BY REGIAO, FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Apoio cell = new Apoio();
                cell.setId(rs.getInt("ID"));
                cell.setRegiao(rs.getString("REGIAO"));
                cell.setFilial(rs.getString("FILIAL"));
                cell.setReal115(rs.getDouble("AREAREAL115"));
                cell.setReal201(rs.getDouble("AREAREAL201"));
                cell.setReal333(rs.getDouble("AREAREAL333"));
                cell.setReal322(rs.getDouble("AREAREAL322"));
                cell.setRealacum((rs.getDouble("AREAREAL115")) + (rs.getDouble("AREAREAL201")) + (rs.getDouble("AREAREAL333")) + (rs.getDouble("AREAREAL322")));
                cells.add(cell);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return cells;
    }
    
    public List<Apoio> listarCar() {
        PreparedStatement stmt = null;
        List<Apoio> cars = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("SELECT ID, REGIAO, FILIAL, "
                    + "ROUND(SUM(IF((AREA ='115' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL115, "
                    + "ROUND(SUM(IF((AREA ='201' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL201, "
                    + "ROUND(SUM(IF((AREA ='333' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL333, "
                    + "ROUND(SUM(IF((AREA ='322' AND PLANOUREAL ='REALIZADO'), VALOR, 0))) AS AREAREAL322, "
                    + "VALOR FROM `apoioorcamento` WHERE "
                    + "TIPO = 'CAR' "
                    + "AND MES = '" + DadosFiltro.getMes()
                    + "' AND ANO = " + DadosFiltro.getAno()
                    + " GROUP BY FILIAL ORDER BY REGIAO, FILIAL");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Apoio car = new Apoio();
                car.setId(rs.getInt("ID"));
                car.setRegiao(rs.getString("REGIAO"));
                car.setFilial(rs.getString("FILIAL"));
                car.setReal115(rs.getDouble("AREAREAL115"));
                car.setReal201(rs.getDouble("AREAREAL201"));
                car.setReal333(rs.getDouble("AREAREAL333"));
                car.setReal322(rs.getDouble("AREAREAL322"));
                car.setRealacum((rs.getDouble("AREAREAL115")) + (rs.getDouble("AREAREAL201")) + (rs.getDouble("AREAREAL333")) + (rs.getDouble("AREAREAL322")));
                cars.add(car);
            }
            ConexaoMySQL.FecharConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiltroCadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            ConexaoMySQL.FecharConexao();
        }
        return cars;
    }
}
