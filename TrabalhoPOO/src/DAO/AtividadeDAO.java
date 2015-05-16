/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Atividade;
import Pojo.Disciplina;
import Pojo.Turma;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    public boolean excluiAtividade (String nome){
        {
        Atividade aux = new Atividade(nome);
        return this.listaAtividade.remove(aux);
        }
    }
    public Atividade buscaAtividade(String nome){
        Atividade aux = new Atividade(nome);
        
        for(Atividade atividade : listaAtividade){
            if(atividade.equals(aux)){
                return atividade;
            }
        }
        
        return null;
        
    }
    public void escreverArquivo(){
       
        try {
        
            FileWriter fw = new FileWriter("Atividades.txt",false);
            PrintWriter saida = new PrintWriter(fw,true);
            for(Atividade atividade : this.listaAtividade){
                saida.println(atividade.getNome());
                saida.println(atividade.getTipo());
                saida.println(atividade.getData());
                saida.println(atividade.getPeso());
                saida.println(atividade.getTurma().getIdTurma());
                saida.println(atividade.getTurma().getDisciplina().getNome());
            }    
            
            saida.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Atividade> getListaAtividade(){
        return this.listaAtividade;
    }

    
}
