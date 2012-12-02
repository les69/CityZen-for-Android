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
import android.widget.Toast;

public class ThirdActivity extends Activity implements OnClickListener {
	private static final int CAMERA_REQUEST = 1888;
	private ImageView imageView;
	private ImageButton img_btn;
	private ImageButton btn_del;
	private Button btn_next;
	private Report report;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture);
		report = Report.getReport();
		img_btn = (ImageButton) findViewById(R.id.btn_image);
		img_btn.setOnClickListener(this);
		btn_del = (ImageButton) findViewById(R.id.btn_delete);
		btn_del.setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.img_preview);
		btn_next = (Button) findViewById(R.id.btn_next2);
		btn_next.setOnClickListener(this);

	}

	public void onClick(View v) {
		// This is ugly, seriously :P
		if (v == img_btn) {
			Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(cameraIntent, CAMERA_REQUEST);

		}
		if (v == btn_del) {
			imageView.setImageBitmap(null);
			report.setImageView(null);

		}
		if (v == btn_next) {
			StringBuilder message = new StringBuilder();
			message.append("Category").append(report.getCategory()).append("\n");
			message.append("Desc: ").append(report.get_description()).append("\n");
			message.append("Addr: ").append(report.getAddress()).append("\n");
			message.append("City: ").append(report.getCity()).append("\n");
			message.append("Country: ").append(report.getCountry()).append("\n");
			message.append("Foto").append(report.getImageView() == null ? "no" : "yes").append("\n");
			Toast.makeText(getBaseContext(), message.toString(), Toast.LENGTH_SHORT).show();
			
//			Intent i = new Intent(this, MyMapActivity.class);
//			startActivity(i);
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			imageView.setImageBitmap(photo);
			report.setImageView(photo);

		}
	}
}
