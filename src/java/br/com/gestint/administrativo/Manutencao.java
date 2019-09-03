/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestint.administrativo;

import java.sql.Date;

/**
 *
 * @author Antonio
 */
public class Manutencao {

    private int id;
    private String placa;
    private Date datainicio;
    private Date datafim;
    private String grupomanutencao;
    private Double valor;
    private Double mediavalor;
    private String responsavel;
    private String detalhe;
    private String orcamento;
    private String carroreserva;
    private int numeromanutencoes;

    public Manutencao() {
    }

    public Manutencao(int id, String placa, Date datainicio, Date datafim, String grupomanutencao, Double valor, Double mediavalor, String responsavel, String detalhe, String orcamento, String carroreserva, int numeromanutencoes) {
        this.id = id;
        this.placa = placa;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.grupomanutencao = grupomanutencao;
        this.valor = valor;
        this.mediavalor = mediavalor;
        this.responsavel = responsavel;
        this.detalhe = detalhe;
        this.orcamento = orcamento;
        this.carroreserva = carroreserva;
        this.numeromanutencoes = numeromanutencoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public String getGrupomanutencao() {
        return grupomanutencao;
    }

    public void setGrupomanutencao(String grupomanutencao) {
        this.grupomanutencao = grupomanutencao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getMediavalor() {
        return mediavalor;
    }

    public void setMediavalor(Double mediavalor) {
        this.mediavalor = mediavalor;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(String orcamento) {
        this.orcamento = orcamento;
    }

    public String getCarroreserva() {
        return carroreserva;
    }

    public void setCarroreserva(String carroreserva) {
        this.carroreserva = carroreserva;
    }

    public int getNumeromanutencoes() {
        return numeromanutencoes;
    }

    public void setNumeromanutencoes(int numeromanutencoes) {
        this.numeromanutencoes = numeromanutencoes;
    }

}
