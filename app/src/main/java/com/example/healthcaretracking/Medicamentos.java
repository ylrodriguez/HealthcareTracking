package com.example.healthcaretracking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

public class Medicamentos {

	private static int bandera_pruebas=0; //Se usa para pruebas..segun fecha
	
	int codigo,cantidadxdia,intervalohoras;
	String nombre;
	String fecha_inicio,fecha_final;
	Calendar dateini, datefin,dateactual;
	
	public Medicamentos(int codigo, int cantidadxdia, int intervalohoras, String nombre, String fecha_inicio, String fecha_final){
		
		this.codigo = codigo;
		this.cantidadxdia = cantidadxdia;
		this.intervalohoras = intervalohoras;
		this.nombre = nombre;
		this.fecha_final = fecha_final;
		this.fecha_inicio = fecha_inicio;
		
		//if(bandera_pruebas == 0){ //Para usar la fecha prueba
			if(bandera_pruebas == -1){ //Para no usar la fecha prueba
						
				//Usa fecha de este metodo, diseñado para pruebas.
				//La fecha del primer medicamento que se obtiene de la BD se cambiara
				//A la fecha que se coloque en "Ajustefechaprueba()"
				
				ajustefechaprueba(); 
				bandera_pruebas++;
			}
			else{
				dateini = ajustar_fecha(fecha_inicio); //Usa fechas originales del server.
				datefin = ajustar_fecha(fecha_final); //Usa fechas originales del server.
			}
		
		int hour,min,sec;
		
		hour =	dateini.get(Calendar.HOUR_OF_DAY);
		min  =	dateini.get(Calendar.MINUTE);
		sec  =	dateini.get(Calendar.SECOND);
		
		//Se usa para obtener la fecha de hoy con la hora en la que inicia
		dateactual = Calendar.getInstance();
		
		
		dateactual.set(Calendar.HOUR_OF_DAY, hour);
		dateactual.set(Calendar.MINUTE, min);
		dateactual.set(Calendar.SECOND, sec);
		dateactual.set(Calendar.MILLISECOND, 0);
		
		//Log.d("Fecha inicio", dateini.getTime()+"");
		//Log.d("Fecha convertir fecha actual", dateactual.getTime()+"");
		
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
		
		Log.d("Ajustar_fecha, Medicamentos", "Devuelvo objeto Calendar");
		
		return aretornar;
	 
}


	private void ajustefechaprueba(){
	
	dateini = Calendar.getInstance();
	
	dateini.set(Calendar.MONTH, 7);// ES AGOSTO (siempre un mes menos)
	dateini.set(Calendar.YEAR, 2015);
	dateini.set(Calendar.DAY_OF_MONTH, 26); //Fecha de inicio

	dateini.set(Calendar.HOUR_OF_DAY, 8);
	dateini.set(Calendar.MINUTE, 30);
	dateini.set(Calendar.SECOND, 0);
	
	
	datefin = Calendar.getInstance(); 		//Fecha de finalizacion
	
	datefin.set(Calendar.MONTH, 8);
	datefin.set(Calendar.YEAR, 2015);
	datefin.set(Calendar.DAY_OF_MONTH, 15);
	
	datefin.set(Calendar.HOUR_OF_DAY, 16);
	datefin.set(Calendar.MINUTE, 30);
	datefin.set(Calendar.SECOND, 0);

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


