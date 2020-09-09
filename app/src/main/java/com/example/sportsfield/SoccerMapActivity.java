//package com.example.sportsfield;
//
//import androidx.fragment.app.FragmentActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SoccerMapActivity extends FragmentActivity implements OnMapReadyCallback {
//
//	private GoogleMap mMap;
//
//
//	//	json object list생성 /
//	public ArrayList<String> CityList;
//	public ArrayList<String>Store_nameList;
//	public ArrayList<String>floor_materiaList;
//	public ArrayList<String>AddressList;
//	public ArrayList<Double> LatitudeList;
//	public ArrayList<Double> LongitudeList;
//	public ArrayList<String> Sports_events;
//
//	static RequestQueue requestQueue;
//
//	//서버 URL 설정(php 파일 연동)
//	final static private String URL = "http://toywar94.dothome.co.kr/soccer.php";
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_soccer_map);
//
//		ImageButton button = findViewById(R.id.locateback1);
//		button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent();
//				setResult(RESULT_OK, intent);
//				finish();
//			}
//		});
//
//		CityList = new ArrayList<>();
//		Store_nameList = new ArrayList<>();
//		floor_materiaList= new ArrayList<>();
//		AddressList= new ArrayList<>();
//		LatitudeList= new ArrayList<>();
//		LongitudeList= new ArrayList<>();
//		Sports_events= new ArrayList<>();
//		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
//		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//				.findFragmentById(R.id.map);
//		mapFragment.getMapAsync(this);
//	}
//
//
//	/**
//	 * Manipulates the map once available.
//	 * This callback is triggered when the map is ready to be used.
//	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
//	 * we just add a marker near Sydney, Australia.
//	 * If Google Play services is not installed on the device, the user will be prompted to install
//	 * it inside the SupportMapFragment. This method will only be triggered once the user has
//	 * installed Google Play services and returned to the app.
//	 */
//	@Override
//	public void onMapReady(GoogleMap googleMap) {
//		mMap = googleMap;
//
//		StringRequest request = new StringRequest(
//				Request.Method.GET,
//				URL,
//				new Response.Listener<String>() {
//					@Override
//					public void onResponse(String response) {
//
//						try {
//							//json 파싱하기
//							JSONArray jsonArray = new JSONArray(response);
//							for (int i = 0; i < jsonArray.length(); i++) {
//								JSONObject obj = jsonArray.getJSONObject(i);
//
//								String City = obj.getString("City");
//								String Store_name = obj.getString("Store_name");
//								String floor_materia = obj.getString("floor_materia");
//								String Address = obj.getString("Address");
//								Double Longitude = obj.getDouble("Longitude");
//								Double Latitude = obj.getDouble("Latitude");
//								String Sports_events = obj.getString("Sports_events");
//
//
//								//위경도 가져와서 마커 가게이름 등등..
//								LatLng marker = new LatLng(Latitude, Longitude);
//								mMap.addMarker(new MarkerOptions()
//										.icon(BitmapDescriptorFactory.fromResource(R.drawable.soccermarker))
//										.position(marker)
//										.title(Store_name)
//										.snippet(Address));
//
//
//							}
//
//						} catch (JSONException e) {
//							e.printStackTrace();
//						}
//
//					}
//				},
//				new Response.ErrorListener() {
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						println("에러 ->" + error.getMessage());
//
//					}
//				}
//
//		) {
//			@Override
//			protected Map<String, String> getParams() throws AuthFailureError {
//				Map<String, String> params = new HashMap<String, String>();
//				return super.getParams();
//			}
//		};
//
//
//		requestQueue = Volley.newRequestQueue(SoccerMapActivity.this); // 요청을 만든다
//		request.setShouldCache(false);
//		requestQueue.add(request);
//
//
//
//		// Add a marker in Sydney and move the camera
////		LatLng sydney = new LatLng(-34, 151);
////		mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////		mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//	}
//	//에러시 토스트 메세지(안되면 지우기)
//	public void println(String data) {
//		Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
//	}
//}
