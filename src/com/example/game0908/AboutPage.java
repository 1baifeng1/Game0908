package com.example.game0908;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

public class AboutPage extends Activity {
	TextView tcInstruction,tvYi,tvEr,tvSan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_about_page);
        
        tcInstruction=(TextView)findViewById(R.id.tvInstruction);
        tcInstruction.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/HanYiXueFengTiJian.ttf"));
        tvYi=(TextView)findViewById(R.id.tvYi);
        tvYi.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/HanYiChaoCuSongJian.ttf"));
	}

	/* (non-Javadoc)
	 * ÆÁ±Î²Ëµ¥°´Å¥
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode==KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_page, menu);
		return true;
	}

}
