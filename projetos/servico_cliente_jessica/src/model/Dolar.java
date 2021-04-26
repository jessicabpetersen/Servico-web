/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jéssica Petersen
 */
public class Dolar {
    
    private  Calendar data = Calendar.getInstance();
    private float valor;
    
    public Dolar(){}
    
    public Dolar(float valor){
        this.valor = valor;
    }
    
    public Dolar(float valor, String data){
        this.valor = valor;
        this.valor = valor;
        SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
	try {
	this.data.setTime((Date)sdf1.parse(data));	
	}catch (Exception e) {
	 // TODO: handle exception
	}
    }
    

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}
