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
  
    @Override
    public boolean equals(Object obj){
        
        if(!(obj instanceof Aluno)){
            return false;
        }
        Aluno aluno = (Aluno)obj;
        return (this.nome.equals(aluno.nome));
    }
    
   
    public int compareTo(Aluno aluno){
        if(this.cpf.compareTo(aluno.cpf)==0){
            return 0;
        }else if(this.cpf.compareTo(aluno.cpf)<0){
            return -1;
        }else
            return 1;           
    }
}
