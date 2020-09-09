package com.example.sportsfield;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LocateF extends Fragment {
	String category = "";

	Button button;

	ImageButton soccer_btn;
	ImageButton basketball_btn;
	ImageButton badminton_btn;
	ImageButton billiard_btn;
	ImageButton swim_btn;
	ImageButton putsal_btn;
	ImageButton golf_btn;
	ImageButton tennis_btn;
	ImageButton baseball_btn;



	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.locate_f, container, false);
		button = v.findViewById(R.id.checkbtn);

		soccer_btn = v.findViewById(R.id.soccer);
		basketball_btn = v.findViewById(R.id.basketball2);
		badminton_btn = v.findViewById(R.id.badminton);
		billiard_btn = v.findViewById(R.id.billiard);
		swim_btn = v.findViewById(R.id.swimpool);
		putsal_btn = v.findViewById(R.id.putsal);
		golf_btn = v.findViewById(R.id.golf);
		tennis_btn = v.findViewById(R.id.tennis);
		baseball_btn = v.findViewById(R.id.baseBall);


		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				switch (v.getId()) {
					case R.id.soccer:
						button.setBackgroundColor(Color.parseColor("#9a59b5"));
						category = "축구장";
						break;

					case R.id.basketball2:
						button.setBackgroundColor(Color.parseColor("#FE9A2E"));
						category = "농구";
						break;

					case R.id.badminton:
						button.setBackgroundColor(Color.parseColor("#F781F3"));
						category = "배드민턴";
						break;

					case R.id.billiard:
						button.setBackgroundColor(Color.parseColor("#2E2E2E"));
						category = "당구장";
						break;

					case R.id.swimpool:
						button.setBackgroundColor(Color.parseColor("#0000FF"));
						category = "수영장";
						break;

					case R.id.putsal:
						button.setBackgroundColor(Color.parseColor("#01DF3A"));
						category = "풋살장";
						break;

					case R.id.tennis:
						category = "테니스장";
						button.setBackgroundColor(Color.parseColor("#81F781"));
						break;

					case R.id.golf:
						button.setBackgroundColor(Color.parseColor("#F7FE2E"));
						category = "골프장";
						break;

					case R.id.baseBall:
						button.setBackgroundColor(Color.parseColor("#F5A9A9"));
						category = "야구장";
						break;

					//이거 좀 이상하니까 바꾸자..
					case R.id.checkbtn:
							Intent intent = new Intent(getContext(), MapsActivity.class);
							intent.putExtra("category", category);
							startActivity(intent);

				}

			}
		};

//		soccer_btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				button.setBackgroundColor(Color.parseColor("#9a59b5"));
//				category = "축구장";
//			}
//		});
//		basketball_btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				button.setBackgroundColor(Color.parseColor("#FE9A2E"));
//				category = "농구";
//			}
//		});
//		badminton_btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				button.setBackgroundColor(Color.parseColor("#F781F3"));
//				category = "배드민턴";
//			}
//		});
//		billiard_btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				button.setBackgroundColor(Color.parseColor("#2E2E2E"));
//				category = "당구장";
//			}
//		});
//		swim_btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				button.setBackgroundColor(Color.parseColor("#0000FF"));
//				category = "수영장";
//			}
//		});
//		putsal_btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				button.setBackgroundColor(Color.parseColor("#01DF3A"));
//				category = "풋살장";
//			}
//		});
//		golf_btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				button.setBackgroundColor(Color.parseColor("#F7FE2E"));
//				category = "골프장";
//			}
//		});
//		tennis_btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				category = "테니스장";
//				button.setBackgroundColor(Color.parseColor("#81F781"));
//
//			}
//		});
//		baseball_btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				button.setBackgroundColor(Color.parseColor("#F5A9A9"));
//				category = "야구장";
//			}
//		});
//		button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if (button != null) {
//					Intent intent = new Intent(getContext(), MapsActivity.class);
//					intent.putExtra("category", category);
//					startActivity(intent);
//				}else{
//					button.setClickable(false);
//				}
//			}
//		});

		soccer_btn.setOnClickListener(listener);
		basketball_btn.setOnClickListener(listener);
		badminton_btn.setOnClickListener(listener);
		billiard_btn.setOnClickListener(listener);
		swim_btn.setOnClickListener(listener);
		putsal_btn.setOnClickListener(listener);
		golf_btn.setOnClickListener(listener);
		tennis_btn.setOnClickListener(listener);
		baseball_btn.setOnClickListener(listener);
		button.setOnClickListener(listener);


// 축구 농구 테니스 등등 클릭시 각 화면으로 이동
//		button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//				Intent intent = new Intent(getContext(), MapsActivity.class);
//				intent.putExtra("category", category);
//				startActivity(intent);
//			}
//		});


		return v;

	}


}



