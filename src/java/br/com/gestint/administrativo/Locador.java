/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativo;

/**
 *
 * @author rosaan
 */
public class Locador {

    private int id;
    private String locadora;

    public Locador() {
    }

    public Locador(int id, String locadora) {
        this.id = id;
        this.locadora = locadora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocadora() {
        return locadora;
    }

    public void setLocadora(String locadora) {
        this.locadora = locadora;
    }

}
