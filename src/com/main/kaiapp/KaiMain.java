package com.main.kaiapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class KaiMain extends Activity {
	
	private Button syncButton;

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
	}
	
	
}
