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


public class Year4 extends Activity {

    public static final int TAG_YEAR_CODE = 4;
    DBHandler db;

    CheckBox BLM402,BLM436,BLM438,BLM468,COM491,BLM431,COM492,BLM433;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year4);

        db = new DBHandler(this);
        ActionBar actionbar = getActionBar();
		actionbar.setTitle("Year 4");
        
        BLM402 = (CheckBox)findViewById(R.id.BLM402);
        BLM436 = (CheckBox)findViewById(R.id.BLM436);
        BLM438 = (CheckBox)findViewById(R.id.BLM438);
        BLM468 = (CheckBox)findViewById(R.id.BLM468);
        COM491 = (CheckBox)findViewById(R.id.COM491);
        COM492 = (CheckBox)findViewById(R.id.COM492);
        BLM431 = (CheckBox)findViewById(R.id.BLM431);
        BLM433 = (CheckBox)findViewById(R.id.BLM433);

        setUIFirst(db.getAllCodes(TAG_YEAR_CODE));
    }

    public void setUIFirst(ArrayList<?> codeArray){
      //  Log.d("SET UI FIRST :", " ArrayList: " + codeArray);

/*        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0));
        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0).getClass());*/

        for(int i=0; i<codeArray.size(); i++) {
/*            Log.d("codeArray.get(): ", ">" + codeArray.get(i));
            Log.d("checkText: ", ">" + chkBLM402.getText().toString());*/

        	if( codeArray.get(i).equals(BLM402.getTag().toString()) ){
            	BLM402.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM436.getTag().toString()) ){
            	BLM436.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM438.getTag().toString()) ){
            	BLM438.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM468.getTag().toString()) ){
            	BLM468.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM491.getTag().toString()) ){
            	COM491.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM492.getTag().toString()) ){
            	COM492.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM431.getTag().toString()) ){
            	BLM431.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM433.getTag().toString()) ){
            	BLM433.setChecked(true);
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
