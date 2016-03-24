package com.example.healthcaretracking;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Receptor_alarmas extends BroadcastReceiver{
	
	private String mensaje = "Error";
	private int id_alarma;
	private int id; 		// Id del evento 
	private int id_evento;  // Tipo evento e.g Cita,Dieta etc
	private String tiponot = "Error";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		/*Intent service1 = new Intent(context, MyAlarmService.class);
	     context.startService(service1);*/
		Bundle extras = intent.getExtras();
		
		Log.d("App", "Called reciver method");
		
		mensaje = intent.getStringExtra("mensaje");
		tiponot = intent.getStringExtra("tiponot");
		id_alarma = extras.getInt("id_alarma");
		id = extras.getInt("id");
		id_evento = extras.getInt("id_evento");
		
		try{
			
			Utils.generateNotification(context,mensaje,id_alarma,tiponot,id_evento,id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
}
