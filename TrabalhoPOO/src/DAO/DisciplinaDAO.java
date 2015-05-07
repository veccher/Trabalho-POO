/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import Pojo.Disciplina;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Vitor
 */
public class DisciplinaDAO {
     private ArrayList<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
    
    public void adicionar(Disciplina disciplina){
        listaDisciplina.add(disciplina);
        Collections.sort(listaDisciplina);
    }
    
    public boolean excluiDisciplina(String nomeDel){
        Disciplina aux = new Disciplina();
        aux.setNome(nomeDel);
        return this.listaDisciplina.remove(aux);
        
   }
    public Disciplina buscaDisciplina(String nome){
        Disciplina aux = new Disciplina();
        aux.setNome(nome);
        for(Disciplina disciplina : listaDisciplina){
            if(disciplina.equals(aux)){
                return disciplina;
            }
        }
        return null;
    }
    
}
