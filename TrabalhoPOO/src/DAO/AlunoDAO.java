/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Aluno;
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

public class AlunoDAO {
    private ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
    //adiciona e ordena um aluno na lista
    public void adicionar(Aluno aluno){
        listaAluno.add(aluno);
        Collections.sort(listaAluno);
    }
    //exclui um aluno da lista
    public boolean excluiAluno(Integer cpf){
        Aluno aux=this.buscaAluno(cpf);
        return this.listaAluno.remove(aux);
        
        
   }
    //busca um aluno baseado no cpf (parametro usado no equals)
    public Aluno buscaAluno(Integer cpf){
        Aluno aux = new Aluno();
        aux.setCpf(cpf);
        for(Aluno aluno : listaAluno){
            if(aluno.equals(aux)){
                return aluno;
            }
        }
        return null;
    }
    //retorna a lista de alunos
    public ArrayList<Aluno> getListaAluno(){
        return this.listaAluno;
    }
    /*salva todos os alunos da lista em um arquivo "Alunos.txt" na seguinte
    ordem:nome->cpf->*volta pro nome (do proximo aluno)*como em um ciclo*/
    public void escreverArquivo(){
       
        try {
        
            FileWriter fw = new FileWriter("Alunos.txt",false);
            PrintWriter saida = new PrintWriter(fw,true);
            for(Aluno aluno : this.listaAluno){
                saida.println(aluno.getNome());
                saida.println(aluno.getCpf());
            }    
            
            saida.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*le o arquivo alunos.txt para carregar as informações ao inicio do programa,
    a leitura é feita considerando a ordem e definições feitas na escrita*/
    public void lerArquivo(){
        
        FileReader fileR;
        BufferedReader buff;
        try {        
            fileR = new FileReader("Alunos.txt");
            buff = new BufferedReader(fileR);
            while(buff.ready()){
                Aluno aluno = new Aluno();
                aluno.setNome(buff.readLine());
                aluno.setCpf(Integer.parseInt(buff.readLine()));
                this.adicionar(aluno);
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
