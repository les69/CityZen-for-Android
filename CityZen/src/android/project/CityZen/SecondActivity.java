package android.project.CityZen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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
		Report.setCategory(s.equalsIgnoreCase("barriera")?"0":"1");
		
		if((txt.getText().length() == 0) || !Report.setDescription(txt.getText().toString()))
		{
			AlertDialog alertDialog = new AlertDialog.Builder(SecondActivity.this).create();
			alertDialog.setTitle("An error occured");
			alertDialog.setMessage("Empty description or more than 300 characters");
			alertDialog.show();
			return;
		}
		//Start new intent
		Intent i = new Intent(this, ThirdActivity.class);
        startActivity(i);
		
		
	}
}