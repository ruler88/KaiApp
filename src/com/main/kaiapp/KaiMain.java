package com.main.kaiapp;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class KaiMain extends Activity {
	private final static String DEBUG_TAG = "KaiAppMain";
	
	private long timeInterval = (long) 1000*60*1;
	
	private Button syncButton;
	private Camera camera;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kai_main);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		mContext = this;
		
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intentAlarm = new Intent(this, ScheduledReceiver.class);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), timeInterval, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
		
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kai_main, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
	    super.onPause();
	}
	
	
	private void addListenerOnButton() {
		syncButton = (Button) findViewById(R.id.buttonSync);
		syncButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(mContext, "Don't press me mofo", Toast.LENGTH_SHORT).show();
				//ScheduledReceiver.startSync();
			}
		});
	}
	
	
	
	
}
