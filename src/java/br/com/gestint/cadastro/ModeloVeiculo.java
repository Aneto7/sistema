/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.cadastro;

/**
 *
 * @author Antonio
 */
public class ModeloVeiculo {

    private int id;
    private String modelo;
    private String tipo;

    public ModeloVeiculo() {
    }

    public ModeloVeiculo(int id, String modelo, String tipo) {
        this.id = id;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
