package android.project.CityZen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity implements OnClickListener {
	TextView txt;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);
        txt = (TextView)findViewById(R.id.txt_description);
        Button next = (Button)findViewById(R.id.btn_next);
        next.setOnClickListener(this);
        
	}
	
	public void onClick(View v)
	{
		Spinner category =(Spinner)findViewById(R.id.item_category);
		String s = category.getSelectedItem().toString();
		
		Report report = Report.getReport();
		report.setCategory(s.equalsIgnoreCase("barriera")?Category.BARRIERA:Category.INCIVILTA);
		
		if((txt.getText().length() == 0) || !Report.setDescription(txt.getText().toString()))
		{
			Toast.makeText(getBaseContext(), "Empty description or more than 300 characters", Toast.LENGTH_SHORT).show();
			return;
		}
		//Start new intent
		Intent i = new Intent(this, ThirdActivity.class);
        startActivity(i);
	}
}