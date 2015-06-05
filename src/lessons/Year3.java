package lessons;

import java.util.ArrayList;

import web.DBHandler;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.example.cas.R;


public class Year3 extends Activity {

    public static final int TAG_YEAR_CODE = 3;
    DBHandler db;

    CheckBox COM334,COM336,COM352,COM364,COM376,BLM367,BLM343,COM325,BLM331,BLM334,BLM336;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year3);

        db = new DBHandler(this);
        ActionBar actionbar = getActionBar();
		actionbar.setTitle("Year 3");
		
//        int id = Resources.getSystem().getIdentifier("btn_check_holo_light", "drawable", "android");
//        ((CheckBox) findViewById(R.id.COM334)).setButtonDrawable(id);
        
        COM334 = (CheckBox)findViewById(R.id.COM334);
        COM336 = (CheckBox)findViewById(R.id.COM336);
        COM352 = (CheckBox)findViewById(R.id.COM352);
        COM364 = (CheckBox)findViewById(R.id.COM364);
        COM376 = (CheckBox)findViewById(R.id.COM376);
        BLM343 = (CheckBox)findViewById(R.id.BLM343);
        BLM367 = (CheckBox)findViewById(R.id.BLM367);
        COM325 = (CheckBox)findViewById(R.id.COM325);
        BLM331 = (CheckBox)findViewById(R.id.BLM331);
        BLM334 = (CheckBox)findViewById(R.id.BLM334);
        BLM336 = (CheckBox)findViewById(R.id.BLM336);

        setUIFirst(db.getAllCodes(TAG_YEAR_CODE));
    }

    public void setUIFirst(ArrayList<?> codeArray){
      //  Log.d("SET UI FIRST :", " ArrayList: " + codeArray);

/*        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0));
        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0).getClass());*/

        for(int i=0; i<codeArray.size(); i++) {
/*            Log.d("codeArray.get(): ", ">" + codeArray.get(i));
            Log.d("checkText: ", ">" + chkCOM334.getText().toString());*/

        	if( codeArray.get(i).equals(COM334.getTag().toString()) ){
            	COM334.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM336.getTag().toString()) ){
            	COM336.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM352.getTag().toString()) ){
            	COM352.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM364.getTag().toString()) ){
            	COM364.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM376.getTag().toString()) ){
            	COM376.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM343.getTag().toString()) ){
            	BLM343.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM367.getTag().toString()) ){
            	BLM367.setChecked(true);
            }
            else if( codeArray.get(i).equals(COM325.getTag().toString()) ){
            	COM325.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM331.getTag().toString()) ){
            	BLM331.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM334.getTag().toString()) ){
            	BLM334.setChecked(true);
            }
            else if( codeArray.get(i).equals(BLM336.getTag().toString()) ){
            	BLM336.setChecked(true);
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
