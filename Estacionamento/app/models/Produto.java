package models;

import javax.persistence.*;
import io.ebean.*;

import play.data.validation.Constraints.Required;

@Entity
public class Produto extends Model{

    @Id
    public Long id;
    @Required(message = "O nome do produto é obrigatório")
    public String nome;
    public String descricao, unidade;
    public Double preco;

    public static Finder<Long,Produto> find = 
        new Finder<>(Produto.class);
}