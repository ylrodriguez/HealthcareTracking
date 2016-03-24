package com.example.healthcaretracking;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Citasprogramadas extends Activity implements OnClickListener{

	int numerocitas;
	RelativeLayout miscitas[];
	TextView tv_cita_fecha[];
	TextView tv_cita_lugar[];
	TextView tv_cita_hora[];
	TextView tv_cita_numdia[];
	LinearLayout milayout;
	Usuario usertemp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		usertemp = Home.userglobal;
		
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_citasprogramadas);
		numerocitas = getNumerocitas();
		
		miscitas = new RelativeLayout[numerocitas];
		tv_cita_fecha = new TextView[numerocitas];
		tv_cita_lugar = new TextView[numerocitas];
		tv_cita_hora = new TextView[numerocitas];
		tv_cita_numdia = new TextView[numerocitas];
					
		milayout = (LinearLayout) findViewById(R.id.milayout);
		
		// CONVERSION DE PIXELES A DP
		final float scale = getResources().getDisplayMetrics().density;
		
		int dp_100 = (int)(100 * scale + 0.5f); 
		int dpi_3dp = (int)(3 * scale + 0.5f);
		
		//-----------------------------------------------------------
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,
				dp_100
				);
		
		lp.setMargins(0, 0, 0, dpi_3dp);
		
		
		
		for(int i=0; i<numerocitas; i++){

			//---------------------------------------------------------------
			RelativeLayout.LayoutParams lp_fecha = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT
					);
			lp_fecha.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
			lp_fecha.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			//----------------------------------------------------------------
			RelativeLayout.LayoutParams lp_numdia = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT
					);
			lp_numdia.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
			lp_numdia.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			//----------------------------------------------------------------
			RelativeLayout.LayoutParams lp_lugar = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT
					);
			lp_lugar.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
			lp_lugar.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			//----------------------------------------------------------------
			RelativeLayout.LayoutParams lp_hora = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT
					);
			lp_hora.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
			lp_hora.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			//----------------------------------------------------------------
			
			
			String temp="";
			
			miscitas[i] = new RelativeLayout(this);
			miscitas[i].setBackgroundResource(R.drawable.bg_button2);
			miscitas[i].setClickable(true);
			miscitas[i].setFocusable(true);
			miscitas[i].setLayoutParams(lp);
			miscitas[i].setId(i+1);
			
			
			tv_cita_fecha[i] = new TextView(this);
			int numtemp = usertemp.miscitas[i].cal.get(Calendar.MONTH); // Sirve obtener el nombre del mes
			temp = theMonth(numtemp);
			tv_cita_fecha[i].setText(temp);
			tv_cita_fecha[i].setTextSize(27);
			tv_cita_fecha[i].setId(i+100);
			tv_cita_fecha[i].setLayoutParams(lp_fecha);
			
			tv_cita_numdia[i] = new TextView(this);
			temp = usertemp.miscitas[i].cal.get(Calendar.DATE)+"";
			tv_cita_numdia[i].setText(temp);
			tv_cita_numdia[i].setTextSize(27);
			tv_cita_numdia[i].setId(i+150);
			tv_cita_numdia[i].setLayoutParams(lp_numdia);
			
			
			temp="";
			tv_cita_lugar[i] = new TextView(this);
			tv_cita_lugar[i].setId(i+200);
			temp="Lugar: "+usertemp.miscitas[i].lugar;
			tv_cita_lugar[i].setText(temp);
			tv_cita_lugar[i].setTextSize(14);
			//lp_lugar.addRule(RelativeLayout.RIGHT_OF, tv_cita_fecha[i].getId());
			tv_cita_lugar[i].setLayoutParams(lp_lugar);
			
			
			temp="Hora: ";
			tv_cita_hora[i] = new TextView(this);
			tv_cita_hora[i].setId(i+250);
			temp+=Actualizar_datos.Buenformatohora(Home.userglobal.miscitas[i].cal.get(Calendar.HOUR_OF_DAY), 
					Home.userglobal.miscitas[i].cal.get(Calendar.MINUTE))
					+Actualizar_datos.am_or_pm(Home.userglobal.miscitas[i].cal.get(Calendar.HOUR_OF_DAY))
					;
			tv_cita_hora[i].setText(temp);
			tv_cita_hora[i].setTextSize(14);
			//lp_hora.addRule(RelativeLayout.RIGHT_OF, tv_cita_fecha[i].getId());
			tv_cita_hora[i].setLayoutParams(lp_hora);
			
			
			
			miscitas[i].addView(tv_cita_fecha[i]);
			miscitas[i].addView(tv_cita_numdia[i]);
			miscitas[i].addView(tv_cita_hora[i]);
			miscitas[i].addView(tv_cita_lugar[i]);
			
			
			
			
			
			
			
			
			miscitas[i].setOnClickListener(this);
			milayout.addView(miscitas[i]);
		}
		
	}
	
	//---------------------------------------------------------------
	private int getNumerocitas(){
		return usertemp.numcitas;
	}

	//---------------------------------------------------------------
	@Override
	public void onClick(View v) {
		//Recibe todos los eventos onClick
		
		for(int i=0; i<numerocitas; i++){
			
			if(v.getId() == (i+1)){
				
				
				Intent ii = new Intent(this,Cita_seleccionada.class);
				ii.putExtra("id_cita",(int)Home.userglobal.miscitas[i].id_cita);
				
				startActivity(ii);
			}
		}
	}

	//---------------------------------------------------------------
	
	public static String theMonth(int month){
	    String[] monthNames = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
	    return monthNames[month];
	} // Obtiene el nombre del mes segun el int ingresado
	
	
}
