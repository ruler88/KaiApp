package com.main.kaiapp;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class KaiMain extends Activity {
	private final static String DEBUG_TAG = "KaiAppMain";
	
	private Button syncButton;
	private Camera camera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kai_main);
		
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
				startSync();
			}
		});
	}
	
	private void startSync() {
		//TODO: take camera photo and upload
		//upload
		cameraCap();
	}
	
	private void cameraCap() {
		if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			//find front camera (or fall back to back camera)
			int numberOfCameras = Camera.getNumberOfCameras();
			int cameraId = -1;
			for (int i = 0; i < numberOfCameras; i++) {
		      CameraInfo info = new CameraInfo();
		      Camera.getCameraInfo(i, info);
		      cameraId = i;
		      if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
		        Log.d(DEBUG_TAG, "Camera found");
		        break;
		      }
		    }
			
			camera = Camera.open(cameraId);
			camera.takePicture(null, null, new PhotoHandler(getApplicationContext()));
		} else {
			Toast.makeText(this, "Camera unavailable", Toast.LENGTH_LONG).show();
		}
	}
	
	
}
