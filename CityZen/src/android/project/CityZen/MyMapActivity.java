package android.project.CityZen;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

//Nothing there works, just trying to do something

public class MyMapActivity extends MapActivity implements OnClickListener {

	private MapView myMap;
	 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		
		initMap();
		initMyLocation();
		
	}
 
	/**
	 * Initialise the map and adds the zoomcontrols to the LinearLayout.
	 */
	private void initMap() {
		myMap = (MapView) findViewById(R.id.mymap);
        myMap.setBuiltInZoomControls(true);
        myMap.displayZoomControls(true);
 
	}
 
	/**
	 * Initialises the MyLocationOverlay and adds it to the overlays of the map
	 */
	private void initMyLocation() {
		MyLocationOverlay myLocOverlay = new MyLocationOverlay(this, myMap);
		myLocOverlay.enableMyLocation();
		myLocOverlay.enableCompass();
		myMap.getOverlays().add(myLocOverlay);
 
	}
 
	protected boolean isRouteDisplayed() {
		return false;
	}
//	
//    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.map);
//        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//
//        LocationProvider locationProvider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
//     // Or use LocationManager.GPS_PROVIDER
//
//        	//Location lastKnownLocation = locationManager.getLastKnownLocation(locationManager\.);
//        try
//        {
//        	//getAddressForLocation(MapActivity.this.getApplicationContext(),myLocation);
//        }
//        catch(Exception e)
//        {}
//    }
	
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

        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = 
        gcd.getFromLocation(latitude, longitude,100);
        if (addresses.size() > 0 && addresses != null) {
        	StringBuilder result = new StringBuilder();
        	//txt.setText(addresses.get(0).getFeatureName()+"-"+addresses.get(0).getLocality()+"-"+addresses.get(0).getAdminArea()+"-"+addresses.get(0).getCountryName());
        }
    }
}