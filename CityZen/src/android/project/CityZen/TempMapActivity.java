package android.project.CityZen;


import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class TempMapActivity extends MapActivity implements OnClickListener {
	 private MapView map;
	 private MapController mapController;
	 private LocationListener locationListener;
	 private Location myLocation;
	 private AlertDialog alert;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tempmap);
        Button btn_next=(Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        
        alert = new AlertDialog.Builder(this).create();
        initMap();
    	initMyLocation(); 
       
    }
    private void initMyLocation() {

		locationListener = new GPSLocationListener();
		getPosition();

		MyLocationOverlay myLocOverlay = new MyLocationOverlay(this, map);
		myLocOverlay.enableMyLocation();
		
		map.getOverlays().add(myLocOverlay);
		updatePosition(myLocation);
	}

    private void initMap() {
        map =(MapView)findViewById(R.id.mymap);
        map.setBuiltInZoomControls(true);
		map.setBuiltInZoomControls(true);
		map.displayZoomControls(true);
		mapController = map.getController();
		mapController.setZoom(16);

	}
    private void updatePosition(Location location) {
    	if(myLocation == null)
    		return;
		GeoPoint point = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));
		mapController.animateTo(point);
		//mapController.setCenter(point);
		getPosition();
	}


    public void getPosition()
    {
    	EditText txt_position = (EditText)findViewById(R.id.edit_position);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		//locationListener = new GPSLocationListener();

		//locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

	    myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if (myLocation == null)
			myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		// It could be a good idea to observe gps accuracy

		if (myLocation == null) 
		{
			
			alert.setTitle("An error has occured");
			alert.setMessage("Enable GPS or networking to determine your position");
			alert.show();
			return;
			
		}
		
		double lat = myLocation.getLatitude(), lon = myLocation.getLongitude();
		Geocoder gc = new Geocoder(TempMapActivity.this.getApplicationContext(), Locale.getDefault());
		List<Address> addresses = null;
		
		try 
		{
			addresses = gc.getFromLocation(lat, lon, 1);
		} 
		catch (Exception e) 
		{		}
		Address address;
		StringBuilder result = new StringBuilder();
		if (addresses != null && addresses.size() > 0) {
			address = addresses.get(0);
			result.append(address.getAddressLine(0));
			result.append(", ");
			result.append(address.getLocality());
			result.append(", ");
			result.append(address.getCountryName());
			txt_position.setText(result.toString());
			
		}

    }
    public void onClick(View v) {
    	EditText txt_position = (EditText)findViewById(R.id.edit_position);

    	if(txt_position.getText().toString() == "")
    	{
    		alert.setTitle("Error");
    		alert.setMessage("Field address could not be empty");
    		alert.show();
    		return;
    	}
    	Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	private class GPSLocationListener implements LocationListener {
		
		public void onLocationChanged(Location location) {
			if (location != null) {
				updatePosition(location);
			}
		}

		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub

		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
		}
	}
}