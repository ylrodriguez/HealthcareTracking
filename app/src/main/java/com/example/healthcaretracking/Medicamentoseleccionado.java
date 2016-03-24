package com.example.healthcaretracking;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Medicamentoseleccionado extends Activity {

	private int id_med;
	TextView textview_1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medicamentoseleccionado);
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		Bundle extras = getIntent().getExtras();
		
		id_med = extras.getInt("id_med");
		
		int i = 0;
		while(i != -1 && Home.userglobal.mismed.length != i){

			if(Home.userglobal.mismed[i].codigo == id_med){
				getActionBar().setTitle(Home.userglobal.mismed[i].nombre);
				
			
				//Log.d("AQUIIIIII", Home.userglobal.mismed[i].dateactual.getTime()+"");
				textview_1 = (TextView) findViewById(R.id.tv_nombremed_edit);
				textview_1.setText(Home.userglobal.mismed[i].nombre);
				
				int numtemp = Home.userglobal.mismed[i].dateini.get(Calendar.MONTH); // Sirve obtener el nombre del mes
				String temp =Actualizar_datos.Buenformatohora(Home.userglobal.mismed[i].dateini.get(Calendar.HOUR_OF_DAY), 
						Home.userglobal.mismed[i].dateini.get(Calendar.MINUTE))
						+am_or_pm(Home.userglobal.mismed[i].dateini.get(Calendar.HOUR_OF_DAY));
				
				
				textview_1 = (TextView) findViewById(R.id.tv_horainiciomed_edit);
				textview_1.setText(temp);
				
				textview_1 = (TextView) findViewById(R.id.tv_cantidadxdiamed_edit);
				textview_1.setText(""+Home.userglobal.mismed[i].cantidadxdia);
				
				textview_1 = (TextView) findViewById(R.id.tv_intervalohorasmed_edit);
				textview_1.setText(""+Home.userglobal.mismed[i].intervalohoras);
				
				numtemp = Home.userglobal.mismed[i].dateini.get(Calendar.MONTH); // Sirve obtener el nombre del mes
				temp=Home.userglobal.mismed[i].dateini.get(Calendar.DATE)
						   +" "+Citasprogramadas.theMonth(numtemp);
				
				
				textview_1 = (TextView) findViewById(R.id.tv_fecha_iniciomed_edit);
				textview_1.setText(temp);
				
				
				numtemp = Home.userglobal.mismed[i].datefin.get(Calendar.MONTH); // Sirve obtener el nombre del mes
				temp=Home.userglobal.mismed[i].datefin.get(Calendar.DATE)
						   +" "+Citasprogramadas.theMonth(numtemp);
				
				textview_1 = (TextView) findViewById(R.id.tv_fecha_finalmed_edit);
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

public static String am_or_pm(int num){
		
		if(num>=12){
			
			return" pm";
		}
		else{
			return" am";
		}
		
	}

	
}
