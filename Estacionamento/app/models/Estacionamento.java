package models;

import javax.persistence.*;
import io.ebean.*;

import play.data.validation.Constraints.Required;

@Entity
public class Estacionamento extends Model{

    @Id
    public Long id;
    @Required(message = "O nome do estacionamento é obrigatório")
    public String nome;
    public String cep;

    public static Finder<Long,Estacionamento> find = new Finder<>(Estacionamento.class);
}