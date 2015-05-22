/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Aluno;
import Pojo.Atividade;
import Pojo.Nota;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    //adiciona uma nota na lista e ordena
    public void adicionar(Nota nota){
        listaNota.add(nota);
        Collections.sort(listaNota);
    }
    //exclui uma nota da lista
    public boolean excluiNota(Aluno aluno, Atividade atividade){
        
        Nota aux = new Nota(new Float(0),aluno,atividade);
        return this.listaNota.remove(aux);
        
   }
    //busca uma nota na lista
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
    /*escreve no arquivo "Notas.txt" todos os objetos da lista na seguinte ordem:
    nota,cpf(usado para buscar o aluno posteriormente em AlunoDAO, nome da Atividade,
    nome da disciplina, e id da turma (usados posteriormente para encontrar a 
    atividade que a nota se refere*/
    public void escreverArquivo(){
       
        try {
        
            FileWriter fw = new FileWriter("Notas.txt",false);
            PrintWriter saida = new PrintWriter(fw,true);
            for(Nota nota : this.listaNota){
                saida.println(nota.getNota());
                saida.println(nota.getAluno().getCpf());
                saida.println(nota.getAtividade().getNome());
                saida.println(nota.getAtividade().getTurma().getDisciplina().getNome());
                saida.println(nota.getAtividade().getTurma().getIdTurma());
                
            }    
            
            saida.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*le as informações do arquivo com base nas especificações da função de escrita*/
    public void lerArquivo(AlunoDAO alunoDAO, AtividadeDAO atividadeDAO, TurmaDAO turmaDAO, DisciplinaDAO disciplinaDAO){
        
        FileReader fileR;
        BufferedReader buff;
        try {        
            fileR = new FileReader("Notas.txt");
            buff = new BufferedReader(fileR);
            while(buff.ready()){
                Nota nota = new Nota();
                nota.setNota(Float.parseFloat(buff.readLine()));
                nota.setAluno(alunoDAO.buscaAluno(Integer.parseInt(buff.readLine())));
                nota.setAtividade(atividadeDAO.buscaAtividade(buff.readLine(),
                                  turmaDAO.buscaTurma(disciplinaDAO.buscaDisciplina(buff.readLine()),
                                            Integer.parseInt(buff.readLine()))));
                nota.getAtividade().addNota(nota);
                this.adicionar(nota);
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
