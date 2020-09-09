package com.example.sportsfield;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
	//map2액티비티에서 넘어온값 받을 변수
	String Store_name;
	String Address;
	String date;
	String time;

	//뷰에 이름 주소 날짜 시간
	TextView store_name2;
	TextView address2;
	TextView date2;
	TextView time2;
	TextView ID;

	Button button;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);

		Intent intent = getIntent();

		//db에서 memberid에 해당하는 4가지 변수를 불러온다//
		Store_name = intent.getStringExtra("Store_name");
		Address = intent.getStringExtra("Address");
		date = intent.getStringExtra("date");
		time = intent.getStringExtra("time");


		store_name2 = findViewById(R.id.store_name2);
		address2 = findViewById(R.id.address2);
		date2 = findViewById(R.id.date2);
		time2 = findViewById(R.id.time2);

		store_name2.setText(Store_name);
		address2.setText(Address);
		date2.setText("날짜 : " + date);
		time2.setText("시간 : " + time);

		//pref를 이용해서 ID값 가져오기
		ID = findViewById(R.id.ID);
		SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
		String userID = pref.getString("userID", "");
		ID.setText("ID : "+ userID);

		button = findViewById(R.id.registration);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//현재 main으로 화면 전환했는데 나중에 documentFragment로 이동시키자 잘몰라서 보류........
				Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
				startActivity(intent);
				Toast.makeText(CalendarActivity.this, "등록이 정상적으로 되었습니다.", Toast.LENGTH_LONG).show();

			}
		});

		ImageButton button = (ImageButton) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});

	}

}

