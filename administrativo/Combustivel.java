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
public class Combustivel {

    private int id;
    private String placa;
    private String cartao;
    private Date data;
    private Double valor;
    private Double litros;
    private Double KM;
    private String condutor;
    private String centro;

    public Combustivel() {
    }

    public Combustivel(int id, String placa, String cartao, Date data, Double valor, Double litros, Double KM, String condutor, String centro) {
        this.id = id;
        this.placa = placa;
        this.cartao = cartao;
        this.data = data;
        this.valor = valor;
        this.litros = litros;
        this.KM = KM;
        this.condutor = condutor;
        this.centro = centro;
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

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getLitros() {
        return litros;
    }

    public void setLitros(Double litros) {
        this.litros = litros;
    }

    public Double getKM() {
        return KM;
    }

    public void setKM(Double KM) {
        this.KM = KM;
    }

    public String getCondutor() {
        return condutor;
    }

    public void setCondutor(String condutor) {
        this.condutor = condutor;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    
}
