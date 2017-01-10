package com.smart.project;

import java.io.IOException;
import java.util.ArrayList;

import com.smart.vehicletypesettings.DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;

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

public class CheckPriceCalculator extends Activity {

	ArrayList<String> locationlist = new ArrayList<String>();
	ArrayList<String> pricelist = new ArrayList<String>();
	String selected_location, selected_price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkprice_location);
		Spinner SP_location = (Spinner) findViewById(R.id.SP_location);
		final EditText ET_costperhour = (EditText) findViewById(R.id.ET_costperhour);
		final EditText ET_noofhours = (EditText) findViewById(R.id.ET_noofhours);
		final EditText Et_minutes = (EditText) findViewById(R.id.Et_minutes);

		Button Btn_reset = (Button) findViewById(R.id.Btn_reset);
		Button Btn_calculate = (Button) findViewById(R.id.Btn_calculate);

		String LocationQuery = "Select * from lotPrice_master ;";

		ArrayList<String> labels = getAllLabels(LocationQuery);

		ArrayAdapter adapter = new ArrayAdapter(CheckPriceCalculator.this, android.R.layout.simple_spinner_item,
				labels);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		SP_location.setAdapter(adapter);

		SP_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				selected_location = parent.getItemAtPosition(position).toString();

				ET_costperhour.setText(pricelist.get(position));

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		Btn_reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// ET_costperhour.setText("");
				ET_noofhours.setText("");

			}
		});

		Btn_calculate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if ((ET_noofhours.getText().toString().length() > 0)) {

					int hours = Integer.parseInt(ET_noofhours.getText().toString());
					int minutes = 0;
					if ((Et_minutes.getText().toString().length() > 0)
							&& ((Integer.parseInt(Et_minutes.getText().toString())) <= 60)) {
						minutes = Integer.parseInt(Et_minutes.getText().toString());

						if ((Integer.parseInt(Et_minutes.getText().toString()) > 0)
								&& (Integer.parseInt(Et_minutes.getText().toString()) <= 60)) {
							minutes = Integer.parseInt(Et_minutes.getText().toString());

							int amount = Integer.parseInt(ET_costperhour.getText().toString()) * (hours + 1);

							showMessage("Rate", "Amount for " + hours + " hrs and " + minutes + " mins is $ " + amount);

						}

						else {
							Toast.makeText(CheckPriceCalculator.this, "Minutes should not be more than 60", 3000)
									.show();

						}

					} else {

						int amount = Integer.parseInt(ET_costperhour.getText().toString()) * (hours);

						showMessage("Rate", "Amount for " + hours + " hrs and " + minutes + " mins is $ " + amount);

					}

				} else {

					int hours = 0;
					int minutes = 0;
					if ((Et_minutes.getText().toString().length() > 0)
							&& ((Integer.parseInt(Et_minutes.getText().toString())) <= 60)) {
						minutes = Integer.parseInt(Et_minutes.getText().toString());

						if ((Integer.parseInt(Et_minutes.getText().toString()) > 0)
								&& (Integer.parseInt(Et_minutes.getText().toString()) <= 60)) {
							minutes = Integer.parseInt(Et_minutes.getText().toString());

							int amount = Integer.parseInt(ET_costperhour.getText().toString()) * (hours + 1);

							showMessage("Rate", "Amount for " + hours + " hrs and " + minutes + " mins is $ " + amount);

						}

						else {
							Toast.makeText(CheckPriceCalculator.this, "Minutes should not be more than 60", 3000)
									.show();

						}

					}

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

			locationlist.add(cursor.getString(0));
			pricelist.add(cursor.getString(1));
			if (cursor.getCount() >= 0) {

				while (cursor.moveToNext()) {
					locationlist.add(cursor.getString(0));
					pricelist.add(cursor.getString(1));

				}
			}
		}
		// closing connection
		cursor.close();
		dbAdapter.close();

		// returning lables
		return locationlist;

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
		case R.id.action_slot_available:
			// Launch settings activity
			Intent i4 = new Intent(getBaseContext(), SlotsAvailableActivity.class);
			startActivity(i4);
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
