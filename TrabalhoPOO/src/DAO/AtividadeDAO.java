/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Atividade;
import Pojo.Disciplina;
import Pojo.Professor;
import Pojo.Turma;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author veccher
 */
public class AtividadeDAO {
    private ArrayList<Atividade> listaAtividade = new ArrayList<Atividade>();
    //adiciona uma atividade na lista
    public void adicionar (Atividade atividade){
        listaAtividade.add(atividade);
        Collections.sort(listaAtividade);
    }
    //exclui uma atividade da lista
    public boolean excluiAtividade (String nome){
        {
        Atividade aux = new Atividade(nome);
        return this.listaAtividade.remove(aux);
        }
    }
    //busca uma atividade na lista
    public Atividade buscaAtividade(String nome, Turma turma){
        Atividade aux = new Atividade();
        aux.setTurma(turma);
        aux.setNome(nome);
        
        for(Atividade atividade : listaAtividade){
            if(atividade.equals(aux)){
                return atividade;
            }
        }
        
        return null;
        
    }
    /*escreve no arquivo "Atividades.txt" todas as informações contidas na lista,
    a ordem é: nome, tipo, data, peso, disciplina, e turma, nesse caso salvamos
    apenas o nome da disciplina e id da turma, e depois buscamos a atividade
    e a turma com base nessas informações*/
    public void escreverArquivo(){
       
        try {
        
            FileWriter fw = new FileWriter("Atividades.txt",false);
            PrintWriter saida = new PrintWriter(fw,true);
            for(Atividade atividade : this.listaAtividade){
                saida.println(atividade.getNome());
                saida.println(atividade.getTipo());
                DateFormat formatter = new SimpleDateFormat("MM/dd/yy");  
                saida.println(formatter.format(atividade.getData()));
                saida.println(atividade.getPeso());
                saida.println(atividade.getTurma().getDisciplina().getNome());
                saida.println(atividade.getTurma().getIdTurma());
                
            }    
            
            saida.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*le as informações do arquivo com base nas especificações da função de escrita*/
    public void lerArquivo(TurmaDAO turmaDAO, DisciplinaDAO disciplinaDAO) throws ParseException{
        
        FileReader fileR;
        BufferedReader buff;
        try {        
            fileR = new FileReader("Atividades.txt");
            buff = new BufferedReader(fileR);
            while(buff.ready()){
                Atividade atividade = new Atividade();
                atividade.setNome(buff.readLine());
                atividade.setTipo(buff.readLine());
                DateFormat formatter = new SimpleDateFormat("MM/dd/yy");  
                atividade.setData(formatter.parse(buff.readLine()));
                atividade.setPeso(Float.parseFloat(buff.readLine()));
                atividade.setTurma(turmaDAO.buscaTurma(disciplinaDAO.buscaDisciplina(buff.readLine())
                        , Integer.parseInt(buff.readLine())));
                atividade.getTurma().addAtividade(atividade);
                
                this.adicionar(atividade);
            }
            buff.close();
            fileR.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }            
    }
    
    public ArrayList<Atividade> getListaAtividade(){
        return this.listaAtividade;
    }

    
}
