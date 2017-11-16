package controllers;
import play.mvc.*;
import views.html.*;
import javax.inject.Inject;
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


    public Result index() {
        return ok(index.render("alô mundo...."));
    }

    public Result cadastroDeCarro(){
        Form<Carro> formularioDeCarro = formFactory
        .form(Carro.class);
        return ok(cadastroDeCarro.render("Cadatro",formularioDeCarro));
    }

    public Result cadastroDeNovoCarro(){
        //pega as informações que vem do formulário
        Form<Carro> formCarro= formFactory.form(Carro.class).bindFromRequest();

        //valida se tem aglum erro
        if(formCarro.hasErrors()){
            flash("danger", "Tem erros no formulario");
            return badRequest(cadastroDeCarro.render("Cadastro", formCarro));
        }

        //atribui os dados ao objeto produto
        carro = formCarro.get() ;
        //salva o produto
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

        //atribui os dados ao objeto produto
        estacionamento = formEstacionamento.get() ;
        //salva o produto
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
}


