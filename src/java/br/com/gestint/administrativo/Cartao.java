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
public class Cartao {

    private int id;
    private String cartao;

    public Cartao() {
    }

    public Cartao(int id, String cartao) {
        this.id = id;
        this.cartao = cartao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

}
