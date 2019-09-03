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
public class Centro {

    private int id;
    private String centro;

    public Centro() {
    }

    public Centro(int id, String centro) {
        this.id = id;
        this.centro = centro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

}
