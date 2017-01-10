package com.smart.project;

import java.io.IOException;

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
import android.widget.Button;
import android.widget.EditText;

public class AddLocationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_lot_creation);
		final EditText ET_location = (EditText) findViewById(R.id.ET_location);
		final EditText ET_costperhour = (EditText) findViewById(R.id.ET_costperhour);

		Button Btn_reset = (Button) findViewById(R.id.Btn_reset);
		Button Btn_save = (Button) findViewById(R.id.Btn_save);

		Btn_reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				ET_location.setText("");
				ET_costperhour.setText("");
			}
		});

		Btn_save.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(AddLocationActivity.this);
				dbAdapter.openDataBase();

				ContentValues initialValues = new ContentValues();

				initialValues.put("Location", ET_location.getText().toString());
				initialValues.put("costperhour", ET_costperhour.getText().toString());

				long n = dbAdapter.insertRecordsInDB("lotPrice_master", null, initialValues);

				if (n > 0) {
					showMessage("ACKNOWLEDGEMENT", ET_location.getText().toString() + " Added Successfully ");

					ET_location.setText("");
					ET_costperhour.setText("");

					/*
					 * Intent intent = new Intent(RegisterUserActivity.this,
					 * LoginActivity.class); startActivity(intent); finish();
					 */

				} else {
					showMessage("ACKNOWLEDGEMENT", " Location Adding Failed ");

				}

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
