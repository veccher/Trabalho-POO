/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Aluno;
import Pojo.Falta;
import Pojo.Turma;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    public void escreverArquivo(){
       
        try {
        
            FileWriter fw = new FileWriter("Faltas.txt",false);
            PrintWriter saida = new PrintWriter(fw,true);
            for(Falta falta : this.listaFalta){
                saida.println(falta.getNumFaltas());
                saida.println(falta.getAluno().getCpf());
                saida.println(falta.getTurma().getIdTurma());
                saida.println(falta.getTurma().getDisciplina().getNome());
            }    
            
            saida.close();
            fw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
