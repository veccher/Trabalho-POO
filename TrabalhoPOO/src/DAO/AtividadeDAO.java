/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Atividade;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author veccher
 */
public class AtividadeDAO {
    private ArrayList<Atividade> listaAtividade = new ArrayList<Atividade>();
    
    public void adicionar (Atividade atividade){
        listaAtividade.add(atividade);
        Collections.sort(listaAtividade);
    }
}
