/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import DAO.AlunoDAO;
import DAO.AtividadeDAO;
import DAO.FaltaDAO;

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
    private Professor professor;//professor responsavel por lecionar a disciplina
    private Disciplina disciplina;//disciplina que a turma pertence
    private AlunoDAO listaDeMatricula;//lista de alunos na turma
    private AtividadeDAO listaAtividades;//lista de todas as atividades
    private FaltaDAO listaDeFaltas;//lista de faltas de cada aluno
    
    /*construtor de turma, recebe todos os dados necessarios, e incrementa
    o numero de turmas ja lecionadas por aquele professor e n de turmas ja
    criadas*/
    public Turma(){
        
    }
    public Turma(Integer idTurma, Disciplina disciplina){
        this.idTurma=idTurma;
        this.disciplina=disciplina;
    }
    public Turma (Integer ano,Integer periodo, String local,
                  String horario, Integer numVagas, Professor professor, Disciplina disciplina){
        this.idTurma=disciplina.getNumTurmas();
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
    @Override
    public boolean equals(Object obj){
        
        if(!(obj instanceof Turma)){
            return false;
        }
        Turma turma = (Turma)obj;
        return (this.idTurma.equals(turma.idTurma) && this.disciplina.equals
               (turma.disciplina));
    }
    
    
    public int compareTo(Turma turma){
        if (!this.disciplina.equals(turma.disciplina)){
            return (this.disciplina.compareTo(turma.disciplina));
        }
        return (this.idTurma.compareTo(this.idTurma));    
    }
}
