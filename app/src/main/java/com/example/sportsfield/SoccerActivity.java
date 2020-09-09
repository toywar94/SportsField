package com.example.sportsfield;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Movie;
import android.icu.text.LocaleDisplayNames;
import android.location.Address;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SoccerActivity extends AppCompatActivity {


	ImageAdapter adapter;
	RecyclerView adapter2;


	MenuItem search;
	Toolbar toolbar;

	//Random random;
	TextView mainName;

	//RecyclerViewSelect으로 데이터 전달
	String Store_name;
	String Address;
	String floor_materia;
	Double Latitude;
	Double Longitude;

	static public Context context;

	Intent intent;
	String category;
//
	ArrayList<ImageData> item = new ArrayList<ImageData>();

	static RequestQueue requestQueue;

	final static private String URL = "http://toywar94.dothome.co.kr/location.php";

	//툴바 검색 기능 구현
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater =  getMenuInflater();
		inflater.inflate(R.menu.search, menu);

		final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setMaxWidth(Integer.MAX_VALUE);
		searchView.setQueryHint("스포츠 시설 이름을 입력해주세요");


		search = menu.findItem(R.id.action_search);


//		검색버튼 누를시 확장됌
//		search.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
//			@Override
//			public boolean onMenuItemActionExpand(MenuItem item) {
//
//				return true;
//			}
//
//			@Override
//			public boolean onMenuItemActionCollapse(MenuItem item) {
//				mainName.clearFocus();
//				return true;
//			}
//		});

		final SearchView searchOk = (SearchView) search.getActionView();
		//검색 확인버튼 (>) 활성화
		searchOk.setSubmitButtonEnabled(true);
		//검색 리스너 구현  >> OK
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				Log.d(query, "kkkk");
				adapter.getFilter().filter(query);
				return false;

			}

			@Override
			public boolean onQueryTextChange(String query) {
				adapter.getFilter().filter(query);

				return false;

			}
		});

		return true;

	}

	////filter 함수
	private static ArrayList<ImageData> filter(ArrayList<ImageData> models, String query) {
		final String lowerCaseQuery = query.toLowerCase();

		final ArrayList<ImageData> filteredModelList = new ArrayList<>();
		for (ImageData model : models) {
			final String text = model.getStore_name().toLowerCase();
			if (text.contains(lowerCaseQuery)) {
				filteredModelList.add(model);
			}
		}
		return filteredModelList;
	}


	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		//툴바 뒤로가기 버튼만들기
		int id = item.getItemId();
		if (item.getItemId() == android.R.id.home) {
			finish();
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soccer);

		context = this;

		//현재 noactionbar라서 축구장쪽에 검색하기위한 툴바를 설정
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		//툴바 위에 프로젝트 이름 지움
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);


		mainName = findViewById(R.id.mainName);
		//random = new Random();

		intent = getIntent();
		category = intent.getStringExtra("category");


		Response.Listener<String> responseListener = new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {

				try {
					//json 파싱하기
					JSONArray jsonArray = new JSONArray(response);
					//Log.d("kk", jsonArray.toString());

					RecyclerView recyclerView = findViewById(R.id.recyclerView);
					recyclerView.setHasFixedSize(true);
					LinearLayoutManager layoutmanager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
					recyclerView.setLayoutManager(layoutmanager);


					adapter = new ImageAdapter(getApplicationContext(),item);
					adapter.notifyDataSetChanged();


					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject obj = jsonArray.getJSONObject(i);

						String City = obj.getString("City");
						Store_name = obj.getString("Store_name");

						floor_materia = obj.getString("floor_materia");
						if (floor_materia.equals("")) {
							floor_materia = "재질없음";
						}

						Address = obj.getString("Address");
						if(Address.equals("")){
							Address = City;
						}
						Longitude = obj.getDouble("Longitude");
						Latitude = obj.getDouble("Latitude");
						String Sports_events = obj.getString("Sports_events");


						if (category.equals("축구장")) {
							mainName.setText(category);
						} else if (category.equals("농구")) {
							mainName.setText(category);
						} else if (category.equals("배드민턴")) {
							mainName.setText(category);
						} else if (category.equals("당구장")) {
							mainName.setText(category);
						} else if (category.equals("수영장")) {
							mainName.setText(category);
						} else if (category.equals("풋살장")) {
							mainName.setText(category);
						} else if (category.equals("골프장")) {
							mainName.setText(category);
						} else if (category.equals("테니스장")) {
							mainName.setText(category);
						} else if (category.equals("야구장")) {
							mainName.setText(category);
						}
						//아이템에 시설 주소 값 넣기
						adapter.addItem(new ImageData("시설이름 : " +Store_name, "주소 : " +Address,"바닥재질 : "+floor_materia,Latitude,Longitude));


					}
					recyclerView.setAdapter(adapter);



				} catch (JSONException e) {
					e.printStackTrace();

				}

			}

		};

		MapsRequest registerRequest = new MapsRequest(category, responseListener);
		RequestQueue queue = Volley.newRequestQueue(SoccerActivity.this);
		queue.add(registerRequest);


	}


}





