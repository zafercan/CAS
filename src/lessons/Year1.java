package lessons;

import java.util.ArrayList;

import web.DBHandler;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.example.cas.R;


public class Year1 extends Activity {

    public static final int TAG_YEAR_CODE = 1;
    DBHandler db;

    CheckBox COM101,COM102,COM111,COM115,TUR102,PHY121,PHY122,MTH105,MTH106,ENS120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year1);

        db = new DBHandler(this);
        ActionBar actionbar = getActionBar();
		actionbar.setTitle("Year 1");
        
        COM101 = (CheckBox)findViewById(R.id.COM101);
        COM102 = (CheckBox)findViewById(R.id.COM102);
        COM111 = (CheckBox)findViewById(R.id.COM111);
        COM115 = (CheckBox)findViewById(R.id.COM115);
        TUR102 = (CheckBox)findViewById(R.id.TUR102);
        PHY122 = (CheckBox)findViewById(R.id.PHY122);
        PHY121 = (CheckBox)findViewById(R.id.PHY121);
        MTH105 = (CheckBox)findViewById(R.id.MTH105);
        MTH106 = (CheckBox)findViewById(R.id.MTH106);
        ENS120 = (CheckBox)findViewById(R.id.ENS120);

        setUIFirst(db.getAllCodes(TAG_YEAR_CODE));
    }

    public void setUIFirst(ArrayList codeArray){
        Log.d("SET UI FIRST :", " ArrayList: " + codeArray);

/*        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0));
        Log.d("VALUES ARRAY LIST:", " >: " + codeArray.get(0).getClass());*/

        for(int i=0; i<codeArray.size(); i++) {
/*            Log.d("codeArray.get(): ", ">" + codeArray.get(i));
            Log.d("checkText: ", ">" + chkCom101.getText().toString());*/

        	 if( codeArray.get(i).equals(COM101.getTag().toString()) ){
             	COM101.setChecked(true);
             }
             else if( codeArray.get(i).equals(COM102.getTag().toString()) ){
             	COM102.setChecked(true);
             }
             else if( codeArray.get(i).equals(COM111.getTag().toString()) ){
             	COM111.setChecked(true);
             }
             else if( codeArray.get(i).equals(COM115.getTag().toString()) ){
             	COM115.setChecked(true);
             }
             else if( codeArray.get(i).equals(TUR102.getTag().toString()) ){
             	TUR102.setChecked(true);
             }
             else if( codeArray.get(i).equals(PHY122.getTag().toString()) ){
             	PHY122.setChecked(true);
             }
             else if( codeArray.get(i).equals(PHY121.getTag().toString()) ){
             	PHY121.setChecked(true);
             }
             else if( codeArray.get(i).equals(MTH105.getTag().toString()) ){
             	MTH105.setChecked(true);
             }
             else if( codeArray.get(i).equals(MTH106.getTag().toString()) ){
             	MTH106.setChecked(true);
             }
             else if( codeArray.get(i).equals(ENS120.getTag().toString()) ){
             	ENS120.setChecked(true);
             }
        	
        
        }

    }

    public void onCheckBoxClicked(View view){

        boolean checked = ((CheckBox) view).isChecked();
        String lectureCode = ((CheckBox) view).getTag().toString();

        //Log.d("DENEME: ",">" + view.getContext().getClass().getSimpleName());

        if(checked){
            // add to database
            Log.d("ADD: ", ">" + lectureCode);
            db.insertLecture(lectureCode, TAG_YEAR_CODE);
        } else{
            // remove from database
            Log.d("REMOVE: ", ">" + lectureCode);
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
