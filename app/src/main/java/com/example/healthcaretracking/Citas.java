package com.example.healthcaretracking;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

public class Citas {
	
	
	private static int bandera_pruebas=0; //Se usa para pruebas..segun fecha
	int id_cita;
	int telefono;
	int sala;
	String direccion;
	String lugar;
	String fecha;
	String nombre_doctor;
	
	Calendar cal;
	
	public Citas(int id_cita, String lugar, String fecha, String direccion, int sala, String nombre_doctor, int telefono){
		
		this.direccion = direccion;
		this.sala = sala;
		this.nombre_doctor = nombre_doctor;
		this.telefono = telefono;
		this.fecha = fecha;
		this.id_cita = id_cita;
		this.lugar = lugar;
		
		
		//if(bandera_pruebas == 0){ //Para usar la fecha prueba
		if(bandera_pruebas == -1){ //Para no usar la fecha prueba
					
			//Usa fecha de este metodo, diseñado para pruebas.
			//La fecha de la primer cita que se obtiene de la BD se cambiara
			//A la fecha que se coloque en "Ajustefechaprueba()"
			
			ajustefechaprueba(); 
			bandera_pruebas++;
		}
		else{
			cal = ajustar_fecha(fecha); //Usa fechas originales del server.
		}
		//Log.d(">>>>>>>>>>>>>>>>>>>>>>", ""+cal.get(Calendar.DAY_OF_WEEK));
	}

	
	
	private static Calendar ajustar_fecha(String fecha){
		
			Calendar aretornar = null;
			
			Date temp = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			
			try {
				temp = sdf.parse(fecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			aretornar = Calendar.getInstance();
			aretornar.setTime(temp);
			
			Log.d("Ajustar_fecha", "Devuelvo objeto Calendar");
			
			return aretornar;
		 
	}
	
	
	private void ajustefechaprueba(){
		cal = Calendar.getInstance();
		
		cal.set(Calendar.MONTH, 8);
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.DAY_OF_MONTH, 3);

		cal.set(Calendar.HOUR_OF_DAY, 16);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 0);
		
	
	}
	
	public static void setBandera_pruebas(boolean flag){
		if(flag){
			bandera_pruebas = 0;
		}
		else{
			bandera_pruebas = 1;
		}
	}
	
}
