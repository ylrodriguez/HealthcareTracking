package com.example.healthcaretracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class Dieta extends Activity implements OnClickListener {

	Button dias[];
	LinearLayout milayout;
	int numerodias = 7;
	public static String nombredia[] = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
	Usuario temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dieta);
		temp = Home.userglobal;
		
		dias = new Button[numerodias];
		
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
		
		
		
		
		
		for(int i=0; i<numerodias; i++){
			if(!temp.miplandieta.Dias[i].equals("")){
			//Log.d("Entonces que es!!!!!!!",i+"");	
			dias[i] = new Button(this);
			dias[i].setText("Dieta - "+nombredia[i]);
			dias[i].setLayoutParams(lp);
			dias[i].setBackgroundResource(R.drawable.bg_button2);
			dias[i].setId(i+500);
			dias[i].setTextSize(25);
			
			dias[i].setOnClickListener(this);
			milayout.addView(dias[i]);
			}
		}
	}


	@Override
	public void onClick(View v) {
				for(int i=0; i<numerodias; i++){
					
					if(v.getId() == (i+500)){
						
						Log.i("Mensaje", "clickeo en dieta: "+nombredia[i]);
						Intent ii = new Intent(this,Dieta_seleccionada.class);
						ii.putExtra("dia",(int)i);
						
						startActivity(ii);
					}
				}
		
	}
	

	
	
}
