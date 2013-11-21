package com.chilindisplayremote;

import java.io.File;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
	SharedPreferences sp;
	Editor editor;

	private final String TAG = "MainLog";
	private String ip = "";
	private int port = 0;
    private Button btnConvert;
    private TextView txtView;
	@SuppressLint("CommitPrefEdits")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			sp = getSharedPreferences("setting", MODE_PRIVATE);
			File f = new File("/shared_perfs/setting.xml");
			if (!f.exists()) {
				createPreferences();
			} else {
				editor = sp.edit();
				ip = sp.getString("ip", "192.168.1.1");
				port = sp.getInt("port", 23);
			}
		} catch (Exception exp) {
			Log.d(TAG, exp.getLocalizedMessage());
		}
		Log.d(TAG, "excute onCreate");
		
		btnConvert = (Button)findViewById(R.id.btnConvert);
		btnConvert.setOnClickListener(btnClickListener);
		txtView= (TextView)findViewById(R.id.textView1);
		
		
	}
	private Button.OnClickListener btnClickListener = new Button.OnClickListener(){
		@Override
		public void onClick(View arg0) {
			txtView.setText(getResources().getString(R.string.push_me));
			
		}
	};
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Log.d(TAG, "excute onCreateOptionsMenu");
		return true;
	}

	private void createPreferences() {
		editor = sp.edit();
		editor.putString("ip", "192.168.1.1");
		editor.putInt("port", 23);
		editor.commit();

	}
}
