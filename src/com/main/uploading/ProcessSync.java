package com.main.uploading;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.main.kaiapp.PhotoHandler;

public class ProcessSync extends AsyncTask<Integer, Void, Void> {
	private Context _parentContext;
	private boolean success = false;
	
	public ProcessSync(Context parentContext) {
		this._parentContext = parentContext;
	}
	
	@Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
	
    @Override
    protected Void doInBackground(Integer... params) {
    	Camera camera = Camera.open(params[0]);
    	
    	File pictureFileDir = getDir();

	    if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
	      Toast.makeText(_parentContext, "Can't create directory to save image.", Toast.LENGTH_LONG).show();
	      success = false;
	      return null;

	    }
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
	    String date = dateFormat.format(new Date());
	    String photoFile = "Pic_" + date + ".jpg";

	    String filename = pictureFileDir.getPath() + File.separator + photoFile;
		camera.takePicture(null, null, new PhotoHandler(_parentContext, filename) );
		
        try {
        	Thread.sleep(3000);
        	Mail m = new Mail(filename); 
        	success = m.send();
        } catch (Exception e) {
        	success = false;
        	e.printStackTrace();
        }
        return null;
    }
    
    private File getDir() {
	    File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	    return new File(sdDir, "KaiCamera");
	}
    
    @Override
    protected void onPostExecute(Void v) {
        if(success){
        	Toast.makeText(_parentContext, "Image sent", Toast.LENGTH_LONG).show();
        }else{
        	Toast.makeText(_parentContext, "Image sent failed", Toast.LENGTH_LONG).show();
        }
    }
    
}
