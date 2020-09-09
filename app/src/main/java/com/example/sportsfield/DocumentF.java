package com.example.sportsfield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class DocumentF extends Fragment {

	String Member_id;
	String Store_name;
	String Address;
	String date;
	String time;

	Intent intent;

	DatePicker datePicker;

	RecyclerView recyclerView;
	DocumentAdapter adapter;
	AlertDialog alertDialog;

	ArrayList<DocumentData> list = new ArrayList<>();


	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.document_f, container, false);

//		Button button = v.findViewById(R.id.document_button);
//		button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				bringData();
//			}
//		});


		recyclerView = v.findViewById(R.id.recyclerView);

		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);

		adapter = new DocumentAdapter();

		adapter.addItem(new DocumentData("ID : 오이소박이먹을래", "시설이름 : 형범이네 헬스클럽", "주소 : 경기도 일산동구 신원7단지 15층 어딘가", "2020/05/04", "pm 8: 25"));



		recyclerView.setAdapter(adapter);

		return v;
	}



	public void bringData() {
		intent = new Intent();
		//bringdata 로 변경 login울
		SharedPreferences pref = this.getActivity().getSharedPreferences("bringdata", MODE_PRIVATE);
		String userID = pref.getString("userID", "");

		Member_id = userID;
		Store_name = intent.getStringExtra("Store_name");
		Address = intent.getStringExtra("Address");
		date = intent.getStringExtra("date");
		time = intent.getStringExtra("time");
		Log.d("Member_id", Member_id);
		Log.d("Store_name", Store_name);
		Log.d("Address", Store_name);
		Log.d("time", Store_name);



		Response.Listener<String> responseListener = new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {
					JSONObject jsonObject = new JSONObject(response);
					boolean success = jsonObject.getBoolean("success");

					//jsonobject로 결과값 받는다
					if (success) {
						String Member_id = jsonObject.getString("Member_id");
						String Store_name = jsonObject.getString("Store_name");
						String Address = jsonObject.getString("Address");
						String date = jsonObject.getString("date");
						String time = jsonObject.getString("time");

						Log.d("Store_name2", Store_name);
						Log.d("date2", date);


						SharedPreferences sharedpreferences = getContext().getSharedPreferences("bringdata", MODE_PRIVATE);
						SharedPreferences.Editor editor = sharedpreferences.edit();
						editor.putString("Member_id_data", Member_id);
						editor.putString("Store_name_data", Store_name);
						editor.putString("Address_data", Address);
						editor.putString("date_data", date);
						editor.putString("time_data", time);
						editor.commit();

						intent.putExtra("Member_id", Member_id);
						intent.putExtra("Store_name", Store_name);
						intent.putExtra("Address", Address);
						intent.putExtra("date", date);
						intent.putExtra("time", time);

						adapter.addItem(new DocumentData(Member_id, Store_name, Address, date, time));

						// 실패시
					} else {

						return;
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		};

		ContentRequeset contentRequeset = new ContentRequeset(Member_id, Store_name, Address, date, time, responseListener);
		RequestQueue queue = Volley.newRequestQueue(DocumentF.this.getActivity());
		queue.add(contentRequeset);
	}
}




