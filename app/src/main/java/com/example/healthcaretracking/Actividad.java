package com.example.healthcaretracking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

public class Actividad {

	int id_activity;
	String descripcion;
	Calendar fecha_final,dateactual;
	
	public Actividad(int id_activity, String descripcion, String fecha){
		this.id_activity = id_activity;
		this.descripcion = descripcion;
		fecha_final = ajustar_fecha(fecha);
		
		int hour,min,sec;
		
		hour =	fecha_final.get(Calendar.HOUR_OF_DAY);
		min  =	fecha_final.get(Calendar.MINUTE);
		sec  =	fecha_final.get(Calendar.SECOND);
		
		dateactual = Calendar.getInstance();
		
		
		dateactual.set(Calendar.HOUR_OF_DAY, hour);
		dateactual.set(Calendar.MINUTE, min);
		dateactual.set(Calendar.SECOND, sec);
		dateactual.set(Calendar.MILLISECOND, 0);
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
	
}
