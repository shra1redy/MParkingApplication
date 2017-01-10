package com.smart.project;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.UUID;




import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainScreenActivity extends Activity {

	String userid;
	
	private static final int REQUEST_ENABLE_BT = 1;
	BluetoothAdapter bluetoothAdapter;
	static final UUID MY_UUID = UUID.randomUUID();
	protected String btAddressDir = Environment.getExternalStorageDirectory()
			+ "";
	String address = null;

	Button Btn_parkingTicket;
	Button Btn_mainscreenSettings;
	Button Btn_reports;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);

	 userid=getIntent().getStringExtra("useridKEY");
		
	

			try {

				FileInputStream fstream = new FileInputStream(btAddressDir
						+ "/BTaddress.txt");

				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				String strLine;

				while ((strLine = br.readLine()) != null) {
					address = strLine;
				}

				in.close();
			} catch (Exception e) {// Catch exception if any
				System.err.println("Error: " + e.getMessage());

			}
		

		Btn_parkingTicket = (Button) findViewById(R.id.Btn_editVehicletypesettings);
		Btn_mainscreenSettings = (Button) findViewById(R.id.Btn_bluetoothSettings);
		Btn_reports= (Button) findViewById(R.id.Btn_reports);
		
		Btn_parkingTicket.setOnClickListener(new OnClickListener() {
			public void onClick(View v) 
			{
				Intent intent = new Intent(MainScreenActivity.this,
						SmartPActivity.class);
				
						startActivity(intent);
				finish();
			}
			
		});

		Btn_mainscreenSettings.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});
		
		
		
		Btn_reports.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				
			}
		});
		

	}



}
