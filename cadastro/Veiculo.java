/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.cadastro;

import java.sql.Date;

/**
 *
 * @author Antonio
 */
public class Veiculo {

    private int id;
    private String placa;
    private String locador;
    private Double valorloc;
    private Date iniciocont;
    private Date fimcont;
    private String unidade;
    private String modelo;
    private String tipo;
    private String classificacao;
    private String empresa;
    private String ccsoja;
    private String cartao;
    private String condutor;
    private String ccmilho;
    private String devolvido;

    public Veiculo() {
    }

    public Veiculo(int id, String placa, String locador, Double valorloc, Date iniciocont, Date fimcont, String unidade, String modelo, String tipo, String classificacao, String empresa, String ccsoja, String cartao, String condutor, String ccmilho, String devolvido) {
        this.id = id;
        this.placa = placa;
        this.locador = locador;
        this.valorloc = valorloc;
        this.iniciocont = iniciocont;
        this.fimcont = fimcont;
        this.unidade = unidade;
        this.modelo = modelo;
        this.tipo = tipo;
        this.classificacao = classificacao;
        this.empresa = empresa;
        this.ccsoja = ccsoja;
        this.cartao = cartao;
        this.condutor = condutor;
        this.ccmilho = ccmilho;
        this.devolvido = devolvido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getLocador() {
        return locador;
    }

    public void setLocador(String locador) {
        this.locador = locador;
    }

    public Double getValorloc() {
        return valorloc;
    }

    public void setValorloc(Double valorloc) {
        this.valorloc = valorloc;
    }

    public Date getIniciocont() {
        return iniciocont;
    }

    public void setIniciocont(Date iniciocont) {
        this.iniciocont = iniciocont;
    }

    public Date getFimcont() {
        return fimcont;
    }

    public void setFimcont(Date fimcont) {
        this.fimcont = fimcont;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCcsoja() {
        return ccsoja;
    }

    public void setCcsoja(String ccsoja) {
        this.ccsoja = ccsoja;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getCondutor() {
        return condutor;
    }

    public void setCondutor(String condutor) {
        this.condutor = condutor;
    }

    public String getCcmilho() {
        return ccmilho;
    }

    public void setCcmilho(String ccmilho) {
        this.ccmilho = ccmilho;
    }

    public String getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(String devolvido) {
        this.devolvido = devolvido;
    }

}
