package android.project.CityZen;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

//Nothing there works, just trying to do something

public class MyMapActivity extends MapActivity implements OnClickListener {

	private MapView myMap;
	private MapController mapController;
	private TextView myAddress;
	private LocationListener locationListener;
	private LocationManager locationManager;
	private Address address;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		initMap();
		initMyLocation();

		Button next = (Button) findViewById(R.id.btn_next);
		next.setOnClickListener(this);
	}

	/**
	 * Initialise the map and adds the zoomcontrols to the LinearLayout.
	 */
	private void initMap() {
		myAddress = (TextView) findViewById(R.id.editText1);
		myMap = (MapView) findViewById(R.id.mymap);
		myMap.setBuiltInZoomControls(true);
		myMap.displayZoomControls(true);

		mapController = myMap.getController();
		mapController.setZoom(16);

	}

	/**
	 * Initialises the MyLocationOverlay and adds it to the overlays of the map
	 */
	private void initMyLocation() {

		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		locationListener = new GPSLocationListener();

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

		Location myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		if (myLocation == null)
			myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		// It could be a good idea to observe gps accuracy

		if (myLocation == null) 
			Toast.makeText(getBaseContext(), "Problem getting current position", Toast.LENGTH_SHORT).show();

		MyLocationOverlay myLocOverlay = new MyLocationOverlay(this, myMap);
		myLocOverlay.enableMyLocation();
		myMap.getOverlays().add(myLocOverlay);
		updatePosition(myLocation);
	}

	protected boolean isRouteDisplayed() {
		return false;
	}

	private void updatePosition(Location location) {
		GeoPoint point = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));
		mapController.animateTo(point);
		double lat = location.getLatitude(), lon = location.getLongitude();
		Geocoder gc = new Geocoder(MyMapActivity.this.getApplicationContext(), Locale.getDefault());
		List<Address> addresses = null;
		try {
			addresses = gc.getFromLocation(lat, lon, 1);
		} catch (Exception e) {
		}

		if (addresses != null && addresses.size() > 0) {
			address = addresses.get(0);
			StringBuilder result = new StringBuilder();
			result.append(address.getAddressLine(0));
			result.append(", ");
			result.append(address.getLocality());
			result.append(", ");
			result.append(address.getCountryName());
			myAddress.setText(result.toString());
		}
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
	
	public void onClick(View v) {
		Report report = Report.getReport();
		if (address != null || myAddress.getText().length() == 0) {
			report.setAddress(address.getAddressLine(0));
			report.setCity(address.getLocality());
			report.setCountry(address.getCountryName());
		} else {
			Toast.makeText(getBaseContext(), "Empty address, please insert manually", Toast.LENGTH_SHORT).show();
			return;
		}
		
		Intent i = new Intent(this, SecondActivity.class);
		startActivity(i);
	}
}