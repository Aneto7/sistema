/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.configuracao;

/**
 *
 * @author rosaan
 */
public class Usuario {

    private int idusuario;
    private String usuario;
    private String filial;
    private String regiao;
    private String senha;
    private String perfil;
    private String email;
    private String nome;
    private String setor;
    private String area;

    public Usuario() {
    }

    public Usuario(int idusuario, String usuario, String filial, String regiao, String senha, String perfil, String email, String nome, String setor, String area) {
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.filial = filial;
        this.regiao = regiao;
        this.senha = senha;
        this.perfil = perfil;
        this.email = email;
        this.nome = nome;
        this.setor = setor;
        this.area = area;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
