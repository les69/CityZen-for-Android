package android.project.CityZen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class ThirdActivity extends Activity implements OnClickListener {
	private static final int CAMERA_REQUEST = 1888; 
    private ImageView imageView;
    ImageButton img_btn;
    ImageButton btn_del;
    Button btn_next;
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
	
    super.onCreate(savedInstanceState);
    setContentView(R.layout.picture);
    img_btn = (ImageButton)findViewById(R.id.btn_image);
    img_btn.setOnClickListener(this);
    btn_del = (ImageButton)findViewById(R.id.btn_delete);
    btn_del.setOnClickListener(this);
    imageView = (ImageView)findViewById(R.id.img_preview);
    imageView.setImageBitmap(Report.getImageView());
    btn_next = (Button)findViewById(R.id.btn_next2);
    
	}

public void onClick(View v) {
	//This is ugly, seriously :P
	if(v == img_btn)
	{
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
		startActivityForResult(cameraIntent, CAMERA_REQUEST);
	}
	if(v == btn_del)
	{
		imageView.setImageBitmap(null);
		Report.setImageView(null);
	}
	if(v == btn_next)
	{
		Intent i = new Intent(this, MapActivity.class);
        startActivity(i);
	}
}
protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
    if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
        Bitmap photo = (Bitmap) data.getExtras().get("data"); 
        imageView.setImageBitmap(photo);
        Report.setImageView(photo);
    }  
}
}


