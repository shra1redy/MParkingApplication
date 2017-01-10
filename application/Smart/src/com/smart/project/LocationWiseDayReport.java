  
 
 
 package com.smart.project;

 import java.io.IOException;
 import java.util.ArrayList;
 import com.smart.vehicletypesettings.DBAdapter;
 import android.os.Bundle;
 import android.app.Activity;
 import android.content.Context;
 import android.database.Cursor;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.Menu;
 import android.view.MenuItem;
 import android.view.View;
 import android.view.View.OnClickListener;
 import android.view.ViewGroup;
 import android.widget.AdapterView;
 import android.widget.ArrayAdapter;
 import android.widget.Button;
 import android.widget.DatePicker;
 import android.widget.ListView;
 import android.widget.Spinner;
 import android.widget.TextView;
 import android.widget.Toast;

 public class LocationWiseDayReport extends Activity {

 	StringBuffer sb = new StringBuffer();
 	DBAdapter dbAdapter;

 	ArrayList<String> locationlist = new ArrayList<String>();

 	private ListView lvUsers;
 	private ArrayList<DetailsVO> mListUsers;
 	
 	String selected_location;

 	DatePicker picker;

 	@Override
 	protected void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);

 		setContentView(R.layout.locationwise_dayreport);


 		dbAdapter = DBAdapter.getDBAdapterInstance(this);
 		
 		picker=(DatePicker)findViewById(R.id.datePicker1);
 		
 		String LocationQuery = "SELECT distinct Location from session_master;";

 		ArrayList<String> labels = getAllLabels(LocationQuery);

 		Spinner SP_location = (Spinner) findViewById(R.id.sp_Location);
 		Button Btn_generate=(Button) findViewById(R.id.Btn_generate);
 	
 		
 		
 		
 		ArrayAdapter adapter = new ArrayAdapter(LocationWiseDayReport.this,
 				android.R.layout.simple_spinner_item, labels);

 		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

 		SP_location.setAdapter(adapter);

 		SP_location
 				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
 					@Override
 					public void onItemSelected(AdapterView<?> parent,
 							View view, int position, long id) {
 						selected_location = parent.getItemAtPosition(position)
 								.toString();

 						

 					}

 					@Override
 					public void onNothingSelected(AdapterView<?> parent) {

 					}

 				});
 		
 		
 		
 		
 		
 		
 		
 		Btn_generate.setOnClickListener(new OnClickListener() {
 			
 			public void onClick(View arg0) {

 				mListUsers = getUsers();
 				lvUsers = (ListView) findViewById(R.id.lv_user);
 				lvUsers.setAdapter(new ListAdapter(LocationWiseDayReport.this,
 						R.id.lv_user, mListUsers));
 				
 				
 			}
 		});
 		
 		
 		
 		
 		

 	}
 	
 	
 	


 	
 	
 	
 	
 	
 	
 	void showToast(CharSequence msg) {
 		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
 	}

 	public ArrayList<DetailsVO> getUsers() {
 	
 		DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(this);
 		try {
 			dbAdapter.createDataBase();
 		} catch (IOException e) {
 			Log.i("*** select ", e.getMessage());
 		}
 		dbAdapter.openDataBase();
 		String query = "select * from session_master where Location='"+selected_location +"' and SessionStartTime like '"+getCurrentDate()+"%';";
 		//String query = "select * from session_master where Location='"+selected_location +"' ;";
 		ArrayList<ArrayList<String>> stringList = dbAdapter
 				.selectRecordsFromDBList(query, null);
 		dbAdapter.close();

 		ArrayList<DetailsVO> usersList = new ArrayList<DetailsVO>();
 		for (int i = 0; i < stringList.size(); i++) {
 			ArrayList<String> list = stringList.get(i);
 			DetailsVO items = new DetailsVO();
 			try {

 				items.location = list.get(0);
 				items.cost = list.get(5);
 				items.starttime = list.get(3);
 				items.endtime = list.get(4);
 				

 			} catch (Exception e) {
 				Log.i("***" + LocationWiseReportActivity.class.toString(),
 						e.getMessage());
 			}
 			usersList.add(items);
 		}
 		return usersList;
 	}

 	// ***ListAdapter***
 	private class ListAdapter extends ArrayAdapter<DetailsVO> { // --CloneChangeRequired
 		private ArrayList<DetailsVO> mList; // --CloneChangeRequired
 		private Context mContext;

 		public ListAdapter(Context context, int textViewResourceId,
 				ArrayList<DetailsVO> list) { // --CloneChangeRequired
 			super(context, textViewResourceId, list);
 			this.mList = list;
 			this.mContext = context;
 		}

 		public View getView(int position, View convertView, ViewGroup parent) {
 			View view = convertView;
 			try {
 				if (view == null) {
 					LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 					view = vi.inflate(R.layout.details_list, null); // --CloneChangeRequired(list_item)
 				}
 				final DetailsVO listItem = mList.get(position); // --CloneChangeRequired
 				if (listItem != null) {

 					// setting list_item views
 					((TextView) view.findViewById(R.id.TV_location))
 							.setText(listItem.getLocation() + "");
 					
 					((TextView) view.findViewById(R.id.TV_cost))
 							.setText(listItem.getCost() + "");
 					
 					((TextView) view.findViewById(R.id.TV_startTime))
 							.setText(listItem.getStarttime() + "");
 					
 					((TextView) view.findViewById(R.id.TV_stopTime))
 							.setText(listItem.getEndtime() + "");
 					
 					

 				}
 			} catch (Exception e) {
 				Log.i(LocationWiseDayReport.ListAdapter.class.toString(),
 						e.getMessage());
 			}
 			return view;
 		}

 	}
 	/* @SuppressWarnings("deprecation")
 	   public void setDate(View view) {
 	      showDialog(999);
 	      Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
 	      .show();
 	   }

 	   @Override
 	   protected Dialog onCreateDialog(int id) {
 	      // TODO Auto-generated method stub
 	      if (id == 999) {
 	         return new DatePickerDialog(this, myDateListener, year, month, day);
 	      }
 	      return null;
 	   }

 	   private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
 	      @Override
 	      public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
 	         // TODO Auto-generated method stub
 	         // arg1 = year
 	         // arg2 = month
 	         // arg3 = day
 	         showDate(arg1, arg2+1, arg3);
 	      }
 	   };

 	   private void showDate(int year, int month, int day) {
 		  String selected_day=day+"";
 		  String selected_month=month+"";
 		   if(selected_day.length()==1){
 			   selected_day="0"+selected_day;
 		   }
 		   if(selected_month.length()==1){
 			   selected_month="0"+selected_month;
 		   }
 		   
 		   selected_date=selected_day+"/"+selected_month+"/"+year+"";
 	   
 	   }
 	   
 	   
 	   */
 	   
 	   
 	@Override
 	public boolean onCreateOptionsMenu(Menu menu) {
 	    getMenuInflater().inflate(R.menu.main, menu);
 	    return true;
 	}

 	// This method is called once the menu is selected
 	@Override
 	public boolean onOptionsItemSelected(MenuItem item) {
 		switch (item.getItemId()) {}
 		return true;
 	}
 	/**
 	 * Getting all labels returns list of labels
 	 * */
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
 			
 			if (cursor.getCount() >= 0) {

 				while (cursor.moveToNext()) {
 					locationlist.add(cursor.getString(0));
 					

 				}
 			}
 		}
 		// closing connection
 		cursor.close();
 		dbAdapter.close();

 		// returning lables
 		return locationlist;

 	}
 	
 	
 	
 	 public String getCurrentDate(){
 		 
 		String selected_month=(picker.getMonth() + 1)+"";
 		String selected_day=(picker.getDayOfMonth())+"";
 		
 		if(selected_day.length()==1){
 			
 			selected_day="0"+selected_day;
 		}
 		if(selected_month.length()==1){
 			
 			selected_month="0"+selected_month;
 		}
 		
 		
     	StringBuilder builder=new StringBuilder();
 	
 		builder.append(selected_day+"/");
 		builder.append(selected_month+"/");
 		builder.append(picker.getYear());
 		return builder.toString();
     }
 	

 	
 	
 	
 	
 	
 	
 	
 	
 	
 }
