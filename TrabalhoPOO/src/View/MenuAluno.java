/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import DAO.AlunoDAO;
import DAO.DisciplinaDAO;
import DAO.FaltaDAO;
import DAO.NotaDAO;
import DAO.TurmaDAO;
import Pojo.Aluno;
import Pojo.Atividade;
import Pojo.Disciplina;
import Pojo.Falta;
import Pojo.Nota;
import Pojo.Turma;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vitor
 */
public class MenuAluno {
    public static Aluno login(AlunoDAO alunoDAO){
         Scanner scanner=new Scanner(System.in);
         System.out.println("Digite seu nome");
         String nome=scanner.nextLine();
         System.out.println("Digite seu cpf");
         Integer cpf=Integer.parseInt(scanner.nextLine());
         Aluno aluno = alunoDAO.buscaAluno(cpf);
         if (aluno==null){
             System.out.println("Não há nenhum professor com este cpf cadastrado");
             return null;
         }
         if (!nome.equals(aluno.getNome())){
             System.out.println("Dados inconsistentes");
             return null;
         }
         return aluno;
    }
    public static ArrayList<Aluno> alunosNaTurma (ArrayList<Integer> lisCPF,
                                                    AlunoDAO alunoDAO){
        ArrayList<Aluno> alunosTurma=new ArrayList<Aluno>();
        for (Integer cpf: lisCPF){
            alunosTurma.add(alunoDAO.buscaAluno(cpf));
        }
        return alunosTurma;
        
    }
    /*A função verifica se um aluno foi aprovado em uma disciplina,
    verificando sua nota e falta*/
    public void consultaDisciplina(Aluno aluno, DisciplinaDAO disciplinaDAO,TurmaDAO turmaDAO,
                                   NotaDAO notaDAO, String nomeDisciplina){
        
        Disciplina disciplina = disciplinaDAO.buscaDisciplina(nomeDisciplina);
        if(disciplina==null){
            System.out.println("Disciplina nao encontrada");
        }else{
            
        Turma turma=null;
        ArrayList<Turma> listaTurma = turmaDAO.getListaTurma();
        for(Turma aux : listaTurma){
            if(aux.getDisciplina().equals(disciplina) && aux.getListaAlunos().contains(aux)==true){
                turma = aux;
            }
        }
        if(turma==null){
            System.out.println("Não foi encontrada nenhuma turma desta disciplina com"
                               + "matricula deste aluno");
        }else{
            float pesoTotal=0;
            float notaFinal=0;
            for (Atividade atividade:turma.getListaAtividades()){
                Nota nota=null;
                for(Nota notaAux:atividade.getListaNota()){
                    if (notaAux.getAluno().equals(aluno)){
                        nota=notaAux;
                        break;
                    }    
                }
                if (nota==null){
                    System.out.println("Nota não encontrada");
                    break;
                }        
                pesoTotal+=atividade.getPeso();
                notaFinal+=atividade.getPeso()* nota.getNota();
            }
            notaFinal = notaFinal/pesoTotal;//notal final
            System.out.println("Nota Final:"+notaFinal);  
            Falta falta=null;
            for (Falta faltaAux:turma.getListaFaltas()){
                if (faltaAux.getAluno().equals(aluno)){
                    falta=faltaAux;
                    break;
                }
            }
            if (falta==null){
                System.out.println("Não foi encontrado o numero de faltas");
            }
            float relacaoFaltas = falta.getNumFaltas()/disciplina.getChs();//% de faltas
            System.out.println("Numero de Faltas:"+falta.getNumFaltas());    
            if(notaFinal>=6.0&&relacaoFaltas>=0.25){
                System.out.println("Situação : Aprovado!");
            }else{
                System.out.println("Situação : Reprovado!");
            }
            
        }
            
        }
        
    }
    
    
    
    /**Método que imprime o Menu
     
     */
    private void imprimeMenu(){
        System.out.println("-------Escolha uma opcao-----");
        System.out.println("(1)Digite o nome da disciplina a ser consultada");
        System.out.println("(2)Voltar para o menu principal");
        System.out.println("Entre com uma opcao: ");
    }
    
     public void menuOpcao(AlunoDAO alunoDAO, DisciplinaDAO disciplinaDAO, TurmaDAO turmaDAO,
                           NotaDAO notaDAO, FaltaDAO faltaDAO){
         
         Aluno aluno = login(alunoDAO);
         if(aluno!=null){
              String nome;
              Scanner scanner = new Scanner(System.in);
              Scanner scanner2 = new Scanner(System.in);
              int opcao;
             do{
                 imprimeMenu();
                 opcao =Integer.parseInt(scanner2.nextLine());
                 switch(opcao){
                     case 1:
                         System.out.println("Digite o nome da Disciplina"); 
                         nome = scanner2.nextLine();
                         consultaDisciplina(aluno,disciplinaDAO, turmaDAO, notaDAO, nome);
                         
                         
                        
                         break;
                     case 2:
                         break;
                     default:
                    System.out.println("Opção invalida!!");
                 }  
              }while(opcao!=2);
                
        }
         }
         
         
     }
    

