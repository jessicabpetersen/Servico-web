package br.udesc.webservices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.udesc.excecoes.Excecoes;
import br.udesc.excecoes.JaExiste;
import br.udesc.excecoes.SemCotacao;
import br.udesc.model.MoedaDolar;

@Path("dolar")
public class Dolar {

	private static List<MoedaDolar> lista = new ArrayList<>();
	
	static {
		lista.add(new MoedaDolar(5.1f, "04/04/2021"));
		lista.add(new MoedaDolar(5.12f, "05/04/2021"));
		lista.add(new MoedaDolar(5.2f, "06/04/2021"));
		lista.add(new MoedaDolar(5.3f, "07/04/2021"));
		lista.add(new MoedaDolar(5.34f, "08/04/2021"));
		lista.add(new MoedaDolar(5.39f, "09/04/2021"));
		lista.add(new MoedaDolar(5.4f, "21/04/2021"));
		lista.add(new MoedaDolar(5.53f, "22/04/2021"));
		lista.add(new MoedaDolar(4.4f, "23/04/2021"));
		lista.add(new MoedaDolar(6.7f, "24/04/2021"));
	}
	
	@GET
	@Produces("application/json")
	public Response getDolar() {
		Calendar c = Calendar.getInstance();
		for(MoedaDolar dolar : lista) {
			if(dolar.getData().get(Calendar.YEAR) == c.get(Calendar.YEAR) &&
					dolar.getData().get(Calendar.MONTH) == c.get(Calendar.MONTH) &&
					dolar.getData().get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)) {
				return Response.ok(dolar.getValor()).build();
			}
		}
		throw new SemCotacao();
	}
	
	@GET
	@Path("{data}")
	@Produces("application/json")
	public Response getDolarData(@PathParam("data") String data)  {
		data = data.replace(".", "");
		for(MoedaDolar dolar : lista) {
			if(dolar.isThisTheDate(data)) {
				return Response.ok(dolar.getValor()).build();
			}
		}
		//return Response.status(Status.NOT_FOUND).build();
		throw new SemCotacao();
	}
	
	@DELETE
	@Path("{data}")
	@Produces("application/json")
	public Response deleteDolarData(@PathParam("data") String data) {
		data = data.replace(".", "");
		for(MoedaDolar dolar : lista) {
			if(dolar.isThisTheDate(data)) {
				lista.remove(dolar);
				return Response.ok().build();
			}
		}
		throw new SemCotacao();
	}
	
	@POST
	@Path("{data}/{valor}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response adicionarDolarData(@PathParam("data") String data, @PathParam("valor") float valor) {
		data = data.replace(".", "");
		for(MoedaDolar dolara :lista) {
        	if(dolara.isThisTheDate(data)) {
        		throw new JaExiste();
        	}
        }
        
		StringBuilder stringBuilder = new StringBuilder(data);
		stringBuilder.insert(data.length() - 4, '/');
		stringBuilder.insert(data.length() - 6, '/');
		data = stringBuilder.toString();
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
		Date data1 = null;
	    try{
	       data1 =(Date)sdf1.parse(data);
	    }catch(Exception e){
	            
	    }
        Calendar cal = Calendar.getInstance();
        cal.setTime(data1);
        MoedaDolar dolar = new MoedaDolar(valor, cal);
        
        lista.add(dolar);
        return Response.ok().build();
	}
	
	@PUT
	@Path("{data}/{valor}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response alteraDolarData(@PathParam("data") String data, @PathParam("valor") float valor) {
		data = data.replace(".", "");
		for(MoedaDolar dolar : lista) {
			if(dolar.isThisTheDate(data)) {
				dolar.setValor(valor);
				return Response.ok().build();
			}
		}
		throw new SemCotacao();
        
	}
	
	@GET
	@Path("{dia}/{mes}/{ano}")
	@Produces("application/json")
	public Response getDolarDatas(@PathParam("dia") int dia, @PathParam("mes") int mes, @PathParam("ano") int ano) {
		for(MoedaDolar dolar : lista) {
			if(dolar.isThisTheDate(dia, mes, ano)) {
				return Response.ok(dolar.getValor()).build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@DELETE
	@Path("{dia}/{mes}/{ano}")
	@Produces("application/json")
	public Response deleteDolarDatas(@PathParam("dia") int dia, @PathParam("mes") int mes, @PathParam("ano") int ano) {
		for(MoedaDolar dolar : lista) {
			if(dolar.isThisTheDate(dia, mes, ano)) {
				lista.remove(dolar);
				return Response.ok().build();
			}
		}
		throw new SemCotacao();
	}
	
	@POST
	@Path("{dia}/{mes}/{ano}/{valor}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response adicionaDolarDatas(@PathParam("dia") int dia, @PathParam("mes") int mes, @PathParam("ano") int ano, @PathParam("valor") float valor) {
		for(MoedaDolar dolara :lista) {
        	if(dolara.isThisTheDate(dia, mes, ano)) {
        		throw new JaExiste();
        	}
        }
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.MONTH, (mes-1));
		cal.set(Calendar.DAY_OF_MONTH, dia);
		MoedaDolar dolar = new MoedaDolar(valor, cal);
		lista.add(dolar);
		return Response.ok().build();
	}
	
	@PUT
	@Path("{dia}/{mes}/{ano}/{valor}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response alteraDolarData(@PathParam("dia") int dia, @PathParam("mes") int mes, @PathParam("ano") int ano, @PathParam("valor") float valor) {
		for(MoedaDolar dolar : lista) {
			if(dolar.isThisTheDate(dia, mes, ano)) {
				dolar.setValor(valor);
				return Response.ok().build();
			}
		}
		throw new SemCotacao();
	}
	
}
