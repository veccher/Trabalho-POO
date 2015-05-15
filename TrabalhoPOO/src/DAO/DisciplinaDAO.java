/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import Pojo.Disciplina;
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
public class DisciplinaDAO {
     private ArrayList<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
    
    public void adicionar(Disciplina disciplina){
        listaDisciplina.add(disciplina);
        Collections.sort(listaDisciplina);
    }
    
    public boolean excluiDisciplina(String nomeDel){
        Disciplina aux = new Disciplina();
        aux.setNome(nomeDel);
        return this.listaDisciplina.remove(aux);
        
   }
    public Disciplina buscaDisciplina(String nome){
        Disciplina aux = new Disciplina();
        aux.setNome(nome);
        for(Disciplina disciplina : listaDisciplina){
            if(disciplina.equals(aux)){
                return disciplina;
            }
        }
        return null;
    }
    public void escreverArquivo(){
       
        try {
        
            FileWriter fw = new FileWriter("Disciplinas.txt",false);
            PrintWriter saida = new PrintWriter(fw,true);
            for(Disciplina disciplina : this.listaDisciplina){
                saida.println(disciplina.getNome());
                saida.println(disciplina.getEmenta());
                saida.println(disciplina.getChs());
                saida.println(disciplina.getNumTurmas());
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
            fileR = new FileReader("Disciplinas.txt");
            buff = new BufferedReader(fileR);
            while(buff.ready()){
                Disciplina disciplina = new Disciplina(buff.readLine(),
                                        buff.readLine(),
                                        Integer.parseInt(buff.readLine()),
                                        Integer.parseInt(buff.readLine()));
                this.adicionar(disciplina);
            }
            buff.close();
            fileR.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }            
    }
    
    public ArrayList<Disciplina> getListaDisciplina(){
        return this.listaDisciplina;
    }
}
