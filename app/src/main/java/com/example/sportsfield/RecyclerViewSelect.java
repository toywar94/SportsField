package com.example.sportsfield;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;


public class RecyclerViewSelect extends FragmentActivity implements OnMapReadyCallback {

	String storeName;
	String addressName;
	String floorName;
	double latitude;
	double longitude;

	TextView textView1;
	TextView textView2;
	TextView textView3;

	Intent intent;


	public static Context context;

	private GoogleMap mMap;
	private FusedLocationProviderClient mFusedLocationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler_view_select);

		textView1 = findViewById(R.id.store_name);
		textView2 = findViewById(R.id.address);
		textView3 = findViewById(R.id.floor);

		//ImageAdapter 클래스에서 보낸 데이터 받아서 settext
		intent = getIntent();

		storeName = intent.getStringExtra("StoreName");
		textView1.setText(storeName);

		addressName = intent.getStringExtra("AddressName");
		textView2.setText(addressName);

		floorName = intent.getStringExtra("FloorName");
		textView3.setText(floorName);



//		Log.d("StoreName",StoreName);

		mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


		//SoccerActivity가 아닌 RecyclerViewSelect 클래스에서 화면전환 처리
		ImageButton button = findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});


		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}


	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;

		intent = getIntent();
		//왜 -1값을 주는건지.. 흠....냐......
		latitude = intent.getDoubleExtra("Latitude",-1);
		longitude = intent.getDoubleExtra("Longitude",-1);


		//현재 어느 아이템을 클릭해도 똑같은 지도위치 마커로 표시되는데...
		mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
			@Override
			public void onSuccess(Location location) {
				if (location != null) {

						LatLng marker = new LatLng(latitude, longitude);
						CameraPosition cp = new CameraPosition.Builder().target(marker).zoom(15).build();
						mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
						mMap.addMarker(new MarkerOptions()
								.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation))
								.position(marker)
								.title(storeName)
								.snippet(addressName));
				}

				mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						Intent intent = new Intent(RecyclerViewSelect.this, Maps2Activity.class);
						startActivity(intent);
					}
				});
				
				mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
					@Override
					public boolean onMarkerClick(Marker marker) {
						Toast.makeText(RecyclerViewSelect.this, "표를 클릭하면 등록화면으로 이동됩니다.", Toast.LENGTH_SHORT).show();
						return false;
					}
				});
				

			}

		});
	}



	//marker icon 표시하려고 비트맵사용 > 구글에서 복붙..
	private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
		Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
		vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
		Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		vectorDrawable.draw(canvas);
		return BitmapDescriptorFactory.fromBitmap(bitmap);
	}

}
