/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author veccher
 */

public class Nota {
    private Float nota;
    private Aluno aluno;
    private Atividade atividade;
    
    /*o objetivo da classe é representar a nota de um aluno em uma determinada
    atividade, um aluno pode ver sua nota usando "getNota" e um professor pode
    alterar a nota do aluno após uma revisão com "setNota"*/
    public Float getNota(){
        return this.nota;
    }
    public void setNota(Float novaNota){
        if (novaNota>=0 && novaNota<=10){
            this.nota=novaNota;
        }
    }
    public Nota (Float nota,Aluno aluno,Atividade atividade){
        this.setNota(nota);
        this.aluno=aluno;
        this.atividade=atividade;
    }
}
