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

		// ��ʼ��Ϸ��ť,����ؿ�ҳ��
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

		// ��������Ϸ��ť��������ڽ���
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
		// ���ڲ鿴������ť���鿴�Լ�����������е�����
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
//								.setTitle("����ʧ��").setMessage("��������!")
//								.setPositiveButton("ȷ��", listener1);
//					}
//				}).start();
//			}
//		});
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �����˳��Ի���
			AlertDialog isExit = new AlertDialog.Builder(this).create();
			// ���öԻ������
			isExit.setTitle("�˳���");
			// ���öԻ�����Ϣ
			isExit.setMessage("�����������Ҫ�뿪��");
			// ���ѡ��ť��ע�����
			isExit.setButton("�ǵ�", listener);
			isExit.setButton2("ȡ��", listener);
			// ��ʾ�Ի���
			isExit.show();
		}
		// if (keyCode == KeyEvent.KEYCODE_MENU) {
		// return true;
		// }

		// return super.onKeyDown(keyCode, event);
		return false;

	}

	/** �����Ի��������button����¼� */
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����
				 finish();
//				proBar.setVisibility(View.GONE);
				break;
			case AlertDialog.BUTTON_NEGATIVE://"ȡ����ť"
				break;
			// case AlertDialog.BUTTON_NEGATIVE:// "ȡ��"�ڶ�����ťȡ���Ի���
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
