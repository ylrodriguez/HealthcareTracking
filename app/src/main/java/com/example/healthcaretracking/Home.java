package com.example.healthcaretracking;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class Home extends Activity implements OnClickListener{



	UsuarioLocalStore usuarioLS;
	public static Usuario userglobal;
	RelativeLayout botones[]= new RelativeLayout[6];
	private Actualizar_datos actualiza = new Actualizar_datos();
	public static int id_evento=-1;
	public static int id=-1;
	
	/*Botones 0-5 en orden En El ARRAY
	  0-Calendario  
	  1-Medicamentos 
	  2-Citas programadas
	  3-Dieta 
	  4-Actividad Fisica 
	  5- Informacion
	*/
	
	//-------------------------------------------------
	//-------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_home);
		
		for(int i=0; i<6; i++){ //Obtiene los botones mediante un ciclo for
			
			String buttonID = "button"+(i+1); //Sirve para el obtener ID
			int resID = getResources().getIdentifier(buttonID, "id", "com.example.healthcaretracking");
			botones[i] = (RelativeLayout) findViewById(resID);
			botones[i].setOnClickListener(this);
			
		}
		
		Log.d("Activity.Home", "OnCreate, He creado layout.");
		
		usuarioLS = new UsuarioLocalStore(this);
		
		
		Log.d("Activity.Home", "OnCreate, Quiero recibir, si hay.");
		
		
		
	}
	//-------------------------------------------------
	//-------------------------------------------------
	
	@Override
	protected void onStart() {
		super.onStart();
		if(autenticariniciousuario() == true){
			Log.d("Activity.Home", "OnStart, Comienzo dentro del if...");
			
			if(userglobal == null){
				userglobal = usuarioLS.getLoggedInUser();
			}
			
			 VerificarNotificacion();
			 
			 actualiza.asignar_citas(Home.this);
			 actualiza.asignar_med(Home.this);
			 actualiza.asignar_acti(Home.this);
			 actualiza.asignar_dieta(Home.this);
			 
		}
		else{
			Log.d("Activity.Home", "OnStart, No hay inicio de sesion.");
			startActivity(new Intent(this, Login.class)); //Si no tiene inicion iniciada envia a login
			finish();
		}
		
	}
	//-------------------------------------------------
	//-------------------------------------------------
	
	@Override
	public void onClick(View v) { //Recibe todos los eventos onClick
		
		switch(v.getId()){ //Obtiene la ID de la "View" que notifica este OnClick method
		
		case R.id.button1: //CALENDARIO
			startActivity(new Intent(Home.this,Calendario.class));
			break;
		case R.id.button2: //MEDICAMENTOS
			startActivity(new Intent(Home.this,Medicamentosprogramados.class));
			break;
		case R.id.button3: //CITAS PROGRAMADAS
			startActivity(new Intent(Home.this,Citasprogramadas.class));
			break;
		case R.id.button4: //DIETA
			startActivity(new Intent(Home.this,Dieta.class));
			break;
		case R.id.button5: //ACTIVIDAD FISICA
			startActivity(new Intent(Home.this,Actividadfisica.class));
			break;
		case R.id.button6: //INFORMACION
			startActivity(new Intent(Home.this,Aboutme.class));
			break;
			
						}
		
	}
	//-------------------------------------------------
	//-------------------------------------------------
	
	private boolean autenticariniciousuario(){ //Autentica en la clase UsuarioLocalStore si tiene sesion iniciada
		Log.d("Aqui comienza todo", ".....");
		return usuarioLS.getUserLoggedIn();
	}
	
	//-------------------------------------------------
	//-------------------------------------------------	
	@Override
	public void onBackPressed() {
		Intent mainactivity = new Intent(Intent.ACTION_MAIN);
		mainactivity.addCategory(Intent.CATEGORY_HOME);
		mainactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(mainactivity);
		finish();
	}
	//-------------------------------------------------
	//-------------------------------------------------
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			return true;
		}
		if (id == R.id.log_out) {
			Intent mainactivity = new Intent(this,Login.class);
			usuarioLS.setUserLoggedIn(false);
			usuarioLS.ClearUserData();
			startActivity(mainactivity);
			finish();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
	private void VerificarNotificacion(){
		Bundle extras = getIntent().getExtras();
		
		if(extras == null){
			Log.d("Activity.Home", "Nulo: ");
			id_evento = -1;
			id = -1;
		}
		
		else{
			id_evento = extras.getInt("id_evento");
			id = extras.getInt("id");
			Log.d("Activity.Home", "Llego: "+id_evento);
			
			
		}
		
		
	}
	

	//-------------------------------------------------
	//-------------------------------------------------
	
	
}
