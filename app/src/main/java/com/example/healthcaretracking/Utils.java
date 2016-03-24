package com.example.healthcaretracking;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Utils {
	public static NotificationManager mManager;

	@SuppressWarnings("static-access")
	public static void generateNotification(Context context, String mensaje, int id_alarma, String tiponot, int id_evento, int id){ 
		
		mManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		Notification.Builder builder = new Notification.Builder(context);
		
		Intent intent = new Intent(context,Notificacion.class);
		
		intent.putExtra("id_evento",id_evento);
		intent.putExtra("id",id);
		intent.putExtra("mensaje", mensaje);
		intent.putExtra("tiponot", tiponot);
		
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent pendingNotificationIntent = PendingIntent.getActivity(context,id_alarma, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		
		
		
		
		builder .setStyle(new Notification.BigTextStyle()
				.bigText(mensaje))
				.setTicker(tiponot+"\n"+mensaje)
				.setPriority(Notification.PRIORITY_MAX)
				.setSmallIcon(R.drawable.ic_launcher)
				.setWhen(System.currentTimeMillis())
				.setContentTitle(tiponot)
				.setContentIntent(pendingNotificationIntent)
				.setDefaults(Notification.DEFAULT_ALL)
				.setAutoCancel(true)
				.build();
		
		@SuppressWarnings("deprecation")
		Notification notification = builder.getNotification();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		//notification.flags |= Notification.FLAG_INSISTENT;
		
		
		mManager.notify(id_alarma, notification);
		
	}
}
