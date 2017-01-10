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

public class AdminMenuActivity extends Activity {

	String userid;

	Button btn_addLoction;
	Button btn_blockuser;
	Button btn_unblock;
	Button Btn_reports;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_menu_screen);

		btn_addLoction = (Button) findViewById(R.id.btn_addLoction);
		btn_blockuser = (Button) findViewById(R.id.btn_blockuser);
		btn_unblock = (Button) findViewById(R.id.btn_unblock);
		Btn_reports = (Button) findViewById(R.id.Btn_reports);
		btn_addLoction.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(AdminMenuActivity.this, AddLocationActivity.class);

				startActivity(intent);

			}

		});

		btn_blockuser.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(AdminMenuActivity.this, BlockUserActivity.class);

				startActivity(intent);

			}
		});

		btn_unblock.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(AdminMenuActivity.this, UnblockUserActivity.class);

				startActivity(intent);

			}
		});
		Btn_reports.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(AdminMenuActivity.this, ReportsMenuActivity.class);

				startActivity(intent);

			}

		});

	}

}
