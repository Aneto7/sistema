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
public class Unidade {

    private int id;
    private String filial;
    private String regiao;
    private String tipo;
    private String status;
    private String gsub;
    private String grg;
    private String gdiv;

    public Unidade() {
    }

    public Unidade(int id, String filial, String regiao, String tipo, String status, String gsub, String grg, String gdiv) {
        this.id = id;
        this.filial = filial;
        this.regiao = regiao;
        this.tipo = tipo;
        this.status = status;
        this.gsub = gsub;
        this.grg = grg;
        this.gdiv = gdiv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGsub() {
        return gsub;
    }

    public void setGsub(String gsub) {
        this.gsub = gsub;
    }

    public String getGrg() {
        return grg;
    }

    public void setGrg(String grg) {
        this.grg = grg;
    }

    public String getGdiv() {
        return gdiv;
    }

    public void setGdiv(String gdiv) {
        this.gdiv = gdiv;
    }

}
