/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Professor;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vitor
 */
public class ProfessorDAO {
     private ArrayList<Professor> listaProfessor = new ArrayList<Professor>();
    
    public void adicionar(Professor professor){
        listaProfessor.add(professor);
        Collections.sort(listaProfessor);
    }
    
    public boolean excluirProfessor(String nomeDel){
        Professor aux = new Professor();
        aux.setNome(nomeDel);
        return this.listaProfessor.remove(aux);
        
   }
    public Professor buscarProfessor(Integer cpf){
        Professor aux = new Professor();
        aux.setCpf(cpf);
        for(Professor professor : listaProfessor){
            if(professor.equals(aux)){
                return professor;
            }
        }
        return null;
    }
    
}
