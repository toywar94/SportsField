package com.example.sportsfield;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class LoginF extends Fragment implements AutoPermissionsListener {
	public static final int REQUEST_CODE_LOGIN1 = 101;
	public static final int REQUEST_CODE_SIGNUP = 102;

	TextView textView;
	String userID;

	Intent intent;

	ImageButton imageButton;
	File file;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//로그인화면 프래그먼트
		View v = inflater.inflate(R.layout.login_f, container, false);

		textView = v.findViewById(R.id.userLoginID);
		imageButton = v.findViewById(R.id.image_btn);

		//1번째 파라미터
		AutoPermissions.Companion.loadAllPermissions(getActivity(),101);

		imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				takePicture();
			}
		});

		//login2에서 ID값을 저장
		SharedPreferences pref = getActivity().getSharedPreferences("login", MODE_PRIVATE);
		String userID = pref.getString("userID", "");
		Log.d("userkkk", userID);
		textView.setText("ID : "+ userID);

		//로그인이 안되어있으면 버튼에 로그인해주세요가 나오고
		if (userID == "") {
			Button button = v.findViewById(R.id.loginbtn1);
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getContext(), login2Activity.class);
					startActivityForResult(intent, REQUEST_CODE_LOGIN1);
				}
			});
			//로그인을했으면 로그아웃해주세요가 나온다
		} else {
			Button button = v.findViewById(R.id.loginbtn1);
			button.setText("로그아웃 해주세요");
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					//로그아웃 누를시 다이얼로그 알림창
					showMessage();

				}
			});
		}


		//회원가입 클릭
		Button button2 = (Button) v.findViewById(R.id.signUp);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), SignUpActivity.class);
				startActivityForResult(intent, REQUEST_CODE_SIGNUP);
			}
		});


		return v;


	}
	//권한설정
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		AutoPermissions.Companion.parsePermissions(getActivity(),requestCode,permissions,this);
	}

	@Override
	public void onDenied(int i, String[] strings) {
		Toast.makeText(getActivity(), "permissions denied : " + strings.length, Toast.LENGTH_LONG).show();

	}

	@Override
	public void onGranted(int i, String[] strings) {
		Toast.makeText(getActivity(), "permissions granted : " + strings.length, Toast.LENGTH_LONG).show();

	}


	public void takePicture() {

		//파일 만드는 메서드
		file = createFile();
		//첫번째 파라미터 맞는지 확인해보자.. 실행후
		Uri fileUri = FileProvider.getUriForFile(getContext(), "com.example.sportsfield.fileprovider", file);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(intent, 101);
	}
	// cpature.jpg 파일으로 SD카드 만든다.
	private File createFile() {
		String filename = "capture.jpg";
		File storageDir = Environment.getExternalStorageDirectory();
		File outFile = new File(storageDir, filename);
		return outFile;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(requestCode == 101 && resultCode == RESULT_OK){
			//이미지 파일을 Bitmap객체로 만든다
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 6;
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

			imageButton.setImageBitmap(bitmap);
		}
	}

	//다이얼로그 창 구현
	public void showMessage() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("안내");
		builder.setMessage("로그아웃하시겠습니까?");
		builder.setIcon(R.drawable.caution);

//		예 누를시 ID값이 바로 안지워짐 해결하기
		builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//pref에 저장되어있는 값을 지운다
				SharedPreferences pref = getActivity().getSharedPreferences("login", MODE_PRIVATE);
				SharedPreferences.Editor editor = pref.edit();
				editor.clear();
				editor.commit();

			}
		});
		builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}
}





