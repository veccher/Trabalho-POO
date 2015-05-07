/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Atividade;
import Pojo.Disciplina;
import Pojo.Turma;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author veccher
 */
public class AtividadeDAO {
    private ArrayList<Atividade> listaAtividade = new ArrayList<Atividade>();
    
    public void adicionar (Atividade atividade){
        listaAtividade.add(atividade);
        Collections.sort(listaAtividade);
    }
    public boolean excluirAtividade (String nome,Turma turma){
        {
        Atividade aux = new Atividade(nome,turma);
        return this.listaAtividade.remove(aux);
        }
    }
    public Atividade buscarAtividade(String nome,Turma turma){
        Atividade aux = new Atividade(nome,turma);
        
        for(Atividade atividade : listaAtividade){
            if(atividade.equals(aux)){
                return atividade;
            }
        }
        
        return null;
        
    }
}
