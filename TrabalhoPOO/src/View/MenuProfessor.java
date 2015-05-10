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
import Pojo.Atividade;
import Pojo.Disciplina;
import Pojo.Professor;
import Pojo.Turma;
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
         Integer cpf=scanner.nextInt();
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
     // a função vai criar uma nova atividade para uma Turma
     public static boolean adicionaAtividade (Professor professor,Integer idTurma,
                                              Disciplina disciplina){
         Scanner scanner=new Scanner(System.in);
         String nome;
         Turma turma=professor.getTurmasTutoradas().buscaTurma(disciplina, idTurma);
         if (turma==null){
             System.out.println ("Essa turma não foi encontrada na lista de turmas"
                                + "lecionadas por você");
             return false; 
         }
         do{
             System.out.println("Digite o nome da atividade, certifique-se de que não"
                            + "haja outra atividade com o mesmo nome cadastrado"
                            + "nessa turma");
             nome=scanner.nextLine();
         }while (turma.getListaAtividades().buscaAtividade(nome)!=null);
         System.out.println("Digite o tipo da atividade");
         String tipo=scanner.nextLine();
         System.out.println("digite o dia de entrega da atividade");
         Integer dia=scanner.nextInt();
         System.out.println("digite o mes de entrega da atividade");
         Integer mes=scanner.nextInt();
         Date data=new Date(turma.getAno(),mes,dia);
         System.out.println("Digite o valor do peso avaliativo da atividade");
         Float peso=scanner.nextFloat();
         Atividade atividade=new Atividade(nome,tipo,data,peso,turma);
         turma.getListaAtividades().adicionar(atividade);
         return true;
     }
     
     public void menuOpcao(ProfessorDAO professorDAO){
         
         
     }
}
