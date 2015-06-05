package lessons;

import java.util.ArrayList;

import web.DBHandler;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.example.cas.R;


public class Year2 extends Activity {

    public static final int TAG_YEAR_CODE = 2;
    DBHandler db;

    CheckBox COM234,COM240,COM252,COM256,COM267,BLM275,BLM231,COM237,STA250,MAT231,ENS220;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year2);

        db = new DBHandler(this);
        ActionBar actionbar = getActionBar();
		actionbar.setTitle("Year 2");
        
        COM234 = (CheckBox)findViewById(R.id.COM234);
        COM240 = (CheckBox)findViewById(R.id.COM240);
        COM252 = (CheckBox)findViewById(R.id.COM252);
        COM256 = (CheckBox)findViewById(R.id.COM256);
        COM267 = (CheckBox)findViewById(R.id.COM267);
        BLM231 = (CheckBox)findViewById(R.id.BLM231);
        BLM275 = (CheckBox)findViewById(R.id.BLM275);
        COM237 = (CheckBox)findViewById(R.id.COM237);
        STA250 = (CheckBox)findViewById(R.id.STA250);
        MAT231 = (CheckBox)findViewById(R.id.MAT231);
        ENS220 = (CheckBox)findViewById(R.id.ENS220);

        setUIFirst(db.getAllCodes(TAG_YEAR_CODE));
    }

    public void setUIFirst(ArrayList<?> codeArray){
      //  Log.d("SET UI FIRST :", " ArrayList: " + codeArray);

/*        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0));
        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0).getClass());*/

        for(int i=0; i<codeArray.size(); i++) {
/*            Log.d("codeArray.get(): ", ">" + codeArray.get(i));
            Log.d("checkText: ", ">" + chkCOM234.getText().toString());*/

        	if( codeArray.get(i).equals(COM234.getTag().toString()) ){
            	COM234.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM240.getTag().toString()) ){
            	COM240.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM252.getTag().toString()) ){
            	COM252.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM256.getTag().toString()) ){
            	COM256.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM267.getTag().toString()) ){
            	COM267.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM231.getTag().toString()) ){
            	BLM231.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM275.getTag().toString()) ){
            	BLM275.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM237.getTag().toString()) ){
            	COM237.setChecked(true);
            }
            else if( codeArray.get(i).equals(STA250.getTag().toString()) ){
            	STA250.setChecked(true);
            }
            else if( codeArray.get(i).equals(MAT231.getTag().toString()) ){
            	MAT231.setChecked(true);
            }
            else if( codeArray.get(i).equals(ENS220.getTag().toString()) ){
            	ENS220.setChecked(true);
            }
        	
        
        }

    }

    public void onCheckBoxClicked(View view){

        boolean checked = ((CheckBox) view).isChecked();
        String lectureCode = ((CheckBox) view).getTag().toString();

//        Log.d("*** - ",lectureCode );
//        Log.d("DENEME: ",">" + view.getContext().getClass().getSimpleName());

        if(checked){
            // add to database
           // Log.d("ADD: ", ">" + lectureCode);
            db.insertLecture(lectureCode, TAG_YEAR_CODE);
        } else{
            // remove from database
          //  Log.d("REMOVE: ", ">" + lectureCode);
            db.removeLecture(lectureCode, TAG_YEAR_CODE);
        }

        Log.d("ALL RECORDS WITH YEAR: ", ">" + db.getAllCodes(TAG_YEAR_CODE));
        Log.d("ALL RECORDS: ", ">" + db.getAllCodes());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_year1, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
