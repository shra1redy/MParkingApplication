package com.smart.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ReportsMenuActivity extends Activity {

	Button btn_locationwise_report;
	Button btn_locationwise_day_report;
	Button btn_locationwise_daysbetween_report;
	Button btn_locationwise_daySummery_report;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reports_menu_screen);

		btn_locationwise_report = (Button) findViewById(R.id.btn_locationwise_report);
		btn_locationwise_day_report = (Button) findViewById(R.id.btn_locationwise_day_report);
		btn_locationwise_daysbetween_report = (Button) findViewById(R.id.btn_locationwise_daysbetween_report);
		btn_locationwise_daySummery_report = (Button) findViewById(R.id.btn_locationwise_daySummery_report);

		btn_locationwise_report.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ReportsMenuActivity.this, LocationWiseReportActivity.class);
				startActivity(intent);

			}
		});

		btn_locationwise_day_report.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ReportsMenuActivity.this, LocationWiseDayReport.class);

				startActivity(intent);
			}

		});

		btn_locationwise_daysbetween_report.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ReportsMenuActivity.this, LocationWiseDaysBetweenReport.class);

				startActivity(intent);
			}

		});

		btn_locationwise_daySummery_report.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ReportsMenuActivity.this, LocationWiseDaysummeryReport.class);

				startActivity(intent);
			}

		});

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
