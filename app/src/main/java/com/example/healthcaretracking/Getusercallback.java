package com.example.healthcaretracking;

interface Getusercallback {
	
	public abstract void done(Usuario usuarioretornado);//Para usuario
}

interface Getusercallback2 { //Para citas
	
	public abstract void done(Citas[] citasretornadas);
}

interface Getusercallback3 { //Para medicamentos
	
	public abstract void done(Medicamentos[] medicamentosretornadas);
}

interface Getusercallback4 { //Para actividades
	
	public abstract void done(Actividad[] actividadesretornadas);
}

interface Getusercallback5 //Para plan dieta
{
	public abstract void done(Plan_dieta plandietaretornado);
}
