/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Nota;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vitor
 */
public class NotaDAO {
    private ArrayList<Nota> listaNota = new ArrayList<Nota>();
    
    public void adicionar(Nota nota){
        listaNota.add(nota);
        Collections.sort(listaNota);
    }
    
    public boolean excluirNota(String nomeDel){
        Nota aux = new Nota();
        return this.listaNota.remove(aux);
        
   }
    
    
}
