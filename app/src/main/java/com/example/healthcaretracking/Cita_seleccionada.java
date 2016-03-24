package com.example.healthcaretracking;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Cita_seleccionada extends Activity {

	

	private int id_cita;
	private TextView textview_1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cita_seleccionada);
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		Bundle extras = getIntent().getExtras();
		
		id_cita = extras.getInt("id_cita");
		
		int i = 0;
		while(i != -1 && Home.userglobal.miscitas.length != i){

			if(Home.userglobal.miscitas[i].id_cita == id_cita){
				getActionBar().setTitle("Cita #"+id_cita);
				
				int numtemp = Home.userglobal.miscitas[i].cal.get(Calendar.MONTH); // Sirve obtener el nombre del mes
				String temp=Home.userglobal.miscitas[i].cal.get(Calendar.DATE)
						   +" "+Citasprogramadas.theMonth(numtemp);
				
				temp += "       "+Actualizar_datos.Buenformatohora(Home.userglobal.miscitas[i].cal.get(Calendar.HOUR_OF_DAY), 
						Home.userglobal.miscitas[i].cal.get(Calendar.MINUTE))
						+Actualizar_datos.am_or_pm(Home.userglobal.miscitas[i].cal.get(Calendar.HOUR_OF_DAY));
				
				
				textview_1 = (TextView) findViewById(R.id.textview_fechacita_edit);
				textview_1.setText(temp);
				
				textview_1 = (TextView) findViewById(R.id.textview_lugarcita_edit);
				textview_1.setText(""+Home.userglobal.miscitas[i].lugar);
				
				textview_1 = (TextView) findViewById(R.id.textview_Direccioncita_edit);
				textview_1.setText(""+Home.userglobal.miscitas[i].direccion);
				
				textview_1 = (TextView) findViewById(R.id.textview_Doctorcita_edit);
				textview_1.setText(""+Home.userglobal.miscitas[i].nombre_doctor);
				
				textview_1 = (TextView) findViewById(R.id.textview_Salacita_edit);
				textview_1.setText(""+Home.userglobal.miscitas[i].sala);
				
				textview_1 = (TextView) findViewById(R.id.textview_Direccioncita_edit);
				textview_1.setText(""+Home.userglobal.miscitas[i].direccion);
				
				textview_1 = (TextView) findViewById(R.id.textview_Telefonocita_edit);
				textview_1.setText(""+Home.userglobal.miscitas[i].telefono);
				
				i=-1;
			}
			else{
			i++;
			}
		}
		
		
		
	}
	
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this,Home.class);
		startActivity(intent);
		finish();
	}

}