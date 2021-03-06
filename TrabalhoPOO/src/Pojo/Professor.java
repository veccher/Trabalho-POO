/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pojo;

import DAO.TurmaDAO;
import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class Professor extends Pessoa implements Comparable<Professor>{
    private String departamento; //departamento ao qual o prof pertence
    private ArrayList<Turma> turmasTutoradas;//turmas que o prof leciona
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
        this.turmasTutoradas= new ArrayList<Turma>();
    }
    public Professor (String nome,Integer cpf,String departamento,Integer numDisciplinas){
        super (nome,cpf);
        this.departamento=departamento;
        this.numeroDisciplinas=numDisciplinas;
        this.turmasTutoradas= new ArrayList<Turma>();
    }
    //retorna o numero de disciplinas ja lecionadas pelo professor
    public Integer getNumeroDisciplinas(){
        return this.numeroDisciplinas;
    }/*função deve ser chamada cada vez que um professor for cadastrado numa
    nova turma*/
    public String getDepartamento(){
        return this.departamento;
    }
    public void setDepartamento(String departamento){
        this.departamento=departamento;
    }
    public void incrementaNumDisciplinas(){
        this.numeroDisciplinas++;
    }
    public ArrayList<Turma> getTurmasTutoradas(){
        return this.turmasTutoradas;
    }
    public void tutoraTurma (Turma turma){
        this.turmasTutoradas.add(turma);
    }
    public String ToString(){
        return "Nome: "+this.getNome()+"\n"+"Cpf: "+this.getCpf()+"\n"+"Departamento: "
                + this.departamento+"\n"+"Numero de Disciplinas Lecionadas: "+
                this.numeroDisciplinas;
    }
    
    @Override
    public boolean equals(Object obj){
        
        if(!(obj instanceof Professor)){
            return false;
        }
        Professor professor = (Professor)obj;
        return (this.getCpf().equals(professor.getCpf()));
    }
    
   
    public int compareTo(Professor professor){
        if(this.getNome().compareTo(professor.getNome())==0){
            return 0;
        }else if(this.getNome().compareTo(professor.getNome())<0){
            return -1;
        }else
            return 1;           
    }
   
}
