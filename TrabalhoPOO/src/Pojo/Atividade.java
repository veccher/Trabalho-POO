/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.util.Date;

/**
 *
 * @author veccher
 */
public class Atividade {
    private String nome;//nome da atividade ex.: prova1, trabalho de arvores
    private String tipo;//tipo da atividade ex.: prova, trabalho, seminario
    private Date data;//data da atividade
    private Float valor;//valor da nota maxima na atividade
    //falta a lista de notas
    
    public Float getValor(){
        return this.valor;
    }
    /*o construtor simplesmente inicializa as variaveis, e faz o chec para
    garantir que o valor da atividade não seja negativo.*/
    public Atividade (String nome,String tipo, Date data, Float valor){
        this.nome=nome;
        this.tipo=tipo;
        this.data=data;
        if (valor>=0){
            this.valor=valor;
        }
    }
}
