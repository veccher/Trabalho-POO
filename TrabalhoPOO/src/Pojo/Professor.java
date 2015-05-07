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
public class Professor extends Pessoa implements Comparable<Professor>{
    private String departamento; //departamento ao qual o prof pertence
    private Integer numeroDisciplinas;/*numero de disciplinas lecionadas 
    historicamente pelo professor*/
    
    /*construtor de professor, ao cadastrar um professor, o número de 
    disciplinas ja lecionadas por ele é definido como zero*/
    public Professor(){
        
    }
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
    
    @Override
    public boolean equals(Object obj){
        
        if(!(obj instanceof Professor)){
            return false;
        }
        Professor professor = (Professor)obj;
        return (this.cpf.equals(professor.cpf));
    }
    
   
    public int compareTo(Professor professor){
        if(this.nome.compareTo(professor.nome)==0){
            return 0;
        }else if(this.nome.compareTo(professor.nome)<0){
            return -1;
        }else
            return 1;           
    }
   
}
