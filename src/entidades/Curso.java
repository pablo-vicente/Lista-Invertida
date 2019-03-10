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
public class Curso implements Serializable{
    private String nomeCurso;
    private int qntAlunosCurso;
    private ArrayList<Integer> matriculas;

    public Curso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
        this.matriculas=new ArrayList<>();
        this.qntAlunosCurso=0;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso){
        this.nomeCurso = nomeCurso;
    }

    public int getQntAlunosCurso() {
        return qntAlunosCurso;
    }

    public void setQntAlunosCurso(int qntAlunosCurso) {
    this.qntAlunosCurso = qntAlunosCurso;
    }

    public ArrayList<Integer> getMatriculas() {
        return matriculas;
    }
}
