package com.main.kaiapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.main.uploading.ProcessSync;

public class ScheduledReceiver extends BroadcastReceiver {
	private Context mContext;

	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;
		
	}
	
	public void startSync() {
		if (mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			launchProcessSync();
		} else {
			Toast.makeText(mContext, "Camera unavailable", Toast.LENGTH_LONG).show();
		}
		
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
	
	public void launchProcessSync() {
		//find front camera (or fall back to back camera)
		int numberOfCameras = Camera.getNumberOfCameras();
		int cameraId = -1;
		for (int i = 0; i < numberOfCameras; i++) {
	      CameraInfo info = new CameraInfo();
	      Camera.getCameraInfo(i, info);
	      cameraId = i;
	      if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
	        break;
	      }
	    }
		
		ProcessSync ps = new ProcessSync(mContext.getApplicationContext());
        ps.execute(cameraId);
	}
	
}
