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
public class CondutorCad {

    private int id;
    private String matricula;
    private String nome;
    private String centro;
    private String ibutton;
    private boolean poll;
    private boolean trab;
    private boolean desig;
    private String empresa;
    private String area;
    private String cargo;
    private String unidade;
    private String regional;
    private String dataadminissao;
    private String datadefensiva;
    private String ncnh;
    private String vencimentocnh;
    private String categoriacnh;
    private boolean form31;
    private boolean form32;
    private String gestorsubregional;
    private String gestorregional;
    private String gestordivisional;
    private boolean aprovado;
    private String observacao;
    private String status;

    public CondutorCad() {
    }

    public CondutorCad(int id, String matricula, String nome, String centro, String ibutton, boolean poll, boolean trab, boolean desig, String empresa, String area, String cargo, String unidade, String regional, String dataadminissao, String datadefensiva, String ncnh, String vencimentocnh, String categoriacnh, boolean form31, boolean form32, String gestorsubregional, String gestorregional, String gestordivisional, boolean aprovado, String observacao, String status) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.centro = centro;
        this.ibutton = ibutton;
        this.poll = poll;
        this.trab = trab;
        this.desig = desig;
        this.empresa = empresa;
        this.area = area;
        this.cargo = cargo;
        this.unidade = unidade;
        this.regional = regional;
        this.dataadminissao = dataadminissao;
        this.datadefensiva = datadefensiva;
        this.ncnh = ncnh;
        this.vencimentocnh = vencimentocnh;
        this.categoriacnh = categoriacnh;
        this.form31 = form31;
        this.form32 = form32;
        this.gestorsubregional = gestorsubregional;
        this.gestorregional = gestorregional;
        this.gestordivisional = gestordivisional;
        this.aprovado = aprovado;
        this.observacao = observacao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getIbutton() {
        return ibutton;
    }

    public void setIbutton(String ibutton) {
        this.ibutton = ibutton;
    }

    public boolean isPoll() {
        return poll;
    }

    public void setPoll(boolean poll) {
        this.poll = poll;
    }

    public boolean isTrab() {
        return trab;
    }

    public void setTrab(boolean trab) {
        this.trab = trab;
    }

    public boolean isDesig() {
        return desig;
    }

    public void setDesig(boolean desig) {
        this.desig = desig;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getDataadminissao() {
        return dataadminissao;
    }

    public void setDataadminissao(String dataadminissao) {
        this.dataadminissao = dataadminissao;
    }

    public String getDatadefensiva() {
        return datadefensiva;
    }

    public void setDatadefensiva(String datadefensiva) {
        this.datadefensiva = datadefensiva;
    }

    public String getNcnh() {
        return ncnh;
    }

    public void setNcnh(String ncnh) {
        this.ncnh = ncnh;
    }

    public String getVencimentocnh() {
        return vencimentocnh;
    }

    public void setVencimentocnh(String vencimentocnh) {
        this.vencimentocnh = vencimentocnh;
    }

    public String getCategoriacnh() {
        return categoriacnh;
    }

    public void setCategoriacnh(String categoriacnh) {
        this.categoriacnh = categoriacnh;
    }

    public boolean isForm31() {
        return form31;
    }

    public void setForm31(boolean form31) {
        this.form31 = form31;
    }

    public boolean isForm32() {
        return form32;
    }

    public void setForm32(boolean form32) {
        this.form32 = form32;
    }

    public String getGestorsubregional() {
        return gestorsubregional;
    }

    public void setGestorsubregional(String gestorsubregional) {
        this.gestorsubregional = gestorsubregional;
    }

    public String getGestorregional() {
        return gestorregional;
    }

    public void setGestorregional(String gestorregional) {
        this.gestorregional = gestorregional;
    }

    public String getGestordivisional() {
        return gestordivisional;
    }

    public void setGestordivisional(String gestordivisional) {
        this.gestordivisional = gestordivisional;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
