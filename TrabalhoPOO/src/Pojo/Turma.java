/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import DAO.AlunoDAO;
import DAO.AtividadeDAO;
import DAO.FaltaDAO;
import java.util.ArrayList;

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
    private ArrayList<Aluno> listaAluno;//lista de cpf dos alunos da turma
    private ArrayList<Atividade> listaAtividades;//lista de todas as atividades
    private ArrayList<Falta> listaDeFaltas;//lista de faltas de cada aluno
    
    /*construtor de turma, recebe todos os dados necessarios, e incrementa
    o numero de turmas ja lecionadas por aquele professor e n de turmas ja
    criadas*/
    public Turma(){
        
    }
    public Turma(Integer idTurma, Disciplina disciplina){
        this.idTurma=idTurma;
        this.disciplina=disciplina;
    }
    /*construtor padrão, o id da turma é obtido automaticamente a partir do
    numero de turmas ja criados daquela disciplina*/
    public Turma (Integer ano,Integer periodo, String local,
                  String horario, Integer numVagas, Professor professor, 
                  Disciplina disciplina){
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
        listaAluno=new ArrayList<Aluno>();
        listaAtividades=new ArrayList<Atividade>();
        listaDeFaltas=new ArrayList<Falta>();
    }
    /*Construtor exclusivo para o metodo de leitura em DAO, o id Turma aqui
    é passado como argumento, diferente do construtor padrão*/
    public Turma (Integer idTurma,Integer ano,Integer periodo, String local,
                  String horario, Integer numVagas, Professor professor, 
                  Disciplina disciplina){
        this.idTurma=idTurma;
        this.ano=ano;
        this.periodo=periodo;
        this.local=local;
        this.horario=horario;
        this.numVagas=numVagas;
        this.professor=professor;
        this.disciplina=disciplina;
        listaAluno=new ArrayList<Aluno>();
        listaAtividades=new ArrayList<Atividade>();
        listaDeFaltas=new ArrayList<Falta>();
    }
    public Integer getIdTurma(){
        return this.idTurma;
    }
    public Integer getAno(){
        return this.ano;
    }
    public Integer getPeriodo(){
        return this.periodo;
    }
    public String getLocal(){
        return this.local;
    }
    public String getHorario(){
        return this.horario;
    }
    public Integer getNumVagas(){
        return this.numVagas;
    }
    public Professor getProfessor(){
        return this.professor;
    }
    public Disciplina getDisciplina(){
        return this.disciplina;
    }
    public ArrayList<Aluno> getListaAlunos(){
        return this.listaAluno;
    }
    public ArrayList<Falta> getListaFaltas(){
        return this.listaDeFaltas;
    }
    public ArrayList<Atividade> getListaAtividades(){
        return this.listaAtividades;
    }
    public void setListaDeFaltas(ArrayList<Falta> lisFaltas){
        this.listaDeFaltas=lisFaltas;
    }
    public void addAtividade(Atividade atividade ){
        this.listaAtividades.add(atividade);
    }
    public void addFalta(Falta falta){
        this.listaDeFaltas.add(falta);
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
    /*adiciona um aluno na turma*/
    public boolean matriculaAluno (Aluno aluno){
        if (aluno==null){
            return false;
        }
        this.listaAluno.add(aluno);
        return true;
    }
    public int compareTo(Turma turma){
        if (!this.disciplina.equals(turma.disciplina)){
            return (this.disciplina.compareTo(turma.disciplina));
        }
        return (this.idTurma.compareTo(this.idTurma));    
    }

    /*public Object getDisciplina() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
