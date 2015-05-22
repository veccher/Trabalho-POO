/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Aluno;
import Pojo.Professor;
import Pojo.Disciplina;
import Pojo.Turma;
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
public class TurmaDAO {
    private ArrayList<Turma> listaTurma = new ArrayList<Turma>();
    //adiciona uma turma a lista e ordena
    public void adicionar(Turma turma){
        listaTurma.add(turma);
        Collections.sort(listaTurma);
    }
    //exclui uma turma da lista
    public boolean excluiTurma(Disciplina disciplina,Integer idTurma){
        Turma aux = new Turma(idTurma,disciplina);
        return this.listaTurma.remove(aux);
        
   }
    //busca uma turma na lista
    public Turma buscaTurma(Disciplina disciplina,Integer idTurma){
        Turma aux = new Turma(idTurma, disciplina);
        
        for(Turma turma : listaTurma){
            if(turma.equals(aux)){
                return turma;
            }
        }
        
        return null;
        
    }
    public ArrayList<Turma> getListaTurma(){
        return listaTurma;
   }
    /*escreve todos os objetos no arquivo Turmas.txt*/
    public void escreverArquivo(){
       
        try {
        
            FileWriter fw = new FileWriter("Turmas.txt",false);
            PrintWriter saida = new PrintWriter(fw,true);
            for(Turma turma : this.listaTurma){
                saida.println(turma.getIdTurma());
                saida.println(turma.getAno());
                saida.println(turma.getPeriodo());
                saida.println(turma.getLocal());
                saida.println(turma.getHorario());
                saida.println(turma.getNumVagas());
                saida.println(turma.getProfessor().getCpf());
                saida.println(turma.getDisciplina().getNome());
                saida.println(turma.getListaAlunos().size());
                for (Aluno aluno: turma.getListaAlunos()){
                    saida.println(aluno.getCpf());
                }
            }    
            saida.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*le todas as informações do arquivo conforme especificações da função de
    escrita, um construtor diferente é usado para manutenção da variavel
    referente ao numero de turmas ja criadas da disciplina*/
    public void lerArquivo(ProfessorDAO professorDAO, DisciplinaDAO disciplinaDAO, AlunoDAO alunoDAO){
        
        FileReader fileR;
        BufferedReader buff;
        try {        
            fileR = new FileReader("Turmas.txt");
            buff = new BufferedReader(fileR);
            while(buff.ready()){
                Turma turma = new Turma(Integer.parseInt(buff.readLine()),
                Integer.parseInt(buff.readLine()),Integer.parseInt(buff.readLine())
                ,buff.readLine(),buff.readLine(),Integer.parseInt(buff.readLine()),
                        professorDAO.buscarProfessor(Integer.parseInt(buff.readLine())),
                                disciplinaDAO.buscaDisciplina(buff.readLine()));                
                int j=Integer.parseInt(buff.readLine());
                for(int i=0;j>i;i++){
                    turma.matriculaAluno(alunoDAO.buscaAluno(Integer.parseInt(buff.readLine())));             
                    
                }                
                turma.getProfessor().tutoraTurma(turma);
                this.adicionar(turma);
                
                
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
