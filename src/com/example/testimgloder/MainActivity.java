package com.example.testimgloder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testimgloder.base.BaseActivity;
import com.test.R;

public class MainActivity extends BaseActivity {

	private View mChooseBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mChooseBtn = findViewById(R.id.choose_btn);
		
		mChooseBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), ImageListActivity.class);
				startActivity(intent);
			}
		});
	}


}
