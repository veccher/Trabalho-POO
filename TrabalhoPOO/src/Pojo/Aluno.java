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
public class Aluno extends Pessoa implements Comparable <Aluno> {
   public Aluno(){
       super();
       
   }
   public Aluno (String nome,Integer cpf){
        super (nome,cpf);
    }
   
   public String ToString(){
       return "Nome:"+this.getNome()+"\n"+"Cpf:"+this.getCpf() ;
   }
  
    @Override
    //para comparação será usado cpf, onde cpfs iguais significam alunos iguais
    public boolean equals(Object obj){
        
        if(!(obj instanceof Aluno)){
            return false;
        }
        Aluno aluno = (Aluno)obj;
        return (this.getCpf().equals(aluno.getCpf()));
    }
    
    //compara para ordenação baseado em ordem alfabetica
    public int compareTo(Aluno aluno){
        if(this.getNome().compareTo(aluno.getNome())==0){
            return 0;
        }else if(this.getNome().compareTo(aluno.getNome())<0){
            return -1;
        }else
            return 1;           
    }
}
