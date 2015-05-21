/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import DAO.AlunoDAO;
import DAO.DisciplinaDAO;
import DAO.ProfessorDAO;
import DAO.TurmaDAO;
import Pojo.Aluno;
import Pojo.Disciplina;
import Pojo.Professor;
import Pojo.Turma;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Vitor
 */
public class MenuAdmin {
    public void cadastroProfessor(ProfessorDAO professorDAO, String nome,
                                       Integer cpf, String departamento){
        if("".equals(nome)||"".equals(departamento)||"".equals(cpf)){
             
         }else{
            Professor novo = new Professor(nome, cpf, departamento);
            professorDAO.adicionar(novo);
               
         }  
        
    }
    public void cadastroAluno(AlunoDAO alunoDAO, Aluno novo){
        
            alunoDAO.adicionar(novo);
             
         
         
        
    }
    public static void matriculaAluno (AlunoDAO alunoDAO,Turma turma){
        Integer cpf;
        do {
            System.out.println("Digite o cpf do aluno a ser matriculado na turma");
            System.out.println("Para terminar o cadastro digite um numero negativo");
            Scanner scanner=new Scanner(System.in);
            cpf=scanner.nextInt();
            if (cpf<0)
                break;
            Aluno aluno=alunoDAO.buscaAluno(cpf);
            if (aluno==null){
                System.out.println("aluno não encontrado");
            }else{
                turma.matriculaAluno(aluno);
            }
        }while(cpf>=0);
        
    }
    public void  cadastroTurma(TurmaDAO turmaDAO, Integer ano,Integer periodo, String local,
                  String horario, Integer numVagas, Professor professor, Disciplina disciplina,
                  AlunoDAO alunoDAO){
         
        if("".equals(ano)||"".equals(periodo)||"".equals(local)||"".equals(horario)
                ||"".equals(numVagas)){
             
         }else{
            Turma novo = new Turma(ano, periodo, local, horario, numVagas,
                                   professor, disciplina);
            turmaDAO.adicionar(novo);
            professor.tutoraTurma(novo);
            matriculaAluno(alunoDAO,novo);
                
         }  
         
        
    }
    public void cadastroDisciplina(DisciplinaDAO disciplinaDAO, String nome,
                                         String ementa, Integer chs){
        
        if("".equals(nome)||"".equals(ementa)||"".equals(chs)){
             
         }else{
            Disciplina novo = new Disciplina(nome, ementa, chs);
            disciplinaDAO.adicionar(novo);
                
         }  
         
        
    }
    
    
    /**Método que imprime o Menu
     
     */
    private void imprimeMenu(){
        System.out.println("-------Escolha uma opcao-----");
        System.out.println("(1)Cadastrar Professor");
        System.out.println("(2)Cadastrar Aluno");
        System.out.println("(3)Cadastrar Turma");
        System.out.println("(4)Cadastrar Disciplina");
        System.out.println("(5)Voltar para o menu principal");
        System.out.println("Entre com uma opcao: ");
    }
    
 
    public void menuOpcao(ProfessorDAO professorDAO, AlunoDAO alunoDAO, 
                          TurmaDAO turmaDAO, DisciplinaDAO disciplinaDAO){
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        int opcao;

        do{
            imprimeMenu();
            Aluno novo = new Aluno();
            opcao = scanner2.nextInt();
            String nome=null,departamento =null, local = null,horario= null, ementa =null; 
            Integer cpf=null, ano=null, periodo=null, numVagas=null, chs=null ;
            switch(opcao){
                case 1:
                    
                    System.out.println();
                    System.out.println("Entre com o nome do Professor: ");
                    nome = scanner.nextLine();
                    System.out.println();
                    System.out.println("Entre com o cpf do Professor:");
                    cpf = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    System.out.println("Entre com o departamento do Professor:");
                    departamento = scanner.nextLine();
                    
                    this.cadastroProfessor(professorDAO, nome, cpf, departamento);
                    
                    break;
                case 2:
                    
                    System.out.println();
                    System.out.println("Entre com o nome do Aluno: ");
                    novo.setNome(scanner.nextLine());
                    System.out.println();
                    System.out.println("Entre com o cpf do Aluno:");
                    novo.setCpf(Integer.parseInt(scanner.nextLine()));
                    this.cadastroAluno(alunoDAO,novo);
                   
                 
                    break;
                case 3:
                    Professor professor;
                    Disciplina disciplina;
                    System.out.println();
                    System.out.println("Entre com o cpf do Professor que"
                            + " lecionara para turma: ");
                    cpf = Integer.parseInt(scanner.nextLine());
                    professor = professorDAO.buscarProfessor(cpf);
                   
                    System.out.println();
                    System.out.println("Entre com o nome da Disciplina da Turma:");
                    nome = scanner.nextLine();
                    disciplina = disciplinaDAO.buscaDisciplina(nome);
                    if(disciplina!=null&&professor!=null){
                        System.out.println();
                        System.out.println("Entre com o ano da Turma:");
                        ano = Integer.parseInt(scanner.nextLine());
                    
                        System.out.println();
                        System.out.println("Entre com o periodo da Turma:");
                        periodo = Integer.parseInt(scanner.nextLine());
                    
                        System.out.println();
                        System.out.println("Entre com o local da Turma:");
                        local = scanner.nextLine();
                    
                        System.out.println();
                        System.out.println("Entre com o horario da Turma:");
                        horario = scanner.nextLine();
                    
                        System.out.println();
                        System.out.println("Entre com o numero de vagas da Turma:");
                        numVagas = Integer.parseInt(scanner.nextLine());
                    
                        this.cadastroTurma(turmaDAO, ano, periodo, local, horario,
                            numVagas, professor, disciplina, alunoDAO);
                    }else{
                        if(disciplina==null){
                            System.out.println("Disciplina nao encontrada, turma nao sera cadastrada");
                        }
                        if(professor==null){
                            System.out.println("Professor nao encontrado, turma nao sera cadastrada");
                        }
                            
                    }
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Entre com o nome da Disciplina:");
                    nome = scanner.nextLine();
                    
                    System.out.println();
                    System.out.println("Entre com a ementa da Disciplina:");
                    ementa = scanner.nextLine();
                    
                    System.out.println();
                    System.out.println("Entre com a carga horaria da Disciplina:");
                    chs = Integer.parseInt(scanner.nextLine());
                    
                    this.cadastroDisciplina(disciplinaDAO, nome, ementa, chs);
                    
                    
                    break;
                case 5://sair do menu 
                    break;
                default:
                    System.out.println("Opcao invalida!!");
            }
        }while(opcao!=5);
    }
    
}
