package com.smart.project;

import java.io.IOException;
import java.util.ArrayList;

import com.smart.vehicletypesettings.DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	String userid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		final EditText ET_userid = (EditText) findViewById(R.id.ET_userid);
		final EditText ET_password = (EditText) findViewById(R.id.ET_password);

		Button Btn_login = (Button) findViewById(R.id.Btn_login);
		Button Btn_Signup = (Button) findViewById(R.id.Btn_Signup);

		final DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(this);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

		Btn_Signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);

				startActivity(intent);
				finish();
			}
		});

		Btn_login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				String query = "SELECT * FROM usermaster where userid='" + ET_userid.getText().toString()
						+ "' and password='" + ET_password.getText().toString() + "' ;";

				Cursor cursor = dbAdapter.selectRecordsFromDB(query, null);
				if (cursor.moveToFirst()) {

					if (cursor.getString(11).equalsIgnoreCase("0")) {

						String query1 = "SELECT * FROM session_master where Status='0';";

						Cursor cursor1 = dbAdapter.selectRecordsFromDB(query1, null);
						if (cursor1.moveToFirst()) {

							Intent intent = new Intent(LoginActivity.this, SessionActivity.class);

							startActivity(intent);

						} else {

							Intent intent = new Intent(LoginActivity.this, SmartPActivity.class);

							/*
							 * intent.putExtra("useridKEY", ET_userid.getText()
							 * .toString());
							 */

							startActivity(intent);

						}

						// finish();
					} else {
						AlertDialog alert = new AlertDialog.Builder(LoginActivity.this).create();
						alert.setTitle("VALIDATION");
						// alert.setIcon(R.drawable.warning);
						alert.setMessage("Contact admin ,User was blocked");
						alert.setButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
							}
						});
						alert.show();
					}
				} else {

					AlertDialog alert = new AlertDialog.Builder(LoginActivity.this).create();
					alert.setTitle("VALIDATION");
					// alert.setIcon(R.drawable.warning);
					alert.setMessage("Invalid user id/password");
					alert.setButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alert.show();

				}
				if (cursor != null && !cursor.isClosed()) {
					cursor.close();
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login_menu, menu);
		return true;
	}

	// This method is called once the menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_Adminlogin:
			// Launch settings activity
			Intent i = new Intent(getBaseContext(), AdminLoginActivity.class);
			startActivity(i);
			finish();
			break;
		case R.id.action_RegisterUser:
			// Launch settings activity
			Intent i1 = new Intent(getBaseContext(), RegisterUserActivity.class);
			startActivity(i1);
			finish();
			break;

		case R.id.action_Exit:
			// Launch settings activity
			finish();
			break;

		// more code...
		}
		return true;
	}
}
