package com.example.sportsfield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Calendar;

public class Maps2Activity extends AppCompatActivity {
	Intent intent;

	//Mapactivity에서 넘어온 시설이름 주소 값
	String name;
	String name2;

	//넘어온 시설이름 주소값을 settext
	TextView Store_name;
	TextView Address;

	DatePicker datePicker;
	TimePicker timePicker;
	TextView yearOf;
	TextView time;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps2);

		//시설이름, 주소 값 빼기
		intent = getIntent();

		name = intent.getStringExtra("Store_name");
		name2 = intent.getStringExtra("Address");
		//아이디값 찾아주고 카드뷰에 시설이름 주소 값 넣어주기
		Store_name = findViewById(R.id.store_name);
		Address = findViewById(R.id.address);
		Store_name.setText("시설이름 : " + name);
		Address.setText("주소 : " + name2);

		datePicker = findViewById(R.id.datePicker);
		timePicker = findViewById(R.id.timePicker);
		yearOf = findViewById(R.id.yearOf);
		time = findViewById(R.id.time);

		//MapsActivity에서 넘어온거 처리
		ImageButton button1 = (ImageButton) findViewById(R.id.locateback1);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		//예약하기 버튼 누르면 넘어가는 버튼
		Button button2 = findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Registration();
//				startActivity(intent);
				//예약하기 버튼 누를 시  sql으로 데이터 저장시켜야함



			}


		});

		//캘린더 객체 생성
		Calendar c = Calendar.getInstance();
		//년 월 일
		int year = c.get((Calendar.YEAR));
		int month = c.get((Calendar.MONTH)); //
		Log.d("month", Integer.toString(month));
		int day = c.get((Calendar.DAY_OF_MONTH));

		//datepicker의 init메소드 호출해서 년 월 일 텍스트에 넣기
		datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker view, int year, int month, int day) {
				int mn = month + 1;
				yearOf.setText(year + "/" + mn + "/" + day);
			}
		});

		timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				time.setText("  " + hourOfDay + "시" + " " + minute + "분");
			}
		});


	}


	public void Registration() {
		//Member_id어케 가져오냐
		SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
		String userID =pref.getString("userID", "");
		//바이러리를 어떻게 String 으로 변환할지..........
		final String Member_id = userID;
		String re_Store_name = Store_name.getText().toString();
		String re_Address = Address.getText().toString();
		String re_Date = yearOf.getText().toString();
		String re_Time = time.getText().toString();

		Log.d("userID", Member_id);
		Log.d("re_Store_name", re_Store_name);
		Log.d("re_Date", re_Date);
		Log.d("re_Time", re_Time);


		Response.Listener<String> responseListener = new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {

				try {
					JSONObject jsonObject = new JSONObject( response );
					Log.d("df", jsonObject.toString());
					boolean success = jsonObject.getBoolean( "success" );

					//등록 성공시
					if(success) {

						Toast.makeText( getApplicationContext(), "등록이 완료되었습니다", Toast.LENGTH_SHORT ).show();
						Intent intent = new Intent( Maps2Activity.this, CalendarActivity.class );
						intent.putExtra("Store_name", Store_name.getText().toString());
						intent.putExtra("Address", Address.getText().toString());
						intent.putExtra("date", yearOf.getText().toString());
						intent.putExtra("time", time.getText().toString());
						startActivity(intent);
						//안되면 바꾸기
						if (Member_id.isEmpty()) {
							Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_SHORT).show();
							return;
						}

						//등록 실패시
					} else {
						Toast.makeText( getApplicationContext(), "등록 실패되었습니다", Toast.LENGTH_SHORT ).show();
						return;
					}

				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		};

		//서버로 Volley를 이용해서 요청----현재 member_id는 변수 선언이 안되서 오류나는거고 로그인한 다음 그 아이디를 저장어케하냐
		RegisterRequeset2 registerRequest = new RegisterRequeset2(Member_id, re_Store_name, re_Address, re_Date, re_Time,responseListener);
		RequestQueue queue = Volley.newRequestQueue(Maps2Activity.this);
		queue.add(registerRequest);
	}


}







