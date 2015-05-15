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
    
    public void adicionar(Professor professor){
        listaProfessor.add(professor);
        Collections.sort(listaProfessor);
    }
    
    public boolean excluirProfessor(Integer cpf){
        Professor aux = this.buscarProfessor(cpf);
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
}
