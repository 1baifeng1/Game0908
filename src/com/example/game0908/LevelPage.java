package com.example.game0908;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class LevelPage extends Activity {
	TextView tvLevel;
	ImageButton imgbtnLevel1,imgbtnLevel2,imgbtnLevel3,imgbtnLevel4,imgbtnLevel5;
	Intent intentLevel1,intentLevel2,intentLevel3,intentLevel4,intentLevel5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_page);
		
		tvLevel=(TextView)findViewById(R.id.tvLevel);
		tvLevel.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/HanYiXueFengTiJian.ttf"));
		
		//点击进入第一关
		imgbtnLevel1=(ImageButton)findViewById(R.id.imgbtnLevel1);
		imgbtnLevel1.setOnClickListener(new ImageButton.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				intentLevel1=new Intent();
				intentLevel1.setClass(getApplicationContext(), Level1.class);
				startActivity(intentLevel1);
			}
		});
		imgbtnLevel2=(ImageButton)findViewById(R.id.imgbtnLevel2);
		imgbtnLevel2.setOnClickListener(new ImageButton.OnClickListener(){
			@Override
			public void onClick(View v) {
				intentLevel2=new Intent();
				intentLevel2.setClass(getApplicationContext(), Level2.class);
				startActivity(intentLevel2);
			}
		});
		imgbtnLevel3=(ImageButton)findViewById(R.id.imgbtnLevel3);
		imgbtnLevel3.setOnClickListener(new ImageButton.OnClickListener(){
			@Override
			public void onClick(View v) {
				intentLevel3=new Intent();
				intentLevel3.setClass(getApplicationContext(), Level3.class);
				startActivity(intentLevel3);
			}
		});
		imgbtnLevel4=(ImageButton)findViewById(R.id.imgbtnLevel4);
		imgbtnLevel4.setOnClickListener(new ImageButton.OnClickListener(){
			@Override
			public void onClick(View v) {
				intentLevel4=new Intent();
				intentLevel4.setClass(getApplicationContext(), Level4.class);
				startActivity(intentLevel4);
			}
		});
		imgbtnLevel5=(ImageButton)findViewById(R.id.imgbtnLevel5);
		imgbtnLevel5.setOnClickListener(new ImageButton.OnClickListener(){
			@Override
			public void onClick(View v) {
				intentLevel5=new Intent();
				intentLevel5.setClass(getApplicationContext(), Level5.class);
				startActivity(intentLevel5);
			}
		});
	}

	/* 
	 * 屏蔽菜单按钮
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
		getMenuInflater().inflate(R.menu.level_page, menu);
		return true;
	}

}
