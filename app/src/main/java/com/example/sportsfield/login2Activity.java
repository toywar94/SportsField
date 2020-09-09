package com.example.sportsfield;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class login2Activity extends AppCompatActivity {
	private EditText et_id, et_pass;
	private Button loginbtn2;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		//뒤로가기버튼
		ImageButton button = (ImageButton) findViewById(R.id.backbtn1);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});

		et_id = findViewById(R.id.et_id);
		et_pass = findViewById(R.id.et_pass);

		loginbtn2 = findViewById(R.id.loginbtn2);

		//로그인 버튼 클릭 시 수행
		loginbtn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String userID = et_id.getText().toString();
				String userPass = et_pass.getText().toString();

				Response.Listener<String> responseListener = new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						try {
							JSONObject jsonObject = new JSONObject(response);
							boolean success = jsonObject.getBoolean("success");

							//로그인 성공시
							if (success) {
								String userID = jsonObject.getString("userID");
								String userPass = jsonObject.getString("userPassword");

								SharedPreferences sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
								SharedPreferences.Editor editor = sharedpreferences.edit();
								editor.putString("userID", userID);
								editor.commit();

//인텐트 두번쨰 파라미터 바꾸기
								Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(login2Activity.this, MainActivity.class);
								intent.putExtra("userID", userID);
								intent.putExtra("userPass", userPass);
								startActivity(intent);

								// 실패시
							} else {
								Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
								return;
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				};

				LoginRequeset loginRequeset = new LoginRequeset(userID, userPass, responseListener);
				RequestQueue queue = Volley.newRequestQueue( login2Activity.this );
				queue.add(loginRequeset);

			}
		});

	}

}
