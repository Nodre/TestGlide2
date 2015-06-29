package com.example.testimgloder.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {


	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		initDisplayOption();
	}
	
	public void initDisplayOption() {
    }
}
