package com.example.healthcaretracking;

import java.util.Calendar;

import android.util.Log;

public class Plan_dieta {

	String Dias[];
	Calendar dateactual;
	int plan_id;
	
	public Plan_dieta(int plan_id,String Dias[]){
		this.Dias = Dias;
		this.plan_id = plan_id;
		//Se usa para obtener la fecha de hoy con la hora en la que inicia
		
		dateactual = Calendar.getInstance();
		
		
		dateactual.set(Calendar.HOUR_OF_DAY, 9);
		dateactual.set(Calendar.MINUTE, 0);
		dateactual.set(Calendar.SECOND, 0);
		dateactual.set(Calendar.MILLISECOND, 0);
		
		Log.d("Fecha>>>>>>>>>>>>>", dateactual.getTime()+"");
		//Log.d("Fecha convertir fecha actual", dateactual.getTime()+"");
	}
	
}