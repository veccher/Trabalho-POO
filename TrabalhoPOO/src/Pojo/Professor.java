/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pojo;

/**
 *
 * @author Vitor
 */
public class Professor extends Pessoa{
    private String departamento; //departamento ao qual o prof pertence
    private Integer numeroDisciplinas;/*numero de disciplinas lecionadas 
    historicamente pelo professor*/
    
    /*construtor de professor, ao cadastrar um professor, o número de 
    disciplinas ja lecionadas por ele é definido como zero*/
    public Professor (String nome,Integer cpf,String departamento){
        super (nome,cpf);
        this.departamento=departamento;
        this.numeroDisciplinas=0;
    }
    //retorna o numero de disciplinas ja lecionadas pelo professor
    public Integer getNumeroDisciplinas(){
        return this.numeroDisciplinas;
    }/*função deve ser chamada cada vez que um professor for cadastrado numa
    nova turma*/
    public void incrementaNumDisciplinas(){
        this.numeroDisciplinas++;
    }
}
