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
import Pojo.Atividade;
import Pojo.Disciplina;
import Pojo.Falta;
import Pojo.Nota;
import Pojo.Professor;
import Pojo.Turma;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vitor
 */
public class Menu {
    public static Float getNota (Aluno aluno, Turma turma){
        Float somaNotas=new Float(0);
        Float somaPesos=new Float(0);
        for (Atividade atividade:turma.getListaAtividades()){
            somaPesos+=atividade.getPeso();
            for (Nota nota:atividade.getListaNota()){
                if (nota.getAluno().equals(aluno)){
                    somaNotas+=nota.getNota();
                }
            }
        }
        return somaNotas/somaPesos;
    }
    public static Integer getFaltasAluno (Aluno aluno, Turma turma){
        for (Falta falta:turma.getListaFaltas()){
            if (falta.getAluno().equals(aluno)){
                return falta.getNumFaltas();
            }
        }
        return 0;
    }
    public void imprimeMenu(){
        System.out.println("-------Escolha uma opcao-----");
        System.out.println("(1)Administrador");
        System.out.println("(2)Professor");
        System.out.println("(3)Aluno");
        System.out.println("(4)Listar numero de turmas ja oferecidas"
                + " por uma disciplina");
        System.out.println("(5)Listar numero de disciplinas"
                + " ja lecionadas por um professor");
        System.out.println("(6)Consultar turmas");
        System.out.println("(7)Sair");
        System.out.println("Entre com uma opção valida: ");
    }
    public static boolean consultaTurmas(DisciplinaDAO disciplinaDAO,TurmaDAO turmaDAO,
                                    AlunoDAO alunoDAO){
        Disciplina disciplina;
        ArrayList<Turma> turmas=null;
        Scanner scanner=new Scanner(System.in);
        if (disciplinaDAO==null || turmaDAO==null || alunoDAO==null){
            System.out.println("não há informações suficientes no banco de dados para consultas");
            return false;
        }//o while vai buscar as turmas da disciplina e ano digitado
        while (turmas==null){
            Integer ano;
            String nome;
            System.out.println("Lista de disciplinas");
            for (Disciplina aux:disciplinaDAO.getListaDisciplina()){
                System.out.println(aux.getNome());
            }
            System.out.println("Digite o nome da disciplina");
            nome=scanner.nextLine();
            System.out.println("Digite o ano que deseja consultar");
            ano=parseInt(scanner.nextLine());
            disciplina=disciplinaDAO.buscaDisciplina(nome);
            if (disciplina==null){
                System.out.println("disciplina não encontrada, tente novamente");
            }
            for (Turma auxTurma:turmaDAO.getListaTurma()){
                if (auxTurma.getDisciplina().equals(disciplina) && auxTurma.getAno().equals(ano)){
                    turmas.add(auxTurma);
                }
            }
            if (turmas.isEmpty()==true){
                System.out.println("turmas não encontradas, tente novamente");
            }
        }
        for (Turma auxTurma:turmas){
            System.out.println("Turma: "+auxTurma.getIdTurma());
            for (Aluno auxAluno:auxTurma.getListaAlunos()){
                System.out.println("aluno: "+auxAluno.getNome()+"nota: "
                                   + getNota(auxAluno,auxTurma)+"faltas: "
                                   + getFaltasAluno(auxAluno, auxTurma));
                
            }
        }
        return true;
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
                    consultaTurmas(disciplinaDAO, turmaDAO, alunoDAO);break;
                case 7:
                    break;
                default:
                    System.out.println("Opção invalida!!");
            }
        }while(opcao!=7);
                
    }
    
}
