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
public class CentrodeCusto {

    private int id;
    private String centrodecusto;

    public CentrodeCusto() {
    }

    public CentrodeCusto(int id, String centrodecusto) {
        this.id = id;
        this.centrodecusto = centrodecusto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCentrodecusto() {
        return centrodecusto;
    }

    public void setCentrodecusto(String centrodecusto) {
        this.centrodecusto = centrodecusto;
    }

}
