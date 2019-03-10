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
public class Cidade implements Serializable{
    private String nomeCidade;
    private int qntAlunosCidade;
    private ArrayList<Integer> matriculas;
    
    public Cidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
        this.matriculas=new ArrayList<>();
        this.qntAlunosCidade=0;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public int getQntAlunosCidade() {
        return qntAlunosCidade;
    }

    public void setQntAlunosCidade(int qntAlunosCidade) {
        this.qntAlunosCidade = qntAlunosCidade;
    }

    public ArrayList<Integer> getMatriculas() {
        return matriculas;
    }
}
