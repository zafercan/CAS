package lessons;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cas.MainActivity;
import com.example.cas.R;


public class ShowSingleLecture extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_single_lecture);

        ActionBar actionbar = getActionBar();
		
		
        Intent intent = getIntent();
//        TextView t1 = (TextView) findViewById(R.id.publishedDateClockArea);
//        t1.setVisibility(View.VISIBLE);
//        TextView t2 = (TextView) findViewById(R.id.publishedDateMinArea);	
//        t2.setVisibility(View.VISIBLE);
//        TextView t3 = (TextView) findViewById(R.id.deadlineDateClockArea);
//        t3.setVisibility(View.VISIBLE);
//        TextView t4 = (TextView) findViewById(R.id.deadlineDateMinArea);
//        t4.setVisibility(View.VISIBLE);
        
        String title = intent.getStringExtra(MainActivity.TAG_TITLE);
        String content = intent.getStringExtra(MainActivity.TAG_CONTENT);
        String pubdateClock = intent.getStringExtra(MainActivity.TAG_PUBLISH_DATE_CLOCK);
        String pubdateMin = intent.getStringExtra(MainActivity.TAG_PUBLISH_DATE_MIN);
      //  String dlineClock = intent.getStringExtra(MainActivity.TAG_DEADLINE_DATE_CLOCK);
        String dlineMin = intent.getStringExtra(MainActivity.TAG_DEADLINE_DATE_MIN);
        String lectureCode = intent.getStringExtra(MainActivity.TAG_LECTURE_CODE);

        Log.d("Handle",">"+pubdateMin);
        TextView textTitle = ((TextView) findViewById(R.id.title));
        TextView textContent = ((TextView) findViewById(R.id.content));
        //TextView textPubDateClock = ((TextView) findViewById(R.id.publishedDateClockArea));
        TextView textPubDateMin = ((TextView) findViewById(R.id.publishedDateMinArea));
        TextView textDlineClock= ((TextView) findViewById(R.id.deadlineDateClockArea));
        TextView textDlineDateMin = ((TextView) findViewById(R.id.deadlineDateMinArea));
      

        textTitle.setText(title);
        textContent.setText(content);
       // textPubDateClock.setText(pubdateClock);
        textPubDateMin.setText(pubdateMin);
       // textDlineClock.setText(dlineClock);
        textDlineDateMin.setText(dlineMin);
        
        actionbar.setTitle(lectureCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_single_lecture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}