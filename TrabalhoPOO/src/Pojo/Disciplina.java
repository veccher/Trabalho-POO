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
public class Disciplina {
    private String nome;//nome da disciplina
    private String ementa;//ementa da disciplina
    private Integer chs;//carga horaria da disciplina
    private Integer numTurmas;//numero de turmas historicamente ja oferecidas
    
    //incrementa numero de turmas ja oferecidas
    public void incrementaNumTurmas(){
        this.numTurmas++;
    }
    //retorna o numero de turmas historicamente ja oferecidas
    public Integer getNumTurmas(){
        return this.numTurmas;
    }
    //cria nova disciplina com todos os dados exceto numTurmas que é 0 ao criar
    public Disciplina (String nome,String ementa,Integer chs){
        this.nome=nome;
        this.ementa=ementa;
        this.chs=chs;
        this.numTurmas=0;
    }
}
