/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;
import DAO.ProfessorDAO;
import DAO.AlunoDAO;
import DAO.DisciplinaDAO;
import DAO.TurmaDAO;
import DAO.NotaDAO;
import DAO.FaltaDAO;
import DAO.AtividadeDAO;
import java.io.FileNotFoundException;
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
                    menuProfessor.menuOpcao(professorDAO);
                    break;
                case 3:
                    menuAluno.menuOpcao();
                    break;
                case 4:
                   //TODO listagem do numero de turmas ja oferecidas de uma disciplina
                    break;
                case 5:
                    //TODO listagem do numero de disciplinas ja lecionadas por um professor
                case 6:
                    break;
                default:
                    System.out.println("Opção invalida!!");
            }
        }while(opcao!=6);
                
    }
    
}
