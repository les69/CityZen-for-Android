package android.project.CityZen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SubmitActivity extends Activity implements OnClickListener {
	 String json;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit);
        TextView txt = (TextView)findViewById(R.id.txtOut);
        
        String[] keys= new String[] {"country","city","address","category","description","image"};
        String[] val = new String[] {Report.getCountry(),Report.getCity(),Report.getAddress(),Report.getCategory(),Report.get_description(),Report.getBase64Image()};
        
        
        
        json = generateJSON(keys,val);
        	
        
        
        Button submit = (Button)findViewById(R.id.btn_submit);
        submit.setOnClickListener(this);
    }
    public String generateJSON(String[] keys,String[] values)
    {
    	StringBuilder sb = new StringBuilder();
    	sb.append("{");
    	char c= '"';
    	for(int i=0;i<keys.length;i++)
    		sb.append(c+keys[i]+c+":"+c+values[i]+c+',');
    	sb.replace(sb.length()-1, sb.length()," ");
    	return sb.toString().concat("}");
    		
    	
    }
    public void postData(String json) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = null;
        HttpPost httppost = null;

        try {
            // Add your data
        	httpclient = new DefaultHttpClient();
        	httppost = new HttpPost("http://192.168.43.22:8000/api/");
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("data", json));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        //Toast.makeText(getBaseContext(), "Ok", "Everything goes fine").show();
    } 
    public void onClick(View v) {
    	postData(json);
    	//Intent i = new Intent(this, MapActivity.class);
    	Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }
}