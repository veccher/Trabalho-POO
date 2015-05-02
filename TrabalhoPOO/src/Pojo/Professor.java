/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pojo;

/**
 *
 * @author Vitor
 */
public class Professor extends Pessoa{
    private String departamento;
    private Integer numeroDisciplinas;
    
    public Professor (String nome,Integer cpf,String departamento){
        super (nome,cpf);
        this.departamento=departamento;
        this.numeroDisciplinas=0;
    }
    public Integer getNumeroDisciplinas(){
        return this.numeroDisciplinas;
    }
}
