package com.example.sportsfield;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MapsRequest extends StringRequest {

	//서버 URL 설정(php 파일 연동)
	//url이 테니스로 되어있는데 다시 바꾸기
	final static private String URL = "http://toywar94.dothome.co.kr/location.php";
	private Map<String, String> map;
	//private Map<String, String>parameters;

	public MapsRequest(String category,Response.Listener<String> listener) {
		super(Method.POST, URL, listener, null);

		map = new HashMap<>();
		map.put("category", category);

	}

	@Override
	protected Map<String, String>getParams() throws AuthFailureError {
		return map;
	}
}


