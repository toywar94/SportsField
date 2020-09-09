package com.example.sportsfield;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
	//bottom navigation 변수
	HomeF homeF;
	LocateF locateF;
	LoginF loginF;
	DocumentF documentF;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//bottom navigation 객체 생성
		homeF = new HomeF();
		locateF = new LocateF();
		loginF = new LoginF();
		documentF = new DocumentF();
		//home 하단 탭 4개를 클릭할시 각 화면으로 전환시킴
		getSupportFragmentManager().beginTransaction().replace(R.id.container, homeF).commit();

		BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navi);
		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch (item.getItemId()) {
					case R.id.tab1:

						getSupportFragmentManager().beginTransaction().replace(R.id.container, homeF).commit();
						return true;

					case R.id.tab2:
						getSupportFragmentManager().beginTransaction().replace(R.id.container, locateF).commit();
						return true;

					case R.id.tab3:
						getSupportFragmentManager().beginTransaction().replace(R.id.container, documentF).commit();
						return true;

					case R.id.tab4:
						getSupportFragmentManager().beginTransaction().replace(R.id.container, loginF).commit();
						return true;

				}
				return false;
			}
		});

	}

	//						하단 바
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_bottom, menu);
		return true;

	}

}
