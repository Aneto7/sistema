/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamento;

/**
 *
 * @author rosaan
 */
public class Orcamento {

    private int id;
    private String magnitude;
    private String regiao;
    private String responsavel;
    private String filial;
    private String tipoconta;
    private String conta;
    private String descricaoconta;
    private String centrodecusto;
    private String descricaocentrodecusto;
    private String segmento;
    private Double bdgmes;
    private Double realmes;
    private Double diferencames;
    private Double bdgacum;
    private Double realacum;
    private Double diferencaacum;
    private Double bdganual;
    private Double realanual;
    private Double diferencaanual;

    public Orcamento() {
    }

    public Orcamento(int id, String magnitude, String regiao, String responsavel, String filial, String tipoconta, String conta, String descricaoconta, String centrodecusto, String descricaocentrodecusto, String segmento, Double bdgmes, Double realmes, Double diferencames, Double bdgacum, Double realacum, Double diferencaacum, Double bdganual, Double realanual, Double diferencaanual) {
        this.id = id;
        this.magnitude = magnitude;
        this.regiao = regiao;
        this.responsavel = responsavel;
        this.filial = filial;
        this.tipoconta = tipoconta;
        this.conta = conta;
        this.descricaoconta = descricaoconta;
        this.centrodecusto = centrodecusto;
        this.descricaocentrodecusto = descricaocentrodecusto;
        this.segmento = segmento;
        this.bdgmes = bdgmes;
        this.realmes = realmes;
        this.diferencames = diferencames;
        this.bdgacum = bdgacum;
        this.realacum = realacum;
        this.diferencaacum = diferencaacum;
        this.bdganual = bdganual;
        this.realanual = realanual;
        this.diferencaanual = diferencaanual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(String tipoconta) {
        this.tipoconta = tipoconta;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getDescricaoconta() {
        return descricaoconta;
    }

    public void setDescricaoconta(String descricaoconta) {
        this.descricaoconta = descricaoconta;
    }

    public String getCentrodecusto() {
        return centrodecusto;
    }

    public void setCentrodecusto(String centrodecusto) {
        this.centrodecusto = centrodecusto;
    }

    public String getDescricaocentrodecusto() {
        return descricaocentrodecusto;
    }

    public void setDescricaocentrodecusto(String descricaocentrodecusto) {
        this.descricaocentrodecusto = descricaocentrodecusto;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public Double getBdgmes() {
        return bdgmes;
    }

    public void setBdgmes(Double bdgmes) {
        this.bdgmes = bdgmes;
    }

    public Double getRealmes() {
        return realmes;
    }

    public void setRealmes(Double realmes) {
        this.realmes = realmes;
    }

    public Double getDiferencames() {
        return diferencames;
    }

    public void setDiferencames(Double diferencames) {
        this.diferencames = diferencames;
    }

    public Double getBdgacum() {
        return bdgacum;
    }

    public void setBdgacum(Double bdgacum) {
        this.bdgacum = bdgacum;
    }

    public Double getRealacum() {
        return realacum;
    }

    public void setRealacum(Double realacum) {
        this.realacum = realacum;
    }

    public Double getDiferencaacum() {
        return diferencaacum;
    }

    public void setDiferencaacum(Double diferencaacum) {
        this.diferencaacum = diferencaacum;
    }

    public Double getBdganual() {
        return bdganual;
    }

    public void setBdganual(Double bdganual) {
        this.bdganual = bdganual;
    }

    public Double getRealanual() {
        return realanual;
    }

    public void setRealanual(Double realanual) {
        this.realanual = realanual;
    }

    public Double getDiferencaanual() {
        return diferencaanual;
    }

    public void setDiferencaanual(Double diferencaanual) {
        this.diferencaanual = diferencaanual;
    }

}
