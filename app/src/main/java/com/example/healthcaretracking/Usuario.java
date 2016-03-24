package com.example.healthcaretracking;

public class Usuario {

	String username,nombre,apellido,password,fechanac;
	int id;
	
	int numcitas;
	Citas miscitas[];
	Medicamentos mismed[];
	Actividad misactivities[];
	Plan_dieta miplandieta;
	
	
	//Cuando solo se conoce username y contraseña.
	public Usuario(String username, String password){
		this.username = username;
		this.password = password;
	} 
	
	
	//Cuando se conocen todos los datos.
	public Usuario(int id,String nombre, String apellido,String username,String password, String fechanac){
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.fechanac = fechanac;
	}
	
	public void agregarcitas(Citas miscitas[], int numcitas){
		this.numcitas=numcitas;
		this.miscitas=miscitas;
	}
	
	public void agregarmeds(Medicamentos mismed[]){
		this.mismed=mismed;
	}

	public void agregaracts(Actividad misactivities[]){
		this.misactivities=misactivities;
	}
	
	public void agregardieta(Plan_dieta miplandieta){
		this.miplandieta = miplandieta;
	}
	
}
