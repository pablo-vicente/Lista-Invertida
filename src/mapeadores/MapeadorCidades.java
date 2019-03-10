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
import entidades.Cidade;

/**
 *
 * @author pablo
 */
public class MapeadorCidades {
    private HashMap<String, Cidade>cidades;
    private String filename = "src/mapeadores/cidades.bus";
    
    
    public MapeadorCidades(){
        cidades = new HashMap<>();
        load();
    }  
    
    public Cidade get(String cidade) {
        return cidades.get(cidade);
    }
    
    public void put(Cidade cidade){
        cidades.put(cidade.getNomeCidade(), cidade);
        persist();
    }
    
    private void load() {
        try{
        FileInputStream entrada = new FileInputStream(filename);
        ObjectInputStream objetoEntrada = new ObjectInputStream(entrada);
        
        this.cidades = (HashMap<String, Cidade>) objetoEntrada.readObject();
        
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
        objetoSaida.writeObject(cidades);
        
        objetoSaida.flush();
        saida.flush();
        
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    public void remove(String cidade) {
        cidades.remove(cidade);
        persist();
    }
    
    public HashMap<String, Cidade> getList() {
        return cidades;
    }

}
