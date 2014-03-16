package com.main.kaiapp;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.widget.Toast;

import com.main.uploading.ProcessSync;

public class PhotoHandler implements PictureCallback {

	  private final Context context;
	  private final String filename;

	  public PhotoHandler(Context context, String filename) {
	    this.context = context;
	    this.filename = filename;
	  }

	  @Override
	  public void onPictureTaken(byte[] data, Camera camera) {
	    File pictureFile = new File(filename);

	    try {
	      FileOutputStream fos = new FileOutputStream(pictureFile);
	      fos.write(data);
	      fos.close();
	      Toast.makeText(context, "New Image saved:" + filename,Toast.LENGTH_LONG).show();
	      
	    } catch (Exception error) {
	    	error.printStackTrace();
	        Toast.makeText(context, "Image could not be saved.", Toast.LENGTH_LONG).show();
	    }
	  }

} 


	