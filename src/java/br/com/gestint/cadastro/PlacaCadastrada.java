/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.cadastro;

import br.com.gestint.cadastro.*;

/**
 *
 * @author rosaan
 */
public class PlacaCadastrada {
    private int id;
    private String placa;

    public PlacaCadastrada() {
    }

    public PlacaCadastrada(int id, String placa) {
        this.id = id;
        this.placa = placa;
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
    
    
    
}
