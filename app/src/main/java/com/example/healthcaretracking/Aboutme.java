package com.example.healthcaretracking;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Aboutme extends Activity { 
	//Actividad al hacer click en información

	private TextView tv_editname,tv_editlastname,tv_editusername,tv_editfechanac;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutme);
		
		tv_editname = (TextView) findViewById(R.id.tv_editname);
		tv_editlastname = (TextView) findViewById(R.id.tv_editlastname);
		tv_editusername = (TextView) findViewById(R.id.tv_editusername);
		tv_editfechanac = (TextView) findViewById(R.id.tv_editfechanac);
		Mostrarinformacion();
	}
	
	
	private void Mostrarinformacion(){
		Usuario user = Home.userglobal;
		
		tv_editname.setText(user.nombre);
		tv_editlastname.setText(user.apellido);
		tv_editusername.setText(user.username);
		tv_editfechanac.setText(user.fechanac);
	}

}
