/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class IAA implements Serializable{
    private String nomeFaixa;
    private int qntMatriculasFaixa;
    private ArrayList<Integer> matriculas;
    
    public IAA(String nomeFaixa){
        this.nomeFaixa = nomeFaixa;
        this.qntMatriculasFaixa = 0;
        this.matriculas = new ArrayList<>();
    }

    public String getNomeFaixa() {
        return nomeFaixa;
    }

    public void setNomeFaixa(String nomeFaixa) {
        this.nomeFaixa = nomeFaixa;
    }

    public int getQntMatriculasFaixa() {
        return qntMatriculasFaixa;
    }

    public void setQntMatriculasFaixa(int qntMatriculasFaixa) {
        this.qntMatriculasFaixa = qntMatriculasFaixa;
    }

    public ArrayList<Integer> getMatriculas() {
        return matriculas;
    }
}
