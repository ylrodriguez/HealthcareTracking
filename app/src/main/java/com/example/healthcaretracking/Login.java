package com.example.healthcaretracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements OnClickListener {

	
	Button b_login; 				// Botón inicio de sesión
	EditText et_user,et_password;	// Edit Text de usuario y contraseña
	UsuarioLocalStore usuarioLS;
	
	//-------------------------------------------------
	//-------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		et_user = (EditText) findViewById(R.id.et_user);  //Asigna edit text user del layout
		et_password = (EditText) findViewById(R.id.et_password);  //Asigna edit text password del layout
		b_login = (Button) findViewById(R.id.b_login); //Asigna boton de inicio sesion del layout
		
		b_login.setOnClickListener(this);
		
		
		usuarioLS = new UsuarioLocalStore(this);
		
	}
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	@Override
	public void onClick(View v) {
		
		switch(v.getId()){ //Obtiene la ID de la "View" que notifica este OnClick method
		case R.id.b_login:
			
			String username = et_user.getText().toString();
			String password = et_password.getText().toString();
			
			
			
			Usuario user = new Usuario(username,password);
			
			autenticarusuario(user);
			
			
			break;
		}
		
	}
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	private void autenticarusuario(Usuario user){
		
		PeticionServer peticion = new PeticionServer(this,1);
		peticion.LogIn(user, new Getusercallback(){
		//peticion.GuardarDataUsuario(user, new Getusercallback(){;
			@Override
			public void done(Usuario usuarioretornado) {
				// TODO Auto-generated method stub
				if(usuarioretornado == null){
					MensajeError();
				}
				else{
					Iniciarsesionusuario(usuarioretornado);
					
				}
			}
			
			
		} );
	}
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	
	private void MensajeError(){
		
		AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(Login.this);
		dialogbuilder.setMessage("Usuario y/o Contraseña incorrecta");
		dialogbuilder.setPositiveButton("Ok", null);
		dialogbuilder.show();
	}
	
	//-------------------------------------------------
	//-------------------------------------------------	
	
	private void Iniciarsesionusuario(Usuario usuarioretornado){
		
		usuarioLS.storeUserData(usuarioretornado);
		usuarioLS.setUserLoggedIn(true);
		
		Home.userglobal = usuarioretornado;
		
		Intent i = new Intent(this,Home.class);
		startActivity(i);
		finish();
	}

	@Override
	public void onBackPressed() {
		Intent mainactivity = new Intent(Intent.ACTION_MAIN);
		mainactivity.addCategory(Intent.CATEGORY_HOME);
		mainactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(mainactivity);
		finish();
	}
	


	
}
