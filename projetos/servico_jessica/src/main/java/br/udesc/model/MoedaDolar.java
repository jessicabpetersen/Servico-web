package br.udesc.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


public class MoedaDolar {
	
	private float valor;
	private Calendar data = Calendar.getInstance();
	
	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public MoedaDolar() {}
	
	public MoedaDolar(float valor, String date) {
		this.valor = valor;
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date data1 = (Date)sdf1.parse(date);
			data.setTime(data1);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public MoedaDolar(float valor, Calendar cal) {
		this.valor = valor;
		this.data = cal;
	}
	
	
	public MoedaDolar(float valor, int dia, int mes, int ano) {
		this.valor = valor;
		data.set(ano, mes, dia);
	}
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public boolean isThisTheDate(String data) {
		
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
        if(this.data.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &&
        		this.data.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &&
        		this.data.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {
        	return true;
        }
	        
	    return false;
	}

	public boolean isThisTheDate(int dia, int mes, int ano) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.MONTH, (mes-1));
		cal.set(Calendar.DAY_OF_MONTH, dia);
		if(this.data.get(Calendar.YEAR) == ano &&
        		this.data.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &&
        		this.data.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {
        	return true;
        }
	        
	    return false;
	}
}
