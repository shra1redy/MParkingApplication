package com.smart.project;

import java.io.IOException;

import com.smart.vehicletypesettings.DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;
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

public class SlotsAvailableActivity extends Activity {

	int slotNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slot);

		Button Btn_1 = (Button) findViewById(R.id.Btn_1);
		Button Btn_2 = (Button) findViewById(R.id.Btn_2);
		Button Btn_3 = (Button) findViewById(R.id.Btn_3);
		Button Btn_4 = (Button) findViewById(R.id.Btn_4);
		Button Btn_5 = (Button) findViewById(R.id.Btn_5);
		Button Btn_6 = (Button) findViewById(R.id.Btn_6);
		Button Btn_7 = (Button) findViewById(R.id.Btn_7);
		Button Btn_8 = (Button) findViewById(R.id.Btn_8);
		Button Btn_9 = (Button) findViewById(R.id.Btn_9);
		Button Btn_10 = (Button) findViewById(R.id.Btn_10);

		Button Btn_11 = (Button) findViewById(R.id.Btn_11);
		Button Btn_12 = (Button) findViewById(R.id.Btn_12);
		Button Btn_13 = (Button) findViewById(R.id.Btn_13);
		Button Btn_14 = (Button) findViewById(R.id.Btn_14);
		Button Btn_15 = (Button) findViewById(R.id.Btn_15);
		Button Btn_16 = (Button) findViewById(R.id.Btn_16);
		Button Btn_17 = (Button) findViewById(R.id.Btn_17);
		Button Btn_18 = (Button) findViewById(R.id.Btn_18);
		Button Btn_19 = (Button) findViewById(R.id.Btn_19);
		Button Btn_20 = (Button) findViewById(R.id.Btn_20);

		Button Btn_21 = (Button) findViewById(R.id.Btn_21);
		Button Btn_22 = (Button) findViewById(R.id.Btn_22);
		Button Btn_23 = (Button) findViewById(R.id.Btn_23);
		Button Btn_24 = (Button) findViewById(R.id.Btn_24);
		Button Btn_25 = (Button) findViewById(R.id.Btn_25);
		Button Btn_26 = (Button) findViewById(R.id.Btn_26);
		Button Btn_27 = (Button) findViewById(R.id.Btn_27);
		Button Btn_28 = (Button) findViewById(R.id.Btn_28);
		Button Btn_29 = (Button) findViewById(R.id.Btn_29);
		Button Btn_30 = (Button) findViewById(R.id.Btn_30);

		Button Btn_31 = (Button) findViewById(R.id.Btn_31);
		Button Btn_32 = (Button) findViewById(R.id.Btn_32);
		Button Btn_33 = (Button) findViewById(R.id.Btn_33);
		Button Btn_34 = (Button) findViewById(R.id.Btn_34);
		Button Btn_35 = (Button) findViewById(R.id.Btn_35);
		Button Btn_36 = (Button) findViewById(R.id.Btn_36);
		Button Btn_37 = (Button) findViewById(R.id.Btn_37);
		Button Btn_38 = (Button) findViewById(R.id.Btn_38);
		Button Btn_39 = (Button) findViewById(R.id.Btn_39);
		Button Btn_40 = (Button) findViewById(R.id.Btn_40);

		Button Btn_41 = (Button) findViewById(R.id.Btn_41);
		Button Btn_42 = (Button) findViewById(R.id.Btn_42);
		Button Btn_43 = (Button) findViewById(R.id.Btn_43);
		Button Btn_44 = (Button) findViewById(R.id.Btn_44);
		Button Btn_45 = (Button) findViewById(R.id.Btn_45);
		Button Btn_46 = (Button) findViewById(R.id.Btn_46);
		Button Btn_47 = (Button) findViewById(R.id.Btn_47);
		Button Btn_48 = (Button) findViewById(R.id.Btn_48);
		Button Btn_49 = (Button) findViewById(R.id.Btn_49);
		Button Btn_50 = (Button) findViewById(R.id.Btn_50);

		Button Btn_51 = (Button) findViewById(R.id.Btn_51);
		Button Btn_52 = (Button) findViewById(R.id.Btn_52);
		Button Btn_53 = (Button) findViewById(R.id.Btn_53);
		Button Btn_54 = (Button) findViewById(R.id.Btn_54);
		Button Btn_55 = (Button) findViewById(R.id.Btn_55);
		Button Btn_56 = (Button) findViewById(R.id.Btn_56);
		Button Btn_57 = (Button) findViewById(R.id.Btn_57);
		Button Btn_58 = (Button) findViewById(R.id.Btn_58);
		Button Btn_59 = (Button) findViewById(R.id.Btn_59);
		Button Btn_60 = (Button) findViewById(R.id.Btn_60);

		Button Btn_61 = (Button) findViewById(R.id.Btn_61);
		Button Btn_62 = (Button) findViewById(R.id.Btn_62);
		Button Btn_63 = (Button) findViewById(R.id.Btn_63);
		Button Btn_64 = (Button) findViewById(R.id.Btn_64);
		Button Btn_65 = (Button) findViewById(R.id.Btn_65);
		Button Btn_66 = (Button) findViewById(R.id.Btn_66);
		Button Btn_67 = (Button) findViewById(R.id.Btn_67);
		Button Btn_68 = (Button) findViewById(R.id.Btn_68);

		final DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(this);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbAdapter.openDataBase();

		String query1 = "SELECT * FROM session_master where Status='0';";

		Cursor cursor1 = dbAdapter.selectRecordsFromDB(query1, null);
		if (cursor1.moveToFirst()) {

			slotNo = Integer.parseInt(cursor1.getString(1));

			switch (slotNo) {
			case 1:
				Btn_1.setBackgroundResource(R.drawable.unavailable);
				break;
			case 2:
				Btn_2.setBackgroundResource(R.drawable.unavailable);
				break;
			case 3:
				Btn_3.setBackgroundResource(R.drawable.unavailable);
				break;
			case 4:
				Btn_4.setBackgroundResource(R.drawable.unavailable);
				break;

			case 5:
				Btn_5.setBackgroundResource(R.drawable.unavailable);
				break;
			case 6:
				Btn_6.setBackgroundResource(R.drawable.unavailable);
				break;
			case 7:
				Btn_7.setBackgroundResource(R.drawable.unavailable);
				break;
			case 8:
				Btn_8.setBackgroundResource(R.drawable.unavailable);
				break;
			case 9:
				Btn_9.setBackgroundResource(R.drawable.unavailable);
				break;
			case 10:
				Btn_10.setBackgroundResource(R.drawable.unavailable);
				break;

			case 11:
				Btn_11.setBackgroundResource(R.drawable.unavailable);
				break;
			case 12:
				Btn_12.setBackgroundResource(R.drawable.unavailable);
				break;
			case 13:
				Btn_13.setBackgroundResource(R.drawable.unavailable);
				break;
			case 14:
				Btn_14.setBackgroundResource(R.drawable.unavailable);
				break;

			case 15:
				Btn_15.setBackgroundResource(R.drawable.unavailable);
				break;
			case 16:
				Btn_16.setBackgroundResource(R.drawable.unavailable);
				break;
			case 17:
				Btn_17.setBackgroundResource(R.drawable.unavailable);
				break;
			case 18:
				Btn_18.setBackgroundResource(R.drawable.unavailable);
				break;
			case 19:
				Btn_19.setBackgroundResource(R.drawable.unavailable);
				break;
			case 20:
				Btn_20.setBackgroundResource(R.drawable.unavailable);
				break;

			case 21:
				Btn_21.setBackgroundResource(R.drawable.unavailable);
				break;
			case 22:
				Btn_22.setBackgroundResource(R.drawable.unavailable);
				break;
			case 23:
				Btn_23.setBackgroundResource(R.drawable.unavailable);
				break;
			case 24:
				Btn_24.setBackgroundResource(R.drawable.unavailable);
				break;

			case 25:
				Btn_25.setBackgroundResource(R.drawable.unavailable);
				break;
			case 26:
				Btn_26.setBackgroundResource(R.drawable.unavailable);
				break;
			case 27:
				Btn_27.setBackgroundResource(R.drawable.unavailable);
				break;
			case 28:
				Btn_28.setBackgroundResource(R.drawable.unavailable);
				break;
			case 29:
				Btn_29.setBackgroundResource(R.drawable.unavailable);
				break;
			case 30:
				Btn_20.setBackgroundResource(R.drawable.unavailable);
				break;

			case 31:
				Btn_31.setBackgroundResource(R.drawable.unavailable);
				break;
			case 32:
				Btn_32.setBackgroundResource(R.drawable.unavailable);
				break;
			case 33:
				Btn_33.setBackgroundResource(R.drawable.unavailable);
				break;
			case 34:
				Btn_34.setBackgroundResource(R.drawable.unavailable);
				break;

			case 35:
				Btn_35.setBackgroundResource(R.drawable.unavailable);
				break;
			case 36:
				Btn_36.setBackgroundResource(R.drawable.unavailable);
				break;
			case 37:
				Btn_37.setBackgroundResource(R.drawable.unavailable);
				break;
			case 38:
				Btn_38.setBackgroundResource(R.drawable.unavailable);
				break;
			case 39:
				Btn_39.setBackgroundResource(R.drawable.unavailable);
				break;
			case 40:
				Btn_40.setBackgroundResource(R.drawable.unavailable);
				break;

			case 41:
				Btn_41.setBackgroundResource(R.drawable.unavailable);
				break;
			case 42:
				Btn_42.setBackgroundResource(R.drawable.unavailable);
				break;
			case 43:
				Btn_43.setBackgroundResource(R.drawable.unavailable);
				break;
			case 44:
				Btn_44.setBackgroundResource(R.drawable.unavailable);
				break;

			case 45:
				Btn_45.setBackgroundResource(R.drawable.unavailable);
				break;
			case 46:
				Btn_46.setBackgroundResource(R.drawable.unavailable);
				break;
			case 47:
				Btn_47.setBackgroundResource(R.drawable.unavailable);
				break;
			case 48:
				Btn_48.setBackgroundResource(R.drawable.unavailable);
				break;
			case 49:
				Btn_49.setBackgroundResource(R.drawable.unavailable);
				break;
			case 50:
				Btn_50.setBackgroundResource(R.drawable.unavailable);
				break;

			case 51:
				Btn_51.setBackgroundResource(R.drawable.unavailable);
				break;
			case 52:
				Btn_52.setBackgroundResource(R.drawable.unavailable);
				break;
			case 53:
				Btn_53.setBackgroundResource(R.drawable.unavailable);
				break;
			case 54:
				Btn_54.setBackgroundResource(R.drawable.unavailable);
				break;

			case 55:
				Btn_55.setBackgroundResource(R.drawable.unavailable);
				break;
			case 56:
				Btn_56.setBackgroundResource(R.drawable.unavailable);
				break;
			case 57:
				Btn_57.setBackgroundResource(R.drawable.unavailable);
				break;
			case 58:
				Btn_58.setBackgroundResource(R.drawable.unavailable);
				break;
			case 59:
				Btn_59.setBackgroundResource(R.drawable.unavailable);
				break;
			case 60:
				Btn_60.setBackgroundResource(R.drawable.unavailable);
				break;

			case 61:
				Btn_61.setBackgroundResource(R.drawable.unavailable);
				break;
			case 62:
				Btn_62.setBackgroundResource(R.drawable.unavailable);
				break;
			case 63:
				Btn_63.setBackgroundResource(R.drawable.unavailable);
				break;
			case 64:
				Btn_64.setBackgroundResource(R.drawable.unavailable);
				break;

			case 65:
				Btn_65.setBackgroundResource(R.drawable.unavailable);
				break;
			case 66:
				Btn_66.setBackgroundResource(R.drawable.unavailable);
				break;
			case 67:
				Btn_67.setBackgroundResource(R.drawable.unavailable);
				break;
			case 68:
				Btn_68.setBackgroundResource(R.drawable.unavailable);
				break;

			// You can have any number of case statements.
			default: // Optional
				// Statements
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
