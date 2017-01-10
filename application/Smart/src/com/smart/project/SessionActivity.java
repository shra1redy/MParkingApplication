package com.smart.project;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.javacodegeeks.androidgooglemapsexample.AndroidGoogleMapsActivity;
import com.smart.vehicletypesettings.DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.Time;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SessionActivity extends Activity {

	private static final String TAG = "CountdownTimer";

	private TextView mCountdownNote;
	private ProgressWheel mDaysWheel;
	private TextView mDaysLabel;
	private ProgressWheel mHoursWheel;
	private TextView mHoursLabel;
	private ProgressWheel mMinutesWheel;
	private TextView mMinutesLabel;
	private ProgressWheel mSecondsWheel;
	private TextView mSecondsLabel;
	int Totoalhours = 0;

	TextView activity_countdown_timer_note;
	EditText ET_costperhour;
	EditText ET_totalAmount;
	Button Btn_stop;
	Button Btn_showLocation;
	double latitude = 0.0;
	double longitude = 0.0;
	// Timer setup
	Time conferenceTime = new Time(Time.getCurrentTimezone());
	int hour = 22;
	int minute = 33;
	int second = 0;
	int monthDay = 28;
	// month is zero based...7 == August
	int month = 7;
	int year;

	// Values displayed by the timer
	private int mDisplayDays;
	private int mDisplayHours;
	private int mDisplayMinutes;
	private int mDisplaySeconds;
	String locationName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_countdown_timer);

		Btn_stop = (Button) findViewById(R.id.Btn_stop);

		Btn_showLocation = (Button) findViewById(R.id.Btn_showLocation);

		Btn_showLocation.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(SessionActivity.this, AndroidGoogleMapsActivity.class);
				intent.putExtra("lat", latitude);
				intent.putExtra("long", longitude);
				startActivity(intent);

			}
		});

		Btn_stop.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				AlertDialog.Builder builder = new AlertDialog.Builder(SessionActivity.this);

				builder.setTitle("Confirm");
				builder.setMessage("Your Total Payment amount is " + ET_totalAmount.getText().toString() + " for "
						+ Totoalhours + " hours of parking.");

				builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// Do nothing but close the dialog

						Date now = new Date();
						String datetimeStr = now.toString();
						// System.out.println("1. " + datetimeStr);
						SimpleDateFormat curformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

						DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(SessionActivity.this);
						dbAdapter.openDataBase();

						ContentValues initialValues = new ContentValues();
						initialValues.put("SessionEndTime", curformat.format(now));
						initialValues.put("CostPerHour", ET_costperhour.getText().toString());

						initialValues.put("TotalAmount", ET_totalAmount.getText().toString());
						initialValues.put("Status", "1");

						String[] strArray = { "0" };
						long n = dbAdapter.updateRecordsInDB("session_master", initialValues, "Status=?", strArray);
						dbAdapter.close();

						if (n == 0) {
							showMessage("ACKNOWLEDGEMENT", "Record was not updated try Again");

						} else {

							AlertDialog.Builder builder = new AlertDialog.Builder(SessionActivity.this);

							builder.setTitle("ACKNOWLEDGEMENT");
							builder.setMessage("Thanks For parking your Vehicle .Visit Again");

							builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog, int which) {

									dialog.dismiss();
									Intent intent = new Intent(SessionActivity.this, SmartPActivity.class);

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

		configureViews();
		configureConferenceDate();

	}

	private void configureViews() {

		this.conferenceTime.setToNow();
		this.year = conferenceTime.year;

		this.mCountdownNote = (TextView) findViewById(R.id.activity_countdown_timer_note);
		this.mDaysWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_days);
		this.mHoursWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_hours);
		this.mMinutesWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_minutes);
		this.mSecondsWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_seconds);
		this.mDaysLabel = (TextView) findViewById(R.id.activity_countdown_timer_days_text);
		this.mHoursLabel = (TextView) findViewById(R.id.activity_countdown_timer_hours_text);
		this.mMinutesLabel = (TextView) findViewById(R.id.activity_countdown_timer_minutes_text);
		this.mSecondsLabel = (TextView) findViewById(R.id.activity_countdown_timer_seconds_text);
		this.activity_countdown_timer_note = (TextView) findViewById(R.id.activity_countdown_timer_note);

		this.ET_costperhour = (EditText) findViewById(R.id.ET_costperhour);
		this.ET_totalAmount = (EditText) findViewById(R.id.ET_totalAmount);

	}

	private void configureConferenceDate() {
		conferenceTime.set(second, minute, hour, monthDay, month, year);
		conferenceTime.normalize(true);
		long confMillis = conferenceTime.toMillis(true);

		Time nowTime = new Time(Time.getCurrentTimezone());
		nowTime.setToNow();
		nowTime.normalize(true);
		long nowMillis = nowTime.toMillis(true);

		long milliDiff = confMillis - nowMillis;

		new CountDownTimer(milliDiff, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {

				String dateStart = "";
				DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(SessionActivity.this);

				dbAdapter.openDataBase();
				String query = "SELECT * FROM session_master where Status='0';";

				Cursor cursor = dbAdapter.selectRecordsFromDB(query, null);
				if (cursor.moveToFirst()) {

					dateStart = cursor.getString(3);
					locationName = cursor.getString(0);
					latitude = Double.parseDouble(cursor.getString(8));
					longitude = Double.parseDouble(cursor.getString(9));

				}
				if (cursor != null && !cursor.isClosed()) {
					cursor.close();
				}

				String costquery = "SELECT * FROM lotPrice_master where Location='" + locationName + "';";

				Cursor costcursor = dbAdapter.selectRecordsFromDB(costquery, null);
				if (costcursor.moveToFirst()) {

					ET_costperhour.setText(costcursor.getString(1));

				}
				if (costcursor != null && !costcursor.isClosed()) {
					costcursor.close();
				}

				Date now = new Date();
				String datetimeStr = now.toString();
				// System.out.println("1. " + datetimeStr);
				SimpleDateFormat curformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				// System.out.println("2. " + curformat.format(now));

				String dateStop = curformat.format(now);

				// HH converts hour in 24 hours format (0-23), day calculation
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				Date d1 = null;
				Date d2 = null;

				try {
					d1 = format.parse(dateStart);
					d2 = format.parse(dateStop);

					// in milliseconds
					long diff = d2.getTime() - d1.getTime();

					long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);

					// System.out.print(diffDays + " days, ");
					// System.out.print(diffHours + " hours, ");
					// System.out.print(diffMinutes + " minutes, ");
					// System.out.print(diffSeconds + " seconds.");
					// Toast.makeText(getApplicationContext(),
					// hoursDifference(d1, d2), 3000).show();
					/*
					 * SessionActivity.this.mDisplayDays = diffDays;
					 * SessionActivity.this.mDisplayHours = diffHours;
					 * SessionActivity.this.mDisplayMinutes = diffMinutes;
					 * SessionActivity.this.mDisplaySeconds = diffSeconds;
					 */
					SessionActivity.this.activity_countdown_timer_note.setText(hoursDifference(d2, d1) + "");
					if ((Integer.parseInt(String.valueOf(diffMinutes)) >= 1)
							|| (Integer.parseInt(String.valueOf(diffSeconds)) >= 1)) {
						// Integer.parseInt(String.valueOf(diffMinutes))
						Totoalhours = (hoursDifference(d2, d1) + 1);
						ET_totalAmount
								.setText(Totoalhours * Integer.parseInt(ET_costperhour.getText().toString()) + "");

					}

					if (Integer.parseInt(diffDays + "") > 0) {
						SessionActivity.this.mDaysWheel.setText(diffDays + "");
						SessionActivity.this.mDaysWheel.setProgress(Integer.parseInt(diffDays + ""));
					}
					if (Integer.parseInt(diffHours + "") > 0) {
						SessionActivity.this.mHoursWheel.setText(String.valueOf(diffHours));
						SessionActivity.this.mHoursWheel.setProgress(Integer.parseInt(diffHours + "") * 15);
					}
					if (Integer.parseInt(diffMinutes + "") > 0) {
						SessionActivity.this.mMinutesWheel.setText(String.valueOf(diffMinutes));
						SessionActivity.this.mMinutesWheel.setProgress(Integer.parseInt(diffMinutes + "") * 6);
					}

					Animation an = new RotateAnimation(0.0f, 90.0f, 250f, 273f);
					an.setFillAfter(true);

					SessionActivity.this.mSecondsWheel.setText(String.valueOf(diffSeconds));
					SessionActivity.this.mSecondsWheel.setProgress(Integer.parseInt(diffSeconds + "") * 6);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// hoursDifference(d1, d2);

				/*
				 * // decompose difference into days, hours, minutes and seconds
				 * SessionActivity.this.mDisplayDays = (int)
				 * ((millisUntilFinished / 1000) / 86400);
				 * SessionActivity.this.mDisplayHours = (int)
				 * (((millisUntilFinished / 1000) -
				 * (SessionActivity.this.mDisplayDays * 86400)) / 3600);
				 * SessionActivity.this.mDisplayMinutes = (int)
				 * (((millisUntilFinished / 1000) -
				 * ((SessionActivity.this.mDisplayDays * 86400) +
				 * (SessionActivity.this.mDisplayHours * 3600))) / 60);
				 * SessionActivity.this.mDisplaySeconds = (int)
				 * ((millisUntilFinished / 1000) % 60);
				 * 
				 * SessionActivity.this.mDaysWheel.setText(String.valueOf(
				 * SessionActivity.this.mDisplayDays));
				 * SessionActivity.this.mDaysWheel
				 * .setProgress(SessionActivity.this.mDisplayDays);
				 * 
				 * SessionActivity.this.mHoursWheel.setText(String.valueOf(
				 * SessionActivity.this.mDisplayHours));
				 * SessionActivity.this.mHoursWheel
				 * .setProgress(SessionActivity.this.mDisplayHours * 15);
				 * 
				 * SessionActivity.this.mMinutesWheel.setText(String.valueOf(
				 * SessionActivity.this.mDisplayMinutes));
				 * SessionActivity.this.mMinutesWheel
				 * .setProgress(SessionActivity.this.mDisplayMinutes * 6);
				 * 
				 * Animation an = new RotateAnimation(0.0f, 90.0f, 250f, 273f);
				 * an.setFillAfter(true);
				 * 
				 * SessionActivity.this.mSecondsWheel.setText(String.valueOf(
				 * SessionActivity.this.mDisplaySeconds));
				 * SessionActivity.this.mSecondsWheel
				 * .setProgress(SessionActivity.this.mDisplaySeconds * 6);
				 */}

			@Override
			public void onFinish() {
				// TODO: this is where you would launch a subsequent activity if
				// you'd like. I'm currently just setting the seconds to zero
				Logger.d(TAG, "Timer Finished...");
				SessionActivity.this.mSecondsWheel.setText("0");
				SessionActivity.this.mSecondsWheel.setProgress(0);
			}
		}.start();
	}

	private static int hoursDifference(Date date1, Date date2) {

		final int MILLI_TO_HOUR = 1000 * 60 * 60;

		return (int) (date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;

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
