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

public class Medicamentosprogramados extends Activity implements OnClickListener {

	Button medicam[];
	LinearLayout milayout;
	Usuario temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medicamentosprogramados);
		
		temp = Home.userglobal;
		
		milayout = (LinearLayout) findViewById(R.id.milayout_med);
		
		medicam = new Button[temp.mismed.length];
		
		
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
				
				
				for(int i=0; i<temp.mismed.length; i++){
					medicam[i] = new Button(this);
					medicam[i].setText(temp.mismed[i].nombre);
					medicam[i].setLayoutParams(lp);
					medicam[i].setBackgroundResource(R.drawable.bg_button2);
					medicam[i].setId(i+1);
					medicam[i].setTextSize(25);
					
					medicam[i].setOnClickListener(this);
					milayout.addView(medicam[i]);
				}
		
	}

	@Override
	public void onClick(View v) {
		for(int i=0; i<temp.mismed.length; i++){
			
			if(v.getId() == (i+1)){
				
				Log.i("Mensaje", "Selecciono medicamento: ");
				Intent ii = new Intent(this,Medicamentoseleccionado.class);
				ii.putExtra("id_med",(int)temp.mismed[i].codigo);
				
				startActivity(ii);
			}
		}
		
	}

}
