package com.example.healthcaretracking;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class PeticionServer {

	ProgressDialog progressDial;
	
	public static final int CONNECTION_TIMEOUT = 1000 * 15;
	public static final String Server_adress = "http://healthcaretracking.comeze.com/";
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	public PeticionServer(Context context, int flag){
		
		switch(flag){
		case 1:
			progressDial = new ProgressDialog(context);
			progressDial.setCancelable(false);
			progressDial.setTitle("Cargando");
			progressDial.setMessage("Por favor espere...");
			break;
		case 2:
			progressDial = new ProgressDialog(context);
			progressDial.setCancelable(true);
			progressDial.setTitle("Actualizando datos");
			progressDial.setMessage("Por favor espere...");
			
		}
		
	}
	
	//-------------------------------------------------
	//          ***********************
	//-------------------------------------------------
	
	public void LogIn(Usuario user, Getusercallback callback){
		progressDial.show();
		new LogInAsync(user,callback).execute();
	}
	
	//-------------------------------------------------
	//-------------------------------------------------
					
	public class LogInAsync extends AsyncTask<Void,Void,Usuario>{ // void void user

		Usuario user;
		Getusercallback usercallback;
		
		
		public LogInAsync(Usuario user,Getusercallback usercallback){
			
			this.user = user;
			this.usercallback = usercallback;
		}
		
		@Override
		protected Usuario doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			HttpParams httpRequestParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			
			HttpClient client = new DefaultHttpClient(httpRequestParams);
			HttpPost post = new HttpPost(Server_adress + "iniciosesion.php");
			
			

			ArrayList<NameValuePair>dataToSend = new ArrayList<>();
			
			dataToSend.add(new BasicNameValuePair("username", user.username));
			dataToSend.add(new BasicNameValuePair("password", user.password));
			
			Usuario returneduser = null;
			
			try{
				post.setEntity(new UrlEncodedFormEntity(dataToSend));
				HttpResponse httpresponse = client.execute(post);
				HttpEntity entity = httpresponse.getEntity();
				
				
				String result = EntityUtils.toString(entity);
				Log.d("",result);
				
				JSONObject jobject = new JSONObject(result);
				
				if(jobject.length() == 0){
					returneduser = null;
				}
				else{
					String usuario = jobject.getString("username");
					String pass = jobject.getString("password");
					String nombre = jobject.getString("nombre");
					String apellido = jobject.getString("apellido");
					String fechanac = jobject.getString("fechanac");
					int id =  jobject.getInt("id");
					
					returneduser = new Usuario(id,nombre,apellido,usuario,pass,fechanac);
				}
				
			}
			
			catch(Exception e){
				e.printStackTrace();
				return returneduser;
			}
			return returneduser;
		}
		
		@Override
		protected void onPostExecute(Usuario result) {
			// TODO Auto-generated method stub
			progressDial.dismiss();
			usercallback.done(result);
			super.onPostExecute(result);
		}
		
		
		
	}
	
	//-------------------------------------------------
	// 			 ***********************
	//-------------------------------------------------
	
	//-------------------------------------------------
	// 			 ***********************
	//-------------------------------------------------	

	public void ConsultarCitas(Usuario user, Getusercallback2 callback){
		progressDial.show();
		new ConsultarCitasAsync(user,callback).execute();
	}
	
	//-------------------------------------------------
	//-------------------------------------------------
	
	public class ConsultarCitasAsync extends AsyncTask<Void,Void,Citas[]>{

		Usuario user;
		Getusercallback2 usercallback;
		
		public ConsultarCitasAsync(Usuario user,Getusercallback2 usercallback){
			
			
			this.user = user;
			this.usercallback = usercallback;
		}
		

		@Override
		protected Citas[] doInBackground(Void... params) {
		
			HttpParams httpRequestParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			
			HttpClient client = new DefaultHttpClient(httpRequestParams);
			HttpPost post = new HttpPost(Server_adress + "obtenercitas.php");
			
			
			ArrayList<NameValuePair>dataToSend = new ArrayList<>();
			
			dataToSend.add(new BasicNameValuePair("id_usuario", ""+user.id));
			// no se sabe si recibe enteros.
			
			Citas citasaretornar[] = null;
			Citas.setBandera_pruebas(true); //Edita la bandera de pruebas 
			try{
				post.setEntity(new UrlEncodedFormEntity(dataToSend));
				HttpResponse httpresponse = client.execute(post);
				HttpEntity entity = httpresponse.getEntity();
				
				
				String result = EntityUtils.toString(entity);
				Log.d("Resultado Citas: ",result);
				
				
				JSONArray jArray = new JSONArray(result);
				JSONObject jObject;
				
				if(jArray.length() == 0){
					citasaretornar = new Citas[0];
				}
				else{
					
					citasaretornar = new Citas[jArray.length()];
					
					for(int i=0; i<jArray.length(); i++){
						
						jObject = jArray.getJSONObject(i);
						
						int id_cita = jObject.getInt("id_cita");
						int sala = jObject.getInt("sala");
						int telefono = jObject.getInt("telefono");
						String lugar = jObject.getString("lugar");
						String fecha = jObject.getString("fecha");
						String direccion = jObject.getString("direccion");
						String nombre_doctor = jObject.getString("nombre_doctor");
						nombre_doctor+= " "+jObject.getString("apellido_doctor");


						citasaretornar[i] = new Citas(id_cita,lugar,fecha,direccion,sala,nombre_doctor,telefono);
					}
					
				}
				
			}
			
			catch(Exception e){
				e.printStackTrace();
				return citasaretornar;
			}
			
			Log.d("PeticionServer.class","# Citas que retorno: "+citasaretornar.length);
			return citasaretornar;
			
			
		}
		
		@Override
		protected void onPostExecute(Citas[] result) {
			progressDial.dismiss();
			usercallback.done(result);
			super.onPostExecute(result);
		}
		
		
	}
	
	//-------------------------------------------------
	//  			***********************
	//-------------------------------------------------
	
	
	public void ConsultarMedicamentos(Usuario user, Getusercallback3 callback){
		progressDial.show();
		new ConsultarMedicamentosAsync(user,callback).execute();
	}
	
	
	public class ConsultarMedicamentosAsync extends AsyncTask<Void,Void,Medicamentos[]>{

		
		Usuario user;
		Getusercallback3 usercallback;
		
		public ConsultarMedicamentosAsync(Usuario user,Getusercallback3 usercallback){
			
			
			this.user = user;
			this.usercallback = usercallback;
		}
		
		@Override
		protected Medicamentos[] doInBackground(Void... params) {
			
			HttpParams httpRequestParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			
			HttpClient client = new DefaultHttpClient(httpRequestParams);
			HttpPost post = new HttpPost(Server_adress + "obtenermed.php");
			
			
			ArrayList<NameValuePair>dataToSend = new ArrayList<>();
			
			dataToSend.add(new BasicNameValuePair("id_usuario", ""+user.id));
			// no se sabe si recibe enteros.
			
			Medicamentos medsretornar[] = null;
			Medicamentos.setBandera_pruebas(true); //Edita la bandera de pruebas 
			try{
				post.setEntity(new UrlEncodedFormEntity(dataToSend));
				HttpResponse httpresponse = client.execute(post);
				HttpEntity entity = httpresponse.getEntity();
				
				
				String result = EntityUtils.toString(entity);
				Log.d("Resultado Medicamentos: ",result);
				
				
				JSONArray jArray = new JSONArray(result);
				JSONObject jObject;
				
				if(jArray.length() == 0){
					medsretornar = new Medicamentos[0];
				}
				else{
					
					medsretornar = new Medicamentos[jArray.length()];
					
					for(int i=0; i<jArray.length(); i++){
						
						jObject = jArray.getJSONObject(i);
						
						int med_codigo = jObject.getInt("med_codigo");
						int med_cantidadxDia = jObject.getInt("med_cantidadxDia");
						int med_intervalo = jObject.getInt("med_intervalo");
						String med_nombre = jObject.getString("med_nombre");
						String med_fechainicio = jObject.getString("med_fechainicio");
						String med_fechafinal = jObject.getString("med_fechafinal");


						medsretornar[i] = new Medicamentos(med_codigo,med_cantidadxDia,med_intervalo,med_nombre,med_fechainicio,med_fechafinal);
					}
					
				}
				
			}
			
			catch(Exception e){
				e.printStackTrace();
				return medsretornar;
			}
			
			Log.d("PeticionServer.class","# Medicamentos que retorno: "+medsretornar.length);
			return medsretornar;
		}

		@Override
		protected void onPostExecute(Medicamentos[] result) {
			progressDial.dismiss();
			usercallback.done(result);
			super.onPostExecute(result);
		}
	
	}
	
	//-------------------------------------------------
	//  			***********************
	//-------------------------------------------------
	
	public void ConsultarActividades(Usuario user, Getusercallback4 callback){
		progressDial.show();
		new ConsultarActividadesAsync(user,callback).execute();
	}
	
	public class ConsultarActividadesAsync extends AsyncTask<Void,Void,Actividad[]>{
		
		
		Usuario user;
		Getusercallback4 usercallback;
		
		public ConsultarActividadesAsync(Usuario user,Getusercallback4 usercallback){
			this.user = user;
			this.usercallback = usercallback;
		}

		@Override
		protected Actividad[] doInBackground(Void... params) {

			HttpParams httpRequestParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			
			HttpClient client = new DefaultHttpClient(httpRequestParams);
			HttpPost post = new HttpPost(Server_adress + "obteneract.php");
			
			
			ArrayList<NameValuePair>dataToSend = new ArrayList<>();
			
			dataToSend.add(new BasicNameValuePair("id_usuario", ""+user.id));
			// no se sabe si recibe enteros.
			
			Actividad actretornar[] = null;
			//Actividad.setBandera_pruebas(true); //Edita la bandera de pruebas 
			try{
				post.setEntity(new UrlEncodedFormEntity(dataToSend));
				HttpResponse httpresponse = client.execute(post);
				HttpEntity entity = httpresponse.getEntity();
				
				
				String result = EntityUtils.toString(entity);
				Log.d("Resultado Actividades: ",result);
				
				
				JSONArray jArray = new JSONArray(result);
				JSONObject jObject;
				
				if(jArray.length() == 0){
					actretornar = new Actividad[0];
				}
				else{
					
					actretornar = new Actividad[jArray.length()];
					
					for(int i=0; i<jArray.length(); i++){
						
						jObject = jArray.getJSONObject(i);
						
						int actividad_id = jObject.getInt("actividad_id");
						String actividad_descr = jObject.getString("actividad_descr");
						String fecha_final = jObject.getString("fecha_final");


						actretornar[i] = new Actividad(actividad_id,actividad_descr,fecha_final);
					}
					
				}
				
			}
			
			catch(Exception e){
				e.printStackTrace();
				return actretornar;
			}
			
			Log.d("PeticionServer.class","# Actividades que retorno: "+actretornar.length);
			return actretornar;
		}
		
		@Override
		protected void onPostExecute(Actividad[] result) {
			progressDial.dismiss();
			usercallback.done(result);
			super.onPostExecute(result);
		}

		
	}
	//-------------------------------------------------
	//  			***********************
	//-------------------------------------------------
	
	public void ConsultarDieta(Usuario user, Getusercallback5 callback){
		progressDial.show();
		new ConsultarDietaAsync(user,callback).execute();
	}
	
	public class ConsultarDietaAsync extends AsyncTask<Void,Void,Plan_dieta>{
		


		Usuario user;
		Getusercallback5 usercallback;
		
		
		public ConsultarDietaAsync(Usuario user,Getusercallback5 usercallback){
			
			this.user = user;
			this.usercallback = usercallback;
		}


		@Override
		protected Plan_dieta doInBackground(Void... params) {
			
			HttpParams httpRequestParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			
			HttpClient client = new DefaultHttpClient(httpRequestParams);
			HttpPost post = new HttpPost(Server_adress + "obtenerdieta.php");
			
			
			ArrayList<NameValuePair>dataToSend = new ArrayList<>();
			
			dataToSend.add(new BasicNameValuePair("id_usuario", ""+user.id));
			// no se sabe si recibe enteros.
			
			Plan_dieta dietaretorno = null;
			//Actividad.setBandera_pruebas(true); //Edita la bandera de pruebas 
			try{
				post.setEntity(new UrlEncodedFormEntity(dataToSend));
				HttpResponse httpresponse = client.execute(post);
				HttpEntity entity = httpresponse.getEntity();
				
				
				String result = EntityUtils.toString(entity);
				Log.d("Resultado Actividades: ",result);
				
				
				JSONArray jArray = new JSONArray(result);
				JSONObject jObject;
				
				if(jArray.length() == 0){
					dietaretorno = null;
				}
				else{
					
					jObject = jArray.getJSONObject(0);
					String dias[] = new String[7];
					
					int plan_id = jObject.getInt("plan_id");
					String lun = jObject.getString("lun");
					dias[0] = lun;
					String mar = jObject.getString("mar");
					dias[1] = mar;
					String mie = jObject.getString("mie");
					dias[2] = mie;
					String jue = jObject.getString("jue");
					dias[3] = jue;
					String vie = jObject.getString("vie");
					dias[4] = vie;
					String sab = jObject.getString("sab");
					dias[5] = sab;
					String dom = jObject.getString("dom");
					dias[6] = dom;
					
					
					dietaretorno = new Plan_dieta(plan_id,dias);
					
					
				}
				
			}
			
			catch(Exception e){
				e.printStackTrace();
				return dietaretorno;
			}
			
			Log.d("PeticionServer.class","Retorno dieta, correcto");
			return dietaretorno;
		}
		
		@Override
		protected void onPostExecute(Plan_dieta result) {
			progressDial.dismiss();
			usercallback.done(result);
			super.onPostExecute(result);
		}
	}
	
	
}
