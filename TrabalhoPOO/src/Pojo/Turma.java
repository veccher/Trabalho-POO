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
public class Turma implements Comparable<Turma> {
    private Integer idTurma;//identificação da turma exemplo 1 (turma-1)
    private Integer ano;//ano que a disciplina foi/é lecionada
    private Integer periodo;//periodo que a disciplina foi/é lecionada
    private String local;//local (sala/predio/campus) das aulas
    private String horario;//horario que ocorre a aula
    private Integer numVagas;//numero de vagas disponíveis para matricula
    Professor professor;//professor responsavel por lecionar a disciplina
    Disciplina disciplina;//disciplina que a turma pertence
    /*construtor de turma, recebe todos os dados necessarios, e incrementa
    o numero de turmas ja lecionadas por aquele professor*/
    public Turma(){
        
    }
    public Turma (Integer idTurma,Integer ano,Integer periodo, String local, String horario, Integer numVagas, Professor professor, Disciplina disciplina){
        this.idTurma=idTurma;
        this.ano=ano;
        this.periodo=periodo;
        this.local=local;
        this.horario=horario;
        this.numVagas=numVagas;
        this.professor=professor;
        this.disciplina=disciplina;
        professor.incrementaNumDisciplinas();
        disciplina.incrementaNumTurmas();
    }
    //TODO
    //atenção, professor e disciplina podem ser null, discutir depois.
    //implementar turmaDAO
    
    public void setProfessor(Professor professor){
        this.professor=professor;
    }
    public void setDisciplina(Disciplina disciplina){
        this.disciplina=disciplina;
    }
    
    
    @Override
    public boolean equals(Object obj){
        
        if(!(obj instanceof Turma)){
            return false;
        }
        Turma turma = (Turma)obj;
        return (this.idTurma.equals(turma.idTurma) && this.disciplina.equals
        (turma.disciplina))&& this.ano.equals(turma.ano)&&this.periodo.equals
        (turma.periodo);
    }
    
   
    public int compareTo(Turma turma){
        if (!(this.ano==turma.ano)){
            return (this.ano.compareTo(turma.ano));
        }
        if (!(this.periodo==turma.periodo)){
            return (this.periodo.compareTo(turma.periodo));
        }
        if (!this.disciplina.equals(turma.disciplina)){
            return (this.disciplina.compareTo(turma.disciplina));
        }
        return (this.idTurma.compareTo(this.idTurma));    
    }
}
