package com.main.kaiapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.main.uploading.ProcessSync;

public class KaiMain extends Activity {
	private final static String DEBUG_TAG = "KaiAppMain";
	
	private Button syncButton;
	private Camera camera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kai_main);
		
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
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
	    if (camera != null) {
	      camera.release();
	      camera = null;
	    }
	    super.onPause();
	  }
	
	
	private void addListenerOnButton() {
		syncButton = (Button) findViewById(R.id.buttonSync);
		syncButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ScheduledReceiver.startSync();
			}
		});
	}
	
	
	
	
}
