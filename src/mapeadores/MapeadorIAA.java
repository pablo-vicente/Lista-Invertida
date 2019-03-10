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
import entidades.IAA;

/**
 *
 * @author pablo
 */
public class MapeadorIAA {
    private HashMap<String, IAA>faixasIAAs;
    private String filename = "src/mapeadores/iaa.bus";
    
    
    public MapeadorIAA(){
        faixasIAAs = new HashMap<>();
        load();
    }  
    
    public IAA get(String faixaIAA) {
        return faixasIAAs.get(faixaIAA);
    }
    
    public void put(IAA faixaIAA){
        faixasIAAs.put(faixaIAA.getNomeFaixa(), faixaIAA);
        persist();
    }
    
    private void load() {
        try{
        FileInputStream entrada = new FileInputStream(filename);
        ObjectInputStream objetoEntrada = new ObjectInputStream(entrada);
        
        this.faixasIAAs = (HashMap<String, IAA>) objetoEntrada.readObject();
        
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
        objetoSaida.writeObject(faixasIAAs);
        
        objetoSaida.flush();
        saida.flush();
        
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    public void remove(String faixaIAA) {
        faixasIAAs.remove(faixaIAA);
        persist();
    }
    
    public HashMap<String, IAA> getList() {
        return faixasIAAs;
    }

}
