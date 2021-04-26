/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Dolar;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jéssica Petersen
 */
public class Webservice {
     Client client;
     WebTarget target;
    public Webservice(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/servico_jessica/");
    }
    public Dolar cotacaoHoje(){
        Dolar a = new Dolar();
        a.setValor(0);
        try{
        String request = target.path("dolar").request().get(String.class);
        System.out.println(request);
        a.setValor(Float.parseFloat(request));
        System.out.println(a);
        return a;
        }catch(Exception e){
            
        }
         return a;
    }
    
    public String cotacaoDia(String dia){
        dia = dia.replace("/", "");
        try{
            String request = target.path("dolar").path(dia).request().get(String.class);
            return request;
         }catch(Exception e){
            
        }
         return "Sem cotação para esse dia";
    }
    
    public String deletarCotacao(String dia){
        dia = dia.replace("/", "");
        Response request = target.path("dolar").path(dia).request().delete();
        if(request.getStatus() == 200){
            return "Deletada com sucesso";
        }
        return "Erro ao deletar";
    }
    
    public String cadastrar(String dia, String valor){
         dia = dia.replace("/", "");
         valor = valor.replace(",", ".");
         float valoar = Float.parseFloat(valor);
         Dolar dolar = new Dolar(valoar, dia);
         Response request = target.path("dolar").path(dia).path(valor).request().post(Entity.entity(dolar, MediaType.APPLICATION_JSON_TYPE));
          if(request.getStatus() == 200){
              return "Cotação cadastrada";
          }
          return "Erro ao cadastrar";
    }
    
    public String alterar(String dia, String valor){
        dia = dia.replace("/", "");
        valor = valor.replace(",", ".");
        float valoar = Float.parseFloat(valor);
        Dolar dolar = new Dolar(valoar, dia);
        Response request = target.path("dolar").path(dia).path(valor).request().put(Entity.entity(dolar, MediaType.APPLICATION_JSON_TYPE));
        if(request.getStatus() == 200){
              return "Cotação alterada";
          }
          return "Erro na alteração";
    }
}
