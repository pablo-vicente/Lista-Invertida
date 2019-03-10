package mapeadores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entidades.Curso;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author pablo
 */
public class MapeadorCursos {
    private HashMap<String, Curso>cursos;
    private String filename = "src/mapeadores/cursos.bus";
    
    
    public MapeadorCursos(){
        cursos = new HashMap<>();
        load();
    }  
    
    public Curso get(String curso) {
        return cursos.get(curso);
    }
    
    public void put(Curso curso){
        cursos.put(curso.getNomeCurso(), curso);
        persist();
    }
    
    private void load() {
        try{
        FileInputStream entrada = new FileInputStream(filename);
        ObjectInputStream objetoEntrada = new ObjectInputStream(entrada);
        
        this.cursos = (HashMap<String, Curso>) objetoEntrada.readObject();
        
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
        objetoSaida.writeObject(cursos);
        
        objetoSaida.flush();
        saida.flush();
        
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    public void remove(String curso) {
        cursos.remove(curso);
        persist();
    }
    
    public HashMap<String, Curso> getList() {
        return cursos;
    }

}
