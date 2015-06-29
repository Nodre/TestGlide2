package com.example.testimgloder.base;

import android.app.Application;
import android.content.Context;

import com.example.testimgloder.util.AndroidUtil;

public class InitAppliation extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		AndroidUtil.init(getApplicationContext());
		initImageLoader(getApplicationContext());
	}

	private void initImageLoader(Context context) {

	}
}
