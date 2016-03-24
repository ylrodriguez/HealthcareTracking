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

public class Actividadfisica extends Activity implements OnClickListener{

	Button activities[];
	LinearLayout milayout;
	Usuario temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividadfisica);
		
		temp = Home.userglobal;
		milayout = (LinearLayout) findViewById(R.id.milayout_act);
		
		activities = new Button[temp.misactivities.length];
		

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
				
				
				for(int i=0; i<temp.misactivities.length; i++){
					activities[i] = new Button(this);
					activities[i].setText(temp.misactivities[i].descripcion);
					activities[i].setLayoutParams(lp);
					activities[i].setBackgroundResource(R.drawable.bg_button2);
					activities[i].setId(i+1);
					activities[i].setTextSize(25);
					
					activities[i].setOnClickListener(this);
					milayout.addView(activities[i]);
				}
		
		
		
	}

	@Override
	public void onClick(View v) {
		for(int i=0; i<temp.misactivities.length; i++){
			
			if(v.getId() == (i+1)){
				
				Log.i("Mensaje", "Selecciono actividad: ");
				Intent ii = new Intent(this,Actividadseleccionada.class);
				ii.putExtra("id_activity",(int)temp.misactivities[i].id_activity);
				
				startActivity(ii);
			}
		}
		
	}

}
