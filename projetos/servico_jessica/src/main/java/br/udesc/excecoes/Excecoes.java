package br.udesc.excecoes;

public class Excecoes extends RuntimeException{

	private String mensagem;
	private int codigo;
	
	public  Excecoes(String mensagem, int codigo) {
		super();
		this.mensagem = mensagem;
		this.codigo = codigo;
	}
	public String getMensagem() {
		return mensagem;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
}
