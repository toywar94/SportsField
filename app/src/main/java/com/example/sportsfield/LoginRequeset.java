package com.example.sportsfield;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequeset extends StringRequest {

	//서버 URL 설정(php 파일 연동)
	final static private String URL = "http://toywar94.dothome.co.kr/Login.php";
	private Map<String, String> map;
	//private Map<String, String>parameters;

	public LoginRequeset(String userID, String userPassword,Response.Listener<String> listener) {
		super(Method.POST, URL, listener, null);

		map = new HashMap<>();
		map.put("userID", userID);
		map.put("userPassword", userPassword);

	}

	@Override
	protected Map<String, String>getParams() throws AuthFailureError {
		return map;
	}
}


