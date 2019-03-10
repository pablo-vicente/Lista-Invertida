/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;

/**
 *
 * @author pablo
 */
public class Aluno implements Serializable{
    
    private int matricula;
    private String nome;
    private String curso;
    private String cidade;
    private Float iaa;
    
    

    public Aluno(int matricula, String nome, String curso, String cidade, Float iaa) {
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;
        this.cidade = cidade;
        this.iaa = iaa;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatrícula(int matrícula) {
        this.matricula = matrícula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Float getIaa() {
        return iaa;
    }

    public void setIaa(Float iaa) {
        this.iaa = iaa;
    }
    
}
