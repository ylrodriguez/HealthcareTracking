package com.example.healthcaretracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Dieta_seleccionada extends Activity {

	private int dieta_dia;
	TextView textview_1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dieta_seleccionada);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		
		Bundle extras = getIntent().getExtras();
		
		dieta_dia = extras.getInt("dia");
		
		getActionBar().setTitle("Dieta para el dia: "+Dieta.nombredia[dieta_dia]);
		
		textview_1 = (TextView) findViewById(R.id.tv_descripciondieta_edit);
		textview_1.setText(Home.userglobal.miplandieta.Dias[dieta_dia]);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this,Home.class);
		startActivity(intent);
		finish();
	}

}
