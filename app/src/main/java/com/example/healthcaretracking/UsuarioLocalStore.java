package com.example.healthcaretracking;

import android.content.Context;
import android.content.SharedPreferences;

public class UsuarioLocalStore {
	
	public static final String SP_Name ="DetallesUsuario";
	SharedPreferences userLocalDB;
	SharedPreferences usuario_alarmas;
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	public UsuarioLocalStore(Context context){
		userLocalDB = context.getSharedPreferences(SP_Name, 0);
		usuario_alarmas = context.getSharedPreferences("AlarmasCitas", 0);
		
	}
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	// ALMACENAR DATA DE USUARIO
	public void storeUserData(Usuario user){
		
		SharedPreferences.Editor spEditor = userLocalDB.edit();
		spEditor.putString("username", user.username);
		spEditor.putString("password", user.password);
		spEditor.putString("nombre", user.nombre);
		spEditor.putString("apellido", user.apellido);
		spEditor.putString("apellido", user.fechanac);
		spEditor.putInt("id", user.id);
		spEditor.commit();
	}
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	//	OBTENEMOS USUARIO QUE YA HA INICIADO SESION
	public Usuario getLoggedInUser(){ 
		//Recibimos toda la informacion del usuario que ya esta en
		//el almacenamiento de la BD local y lo retornamos en un objeto Usuario
		
		String nombre = userLocalDB.getString("nombre", "");
		String apellido = userLocalDB.getString("apellido", "");
		String username = userLocalDB.getString("username", "");
		String password = userLocalDB.getString("password", "");
		String fechanac = userLocalDB.getString("fechanac", "");
		int id = userLocalDB.getInt("id", -1);
		
		Usuario user = new Usuario(id,nombre, apellido,username, password, fechanac);
		return user;
	}
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	// 	EDITAMOS EL ESTADO DE INICIO DE SESION: TRUE = ONLINE - FALSE = FUERA DE LINEA
	public void setUserLoggedIn(boolean iniciosesion){ 
		//Sabemos si el usuario tiene sesion iniciada o no.
		
		SharedPreferences.Editor spEditor = userLocalDB.edit();
		spEditor.putBoolean("iniciosesion", iniciosesion);
		spEditor.commit();
	}
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	//	OBTENEMOS EL ESTADO DE INICIO DE SESION
	public boolean getUserLoggedIn(){
		
		//Retornamos en una variable boolean
		
		if(userLocalDB.getBoolean("iniciosesion", false) == true){
			return true;
		}
		else{
			return false;
		}
	}
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	//	LIMPIAMOS INFORMACION
	public void ClearUserData(){ 
		//Limpia toda la informacion cuando cierra sesion
		
		SharedPreferences.Editor spEditor = userLocalDB.edit();
		spEditor.clear();
		spEditor.commit();
	}
	
	
	
	public void setAlarma(String key){
		SharedPreferences.Editor spEditor = usuario_alarmas.edit();
		spEditor.putBoolean(key, true); //Esta guardada
		spEditor.commit();
	}
	
	public boolean getStatusAlarma(String key){
		
		if(usuario_alarmas.getBoolean(key, false) == true){
			return true;
		}
		else{
			return false;
		}
	}
	
}
