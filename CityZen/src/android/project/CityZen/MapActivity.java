package android.project.CityZen;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

//Nothing there works, just trying to do something

public class MapActivity extends Activity implements OnClickListener {
	TextView txt;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        txt  =(TextView)findViewById(R.id.txt_temp);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationProvider locationProvider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
     // Or use LocationManager.GPS_PROVIDER

        	//Location lastKnownLocation = locationManager.getLastKnownLocation(locationManager\.);
        try
        {
        	//getAddressForLocation(MapActivity.this.getApplicationContext(),myLocation);
        }
        catch(Exception e)
        {}
    }
    public void onClick(View v) {
    	//Fix that
    	Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }
    public void getAddressForLocation(Context context, Location location) throws IOException {

        if (location == null) {
            return;
        }
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        int maxResults = 1;

        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = 
        gcd.getFromLocation(latitude, longitude,100);
        if (addresses.size() > 0 && addresses != null) {
                   StringBuilder result = new StringBuilder();
        txt.setText(addresses.get(0).getFeatureName()+"-"+addresses.get(0).getLocality()+"-"+addresses.get(0).getAdminArea()+"-"+addresses.get(0).getCountryName());
        }
    }
}