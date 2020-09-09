package com.example.sportsfield;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

	private EditText et_id, et_pass, et_name, et_age;
	private Button btn_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_signup );
		//뒤로가기 버튼
		ImageButton button2 =(ImageButton) findViewById(R.id.backbtn2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});

		//아이디값 찾아주기
		et_id = findViewById( R.id.userId );
		et_pass = findViewById( R.id.userPassword );
		et_name = findViewById( R.id.userName );
		et_age = findViewById( R.id.userAge );


		//회원가입 버튼 클릭 시 수행
		btn_register = findViewById( R.id.loginbtn3 );
		btn_register.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String userID = et_id.getText().toString();
				String userPass = et_pass.getText().toString();
				String userName = et_name.getText().toString();
				int userAge = Integer.parseInt( et_age.getText().toString() );



				Response.Listener<String> responseListener = new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						try {
							JSONObject jsonObject = new JSONObject( response );
							boolean success = jsonObject.getBoolean( "success" );

							//회원가입 성공시
							if(success) {

								Toast.makeText( getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT ).show();
								Intent intent = new Intent( SignUpActivity.this, login2Activity.class );
								startActivity( intent );

								//회원가입 실패시
							} else {
								Toast.makeText( getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT ).show();
								return;
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				};

				//서버로 Volley를 이용해서 요청
				RegisterRequeset registerRequest = new RegisterRequeset( userID, userPass, userName, userAge, responseListener);
				RequestQueue queue = Volley.newRequestQueue( SignUpActivity.this );
				queue.add( registerRequest );
			}
		});
	}
}

