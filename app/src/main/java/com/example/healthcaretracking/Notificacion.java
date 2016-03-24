package com.example.healthcaretracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Notificacion extends Activity implements OnClickListener {

	
	private TextView textView_mensaje;
	private TextView textView_titulo;
	private int id_evento;
	private int id;
	private Button button_masinformacion;
	
	/* * 
	 * ID EVENTOS
	 * 		100 => CITAS
	 * 		
	 * 
	 * */
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notificacion);
		
		Bundle extras = getIntent().getExtras();
		
		id_evento = extras.getInt("id_evento");
		id = extras.getInt("id");
		String tiponot = getIntent().getStringExtra("tiponot");
		String mensaje = getIntent().getStringExtra("mensaje");
		
		button_masinformacion = (Button) findViewById(R.id.button_masinformacion);
		button_masinformacion.setOnClickListener(this);
		
		textView_mensaje = (TextView) findViewById(R.id.textView_mensaje);
		textView_titulo  = (TextView) findViewById(R.id.textView_titulo);
		
		textView_titulo.setText(tiponot);
		textView_mensaje.setText(mensaje);
	}

	@Override
	public void onBackPressed() {
		Intent home = new Intent(this,Home.class);
		home.putExtra("id_evento",(int)id_evento);
		home.putExtra("id",(int)id);
		
		startActivity(home);
		finish();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){ //Obtiene la ID de la "View" que notifica este OnClick method
		
			case R.id.button_masinformacion:
				if(id_evento == 100){
					Intent home = new Intent(this,Home.class);
					home.putExtra("id_evento",(int)id_evento);
					home.putExtra("id",(int)id);
					
					startActivity(home);
					finish();
				}
				if(id_evento == 200){
					Intent home = new Intent(this,Home.class);
					home.putExtra("id_evento",(int)id_evento);
					home.putExtra("id",(int)id);
					
					startActivity(home);
					finish();
				}
				if(id_evento == 300){
					Intent home = new Intent(this,Home.class);
					home.putExtra("id_evento",(int)id_evento);
					home.putExtra("id",(int)id);
					
					startActivity(home);
					finish();
				}
				if(id_evento == 400){
					Intent home = new Intent(this,Home.class);
					home.putExtra("id_evento",(int)id_evento);
					home.putExtra("id",(int)id);
					
					startActivity(home);
					finish();
				}
				break;
			
		}
		
	}
}
