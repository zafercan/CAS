package com.example.cas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		TextView t1 =(TextView) findViewById(R.id.appname1);
		TextView t2 =(TextView) findViewById(R.id.appname3);
		TextView t3 =(TextView) findViewById(R.id.appname2);
		
		Typeface type = Typeface.createFromAsset(this.getAssets(),"green avocado.ttf");
		t1.setTypeface(type);
		t2.setTypeface(type);
		t3.setTypeface(type);
		
		Thread mSplashThread;
		mSplashThread = new Thread() {
			@Override
			public void run() {
				try {

					synchronized (this) {
						wait(4000);
					}
				} catch (InterruptedException ex) {

				} finally {

					Intent i = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(i);
					finish();
				}

			}
		};
		mSplashThread.start();
	}

}
