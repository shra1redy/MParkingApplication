
package com.smart.project;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.smart.vehicletypesettings.DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BlockUserActivity extends Activity {

	ArrayList<String> userlist = new ArrayList<String>();

	String selected_user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.block_user);
		Spinner sp_userid = (Spinner) findViewById(R.id.sp_userid);

		Button Btn_blockuser = (Button) findViewById(R.id.Btn_blockuser);

		String userQuery = "Select * from usermaster where usertype='0' and blockstatus ='0'  ;";

		ArrayList<String> labels = getAllLabels(userQuery);

		ArrayAdapter adapter = new ArrayAdapter(BlockUserActivity.this, android.R.layout.simple_spinner_item, labels);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sp_userid.setAdapter(adapter);

		sp_userid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				selected_user = parent.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		Btn_blockuser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AlertDialog.Builder builder = new AlertDialog.Builder(BlockUserActivity.this);

				builder.setTitle("Confirm");
				builder.setMessage("Do you want to block the " + selected_user);

				builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// Do nothing but close the dialog

						DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(BlockUserActivity.this);
						dbAdapter.openDataBase();

						ContentValues initialValues = new ContentValues();

						initialValues.put("blockstatus", "1");

						String[] strArray = { selected_user };
						long n = dbAdapter.updateRecordsInDB("usermaster", initialValues, "userid=?", strArray);
						dbAdapter.close();

						if (n == 0) {
							showMessage("ACKNOWLEDGEMENT", "user blocking failed was not updated try Again.");

						} else {

							AlertDialog.Builder builder = new AlertDialog.Builder(BlockUserActivity.this);

							builder.setTitle("ACKNOWLEDGEMENT");
							builder.setMessage(selected_user + " Blocked successfully.");

							builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog, int which) {

									dialog.dismiss();
									Intent intent = new Intent(BlockUserActivity.this, BlockUserActivity.class);

									startActivity(intent);
									finish();

								}

							});

							AlertDialog alert = builder.create();
							alert.show();
						}

						dialog.dismiss();
					}

				});

				builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
						dialog.dismiss();
					}
				});

				AlertDialog alert = builder.create();
				alert.show();

			}
		});

	}

	public void showMessage(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}

	/**
	 * Getting all labels returns list of labels
	 */
	public ArrayList<String> getAllLabels(String selectQuery) {

		final DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(this);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ", e.getMessage());
		}

		dbAdapter.openDataBase();

		Cursor cursor = dbAdapter.selectRecordsFromDB(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {

			userlist.add(cursor.getString(0));

			if (cursor.getCount() >= 0) {

				while (cursor.moveToNext()) {
					userlist.add(cursor.getString(0));

				}
			}
		}
		// closing connection
		cursor.close();
		dbAdapter.close();

		// returning lables
		return userlist;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// This method is called once the menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_SmartPark:
			// Launch settings activity
			Intent i = new Intent(getBaseContext(), SmartPActivity.class);
			startActivity(i);
			finish();
			break;
		case R.id.action_RegisterUser:
			// Launch settings activity
			Intent i1 = new Intent(getBaseContext(), RegisterUserActivity.class);
			startActivity(i1);
			finish();
			break;

		case R.id.action_Reports:
			// Launch settings activity
			Intent i2 = new Intent(getBaseContext(), ReportsMenuActivity.class);
			startActivity(i2);
			finish();
			break;
		case R.id.action_PriceCalulator:
			// Launch settings activity
			Intent i3 = new Intent(getBaseContext(), CheckPriceCalculator.class);
			startActivity(i3);
			finish();
			break;
		case R.id.action_Logout:
			// Launch settings activity
			finish();
			break;

		// more code...
		}
		return true;
	}

}
