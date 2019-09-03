/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.orcamento;

/**
 *
 * @author Antonio
 */
public class UsuarioListado {

    private int id;
    private String usuario;
    private String regiao;
    private String filial;

    public UsuarioListado() {
    }

    public UsuarioListado(int id, String usuario, String regiao, String filial) {
        this.id = id;
        this.usuario = usuario;
        this.regiao = regiao;
        this.filial = filial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

}
