/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Aluno;
import Pojo.Falta;
import Pojo.Turma;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Vitor
 */
public class FaltaDAO {
    private ArrayList<Falta> listaFalta = new ArrayList<Falta>();
    
    public void adicionar(Falta falta){
        listaFalta.add(falta);
        Collections.sort(listaFalta);
    }
    
    public boolean excluiFalta(Aluno aluno, Turma turma){
        
        Falta aux = new Falta(new Integer(0),aluno,turma);
        return this.listaFalta.remove(aux);
        
   }
    
    public Falta buscaFalta(Aluno aluno, Turma turma){
        Falta aux = new Falta(new Integer(0),aluno,turma);
        
        for(Falta falta : listaFalta){
            if(falta.equals(aux)){
                return falta;
            }
            
        }
        return null;
    }
    public ArrayList<Falta> getListaFalta(){
        return this.listaFalta;
    }


    
    
    
}
