package com.example.healthcaretracking;

import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Actualizar_datos {

	private PendingIntent pendingIntent;
	private PendingIntent pendingIntent2;
													
	public void asignar_citas(final Context context){
		
		// Comienza la petición del server para obtener citas
		
		
		PeticionServer peticion = new PeticionServer(context,2);
		Log.d("Actualizar_datos.class", "asignar_citas, Comienzo peticion");
		peticion.ConsultarCitas(Home.userglobal, new Getusercallback2(){
		
			@Override
			public void done(Citas[] citasretornadas) {
				if(citasretornadas == null){
					Log.d("Actualizar_datos.class", "Error obteniendo citas");
					Toast tea = Toast.makeText(context, "Error actualizando datos", Toast.LENGTH_SHORT);
					tea.show();
				} 
				else{
					Log.d("Actualizar_datos.class", "Se han asignado citas");
					int num_citas = citasretornadas.length;
					Home.userglobal.agregarcitas(citasretornadas, num_citas);
					crear_alarmas_citas(context);
					 //Tener en cuenta que se deberia poner cuando se cargue todo
					
				}
				
			}
			}
				);
		
		
	}
	

	public void crear_alarmas_citas(Context context){
		
		
		Log.d("Actualizar_datos.class", "Crear_alarmas, Comienzo");
		
		// C I T A S
		for(int i=0; i<Home.userglobal.miscitas.length; i++){
			
			if(Home.userglobal.miscitas[i].cal.getTimeInMillis() >= System.currentTimeMillis()){
			
			String tempmensaje = "";
			
			tempmensaje = "Lugar: "+Home.userglobal.miscitas[i].lugar+"\n";
			int numtemp = Home.userglobal.miscitas[i].cal.get(Calendar.MONTH); // Sirve obtener el nombre del mes
			
			tempmensaje +="Fecha: "+Home.userglobal.miscitas[i].cal.get(Calendar.DATE)
						   +" "+Citasprogramadas.theMonth(numtemp)+"\n"
					;
			tempmensaje +="Hora: "+Actualizar_datos.Buenformatohora(Home.userglobal.miscitas[i].cal.get(Calendar.HOUR_OF_DAY), 
					Home.userglobal.miscitas[i].cal.get(Calendar.MINUTE))
					+am_or_pm(Home.userglobal.miscitas[i].cal.get(Calendar.HOUR_OF_DAY))
					;
			
			
			
			Intent myIntent = new Intent(context,Receptor_alarmas.class);
			int cita_id = Home.userglobal.miscitas[i].id_cita;
			
			myIntent.putExtra("id_evento", (int)100); //Id de citas.
			myIntent.putExtra("mensaje", tempmensaje);
			myIntent.putExtra("tiponot", "¡Cita en un dia!");
			myIntent.putExtra("id", (int)cita_id);
			myIntent.putExtra("id_alarma", (int)cita_id);
			
			String prueba =""+cita_id+1113; //Referencia a la ultima alarma
			//Si existe esta ultima alarma no es necesario crear nada
			//Significa que ya estan todas las demas creadas.
			
			
			boolean alarmUp = (
			PendingIntent.getBroadcast(context ,Integer.parseInt(prueba), myIntent, PendingIntent.FLAG_NO_CREATE) != null
			);
			
			if(alarmUp == true){
				Log.d("Actualizar_datos.class", "Crear_alarmas, YA existe esta alarma, no la creo ;)");
			}
			else{
				
				//GUARDA ALARMA
				
				Log.d("Actualizar_datos.class", "Crear_alarmas, Esta alarma NO existe, yo la creo :).");
				
				pendingIntent = PendingIntent.getBroadcast(context, cita_id, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);
				
				Calendar calendar = Calendar.getInstance();
				calendar = Home.userglobal.miscitas[i].cal;
				
				AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
				// Primer alarma de esta cita será un dia antes (86400000 milisegundos)
				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - 86400000, pendingIntent);
			
				
				// Cita Id - Codigo Alarma:
				//Ejemplo 1-1111 o 1-1112  etc
				
				String temp =""+cita_id+1111; //1111 identifica que faltan 4 horas
				int temp_id = Integer.parseInt(temp); // id temporal para definir alarma
				Log.d("Codigo", temp);
				
				myIntent = new Intent(context,Receptor_alarmas.class);
				myIntent.putExtra("id_evento", (int)100); //Id de citas.
				myIntent.putExtra("mensaje", tempmensaje);
				myIntent.putExtra("tiponot", "¡Cita en 4 horas!");
				myIntent.putExtra("id", (int)cita_id);
				myIntent.putExtra("id_alarma", (int)temp_id);
				
				pendingIntent = PendingIntent.getBroadcast(context, temp_id, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);
				
				// Segunda alarma 4 horas antes
				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - 14400000, pendingIntent);
				
				//
				
				
				temp =""+cita_id+1112; //1112 identifica que falta 1 hora
				temp_id = Integer.parseInt(temp); // id temporal para definir alarma
				Log.d("Codigo", temp);
				
				myIntent = new Intent(context,Receptor_alarmas.class);
				myIntent.putExtra("id_evento", (int)100); //Id de citas.
				myIntent.putExtra("mensaje", tempmensaje);
				myIntent.putExtra("tiponot", "¡Cita en 1 hora!");
				myIntent.putExtra("id", (int)cita_id);
				myIntent.putExtra("id_alarma", (int)temp_id);
				
				pendingIntent = PendingIntent.getBroadcast(context, temp_id, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);
				
				// Tercera alarma 1 hora antes
				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - 3600000, pendingIntent);
				
				//
				
				
				temp =""+cita_id+1113; //Hora cita
				temp_id = Integer.parseInt(temp); // id temporal para definir alarma
				Log.d("Codigo", temp);
				
				myIntent = new Intent(context,Receptor_alarmas.class);
				myIntent.putExtra("id_evento", (int)100); //Id de citas.
				myIntent.putExtra("mensaje", tempmensaje);
				myIntent.putExtra("tiponot", "¡Cita!");
				myIntent.putExtra("id", (int)cita_id);
				myIntent.putExtra("id_alarma", (int)temp_id);
				
				pendingIntent = PendingIntent.getBroadcast(context, temp_id, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);
				
				// Cuarta alarma hora cita.
				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
				
				//
				
			}
			
		}
			}
		
		
		}
	
	
	public void asignar_med(final Context context){
		PeticionServer peticion = new PeticionServer(context,2);
		Log.d("Actualizar_datos.class", "asignar_med, Comienzo peticion");
		peticion.ConsultarMedicamentos(Home.userglobal, new Getusercallback3(){

			@Override
			public void done(Medicamentos[] medicamentosretornadas) {
				if(medicamentosretornadas == null){
					Log.d("Actualizar_datos.class", "Error obteniendo medicamentos");
					Toast tea = Toast.makeText(context, "Error actualizando datos", Toast.LENGTH_SHORT);
					tea.show();
				} 
				else{
					Log.d("Actualizar_datos.class", "Se han asignado medicamentos");
					Home.userglobal.agregarmeds(medicamentosretornadas);
					//Tener en cuenta que se deberia poner cuando se cargue todo
					crear_alarmas_meds(context);
				}
				
			}
			
			
		}
				
				);
		
	}
	

	public void crear_alarmas_meds(Context context){
		
		
		Log.d("Actualizar_datos.class", "Crear_alarmas_meds, Comienzo");
		
		// M E D I C A M E N T O S
		
		
		for(int i=0; i<Home.userglobal.mismed.length; i++){
			
			if(Home.userglobal.mismed[i].datefin.getTimeInMillis() >= System.currentTimeMillis()){
			//.
						
			int intervalohoras = Home.userglobal.mismed[i].intervalohoras;
			Calendar fechatemp = Calendar.getInstance();
			fechatemp.setTime(Home.userglobal.mismed[i].dateactual.getTime());  
			
			String codigo_ultima_alarma = "";
			int counter=0;
			
			for(int j=0; j < Home.userglobal.mismed[i].cantidadxdia; j++){
				counter = j;
				if(j!=(Home.userglobal.mismed[i].cantidadxdia-1)){
				int temp_to_set_hour = fechatemp.get(Calendar.HOUR_OF_DAY);
				fechatemp.set(Calendar.HOUR_OF_DAY, intervalohoras+temp_to_set_hour);
				}
			}
			
			if(fechatemp.getTimeInMillis() >= System.currentTimeMillis()){
			
			int med_id = Home.userglobal.mismed[i].codigo;
			
		    codigo_ultima_alarma=""+med_id+222+""+counter;
			
			Intent myIntent = new Intent(context,Receptor_alarmas.class);
			
			
			String mensajetemporal = "";
			
			
			mensajetemporal = "Medicamento: "+Home.userglobal.mismed[i].nombre+"\n";
			mensajetemporal +="Hora: "+Actualizar_datos.Buenformatohora(fechatemp.get(Calendar.HOUR_OF_DAY), 
					fechatemp.get(Calendar.MINUTE))+am_or_pm(fechatemp.get(Calendar.HOUR_OF_DAY))
					;
			
			myIntent.putExtra("id_evento", (int)200); //Id de Medicamentos.
			myIntent.putExtra("mensaje", mensajetemporal);
			myIntent.putExtra("tiponot", "¡Tomar medicamento!");
			myIntent.putExtra("id", (int)med_id);
			myIntent.putExtra("id_alarma", (int)Integer.parseInt(codigo_ultima_alarma));
			
			
			Log.d("Codigo para verificar",codigo_ultima_alarma );
			
			boolean alarmUp = (
			PendingIntent.getBroadcast(context ,Integer.parseInt(codigo_ultima_alarma), myIntent, PendingIntent.FLAG_NO_CREATE) != null
			);
					
			if(alarmUp == true){
			Log.d("Actualizar_datos.class", "Crear_alarmas_med, YA existe esta alarma, no la creo ;)");
					}
			
			else{
				
				//GUARDA ALARMA
				
				Log.d("Actualizar_datos.class", "Crear_alarmas_med, Esta alarma NO existe, yo la creo :).");
				
			
			
			
			intervalohoras = Home.userglobal.mismed[i].intervalohoras;
			
			Calendar fechatemp2 = Calendar.getInstance();
			fechatemp2.setTime(Home.userglobal.mismed[i].dateactual.getTime());
			
			//----------------------------------------------------------------
			for(int j=0; j < Home.userglobal.mismed[i].cantidadxdia; j++){
				
				String temp =""+med_id+222+""+j; //222 para medicamentos
				int temp_id = Integer.parseInt(temp); // id temporal para definir alarma
				Log.d("Codigo Alarma Medicamento:", temp);
				
				
				myIntent = new Intent(context,Receptor_alarmas.class);
			
				mensajetemporal = "";
				
				mensajetemporal = "Medicamento: "+Home.userglobal.mismed[i].nombre+"\n";
				mensajetemporal +="Hora: "+Actualizar_datos.Buenformatohora(fechatemp2.get(Calendar.HOUR_OF_DAY), 
						fechatemp2.get(Calendar.MINUTE))+am_or_pm(fechatemp2.get(Calendar.HOUR_OF_DAY))
						;
				
				myIntent.putExtra("id_evento", (int)200); //Id de citas.
				myIntent.putExtra("mensaje", mensajetemporal);
				myIntent.putExtra("tiponot", "¡Tomar medicamento!");
				myIntent.putExtra("id", (int)med_id);
				myIntent.putExtra("id_alarma", (int)temp_id);
				
				Log.d("Fecha que creare alarma: ", ""+fechatemp2.getTime());
				
				pendingIntent2 = PendingIntent.getBroadcast(context, temp_id, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);
				AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
				alarmManager.set(AlarmManager.RTC_WAKEUP, fechatemp2.getTimeInMillis(), pendingIntent2);
				
				
				int temp_to_set_hour = fechatemp2.get(Calendar.HOUR_OF_DAY);
				fechatemp2.set(Calendar.HOUR_OF_DAY, intervalohoras+temp_to_set_hour);
				
			}
			}
			}
			}
		
			}
		}
	
	public void asignar_acti(final Context context){
		PeticionServer peticion = new PeticionServer(context,2);
		Log.d("Actualizar_datos.class", "asignar_act, Comienzo peticion");
		peticion.ConsultarActividades(Home.userglobal, new Getusercallback4(){
			@Override
			public void done(Actividad[] actividadesretornadas) {
				if(actividadesretornadas == null){
					Log.d("Actualizar_datos.class", "Error obteniendo actividades");
					Toast tea = Toast.makeText(context, "Error actualizando datos", Toast.LENGTH_SHORT);
					tea.show();
				} 
				else{
					Log.d("Actualizar_datos.class", "Se han asignado actividades");
					Home.userglobal.agregaracts(actividadesretornadas);;
					//Tener en cuenta que se deberia poner cuando se cargue todo
					crear_alarmas_act(context);

				}
				
			}
			
		}
		);
	}
	
	public void crear_alarmas_act(Context context){
		Log.d("Actualizar_datos.class", "Crear_alarmas_act, Comienzo");
		
		// A C T I V I D A D E S
		
		for(int i=0; i<Home.userglobal.misactivities.length; i++){
			
			if(Home.userglobal.misactivities[i].fecha_final.getTimeInMillis() >= System.currentTimeMillis() && 
			   Home.userglobal.misactivities[i].dateactual.getTimeInMillis() >= System.currentTimeMillis()){
			
				String tempmensaje = Home.userglobal.misactivities[i].descripcion;
				Intent myIntent = new Intent(context,Receptor_alarmas.class);
				int id_activity = Home.userglobal.misactivities[i].id_activity;
				
				myIntent.putExtra("id_evento", (int)300); //Id de Actividades.
				myIntent.putExtra("mensaje", tempmensaje);
				myIntent.putExtra("tiponot", "¡Actividad fisica!");
				myIntent.putExtra("id", (int)id_activity);
				myIntent.putExtra("id_alarma", (int)id_activity);
				
				
				boolean alarmUp = (
				PendingIntent.getBroadcast(context ,id_activity, myIntent, PendingIntent.FLAG_NO_CREATE) != null
				);
				if(alarmUp == true){
					Log.d("Actualizar_datos.class", "Crear_alarmas_act, YA existe esta alarma, no la creo ;)");
				}
				else{
					//GUARDA ALARMA
					
					Log.d("Actualizar_datos.class", "Crear_alarmas_act, Esta alarma NO existe, yo la creo :).");
					
					pendingIntent = PendingIntent.getBroadcast(context, id_activity, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);
					AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
					alarmManager.set(AlarmManager.RTC_WAKEUP, Home.userglobal.misactivities[i].dateactual.getTimeInMillis(), pendingIntent);
						
				}
				
			}
		}
	}
	
	public void asignar_dieta(final Context context){
		PeticionServer peticion = new PeticionServer(context,2);
		Log.d("Actualizar_datos.class", "asignar_act, Comienzo peticion");
		peticion.ConsultarDieta(Home.userglobal, new Getusercallback5(){

			@Override
			public void done(Plan_dieta plandietaretornado) {
				if(plandietaretornado == null){
					Log.d("Actualizar_datos.class", "Error obteniendo dieta");
					Toast tea = Toast.makeText(context, "Error actualizando datos", Toast.LENGTH_SHORT);
					tea.show();
				} 
				else{
					Log.d("Actualizar_datos.class", "Se ha asignado dieta");
					Home.userglobal.agregardieta(plandietaretornado);
					crear_alarmas_dieta(context);

					if(Home.id != -1 && Home.id_evento != -1){
						EjecutarNotificacion(context);
					}
				}
				
			}
			
		}
		
		);
	}
	
	public void crear_alarmas_dieta(Context context){
		Log.d("Actualizar_datos.class", "Crear_alarmas_dieta, Comienzo");
		
		Calendar temp = Calendar.getInstance();
		
		if(Home.userglobal.miplandieta.dateactual.getTimeInMillis() >= System.currentTimeMillis()){
		
		// D I E T A
			
		int numerodias = 7;
		for(int i=0; i<numerodias; i++){
			
			if(!Home.userglobal.miplandieta.Dias[i].equals("")
					&& temp.get(Calendar.DAY_OF_WEEK) == (i+2)
					){
				
				Log.d("---->",temp.get(Calendar.DAY_OF_WEEK)+"  "+(i+2));
				String tempmensaje = Home.userglobal.miplandieta.Dias[i];
				Intent myIntent = new Intent(context,Receptor_alarmas.class);
				int id_activity = Home.userglobal.miplandieta.plan_id;
				
				myIntent.putExtra("id_evento", (int)400); //Id de Actividades.
				myIntent.putExtra("mensaje", tempmensaje);
				myIntent.putExtra("tiponot", "¡Plan dieta de hoy!");
				myIntent.putExtra("id", (int)i);
				myIntent.putExtra("id_alarma", (int)id_activity);
				
				boolean alarmUp = (
				PendingIntent.getBroadcast(context ,id_activity, myIntent, PendingIntent.FLAG_NO_CREATE) != null
				);
				if(alarmUp == true){
					Log.d("Actualizar_datos.class", "Crear_alarmas_dieta, YA existe esta alarma, no la creo ;)");
				}
				else{
				//GUARDA ALARMA
							
				Log.d("Actualizar_datos.class", "Crear_alarmas_dieta, Esta alarma NO existe, yo la creo :).");
							
				pendingIntent = PendingIntent.getBroadcast(context, id_activity, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);
				AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
				alarmManager.set(AlarmManager.RTC_WAKEUP, Home.userglobal.miplandieta.dateactual.getTimeInMillis(), pendingIntent);
								
						}
			}
		}
		
		}
	}
	
	
	
	public static String am_or_pm(int num){
		
		if(num>=12){
			
			return" pm";
		}
		else{
			return" am";
		}
		
	}
	
	private void EjecutarNotificacion(Context context){
		Intent intent;
		switch(Home.id_evento){
		case 100: //Citas.
			intent = new Intent(context,Cita_seleccionada.class);
			intent.putExtra("id_cita",(int)Home.id);
			context.startActivity(intent);
			Home.id_evento = -1;
			Home.id = -1;
			break;
		case 200: //Medicamentos
			intent = new Intent(context,Medicamentoseleccionado.class);
			intent.putExtra("id_med",(int)Home.id);
			context.startActivity(intent);
			Home.id_evento = -1;
			Home.id = -1;
			break;
		case 300: //Actividad fisica
			intent = new Intent(context,Actividadseleccionada.class);
			intent.putExtra("id_activity",(int)Home.id);
			context.startActivity(intent);
			Home.id_evento = -1;
			Home.id = -1;
			break;
		case 400: //Dieta
			intent = new Intent(context,Dieta_seleccionada.class);
			intent.putExtra("dia",(int)Home.id);
			context.startActivity(intent);
			Home.id_evento = -1;
			Home.id = -1;
			break;
		}
		
		
	}
	
	@SuppressLint("DefaultLocale")
	public static String Buenformatohora(int hora, int min){
		String curTime = String.format("%02d:%02d",hora,min);
		return curTime;
	}
	

	
}
