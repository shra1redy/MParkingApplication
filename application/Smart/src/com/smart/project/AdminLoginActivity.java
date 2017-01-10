
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

public class AdminLoginActivity extends Activity {
	String userid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminlogin);
		final EditText ET_userid = (EditText) findViewById(R.id.ET_userid);
		final EditText ET_password = (EditText) findViewById(R.id.ET_password);

		Button Btn_login = (Button) findViewById(R.id.Btn_login);
		

		final DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(this);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}
		dbAdapter.openDataBase();

	

		Btn_login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				String query = "SELECT * FROM usermaster where userid='"
						+ ET_userid.getText().toString() + "' and password='"
						+ ET_password.getText().toString() + "' and usertype='1';";

				Cursor cursor = dbAdapter.selectRecordsFromDB(query, null);
				if (cursor.moveToFirst()) {

				

						Intent intent = new Intent(AdminLoginActivity.this,
								AdminMenuActivity.class);

						startActivity(intent);

					

				} else {

					AlertDialog alert = new AlertDialog.Builder(
							AdminLoginActivity.this).create();
					alert.setTitle("VALIDATION");
					// alert.setIcon(R.drawable.warning);
					alert.setMessage("Invalid user id/password");
					alert.setButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
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
	
}
