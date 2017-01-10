package com.smart.project;

import com.smart.vehicletypesettings.DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterUserActivity extends Activity {

	EditText ET_firstname;
	EditText ET_Emailaddress;
	EditText ET_lastname;
	EditText ET_Userid;
	EditText ET_password;
	EditText ET_phoneno;
	EditText ET_address;
	EditText ET_licenceno;
	EditText ET_paymentcardno;

	Button Btn_save;
	Button Btn_back;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_user);

		ET_firstname = (EditText) findViewById(R.id.ET_firstname);
		ET_lastname = (EditText) findViewById(R.id.ET_lastname);
		ET_Emailaddress = (EditText) findViewById(R.id.ET_Emailaddress);
		ET_Userid = (EditText) findViewById(R.id.ET_Userid);
		ET_password = (EditText) findViewById(R.id.ET_password);
		ET_phoneno = (EditText) findViewById(R.id.ET_phoneno);
		ET_address = (EditText) findViewById(R.id.ET_address);
		ET_licenceno = (EditText) findViewById(R.id.ET_licenceno);
		ET_paymentcardno = (EditText) findViewById(R.id.ET_paymentcardno);

		Btn_save = (Button) findViewById(R.id.Btn_save);
		Btn_back = (Button) findViewById(R.id.Btn_back);

		Btn_save.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(RegisterUserActivity.this);
				dbAdapter.openDataBase();

				ContentValues initialValues = new ContentValues();

				initialValues.put("userid", ET_Userid.getText().toString());
				initialValues.put("password", ET_password.getText().toString());
				initialValues.put("firstname", ET_firstname.getText().toString());
				initialValues.put("lastname", ET_lastname.getText().toString());
				initialValues.put("emailid", ET_Emailaddress.getText().toString());

				initialValues.put("phoneno", ET_phoneno.getText().toString());

				initialValues.put("address", ET_address.getText().toString());
				initialValues.put("licenceno", ET_licenceno.getText().toString());
				initialValues.put("Paymentcardno", ET_paymentcardno.getText().toString());
				initialValues.put("loginstatus", "0");
				initialValues.put("usertype", "0");
				initialValues.put("blockstatus", "0");// 0 is active user 1 is
														// blocked user

				long n = dbAdapter.insertRecordsInDB("usermaster", null, initialValues);

				if (n > 0) {
					showMessage("ACKNOWLEDGEMENT",
							"ID::" + ET_Userid.getText().toString() + " User Registered Successfully ");

					// ET_firstname.setText("");
					// ET_Emailaddress.setText("");
					// ET_lastname.setText("");
					// ET_Userid.setText("");
					// ET_password.setText("");
					// ET_phoneno.setText("");
					// ET_address.setText("");
					// ET_licenceno.setText("");
					// ET_paymentcardno.setText("");

					Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
					startActivity(intent);
					finish();

				} else {
					showMessage("ACKNOWLEDGEMENT", " User Registration Failed ");

				}

			}

		});

		// *********************back Button***********************************

		Btn_back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();

			}

		});

		// **************************************************************

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
