package android.project.CityZen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class CityZenActivity extends Activity implements OnClickListener {
	    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root);
        
        Button first_button = (Button)findViewById(R.id.button_begin);
        first_button.setOnClickListener(this);
    }
    public void onClick(View v) {
    	Intent i = new Intent(this, TempMapActivity.class);
        startActivity(i);
    }
}