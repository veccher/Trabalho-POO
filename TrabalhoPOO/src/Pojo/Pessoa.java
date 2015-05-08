/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author veccher
 */
public class Pessoa {
    private String nome;
    private Integer cpf;
    
    public Pessoa(){
    }
    

    public Pessoa (String nome,Integer cpf){
        this.nome=nome;
        this.cpf=cpf;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome() {   
            return (this.nome);
    }
    public void setCpf(Integer cpf){
        this.cpf=cpf;
    }
    public Integer getCpf(){
        return (this.cpf);
        
    }
            
}
