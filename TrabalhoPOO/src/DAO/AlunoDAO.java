/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Aluno;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vitor
 */
public class AlunoDAO {
    private ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
    
    public void adicionar(Aluno aluno){
        listaAluno.add(aluno);
        Collections.sort(listaAluno);
    }
    
    public boolean excluiAluno(String nomeDel){
        Aluno aux = new Aluno();
        aux.setNome(nomeDel);
        return this.listaAluno.remove(aux);
        
   }
  
    public Aluno buscaAluno(Integer cpf){
        Aluno aux = new Aluno();
        aux.setCpf(cpf);
        for(Aluno aluno : listaAluno){
            if(aluno.equals(aux)){
                return aluno;
            }
        }
        return null;
    }
}
