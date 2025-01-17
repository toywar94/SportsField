package com.example.sportsfield;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

/*public class MapsActivity_back extends FragmentActivity implements AutoPermissionsListener {
	SupportMapFragment mapFragment;
	GoogleMap map;


	TextView textView;

	MarkerOptions myLocationMarker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap googleMap) {
				Log.d("Map", "지도 준비됨.");
				map = googleMap;
			}
		});

		try {
			MapsInitializer.initialize(this);
		} catch (Exception e) {
			e.printStackTrace();
		}


		Button button = findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLocationService();
			}
		});

		AutoPermissions.Companion.loadAllPermissions(this, 101);
	}

	public void startLocationService() {
		LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		try {
			Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				double latitude = location.getLatitude();
				double longitude = location.getLongitude();
				String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;

				textView.setText(message);
			}

			GPSListener gpsListener = new GPSListener();
			long minTime = 10000;
			float minDistance = 0;

			manager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER,
					minTime, minDistance, gpsListener);

			Toast.makeText(getApplicationContext(), "내 위치확인 요청함",
					Toast.LENGTH_SHORT).show();

		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	class GPSListener implements LocationListener {
		public void onLocationChanged(Location location) {
			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			String message = "내 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;
			textView.setText(message);

			showCurrentLocation(latitude, longitude);
		}

		public void showCurrentLocation(Double latitude, Double longitude) {
			LatLng curpoint = new LatLng(latitude, longitude);
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(curpoint, 15));

			showMyLocationMarker(curpoint);
		}

		private void showMyLocationMarker(LatLng curPoint) {
			if (myLocationMarker == null) {
				myLocationMarker = new MarkerOptions();
				myLocationMarker.position(curPoint);
				myLocationMarker.title("● 내 위치\n");
				myLocationMarker.snippet("● GPS로 확인한 위치");
				myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
				map.addMarker(myLocationMarker);
			} else {
				myLocationMarker.position(curPoint);
			}
		}


		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}
	//권한추가
	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
	}

	@Override
	public void onDenied(int requestCode, String[] permissions) {
		Toast.makeText(this, "permissions denied : " + permissions.length, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onGranted(int requestCode, String[] permissions) {
		Toast.makeText(this, "permissions granted : " + permissions.length, Toast.LENGTH_LONG).show();
	}

}*/


