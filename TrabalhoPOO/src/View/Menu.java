/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;
import DAO.AlunoDAO;
import DAO.AtividadeDAO;
import DAO.DisciplinaDAO;
import DAO.FaltaDAO;
import DAO.NotaDAO;
import DAO.ProfessorDAO;
import DAO.TurmaDAO;
import Pojo.Aluno;
import Pojo.Disciplina;
import Pojo.Professor;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author Vitor
 */
public class Menu {
    
    public void imprimeMenu(){
        System.out.println("-------Escolha uma opcao-----");
        System.out.println("(1)Administrador");
        System.out.println("(2)Professor");
        System.out.println("(3)Aluno");
        System.out.println("(4)Listar numero de turmas ja oferecidas"
                + " por uma disciplina");
        System.out.println("(5)Listar numero de disciplinas"
                + " ja lecionadas por um professor");
        System.out.println("(6)sair");
        System.out.println("Entre com uma opção valida: ");
    }
    public static void main(String[] args) throws FileNotFoundException, ParseException  {
        MenuAdmin menuAdmin = new MenuAdmin();
        MenuProfessor menuProfessor = new MenuProfessor();
        MenuAluno menuAluno= new MenuAluno();
        ProfessorDAO professorDAO= new ProfessorDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        NotaDAO notaDAO = new NotaDAO();
        FaltaDAO faltaDAO = new FaltaDAO();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        
        
        
        
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        byte opcao;
        
        do{
            menu.imprimeMenu();
            opcao = scanner.nextByte();
                switch(opcao){
                case 1:
                    menuAdmin.menuOpcao(professorDAO, alunoDAO,turmaDAO, disciplinaDAO);
                    break;
                case 2:
                    menuProfessor.menuOpcao(professorDAO,disciplinaDAO,alunoDAO);
                    break;
                case 3:
                    menuAluno.menuOpcao(alunoDAO, disciplinaDAO, turmaDAO, notaDAO, faltaDAO);
                    break;
                case 4:
                    System.out.println("Lista de Disciplinas:");
                    int cont=1;
                    int numTurmas;
                        for(Disciplina disciplina:disciplinaDAO.getListaDisciplina()){
                            System.out.println(cont +"-"+disciplina.getNome());
                            cont++;
                        }
                    cont--;
                    while (true){
                        System.out.println("Digite o indice da Disciplina que deseja"
                                          +"(o indice se encontra a esquerda do"
                                          +"nome da disciplina");
                        int index=parseInt(scanner.nextLine());
                        if (index>cont ||index<1){
                            System.out.println("indice invalido, tente novamente");
                            return;
                        }
                        numTurmas=disciplinaDAO.getListaDisciplina().get(index-1).getNumTurmas();
                        System.out.println(numTurmas +" turmas ja foram oferecidas dessa disciplina");
                        break;
                    }
                    break;
                case 5:
                    System.out.println("Lista de Professores:");
                    int i=1;
                    int numDisciplinas;
                        for(Professor professor:professorDAO.getListaProfessor()){
                            System.out.println(i +"-"+professor.getNome());
                            i++;
                        }
                    i--;
                    while (true){
                        System.out.println("Digite o indice do professor que deseja"
                                          +"(o indice se encontra a esquerda do"
                                          +"nome do professor");
                        int idx=parseInt(scanner.nextLine());
                        if (idx>i ||idx<1){
                            System.out.println("indice invalido, tente novamente");
                            return;
                        }
                        numDisciplinas=professorDAO.getListaProfessor().get(idx-1).getNumeroDisciplinas();
                        System.out.println(numDisciplinas +" turmas ja foram lecionadas por esse professor");
                        break;
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opção invalida!!");
            }
        }while(opcao!=6);
                
    }
    
}
