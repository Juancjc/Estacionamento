package controllers;
import play.mvc.*;
import views.html.*;
import javax.inject.Inject;

import javafx.geometry.HorizontalDirection;
import play.data.*;
import models.*;
import java.util.List;



public class HomeController extends Controller {
    
    @Inject
    FormFactory formFactory;
    @Inject
    Carro carro;
    @Inject
    Estacionamento estacionamento;
    @Inject
    Vaga vaga;
    /*@Inject
    Saida saida;*/
    


    public Result index() {
        return ok(index.render("alô mundo...."));
    }

    public Result cadastroDeCarro(){

        Form<Carro> formularioDeCarro = formFactory.form(Carro.class);

        return ok(cadastroDeCarro.render("Cadastro",formularioDeCarro));

        //Form<Vaga> formVaga = formFactory
        //.form(Vaga.class);
        //Form<Vaga> form = vaga.find.all();
    }

    public Result cadastroDeNovoCarro(){
        //pega as informações que vem do formulário
        Form<Carro> formCarro= formFactory.form(Carro.class).bindFromRequest();

        //hora
        //minuto
        //valida se tem aglum erro

        if(formCarro.hasErrors()){
            flash("danger", "Tem erros no formulario");

            //List<Vaga> vaga = Vaga.find.findList();

            return badRequest(cadastroDeCarro.render("Cadastro",formCarro));//,vaga

            }
        


        carro = formCarro.get() ;
    
        int entrada=(carro.hora*60)+carro.minuto;
        int saida=(carro.saidaHora*60)+carro.saidaMinuto;
        
        carro.tempo=saida-entrada;

        int valor; // valor do estacionamento sera 3 reais

        if(carro.tempo <= 15)
            valor =0;
        else{
            if(carro.tempo >=16 && carro.tempo <= 60 )
              valor= 3;
           else
              valor = ((carro.tempo/60)*3);
     }
        
           
        carro.valor=valor;

        //vaga


        if(carro.id != null)
            carro.update();
        else
            carro.save();
        //notifica o usuário que o produto foi salvo
        flash("success", "Novo produto adicionado: "+carro.placa);

        //redireciona para a tela de cadastro novamente
        return redirect(routes.HomeController.listaCarro());

       
    }

    public Result editarCarro(Long id){
        Carro c = carro.find.byId(id);
        Form<Carro> formCarro = 
        formFactory.form(Carro.class).fill(c);
        //Vaga v = vaga.find.byId(id);
        //Form<Vaga> formVaga = 
        //formFactory.form(Vaga.class).fill(v);
        
        return ok(cadastroDeCarro
        .render("Editar",formCarro));

    }

    public Result listaCarro(){
        List<Carro> list = carro.find.all();
        return ok(listCarro.render(list));
    }
    public Result listaEstacionamento(){
        List<Estacionamento> list = estacionamento.find.all();
        return ok(listEstacionamento.render(list));
    }

    public Result cadastroDeEstacionamento(){
        Form<Estacionamento> formularioDeEstacionamento = formFactory
        .form(Estacionamento.class);
        return ok(cadastroDeEstacionamento.render("Cadatro",formularioDeEstacionamento));
    }

    public Result cadastroDeNovoEstacionamento(){
        //pega as informações que vem do formulário
        Form<Estacionamento> formEstacionamento = formFactory.form(Estacionamento.class)
        .bindFromRequest();

        //valida se tem aglum erro
        if(formEstacionamento.hasErrors()){
            flash("danger", "Tem erros no formulario");
            return badRequest(cadastroDeEstacionamento.render("Cadastro", formEstacionamento));
        }

        
        estacionamento = formEstacionamento.get();
        //atribui os dados ao objeto carro
        // carro = formCarro.get();
        //carro.ocupa = estacionamento.vaga;
        //salva o tudo

        if(estacionamento.id != null)
            estacionamento.update();
        else
            estacionamento.save();
        //notifica o usuário que o produto foi salvo
        flash("success", "Novo estacionamento adicionado: "+estacionamento.nome);

        //redireciona para a tela de cadastro novamente
        return redirect(routes.HomeController.listaEstacionamento());
    }

    public Result editarEstacionamento(Long id){
        Estacionamento e = estacionamento.find.byId(id);
        Form<Estacionamento> formEstacionamento = 
        formFactory.form(Estacionamento.class).fill(e);
        
        return ok(cadastroDeEstacionamento
        .render("Editar",formEstacionamento));

    }
//Saida/Vaga

public Result listaVaga(){
    List<Vaga> list = vaga.find.all();
    return ok(listVaga.render(list));
}

public Result vagaLivre(){
    Form<Vaga> formularioDeVaga = formFactory
    .form(Vaga.class);
    return ok(vagaLivre.render("Cadatro",formularioDeVaga));
}

public Result vagaLivreNovo(){
    //pega as informações que vem do formulário
    Form<Vaga> formVaga= formFactory.form(Vaga.class)
    .bindFromRequest();

    //valida se tem aglum erro
    if(formVaga.hasErrors()){
        flash("danger", "Tem erros no formulario");
        return badRequest(vagaLivre.render("Cadastro", formVaga));
    }

    
    vaga = formVaga.get();

    /*if(vaga.ocupada=0)
        return "Livre";
    else
          return "Ocupada";   */            


    if(vaga.id != null)
        vaga.update();
    else
        vaga.save();

    flash("success", "vagas criadas com sucesso");


    return redirect(routes.HomeController.listaVaga());
}



public Result editarVaga(Long id){
    Vaga v = vaga.find.byId(id);
    Form<Vaga> formVaga = 
    formFactory.form(Vaga.class).fill(v);
    
    return ok(vagaLivre
    .render("Editar",formVaga));

  }




  
}

