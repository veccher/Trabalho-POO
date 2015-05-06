/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Pojo.Professor;
import Pojo.Disciplina;
import Pojo.Turma;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vitor
 */
public class TurmaDAO {
    private ArrayList<Turma> listaTurma = new ArrayList<Turma>();
    
    public void adicionar(Turma turma){
        listaTurma.add(turma);
        Collections.sort(listaTurma);
    }
    
    public boolean excluirTurma(Professor professor, Disciplina disciplina){
        Turma aux = new Turma();
        aux.setProfessor(professor);
        aux.setDisciplina(disciplina);
        return this.listaTurma.remove(aux);
        
   }
    
}
