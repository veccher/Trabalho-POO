/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Aluno;
import Pojo.Atividade;
import Pojo.Nota;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    
    public boolean excluiNota(Aluno aluno, Atividade atividade){
        
        Nota aux = new Nota(new Float(0),aluno,atividade);
        return this.listaNota.remove(aux);
        
   }
    
    public Nota buscaNota(Aluno aluno, Atividade atividade){
        Nota aux = new Nota(new Float(0),aluno,atividade);
        
        for(Nota nota : listaNota){
            if(nota.equals(aux)){
                return nota;
            }
            
        }
        return null;
    }
    public ArrayList<Nota> getListaNota(){
        return this.listaNota;
    }
    public void escreverArquivo(){
       
        try {
        
            FileWriter fw = new FileWriter("Notas.txt",false);
            PrintWriter saida = new PrintWriter(fw,true);
            for(Nota nota : this.listaNota){
                saida.println(nota.getNota());
                saida.println(nota.getAluno().getCpf());
                saida.println(nota.getAluno().getCpf());
                saida.println(nota.getAtividade().getNome());
                saida.println(nota.getAtividade().getTurma().getIdTurma());
                saida.println(nota.getAtividade().getTurma().getDisciplina().getNome());
            }    
            
            saida.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
