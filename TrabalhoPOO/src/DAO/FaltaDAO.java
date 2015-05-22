/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Aluno;
import Pojo.Falta;
import Pojo.Turma;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    //adiciona uma falta na lista e ordena
    public void adicionar(Falta falta){
        listaFalta.add(falta);
        Collections.sort(listaFalta);
    }
    //exclui uma falta da lista
    public boolean excluiFalta(Aluno aluno, Turma turma){
        
        Falta aux = new Falta(new Integer(0),aluno,turma);
        return this.listaFalta.remove(aux);
        
   }
    //busca uma falta da lista
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
    /*escreve no arquivo todas os objetos da lista na ordem: numero de faltas,
    cpf do aluno (serve para buscar o aluno posteriormente em alunoDAO), nome
    da Disciplina e id da turma (usados posteriormente para buscar a turma em
    turmaDAO*/
    public void escreverArquivo(){
       
        try {
        
            FileWriter fw = new FileWriter("Faltas.txt",false);
            PrintWriter saida = new PrintWriter(fw,true);
            for(Falta falta : this.listaFalta){
                saida.println(falta.getNumFaltas());
                saida.println(falta.getAluno().getCpf());
                saida.println(falta.getTurma().getDisciplina().getNome());
                saida.println(falta.getTurma().getIdTurma());
                
            }    
            
            saida.close();
            fw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*le do arquivo as informações para inicialização do programa com base nas
    especificações da função de escrita*/
    public void lerArquivo(AlunoDAO alunoDAO, TurmaDAO turmaDAO, DisciplinaDAO disciplinaDAO){
        
        FileReader fileR;
        BufferedReader buff;
        try {        
            fileR = new FileReader("Faltas.txt");
            buff = new BufferedReader(fileR);
            while(buff.ready()){
                Falta falta = new Falta();
                falta.setNumFaltas(Integer.parseInt(buff.readLine()));
                falta.setAluno(alunoDAO.buscaAluno(Integer.parseInt(buff.readLine())));
                falta.setTurma(turmaDAO.buscaTurma(disciplinaDAO.buscaDisciplina(buff.readLine())
                        , Integer.parseInt(buff.readLine())));
                falta.getTurma().addFalta(falta);

                this.adicionar(falta);
            }
            buff.close();
            fileR.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }            
    }
}
