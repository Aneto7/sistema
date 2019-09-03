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
public class DashboardOrcamento {

    private int nmes;
    private String mes;
    private Double realizado;
    private Double bdgmes;
    private int jusdentro;
    private int jussub;
    private int jussuper;
    private int jussem;
    private int jusa115;
    private int jusa201;
    private int jusa322;
    private int jusa333;
    private int jussoja;
    private int jusmilho;

    public DashboardOrcamento() {
    }

    public DashboardOrcamento(int nmes, String mes, Double realizado, Double bdgmes, int jusdentro, int jussub, int jussuper, int jussem, int jusa115, int jusa201, int jusa322, int jusa333, int jussoja, int jusmilho) {
        this.nmes = nmes;
        this.mes = mes;
        this.realizado = realizado;
        this.bdgmes = bdgmes;
        this.jusdentro = jusdentro;
        this.jussub = jussub;
        this.jussuper = jussuper;
        this.jussem = jussem;
        this.jusa115 = jusa115;
        this.jusa201 = jusa201;
        this.jusa322 = jusa322;
        this.jusa333 = jusa333;
        this.jussoja = jussoja;
        this.jusmilho = jusmilho;
    }

    public int getNmes() {
        return nmes;
    }

    public void setNmes(int nmes) {
        this.nmes = nmes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Double getRealizado() {
        return realizado;
    }

    public void setRealizado(Double realizado) {
        this.realizado = realizado;
    }

    public Double getBdgmes() {
        return bdgmes;
    }

    public void setBdgmes(Double bdgmes) {
        this.bdgmes = bdgmes;
    }

    public int getJusdentro() {
        return jusdentro;
    }

    public void setJusdentro(int jusdentro) {
        this.jusdentro = jusdentro;
    }

    public int getJussub() {
        return jussub;
    }

    public void setJussub(int jussub) {
        this.jussub = jussub;
    }

    public int getJussuper() {
        return jussuper;
    }

    public void setJussuper(int jussuper) {
        this.jussuper = jussuper;
    }

    public int getJussem() {
        return jussem;
    }

    public void setJussem(int jussem) {
        this.jussem = jussem;
    }

    public int getJusa115() {
        return jusa115;
    }

    public void setJusa115(int jusa115) {
        this.jusa115 = jusa115;
    }

    public int getJusa201() {
        return jusa201;
    }

    public void setJusa201(int jusa201) {
        this.jusa201 = jusa201;
    }

    public int getJusa322() {
        return jusa322;
    }

    public void setJusa322(int jusa322) {
        this.jusa322 = jusa322;
    }

    public int getJusa333() {
        return jusa333;
    }

    public void setJusa333(int jusa333) {
        this.jusa333 = jusa333;
    }

    public int getJussoja() {
        return jussoja;
    }

    public void setJussoja(int jussoja) {
        this.jussoja = jussoja;
    }

    public int getJusmilho() {
        return jusmilho;
    }

    public void setJusmilho(int jusmilho) {
        this.jusmilho = jusmilho;
    }

}
