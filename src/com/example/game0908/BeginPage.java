package com.example.game0908;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;

public class BeginPage extends Activity {
	Button btnStart, btnAbout, btnScore;
	TextView tvName;
	ProgressBar proBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_begin_page);

		tvName = (TextView) findViewById(R.id.tvName);
		tvName.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/HanYiLuoBoTiJian.ttf"));

		// 开始游戏按钮,进入关卡页面
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStart.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// setContentView(R.layout.activity_level_page);
				Intent intentLevelPage = new Intent();
				intentLevelPage.setClass(getApplicationContext(),
						LevelPage.class);
				startActivity(intentLevelPage);
			}
		});

		// 关于有游戏按钮，进入关于界面
		btnAbout = (Button) findViewById(R.id.btnAbout);
		btnAbout.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// setContentView(R.layout.activity_about_page);
				Intent intentAboutPage = new Intent();
				intentAboutPage.setClass(getApplicationContext(),
						AboutPage.class);
				startActivity(intentAboutPage);
			}
		});

//		proBar = (ProgressBar) findViewById(R.id.proBar);
		// 关于查看排名按钮，查看自己在所有玩家中的排名
//		btnScore = (Button) findViewById(R.id.btnScore);
//		btnScore.setOnClickListener(new Button.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				new Thread(new Runnable() {
//					public void run() {
//						proBar.setVisibility(View.VISIBLE);
//						try {
//							Thread.sleep(5000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						new AlertDialog.Builder(getApplication())
//								.setTitle("连接失败").setMessage("请检查网络!")
//								.setPositiveButton("确定", listener1);
//					}
//				}).start();
//			}
//		});
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 创建退出对话框
			AlertDialog isExit = new AlertDialog.Builder(this).create();
			// 设置对话框标题
			isExit.setTitle("退出？");
			// 设置对话框消息
			isExit.setMessage("少侠，您真的要离开吗？");
			// 添加选择按钮并注册监听
			isExit.setButton("是的", listener);
			isExit.setButton2("取消", listener);
			// 显示对话框
			isExit.show();
		}
		// if (keyCode == KeyEvent.KEYCODE_MENU) {
		// return true;
		// }

		// return super.onKeyDown(keyCode, event);
		return false;

	}

	/** 监听对话框里面的button点击事件 */
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
				 finish();
//				proBar.setVisibility(View.GONE);
				break;
			case AlertDialog.BUTTON_NEGATIVE://"取消按钮"
				break;
			// case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
			// break;
			default:
				break;
			}
		}
	};
	
//	DialogInterface.OnClickListener listener1 = new DialogInterface.OnClickListener() {
//		public void onClick(DialogInterface dialog, int which) {
//			switch (which) {
//			case AlertDialog.BUTTON_POSITIVE:
//				proBar.setVisibility(View.GONE);
//				break;
//			default:
//				break;
//			}
//		}
//	};
}
