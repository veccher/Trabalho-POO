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
    public void CadastroProfessor(ProfessorDAO professorDAO, String nome,
                                       Integer cpf, String departamento){
        if("".equals(nome)||"".equals(departamento)||"".equals(cpf)){
             
         }else{
            Professor novo = new Professor(nome, cpf, departamento);
            professorDAO.adicionar(novo);
               
         }  
        
    }
    public void CadastroAluno(AlunoDAO alunoDAO, String nome, Integer cpf){
        
        if("".equals(nome)||"".equals(cpf)){
             
         }else{
            Aluno novo = new Aluno(nome, cpf);
            alunoDAO.adicionar(novo);
             
         }  
         
        
    }
    public void  CadastroTurma(TurmaDAO turmaDAO, Integer ano,Integer periodo, String local,
                  String horario, Integer numVagas, Professor professor, Disciplina disciplina){
         
        if("".equals(ano)||"".equals(periodo)||"".equals(local)||"".equals(horario)
                ||"".equals(numVagas)){
             
         }else{
            Turma novo = new Turma(ano, periodo, local, horario, numVagas,
                                   professor, disciplina);
            turmaDAO.adicionar(novo);
                
         }  
         
        
    }
    public void CadastroDisciplina(DisciplinaDAO disciplinaDAO, String nome,
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
        byte opcao,i;

        do{
            imprimeMenu();
            opcao = Byte.parseByte(scanner.nextLine());
            String nome=null,departamento =null, local = null,horario= null, ementa =null; 
            Integer cpf=null, ano=null, periodo=null, numVagas=null, chs=null ;
            switch(opcao){
                case 1:
                    System.out.println();
                    System.out.println("Entre com o nome do Professor: ");
                    nome = scanner.nextLine();
                    System.out.println();
                    System.out.println("Entre com o cpf do Professor:");
                    cpf = scanner.nextInt();
                    System.out.println();
                    System.out.println("Entre com o departamento do Professor:");
                    departamento = scanner.nextLine();
                    
                    this.CadastroProfessor(professorDAO, nome, cpf, departamento);
                    
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Entre com o nome do Aluno: ");
                    nome = scanner.nextLine();
                    System.out.println();
                    System.out.println("Entre com o cpf do Aluno:");
                    cpf = scanner.nextInt();
                    
                    this.CadastroAluno(alunoDAO, nome, cpf);
                   
                 
                    break;
                case 3:
                    Professor professor;
                    Disciplina disciplina;
                    System.out.println();
                    System.out.println("Entre com o cpf do Professor que"
                            + " lecionara para turma: ");
                    cpf = scanner.nextInt();
                    professor = professorDAO.buscarProfessor(cpf);
                   
                    System.out.println();
                    System.out.println("Entre com o nome da Disciplina da Turma:");
                    nome = scanner.nextLine();
                    disciplina = disciplinaDAO.buscaDisciplina(nome);
                    
                    System.out.println();
                    System.out.println("Entre com o ano da Turma:");
                    ano = scanner.nextInt();
                    
                    System.out.println();
                    System.out.println("Entre com o periodo da Turma:");
                    periodo = scanner.nextInt();
                    
                    System.out.println();
                    System.out.println("Entre com o local da Turma:");
                    local = scanner.nextLine();
                    
                    System.out.println();
                    System.out.println("Entre com o horario da Turma:");
                    horario = scanner.nextLine();
                    
                    System.out.println();
                    System.out.println("Entre com o numero de vagas da Turma:");
                    numVagas = scanner.nextInt();
                    
                    this.CadastroTurma(turmaDAO, ano, periodo, local, horario,
                            numVagas, professor, disciplina);
                 
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
                    chs = scanner.nextInt();
                    
                    this.CadastroDisciplina(disciplinaDAO, nome, ementa, chs);
                    
                    
                    break;
                case 5://sair do menu 
                    break;
                default:
                    System.out.println("Opcao invalida!!");
            }
        }while(opcao!=5);
    }
    
}
