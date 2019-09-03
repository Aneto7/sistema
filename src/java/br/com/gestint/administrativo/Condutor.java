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
public class Condutor {

    private int id;
    private String condutor;

    public Condutor() {
    }

    public Condutor(int id, String condutor) {
        this.id = id;
        this.condutor = condutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCondutor() {
        return condutor;
    }

    public void setCondutor(String condutor) {
        this.condutor = condutor;
    }

}
