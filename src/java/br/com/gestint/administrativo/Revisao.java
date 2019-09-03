/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativo;

import java.sql.Date;

/**
 *
 * @author Antonio
 */
public class Revisao {

    private int id;
    private String placa;
    private String locadora;
    private String base;
    private Double km;
    private Date data;
    private String detalhe;
    private Date proximarevisao;
    private int numerorevisoes;

    public Revisao() {
    }

    public Revisao(int id, String placa, String locadora, String base, Double km, Date data, String detalhe, Date proximarevisao, int numerorevisoes) {
        this.id = id;
        this.placa = placa;
        this.locadora = locadora;
        this.base = base;
        this.km = km;
        this.data = data;
        this.detalhe = detalhe;
        this.proximarevisao = proximarevisao;
        this.numerorevisoes = numerorevisoes;
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

    public String getLocadora() {
        return locadora;
    }

    public void setLocadora(String locadora) {
        this.locadora = locadora;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public Date getProximarevisao() {
        return proximarevisao;
    }

    public void setProximarevisao(Date proximarevisao) {
        this.proximarevisao = proximarevisao;
    }

    public int getNumerorevisoes() {
        return numerorevisoes;
    }

    public void setNumerorevisoes(int numerorevisoes) {
        this.numerorevisoes = numerorevisoes;
    }

}
