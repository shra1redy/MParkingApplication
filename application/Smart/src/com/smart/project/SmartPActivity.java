package com.smart.project;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.smart.vehicletypesettings.DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SmartPActivity extends Activity {
	EditText ET_location;
	EditText ET_lotno;
	EditText ET_meterno;
	Button Btn_scan;
	Button Btn_Start;
	double latitude;
	double longitude;
	Button Btn_getlocationdetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_p);

		ET_location = (EditText) findViewById(R.id.ET_location);
		ET_lotno = (EditText) findViewById(R.id.ET_lotno);
		ET_meterno = (EditText) findViewById(R.id.ET_meterno);
		Btn_scan = (Button) findViewById(R.id.Btn_scan);
		Btn_Start = (Button) findViewById(R.id.Btn_Start);
		Btn_getlocationdetails = (Button) findViewById(R.id.Btn_getlocationdetails);

		Btn_getlocationdetails.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				GPSCoordinates gpsdata = new GPSCoordinates(SmartPActivity.this);
				if (gpsdata.canGetLocation()) {
					// FTUPGpsLoc.setEnabled(false);
					latitude = gpsdata.getLatitude();
					longitude = gpsdata.getLongitude();
					Toast.makeText(SmartPActivity.this, "GPS Location Identified " + latitude + "  " + longitude, 3000)
							.show();
					/*
					 * FTUPGpsLoc.setText(String.valueOf(latitude) + "," +
					 * String.valueOf(longitude));
					 */
				}
				if (!gpsdata.canGetLocation()) {
					// FTUPGpsLoc.setEnabled(true);
					Toast.makeText(SmartPActivity.this, "GPS Location failed", 3000).show();
					/*
					 * FTUPGpsLoc.setText(String.valueOf(latitude) + " " +
					 * String.valueOf(longitude));
					 */
				}

			}
		});

		Btn_Start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (ET_location.getText().toString().length() > 0) {

					if (ET_lotno.getText().toString().length() > 0) {

						if (ET_meterno.getText().toString().length() > 0) {
							Date now = new Date();
							String datetimeStr = now.toString();

							SimpleDateFormat curformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

							DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(SmartPActivity.this);
							dbAdapter.openDataBase();

							ContentValues initialValues = new ContentValues();

							initialValues.put("Location", ET_location.getText().toString());
							initialValues.put("LotNo", ET_lotno.getText().toString());
							initialValues.put("MeterNo", ET_meterno.getText().toString());
							initialValues.put("SessionStartTime", curformat.format(now));
							initialValues.put("CostPerHour", "1");
							initialValues.put("Status", "0");
							initialValues.put("Latitude", latitude);
							initialValues.put("Longitude", longitude);

							long n = dbAdapter.insertRecordsInDB("session_master", null, initialValues);

							if (n > 0) {
								/*
								 * showMessage("ACKNOWLEDGEMENT", "ID::" +
								 * ET_Userid.getText() .toString() +
								 * " User Registered Successfully ");
								 */

								Intent intent = new Intent(SmartPActivity.this, SessionActivity.class);
								startActivity(intent);
								finish();

							} else {
								// showMessage("ACKNOWLEDGEMENT",
								// " User Registration Failed ");

							}

						} else {
							AlertDialog alert = new AlertDialog.Builder(SmartPActivity.this).create();
							alert.setTitle("VALIDATION");

							alert.setMessage("Read the Barcode first to start the session ");
							alert.setButton("OK", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
								}
							});
							alert.show();
						}

					} else {
						AlertDialog alert = new AlertDialog.Builder(SmartPActivity.this).create();
						alert.setTitle("VALIDATION");

						alert.setMessage("Read the Barcode first to start the session ");
						alert.setButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
							}
						});
						alert.show();
					}

				} else {
					AlertDialog alert = new AlertDialog.Builder(SmartPActivity.this).create();
					alert.setTitle("VALIDATION");

					alert.setMessage("Read the Barcode first to start the session ");
					alert.setButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					alert.show();
				}

			}
		});

		// in some trigger function e.g. button press within your code you
		// should add:
		Btn_scan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				try {

					Intent intent = new Intent("com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
					startActivityForResult(intent, 0);

				} catch (Exception e) {

					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, 1).show();

				}

			}
		});

	}

	// In the same activity you’ll need the following to retrieve the results:
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {

			if (resultCode == RESULT_OK) {
				// tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
				String data = intent.getStringExtra("SCAN_RESULT");
				String scandata[] = data.split(",");

				Toast.makeText(getApplicationContext(), data, 3000).show();
				// ET_location.setText(scandata.length+"");
				// ET_lotno.setText(scandata[1]);
				// ET_meterno.setText(scandata[2]);

				if (scandata.length > 2) {
					ET_location.setText(scandata[0]);
					ET_lotno.setText(scandata[1]);
					ET_meterno.setText(scandata[2]);

				}

			} else if (resultCode == RESULT_CANCELED) {
				// tvStatus.setText("Press a button to start a scan.");
				// tvResult.setText("Scan cancelled.");
				Toast.makeText(getApplicationContext(), "Press a button to start a scan", 3000).show();
			}
		}
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
