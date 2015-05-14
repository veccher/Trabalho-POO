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
import DAO.ProfessorDAO;
import DAO.TurmaDAO;
import Pojo.Aluno;
import Pojo.Atividade;
import Pojo.Disciplina;
import Pojo.Falta;
import Pojo.Nota;
import Pojo.Professor;
import Pojo.Turma;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author Vitor
 */
public class MenuProfessor {
    /*essa função será responsavel pelo log in, ela pede o cpf do professor
    e o nome, isso foi definido por nós para garantir que o acesso não seja 
    livre para qualquer pessoa ja que não foi exigido sistema com senha, após
    o log in todas as funções da classe darão informações baseadas naquele profes-
    sor*/
     public static Professor login(ProfessorDAO professorDAO){
         Scanner scanner=new Scanner(System.in);
         System.out.println("Digite seu nome");
         String nome=scanner.nextLine();
         System.out.println("Digite seu cpf");
         Integer cpf=parseInt(scanner.nextLine());
         Professor professor=professorDAO.buscarProfessor(cpf);
         if (professor==null){
             System.out.println("Não há nenhum professor com este cpf cadastrado");
             return null;
         }
         if (!nome.equals(professor.getNome())){
             System.out.println("Dados inconsistentes");
             return null;
         }
         return professor;
     }
     public static Turma getTurmaDoProfessor (Professor professor,DisciplinaDAO
                                              disciplinaDAO){
         Scanner scanner=new Scanner(System.in);
         Turma turma;
         Disciplina disciplina;
         if (professor==null || disciplinaDAO==null)
             return null;
         do{
             System.out.println("Digite o nome da disciplina da turma");
             String nome=scanner.nextLine();
             disciplina=disciplinaDAO.buscaDisciplina(nome);
         }while(disciplina==null);
         do{
             System.out.println("digite a id da turma");
             Integer idTurma=parseInt(scanner.nextLine());
             turma=professor.getTurmasTutoradas().buscaTurma(disciplina, idTurma);
         }while(turma==null);
         return turma;
     }
     // a função vai criar uma nova atividade para uma Turma
     public static boolean adicionaAtividade (Professor professor,
                                              DisciplinaDAO disciplinaDAO){
         Scanner scanner=new Scanner(System.in);
         String nome;
         Turma turma=getTurmaDoProfessor(professor,disciplinaDAO);
         do{
             System.out.println("Digite o nome da atividade, certifique-se de que não"
                            + "haja outra atividade com o mesmo nome cadastrado"
                            + "nessa turma");
             nome=scanner.nextLine();
         }while (turma.getListaAtividades().buscaAtividade(nome)!=null);
         System.out.println("Digite o tipo da atividade");
         String tipo=scanner.nextLine();
         System.out.println("digite o dia de entrega da atividade");
         Integer dia=parseInt(scanner.nextLine());
         System.out.println("digite o mes de entrega da atividade");
         Integer mes=parseInt(scanner.nextLine());
         Date data=new Date(turma.getAno(),mes,dia);
         System.out.println("Digite o valor do peso avaliativo da atividade");
         Float peso=scanner.nextFloat();
         Atividade atividade=new Atividade(nome,tipo,data,peso,turma);
         turma.getListaAtividades().adicionar(atividade);
         return true;
     }
     //essa função vai lançar as notas de todos os alunos para uma determinada atividade
     public static void lancaNotas (Professor professor,DisciplinaDAO disciplinaDAO
                                    ,AlunoDAO alunoDAO){
         Turma turma=getTurmaDoProfessor(professor,disciplinaDAO);
         Scanner scanner=new Scanner(System.in);
         Atividade atividade;
         NotaDAO notaDAO=new NotaDAO();
         do{
             System.out.println("Digite o nome da atividade que deseja lançar notas");
             String nome=scanner.nextLine();
             atividade=turma.getListaAtividades().buscaAtividade(nome);
         }while (atividade==null);
         for (Aluno aluno : MenuAluno.alunosNaTurma(turma.getListaAlunos(), alunoDAO)){
             System.out.println("Digite a nota do aluno "+aluno.getNome());
             Float notaRecebida=scanner.nextFloat();
             Nota nota=new Nota(notaRecebida,aluno,atividade);
             notaDAO.adicionar(nota);
         }
         atividade.setListaNotas(notaDAO);
     }
     //lança o numero total de faltas dos alunos de uma turma;
     public static void lancaFaltas (Professor professor,DisciplinaDAO disciplinaDAO,
                                    AlunoDAO alunoDAO){
         Turma turma=getTurmaDoProfessor(professor,disciplinaDAO);
         Scanner scanner=new Scanner(System.in);
         FaltaDAO faltaDAO=new FaltaDAO();
         for (Aluno aluno : MenuAluno.alunosNaTurma(turma.getListaAlunos(), alunoDAO)){
             System.out.println("Digite o numero de faltas do aluno "+aluno.getNome());
             Integer numFaltas=parseInt(scanner.nextLine());
             Falta falta=new Falta(numFaltas,aluno,turma);
             faltaDAO.adicionar(falta);
         }
         turma.setListaDeFaltas(faltaDAO);
     }
     public static Integer getAcao(){
         System.out.println("Digite 1 para criar uma atividade");
         System.out.println("Digite 2 para lançar notas de uma atividade");
         System.out.println("Digite 3 para lançar faltas de uma turma");
         System.out.println("Digite 4 para sair");
         Scanner scanner=new Scanner(System.in);
         Integer res;
         while(true){
             res=parseInt(scanner.nextLine());
             if(res>4 || res<1)
                 System.out.println("numero invalido");
             else
                 break;
         }
         return res;
     }
     public void menuOpcao(ProfessorDAO professorDAO,DisciplinaDAO disciplinaDAO,
             AlunoDAO alunoDAO){
         Professor professor=login(professorDAO);
         Integer opcao=getAcao();
         do{
            switch (opcao){
                case 1: adicionaAtividade(professor,disciplinaDAO);break;
                case 2: lancaNotas(professor, disciplinaDAO,alunoDAO);break;
                case 3: lancaFaltas(professor, disciplinaDAO,alunoDAO);break;
                case 4:break;
            } 
         }while (opcao!=4);
     }
}