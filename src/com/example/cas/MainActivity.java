package com.example.cas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import lessons.AddLessonSelectYear;
import lessons.ShowSingleLecture;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import web.DBHandler;
import web.DateHandler;
import web.ServiceHandler;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private ActionBar actionBar;
	private Menu optionsMenu;
	private ProgressBar spinner;

	private static String url = "http://188.166.44.115/api/show";

	JSONArray announcements = null;
	ArrayList<HashMap<String, String>> announcementList;
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	DateHandler dhandler = new DateHandler();
	String jsonStr;
	
	public static final String TAG_TITLE = "title";
	public static final String TAG_CONTENT = "content";
	public static final String TAG_PUBLISH_DATE = "created_at";
	public static final String TAG_DEADLINE_DATE = "deadline";
	public static final String TAG_LECTURE_CODE = "name";
	public static final String TAG_LECTURE = "lecture";
	
	// private static final String TAG_LECTURE_ID = "lecture_id";
	// private static final String TAG_POST_ID = "id";
	private static final String TAG_UPDATE = "updated_at";
	// private static final String TAG_USER_ID = "user_id";
	ArrayList paramsList;
	public static final String TAG_PUBLISH_DATE_CLOCK = "created_clock";
	public static final String TAG_PUBLISH_DATE_MIN = "created_min_date";
	public static final String TAG_DEADLINE_DATE_CLOCK = "deadline_clock";
	public static final String TAG_DEADLINE_DATE_MIN = "deadline_min_date";

	private DBHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		db = new DBHandler(this);

		spinner = (ProgressBar) findViewById(R.id.progressBar);
		spinner.setVisibility(View.GONE); // adapter.notify(); adapter.notify();

		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		 //actionBar.setDisplayHomeAsUpEnabled(true);

		 paramsList = new ArrayList();
		paramsList = db.getAllCodes();

		Log.d("PARAMS LİST: ", ">" + paramsList);

		// burada local database den seçili ders kodlarını alıcaz ve
		// parametrelere ekliyoruz
		for (int i = 0; i < paramsList.size(); i++) {
			if (paramsList.get(i) != null) {
				params.add(new BasicNameValuePair("lecture[]",
						(String) paramsList.get(i)));
			}
		}

		announcementList = new ArrayList<HashMap<String, String>>();
		 ListView lv = getListView();
		 lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int
		 position, long id) {
		 String code = ((TextView) view.findViewById(R.id.LectureCode)).getText().toString();
		 String title = ((TextView) view.findViewById(R.id.announcementTitle)).getText().toString();
		 String content = ((TextView)view.findViewById(R.id.announcementContent)).getText().toString();
		 String pubdate = ((TextView) view.findViewById(R.id.publishedDateMinArea)).getText().toString();
		 String pubdateClock = ((TextView) view.findViewById(R.id.deadlineDateClockArea)).getText().toString();
		 String dlinedate = ((TextView) view.findViewById(R.id.deadlineDateMinArea)).getText().toString();
		 String dlineClock = ((TextView) view.findViewById(R.id.deadlineDateClockArea)).getText().toString();
		 String lectureCode = ((TextView) view.findViewById(R.id.LectureCode)).getText().toString();
		
		 // bir duyuruya tıklandığında o duyurunun ayrıntılarını gösterecek
		// sayfa
		 // Single Lecture Activity
		 Intent in = new Intent(getApplicationContext(),
		 ShowSingleLecture.class);
		// in.putExtra(TAG_CODE, code);
		 in.putExtra(TAG_TITLE, title);
		 in.putExtra(TAG_CONTENT, content);
		 in.putExtra(TAG_PUBLISH_DATE_MIN, pubdate);
		 in.putExtra(TAG_DEADLINE_DATE_MIN, dlinedate);
		 in.putExtra(TAG_PUBLISH_DATE_CLOCK, pubdateClock);
		 in.putExtra(TAG_DEADLINE_DATE_CLOCK, dlineClock);
		 in.putExtra(TAG_LECTURE_CODE, lectureCode);
		 startActivity(in);
		 }
		 });
		new GetAnnouncements().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		this.optionsMenu = menu;

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		int id = item.getItemId();

		if (id == R.id.dersEkle) {
			dersEkle();
		} else if (id == R.id.refresh) {
			refresh();
		}
		return super.onOptionsItemSelected(item);
	}

	public void dersEkle() {

		Intent myIntent = new Intent(MainActivity.this,
				AddLessonSelectYear.class);
		startActivity(myIntent);

	}

	public void refresh() {

		spinner.setVisibility(View.VISIBLE);
		Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
		startActivity(myIntent);
		finish();
	}

	private class GetAnnouncements extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			// pDialog = new ProgressDialog(MainActivity.this);
			// pDialog.setMessage("Please wait...");
			// pDialog.setCancelable(false);
			// pDialog.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			
			// String jsonStr = sh.downloadUrl(url);
			//for(int i=0 ; i<3;i++){
				jsonStr = sh.downloadUrl(url, params);
				
			//}
			System.out.println(jsonStr+"*****************");
			

			if (jsonStr != null) {
				try {
					// JSONArray jsonArr = new JSONArray(jsonStr);
					announcements = new JSONArray(jsonStr);
					// Getting JSON Array node
					// announcements = jsonArr.getJSONObject();

					// JSON PARSE
					// looping through All Contacts
					for (int i = 0; i < announcements.length(); i++) {
						JSONObject c = announcements.getJSONObject(i);

						// Log.d("Ann ::::: ", "> " + announcements);
						// String id = c.getString(TAG_POST_ID);
						String title = c.getString(TAG_TITLE);
						String content = c.getString(TAG_CONTENT);
						String deadline = c.getString(TAG_DEADLINE_DATE);
						String pubdate = c.getString(TAG_PUBLISH_DATE);
						// String userid = c.getString(TAG_USER_ID);
						// String lectureid = c.getString(TAG_LECTURE_ID);

						JSONObject lectureObject = c.getJSONObject(TAG_LECTURE);
						String lectureCode = lectureObject
								.getString(TAG_LECTURE_CODE);

						// tmp hashmap for single contact
						HashMap<String, String> announcement = new HashMap<String, String>();

						dhandler.setDateObject(pubdate);
						Date pubDateObject = dhandler.getDateObject();
						String pubDateClockString = dhandler
								.getClock(pubDateObject);
						String pubDateString = dhandler.getDate(pubDateObject);

						dhandler.setDateObject(deadline);
						Date deadlineObject = dhandler.getDateObject();
						String deadlineClockString = dhandler
								.getClock(deadlineObject);
						String deadlineDateString = dhandler
								.getDate(deadlineObject);

						/*
						 * Log.d("PUBDATEOBJECT",">" + pubDateObject);
						 * Log.d("GET CLOCK: ",">" +
						 * dhandler.getClock(pubDateObject));
						 * Log.d("PUBDATEOBJECT",">" + pubDateObject);
						 * Log.d("GET DATE: ", ">" +
						 * dhandler.getDate(pubDateObject));
						 * Log.d("PUBDATEOBJECT",">" + pubDateObject);
						 */

						// adding each child node to HashMap key => value
						// announcement.put(TAG_POST_ID, id);
						announcement.put(TAG_TITLE, title);
						announcement.put(TAG_CONTENT, content);

						announcement.put(TAG_LECTURE_CODE, lectureCode);

						announcement.put(TAG_DEADLINE_DATE, deadline);
						announcement.put(TAG_DEADLINE_DATE_CLOCK,
								deadlineClockString);
						announcement.put(TAG_DEADLINE_DATE_MIN,
								deadlineDateString);

						announcement.put(TAG_PUBLISH_DATE, pubdate);
						announcement.put(TAG_PUBLISH_DATE_CLOCK,
								pubDateClockString);
						announcement.put(TAG_PUBLISH_DATE_MIN, pubDateString);

						// announcement.put(TAG_USER_ID, userid);
						// announcement.put(TAG_LECTURE_ID, lectureid);
						//Log.d("Ann tek t ::::: ", "> " + announcement);
						// adding contact to contact list
						announcementList.add(announcement);
						//Log.d("Announcement List: ", ">" + announcementList);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}
			jsonStr = "";
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			// if (pDialog.isShowing())
			// pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			jsonStr = "";
			if(paramsList.size() == 0){
				final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

				builder.setTitle("Uyarı");
				builder.setMessage("Henüz ders seçmediniz.\nLütfen duyurularını almak istediğiniz dersi seçin.");
				builder.setCancelable(false);

				builder.setPositiveButton("Tamam",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
//								Toast.makeText(MainActivity.this,
//										"Allah razı olsun", Toast.LENGTH_LONG)
//										.show();
							}
						});

				AlertDialog dialog = builder.create();
				dialog.show();
			}
			else{
			ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, announcementList,
                    R.layout.list_item, new String[] { TAG_TITLE,
                    TAG_CONTENT,TAG_PUBLISH_DATE_MIN, TAG_PUBLISH_DATE_CLOCK,
                    TAG_DEADLINE_DATE_MIN, TAG_DEADLINE_DATE_CLOCK,TAG_LECTURE_CODE},
                    new int[] {
                            R.id.announcementTitle, R.id.announcementContent,
                            R.id.publishedDateMinArea, R.id.publishedDateClockArea,
                            R.id.deadlineDateMinArea, R.id.deadlineDateClockArea,R.id.LectureCode
                            });
			
			
			
			setListAdapter(adapter);
			
//			int i = 0 ; int i1 = 1;
//			SQLiteDatabase  sb1 = null;
//			db.onUpgrade(sb1, i, i1);
		//	Log.d("ann list : ", "" + announcementList);
			}
		}

	}

}