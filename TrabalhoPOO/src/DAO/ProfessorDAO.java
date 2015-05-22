/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Professor;
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
public class ProfessorDAO {
     private ArrayList<Professor> listaProfessor = new ArrayList<Professor>();
    //adiciona um professor na lista e ordena
    public void adicionar(Professor professor){
        listaProfessor.add(professor);
        Collections.sort(listaProfessor);
    }
    //exclui um professor da lista
    public boolean excluirProfessor(Integer cpf){
        Professor aux = this.buscarProfessor(cpf);
        return this.listaProfessor.remove(aux);
        
   }//busca um professor na lista
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
    /*escreve no arquivo "Professores.txt" todas as informações contidas na lista
    na seguinte ordem: nome, cpf, departamento e numero de disciplinas, a variavel
    turmas tutoradas é automaticamente preenchida posteriormente ao criar novas
    turmas nas outras funções*/
    public void escreverArquivo(){
       
        try {
        
            FileWriter fw = new FileWriter("Professores.txt",false);
            PrintWriter saida = new PrintWriter(fw,true);
            for(Professor professor : this.listaProfessor){
                saida.println(professor.getNome());
                saida.println(professor.getCpf());
                saida.println(professor.getDepartamento());
                saida.println(professor.getNumeroDisciplinas());
            }    
            
            saida.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*le do arquivo as informações com base nas especificações da função de escrita,
    a função de leitura usa um construtor especial por causa da variavel do
    numero de turmas ja lecionadas por ele*/
    public void lerArquivo(){
        
        FileReader fileR;
        BufferedReader buff;
        try {        
            fileR = new FileReader("Professores.txt");
            buff = new BufferedReader(fileR);
            while(buff.ready()){
                Professor professor = new Professor(buff.readLine(),
                                      Integer.parseInt(buff.readLine()),
                                      buff.readLine(),
                                      Integer.parseInt(buff.readLine()));
                this.adicionar(professor);
            }
            buff.close();
            fileR.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }            
    }
    
    public ArrayList<Professor> getListaProfessor(){
        return this.listaProfessor;
    }
}
