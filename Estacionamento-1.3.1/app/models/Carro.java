package models;

import javax.persistence.*;
import io.ebean.*;
import java.util.Date;
import play.data.validation.Constraints.Required;

@Entity
public class Carro extends Model{

    @Id
    public Long id;
    @Required(message = "A placa Carro é obrigatório")
    public String placa;
    public String modelo;
    
    public int ocupada;
    

    public int hora;
    public int minuto;
    
    public int saidaHora;
    public int saidaMinuto;

    public int tempo;

    public float valor;
 
    public static Finder<Long,Carro> find = new Finder<>(Carro.class);
}