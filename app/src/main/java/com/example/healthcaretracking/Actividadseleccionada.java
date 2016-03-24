package com.example.healthcaretracking;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Actividadseleccionada extends Activity {

	

	private int id_activity;
	TextView textview_1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividadseleccionada);
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		Bundle extras = getIntent().getExtras();
		
		id_activity = extras.getInt("id_activity");
		
		int i = 0;
		while(i != -1 && Home.userglobal.misactivities.length != i){

			if(Home.userglobal.misactivities[i].id_activity == id_activity){
				getActionBar().setTitle(Home.userglobal.mismed[i].nombre);
				
			
				//Log.d("AQUIIIIII", Home.userglobal.mismed[i].dateactual.getTime()+"");
				textview_1 = (TextView) findViewById(R.id.tv_descractivity_edit);
				textview_1.setText(Home.userglobal.misactivities[i].descripcion);
				
				
				int numtemp = Home.userglobal.misactivities[i].fecha_final.get(Calendar.MONTH); // Sirve obtener el nombre del mes
				String temp=Home.userglobal.misactivities[i].fecha_final.get(Calendar.DATE)
						   +" "+Citasprogramadas.theMonth(numtemp);
				
				textview_1 = (TextView) findViewById(R.id.tv_fechafinalactivity_edit);
				textview_1.setText(temp);
				
				
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
