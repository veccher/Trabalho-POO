/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import DAO.NotaDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author veccher
 */
public class Atividade implements Comparable<Atividade>{
    private String nome;//nome da atividade ex.: prova1, trabalho de arvores
    private String tipo;//tipo da atividade ex.: prova, trabalho, seminario
    private Date data;//data da atividade
    private Float peso;//coeficiente que indica a importancia da nota
    private Turma turma;//valor da nota maxima na atividade
    private ArrayList<Nota> listaNotas = new ArrayList<Nota>();//lista de notas de cada aluno na atividade 
    
    public Float getPeso(){
        return this.peso;
    }
    public String getNome(){
        return this.nome;
    }
    public String getTipo(){
        return this.tipo;
    }
    public Date getData(){
        return this.data;
    }
    public Turma getTurma(){
        return this.turma;
    }
    /*o construtor simplesmente inicializa as variaveis, e faz o chec para
    garantir que o valor da atividade não seja negativo.*/
    public Atividade (String nome,String tipo, Date data, Float peso,Turma turma){
        this.nome=nome;
        this.tipo=tipo;
        this.data=data;
        if (peso>=0){
            this.peso=peso;
        }
        this.turma=turma;
        listaNotas=new ArrayList<Nota>();
    }
    public Atividade (){
        
    }
    public Atividade (String nome){
        this.nome=nome;
    }
    /*a comparação entre 2 classes é baseada primariamente na turmae no nome 
    para criterio de desempate*/
    public ArrayList<Nota> getListaNota(){
        return this.listaNotas;
    }
    public void setListaNotas (ArrayList<Nota> lisNotas){
        this.listaNotas=lisNotas;
    }
    public void addNota(Nota nota){
        this.listaNotas.add(nota);
    }
    public void setPeso(Float peso){
        this.peso=peso;
    }
    public void setTipo(String tipo){
        this.tipo=tipo;
    }
    public void setData(Date data){
        this.data=data;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setTurma(Turma turma){
        this.turma=turma;
    }
    
    @Override
    public int compareTo (Atividade atividade){
        return this.nome.compareTo(atividade.nome);
    }
    public boolean equals (Atividade atividade){
        return(this.nome.equals(atividade.nome)&&this.turma.equals(atividade.turma));
    }
}
