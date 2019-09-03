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
public class Conta {

    private int id;
    private String conta;

    public Conta() {
    }

    public Conta(int id, String conta) {
        this.id = id;
        this.conta = conta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

}
