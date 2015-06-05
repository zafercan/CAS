package lessons;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.cas.R;


public class AddLessonSelectYear extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.addlessonselectyear);

		ActionBar actionbar = getActionBar();
		actionbar.setTitle("Years");
//		actionbar.setIcon(R.drawable.back_w);
//		actionbar.setIcon(null);
//		actionbar.setDisplayUseLogoEnabled(false);
		//actionbar.setHomeAsUpIndicator(null);
		
		Button button1 = (Button) findViewById(R.id.button_year1);
		Button button2 = (Button) findViewById(R.id.button_year2);
		Button button3 = (Button) findViewById(R.id.button_year3);
		Button button4 = (Button) findViewById(R.id.button_year4);

		Drawable d1 = this.getResources().getDrawable(R.drawable.ic_1);
		Drawable d2 = this.getResources().getDrawable(R.drawable.ic_2);
		Drawable d3 = this.getResources().getDrawable(R.drawable.icon_3);
		Drawable d4 = this.getResources().getDrawable(R.drawable.icon_4);
		
		
		button1.setTextColor(Color.parseColor("white"));
		button2.setTextColor(Color.parseColor("white"));
		button3.setTextColor(Color.parseColor("white"));
		button4.setTextColor(Color.parseColor("white"));
		
//		button1.setBackgroundDrawable(d1);
//		button2.setBackgroundDrawable(d2);
//		button3.setBackgroundDrawable(d3);
//		button4.setBackgroundDrawable(d4);
		
		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(AddLessonSelectYear.this, Year1.class);
				startActivity(i);

			}
		});
		
		button2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(AddLessonSelectYear.this, Year2.class);
				startActivity(i);

			}
		});
		
		button3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(AddLessonSelectYear.this, Year3.class);
				startActivity(i);

			}
		});
		
		button4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(AddLessonSelectYear.this, Year4.class);
				startActivity(i);

			}
		});

	}

}
