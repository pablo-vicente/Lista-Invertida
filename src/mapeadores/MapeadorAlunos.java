package mapeadores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import entidades.Aluno;

/**
 *
 * @author pablo
 */
public class MapeadorAlunos {
    private HashMap<Integer, Aluno> alunos;
    private String filename = "src/mapeadores/alunos.bus";

    
    public MapeadorAlunos(){
        alunos = new HashMap<>();
        load();
    }  
    
    public Aluno get(Integer matricula) {
        return alunos.get(matricula);
    }
    
    public void put(Aluno aluno){
        alunos.put(aluno.getMatricula(), aluno);
        persist();
    }
    
    private void load() {
        try{
        FileInputStream entrada = new FileInputStream(filename);
        ObjectInputStream objetoEntrada = new ObjectInputStream(entrada);
        
        this.alunos = (HashMap<Integer, Aluno>) objetoEntrada.readObject();
        
        objetoEntrada.close();
        entrada.close();
        
        objetoEntrada = null;
        entrada = null;
        
        }catch(ClassNotFoundException ae){
            System.out.println(ae);
        }catch(FileNotFoundException ae){
            System.out.println(ae);
        }catch(IOException ae){
            System.out.println(ae);
        }
    }


    private void persist(){
        try{
        FileOutputStream saida = new FileOutputStream(filename);
        ObjectOutputStream objetoSaida = new ObjectOutputStream(saida);
        objetoSaida.writeObject(alunos);
        
        objetoSaida.flush();
        saida.flush();
        
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    public void remove(Integer matricula) {
        alunos.remove(matricula);
        persist();
    }
    
    public HashMap<Integer, Aluno> getList() {
        return alunos;
    }
}