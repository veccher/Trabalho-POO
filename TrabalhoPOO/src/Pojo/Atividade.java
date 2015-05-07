/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.util.Date;

/**
 *
 * @author veccher
 */
public class Atividade implements Comparable<Atividade>{
    private String nome;//nome da atividade ex.: prova1, trabalho de arvores
    private String tipo;//tipo da atividade ex.: prova, trabalho, seminario
    private Date data;//data da atividade
    private Float peso;//importancia da nota, o valor será sempre de 0 a 10
    private Turma turma;//valor da nota maxima na atividade
    //falta a lista de notas
    
    public Float getPeso(){
        return this.peso;
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
    }
    public Atividade (String nome, Turma turma){
        this.nome=nome;
        this.turma=turma;
    }
    /*a comparação entre 2 classes é baseada primariamente na turmae no nome 
    para criterio de desempate*/
    
    @Override
    public int compareTo (Atividade atividade){
        if (!(this.turma.equals(atividade.turma))){
            return this.turma.compareTo(atividade.turma);
        }
        return this.nome.compareTo(atividade.nome);
    }
    public boolean equals (Atividade atividade){
        return(this.nome.equals(atividade.nome) && this.turma.equals(atividade.turma));
    }
}
