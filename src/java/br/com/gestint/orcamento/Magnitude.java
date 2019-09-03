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
public class Magnitude {

    private int id;
    private String magnitude;

    public Magnitude() {
    }

    public Magnitude(int id, String magnitude) {
        this.id = id;
        this.magnitude = magnitude;
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

}
