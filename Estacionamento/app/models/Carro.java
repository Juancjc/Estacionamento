package models;

import javax.persistence.*;
import io.ebean.*;

import play.data.validation.Constraints.Required;

@Entity
public class Carro extends Model{

    @Id
    public Long id;
    @Required(message = "A placa Carro é obrigatório")
    public String placa;
    public String modelo;

    public static Finder<Long,Carro> find = new Finder<>(Carro.class);
}