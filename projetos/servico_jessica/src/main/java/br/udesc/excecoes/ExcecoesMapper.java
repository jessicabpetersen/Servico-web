package br.udesc.excecoes;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExcecoesMapper implements ExceptionMapper<Excecoes> {

	@Override
	public Response toResponse(Excecoes exception) {
		
		return Response.status(exception.getCodigo()).entity(exception.getMensagem()).build();
	}

}
