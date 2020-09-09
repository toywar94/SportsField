package com.example.sportsfield;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeF extends Fragment {
	String category = "";

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.home_f, container, false);




		ImageButton soccer_btn = v.findViewById(R.id.soccer);
		ImageButton basketball_btn = v.findViewById(R.id.basketball2);
		ImageButton badminton_btn = v.findViewById(R.id.badminton);
		ImageButton billiard_btn = v.findViewById(R.id.billiard);
		ImageButton swim_btn = v.findViewById(R.id.swimpool);
		ImageButton putsal_btn = v.findViewById(R.id.putsal);
		ImageButton golf_btn = v.findViewById(R.id.golf);
		ImageButton tennis_btn = v.findViewById(R.id.tennis);
		ImageButton baseball_btn = v.findViewById(R.id.baseBall);


//각 버튼 클릭리스너 만들고 각 종목에 대한 리사이클러뷰 만들기. -- 농구부터 축구액티비티 일단 복붙해서 실행가능하게하고 다시구성하기
		soccer_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getContext(), SoccerActivity.class);
				category = "축구장";
				intent.putExtra("category", category);
				startActivity(intent);

			}
		});

		basketball_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), SoccerActivity.class);
				category = "농구";
				intent.putExtra("category", category);
				startActivity(intent);

			}
		});
		badminton_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), SoccerActivity.class);
				category = "배드민턴";
				intent.putExtra("category", category);
				startActivity(intent);

			}
		});
		billiard_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), SoccerActivity.class);
				category = "당구장";
				intent.putExtra("category", category);
				startActivity(intent);

			}
		});
		swim_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), SoccerActivity.class);
				category = "수영장";
				intent.putExtra("category", category);
				startActivity(intent);

			}
		});
		putsal_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), SoccerActivity.class);
				category = "풋살장";
				intent.putExtra("category", category);
				startActivity(intent);

			}
		});
		golf_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), SoccerActivity.class);
				category = "골프장";
				intent.putExtra("category", category);
				startActivity(intent);

			}
		});
		tennis_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), SoccerActivity.class);
				category = "테니스장";
				intent.putExtra("category", category);
				startActivity(intent);

			}
		});
		baseball_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), SoccerActivity.class);
				category = "야구장";
				intent.putExtra("category", category);
				startActivity(intent);

			}
		});

		return v;

	}

}
