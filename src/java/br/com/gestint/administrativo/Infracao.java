/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativo;

import java.util.Date;

/**
 *
 * @author rosaan
 */
public class Infracao {

    private int id;
    private Date data;
    private String placa;
    private String locadora;
    private int pontos;
    private int somapontos;
    private String gravidade;
    private String codigo;
    private String descricao;
    private Double valor;
    private Double valores;
    private String auto;
    private Date prazo;
    private String condutor;
    private String chamado;
    private String cindent;
    private String reembolso;
    private String observacao;
    private int numerodeinfracoes;

    public Infracao() {
    }

    public Infracao(int id, Date data, String placa, String locadora, int pontos, int somapontos, String gravidade, String codigo, String descricao, Double valor, Double valores, String auto, Date prazo, String condutor, String chamado, String cindent, String reembolso, String observacao, int numerodeinfracoes) {
        this.id = id;
        this.data = data;
        this.placa = placa;
        this.locadora = locadora;
        this.pontos = pontos;
        this.somapontos = somapontos;
        this.gravidade = gravidade;
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.valores = valores;
        this.auto = auto;
        this.prazo = prazo;
        this.condutor = condutor;
        this.chamado = chamado;
        this.cindent = cindent;
        this.reembolso = reembolso;
        this.observacao = observacao;
        this.numerodeinfracoes = numerodeinfracoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getLocadora() {
        return locadora;
    }

    public void setLocadora(String locadora) {
        this.locadora = locadora;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getSomapontos() {
        return somapontos;
    }

    public void setSomapontos(int somapontos) {
        this.somapontos = somapontos;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValores() {
        return valores;
    }

    public void setValores(Double valores) {
        this.valores = valores;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public String getCondutor() {
        return condutor;
    }

    public void setCondutor(String condutor) {
        this.condutor = condutor;
    }

    public String getChamado() {
        return chamado;
    }

    public void setChamado(String chamado) {
        this.chamado = chamado;
    }

    public String getCindent() {
        return cindent;
    }

    public void setCindent(String cindent) {
        this.cindent = cindent;
    }

    public String getReembolso() {
        return reembolso;
    }

    public void setReembolso(String reembolso) {
        this.reembolso = reembolso;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getNumerodeinfracoes() {
        return numerodeinfracoes;
    }

    public void setNumerodeinfracoes(int numerodeinfracoes) {
        this.numerodeinfracoes = numerodeinfracoes;
    }

}
