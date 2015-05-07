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
public class Falta implements Comparable<Falta>{
    private Integer numFaltas;
    private Aluno aluno;
    private Turma turma;
    
    /*o objetivo da classe é armazenar a informação do numero de faltass de
    um aluno em uma determinada turma*/
    
    //um aluno pode ver quantas faltas ele tem na turma
    public Integer getNumFaltas (){
        return this.numFaltas;
    }
    /*um professor pode adicionar/modificar o n de faltas de um aluno, desde
    que não seja negativo*/
    public void setNumFaltas (Integer numFaltas){
        if (numFaltas>=0){
            this.numFaltas=numFaltas;
        }
    }
    public Falta (Integer numFaltas,Aluno aluno, Turma turma){
        this.setNumFaltas(numFaltas);
        this.aluno=aluno;
        this.turma=turma;
    }
    @Override
    //compareTo vai ser usado pra ordenação e vai ser baseado apenas em aluno
    public int compareTo(Falta falta){
        return this.aluno.compareTo (falta.aluno);
    }
    public boolean equals (Falta falta){
        return (this.aluno.equals(falta.aluno) && this.turma.equals(falta.turma));
    }
    
}
