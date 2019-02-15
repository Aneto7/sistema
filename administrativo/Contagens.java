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
public class Contagens {

    private int numemrodeanalises;
    private Double realmes;
    private Double bdgmes;
    private Double realacum;
    private Double bdgacum;
    private Double difmes;
    private Double difacum;

    public Contagens() {
    }

    public Contagens(int numemrodeanalises, Double realmes, Double bdgmes, Double realacum, Double bdgacum, Double difmes, Double difacum) {
        this.numemrodeanalises = numemrodeanalises;
        this.realmes = realmes;
        this.bdgmes = bdgmes;
        this.realacum = realacum;
        this.bdgacum = bdgacum;
        this.difmes = difmes;
        this.difacum = difacum;
    }

    public int getNumemrodeanalises() {
        return numemrodeanalises;
    }

    public void setNumemrodeanalises(int numemrodeanalises) {
        this.numemrodeanalises = numemrodeanalises;
    }

    public Double getRealmes() {
        return realmes;
    }

    public void setRealmes(Double realmes) {
        this.realmes = realmes;
    }

    public Double getBdgmes() {
        return bdgmes;
    }

    public void setBdgmes(Double bdgmes) {
        this.bdgmes = bdgmes;
    }

    public Double getRealacum() {
        return realacum;
    }

    public void setRealacum(Double realacum) {
        this.realacum = realacum;
    }

    public Double getBdgacum() {
        return bdgacum;
    }

    public void setBdgacum(Double bdgacum) {
        this.bdgacum = bdgacum;
    }

    public Double getDifmes() {
        return difmes;
    }

    public void setDifmes(Double difmes) {
        this.difmes = difmes;
    }

    public Double getDifacum() {
        return difacum;
    }

    public void setDifacum(Double difacum) {
        this.difacum = difacum;
    }

}
