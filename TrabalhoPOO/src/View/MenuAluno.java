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
    public void consultaDisciplina(Aluno aluno, DisciplinaDAO disciplinaDAO,TurmaDAO turmaDAO,
                                   NotaDAO notaDAO, String nomeDisciplina){
        
        Disciplina disciplina = disciplinaDAO.buscaDisciplina(nomeDisciplina);
        if(disciplina==null){
            System.out.println("Disciplina nao encontrada");
        }else{
            
        Turma turma=null;
        ArrayList<Turma> listaTurma = turmaDAO.getListaTurma();
        for(Turma aux : listaTurma){
            if(aux.getDisciplina().equals(disciplina)){
                turma = aux;
            }
        }
        if(turma==null){
            System.out.println("Nao foi encontrada nenhuma turma com tal disciplina");
        }else{
            int posicaoAluno;
            float pesoTotal=0;
            float notaFinal=0;
            posicaoAluno = turma.getListaAlunos().getListaAluno().indexOf(aluno);
            if(posicaoAluno==-1){
                System.out.println("Não Consta tal aluno na turma");
            }else{
                
                ArrayList<Atividade> listaAtividade = turma.getListaAtividades().getListaAtividade();
                for(Atividade atividade : listaAtividade){
                    ArrayList<Nota> notas = atividade.getListaNota().getListaNota();
                    Nota nota = notas.get(posicaoAluno);
                    pesoTotal+=atividade.getPeso();
                    notaFinal+=atividade.getPeso()* nota.getNota();
                }
                notaFinal = notaFinal/pesoTotal;//notal final
                System.out.println("Nota Final:"+notaFinal);
            
                ArrayList<Falta> listaFalta = turma.getListaFaltas().getListaFalta();
                Falta falta = listaFalta.get(posicaoAluno);
            
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
    

