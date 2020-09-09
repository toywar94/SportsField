package com.example.sportsfield;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ContentRequeset extends StringRequest {

	//서버 URL 설정(php 파일 연동)
	final static private String URL = "http://toywar94.dothome.co.kr/ContentData.php";
	private Map<String, String> map;
	//private Map<String, String>parameters;


	////첫번 째 파라미터 안될시 삭제
	public ContentRequeset(String Member_id, String Store_name, String Address,
						   String Date_date, String Time_time, Response.Listener<String> listener) {
		super(Method.POST, URL, listener, null);

		map = new HashMap<>();
		//map.put("ID", ID + "");
		Log.d("zz",Store_name);
		map.put("Member_id", Member_id);
		map.put("Store_name", Store_name);
		map.put("Address", Address);
		map.put("Date_date", Date_date);
		map.put("Time_time", Time_time);
	}

	@Override
	protected Map<String, String>getParams() throws AuthFailureError {
		return map;
	}
}


