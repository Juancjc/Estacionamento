package models;

import javax.persistence.*;
import io.ebean.*;
import java.util.Date;
import play.data.validation.Constraints.Required;

@Entity
public class Vaga extends Model{

    @Id
    public Long id;
    @Required(message = "erro")
    public int quantidade;
    public int nome;
    public int ocupada;
 
 
    public static Finder<Long,Vaga> find = new Finder<>(Vaga.class);
}