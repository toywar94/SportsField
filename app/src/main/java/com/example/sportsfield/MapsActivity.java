package com.example.sportsfield;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
		GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

	//id : text버튼
	Button button;
	//	json object list생성 /
	public ArrayList<String> CityList;
	//Locate에서 누른 각 스포츠 category에 저장
	Intent intent;
	String category;

//	public Class<R.drawable> drawable = R.drawable.class;
//	Drawable category_icon;
	int category_icon;

//	public ArrayList<String>Store_nameList;
//	public ArrayList<String>floor_materiaList;
//	public ArrayList<String>AddressList;
//	public ArrayList<Double> LatitudeList;
//	public ArrayList<Double> LongitudeList;
//	public ArrayList<String> Sports_events;

	static RequestQueue requestQueue;

//	//스피너 1 도
//	String[] items = {"경기도"};
//	//스피도 2 시/군
//	String[] items2 = {"고양시", "수원시", "성남시", "용인시",
//			"부천시", "안산시", "남양주", "안양시", "화성시",
//			"평택시", "파주시", "시흥시", "광명시", "김포시",
//			"광주시", "군포시", "오산시", "이천시", "양주시", "구리시", "안성시", "의왕시", "포천시",
//			"하남시", "여주시", "양평군", "동두천", "과천시", "가평군"};

	// 권한 체크 요청 코드 정의
	public static final int REQUEST_CODE_PERMISSIONS = 1000;
	//서버 URL 설정(php 파일 연동)
	final static private String URL = "http://toywar94.dothome.co.kr/location.php";

	private GoogleMap mMap;
	private GoogleApiClient mGoogleApiClient;

	//내 위치마커 변경
	MarkerOptions myLocationMarker;
	// 위치 정보 얻는 객체
	private FusedLocationProviderClient mFusedLocationClient;

	public MapsActivity() throws JSONException {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		//LocateF 클래스에서 각 스포츠 누르것을 가져온다
		intent = getIntent();
		category = intent.getStringExtra("category");



		//지도 표시할 arraylist
//		CityList = new ArrayList<>();
//		Store_nameList = new ArrayList<>();
//		floor_materiaList= new ArrayList<>();
//		AddressList= new ArrayList<>();
//		LatitudeList= new ArrayList<>();
//		LongitudeList= new ArrayList<>();
//		Sports_events= new ArrayList<>();
		//스피너 아이디 찾아주고
//		Spinner spinner = findViewById(R.id.spinner);
//		Spinner spinner2 = findViewById(R.id.spinner2);

//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
//		spinner.setAdapter(adapter);
//
//		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items2);
//		spinner2.setAdapter(adapter2);


		ImageButton button2 = (ImageButton) findViewById(R.id.locateback1);

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("category", category);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		//Maps2Activity로 넘어가기 >>지우기
//		Button button = findViewById(R.id.test);
////		button.setOnClickListener(new View.OnClickListener() {
////			@Override
////			public void onClick(View v) {
////				Intent intent = new Intent(MapsActivity.this, Maps2Activity.class);
////				startActivity(intent);
////
////
////			}
////		});


		// GoogleAPIClient의 인스턴스 생성
		if (mGoogleApiClient == null) {
			mGoogleApiClient = new GoogleApiClient.Builder(this)
					.addConnectionCallbacks(this)
					.addOnConnectionFailedListener(this)
					.addApi(LocationServices.API)
					.build();
		}

		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

		mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
	}

	@Override
	protected void onStart() {
		mGoogleApiClient.connect();
		super.onStart();
	}

	@Override
	protected void onStop() {
		mGoogleApiClient.disconnect();
		super.onStop();
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
//인포 리스너 설정

		Response.Listener<String> responseListener = new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {

				try {
					//json 파싱하기
					JSONArray jsonArray = new JSONArray(response);
//					Log.d("response", response);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject obj = jsonArray.getJSONObject(i);

						String City = obj.getString("City");
						String Store_name = obj.getString("Store_name");
						String floor_materia = obj.getString("floor_materia");
						String Address = obj.getString("Address");
						Double Longitude = obj.getDouble("Longitude");
						Double Latitude = obj.getDouble("Latitude");
						String Sports_events = obj.getString("Sports_events");


						if (category.equals("축구장")) {
							category_icon = R.drawable.soccermarker;
						} else if (category.equals("농구")) {
							category_icon = R.drawable.basketballmarker;
						} else if (category.equals("배드민턴")) {
							category_icon = R.drawable.badmintonmarker;
						} else if (category.equals("당구장")) {
							category_icon = R.drawable.billiardmarker;
						} else if (category.equals("수영장")) {
							category_icon = R.drawable.swimmarker;
						} else if (category.equals("풋살장")) {
							category_icon = R.drawable.putsalmarker;
						} else if (category.equals("골프장")) {
							category_icon = R.drawable.golfmarker;
						} else if (category.equals("테니스장")) {
							category_icon = R.drawable.tennismarker;
						} else if (category.equals("야구장")) {
							category_icon = R.drawable.baseballmarker;

						}


						//sql 데이터 가져온 것을 위경도, 가게이름, 주소에 넣음
						LatLng marker = new LatLng(Latitude, Longitude);
						mMap.addMarker(new MarkerOptions()
								.icon(bitmapDescriptorFromVector(getApplicationContext(), category_icon))
								.position(marker)
								.title(Store_name)
								.snippet(Address));


					}


				} catch (JSONException e) {
					e.printStackTrace();

				}

			}

		};

		mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker marker) {
				Intent intent = new Intent(MapsActivity.this, Maps2Activity.class);
				intent.putExtra("Store_name", marker.getTitle());
				intent.putExtra("Address", marker.getSnippet());
				startActivity(intent);

			}
		});

		mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				Toast.makeText(MapsActivity.this, "대화창을 클릭하면 등록 할 수 있습니다.", Toast.LENGTH_LONG).show();
				return false;
			}
		});


//		requestQueue = Volley.newRequestQueue(MapsActivity.this); // 요청을 만든다
//		request.setShouldCache(false);
//		requestQueue.add(request);

		MapsRequest registerRequest = new MapsRequest(category, responseListener);
		RequestQueue queue = Volley.newRequestQueue(MapsActivity.this);
		queue.add(registerRequest);


		// LatLng marker_default = new LatLng(34, 98);
		//mMap.moveCamera(CameraUpdateFactory.newLatLng(marker_default));

		// 카메라 줌
//		mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
//
		mMap.getUiSettings().setMyLocationButtonEnabled(true);
		mMap.getUiSettings().setZoomControlsEnabled(true);
		mMap.getUiSettings().setCompassEnabled(true);


	}


	@Override
	public void onConnected(@Nullable Bundle bundle) {

	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		switch (requestCode) {
			case REQUEST_CODE_PERMISSIONS:
				if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
					Toast.makeText(this, "권한 체크 거부 됨", Toast.LENGTH_SHORT).show();
				}
				return;
		}

	}

	@Override
	public void onConnectionSuspended(int i) {

	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

	}

	//mMap.icon  bitmapDescriptorFromVector을 사용하기위해 쓴거임 구글에 복붙함
	private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
		Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
		vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
		Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		vectorDrawable.draw(canvas);
		return BitmapDescriptorFactory.fromBitmap(bitmap);
	}

	public void onLastLocationButtonClicked(View view) {
		// 권한 체크
		if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSIONS);
			return;
		}
		mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
			@Override
			public void onSuccess(Location location) {
				if (location != null) {
					// 현재 위치
					LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
					CameraPosition cp = new CameraPosition.Builder().target(myLocation).zoom(15).build();
					mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
					mMap.addMarker(new MarkerOptions()
							.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation))
							.position(myLocation)
							.title("현재 위치"));

//					mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
//
//					// 카메라 줌
//					mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
				}
			}
		});


	}

	//에러시 토스트 메세지(안되면 지우기)
	public void println(String data) {
		Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
	}


}












