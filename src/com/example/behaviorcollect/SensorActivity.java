package com.example.behaviorcollect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SensorActivity extends Activity {

	SensorManager sensorManager;
	TextView textView;
	Button listen;
	Button polling;
	String filePath = "/BehaviorCollectData";
	PrintWriter pwData = null;
	PrintWriter pwFile = null;
	BufferedReader br = null;
	Handler handler = null;
	TimeService timeService = new TimeService();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		
		listen = (Button) findViewById(R.id.listen);
		polling = (Button) findViewById(R.id.polling);
		textView = (TextView) findViewById(R.id.tv);
		System.out.println("hi");
		listen.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Thread thread = new Thread(timeService);
				thread.start();
			}
		});
		
		polling.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("calculating");
				new Thread(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for(int i = 0; i < 1000;i++){
							System.out.println(i);
							try {
								timeService.pollingTime();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						//textView.setText("avg time is"+timeService.getAvgTime());
						System.out.println("avg time is "+timeService.getAvgTime());
					}
					
				}.start();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
		return true;
	}
	
}
