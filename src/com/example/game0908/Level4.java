package com.example.game0908;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

public class Level4 extends Activity {
	/**
	 * 游戏画布
	 */
	MyView4 game4;

	// 右侧
	int M[] = new int[15];
	int F[] = new int[10];
	int f[] = new int[5];

	// 左侧位图
	Bitmap bmpr1, bmpr2, bmpr3;
	Bitmap[] bmpR = { bmpr1, bmpr2, bmpr3, bmpr2, bmpr1 };
	int bmpindex = 0;// bmpR的索引

	/**
	 * 记录哪个函数处于编辑状态 ，1为主函数，2为辅助函数，3为循环
	 */
	int biaozhi = 1;

	/**
	 * 开始与停止按钮
	 */
	Button btnStartAndstop;

	/**
	 * 右侧的所有图片按钮
	 * */
	LinearLayout linlayMain, linlayFun, linlayFor;

	/**
	 * 1为直行，2为左转，3为右转，4为辅助函数，5为循环，6为完成
	 */
	public ImageButton imgbtnZhixing, imgbtnZuozhuan, imgbtnYouzhuan,
			imgbtnHanshu, imgbtnXunhuan, imgbtnWancheng;
	/**
	 * 主函数中的15个步骤
	 */
	ImageButton imgbtnstepM[] = new ImageButton[15];
	/**
	 * 辅助函数中的10个步骤
	 */
	ImageButton imgbtnstepF[] = new ImageButton[10];
	/**
	 * for循环中的5个步骤
	 */
	ImageButton imgbtnstepf[] = new ImageButton[5];

	/**
	 * 记录主函数的该处是否有命令
	 */
	Boolean MB[] = { false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false };
	/**
	 * 记录辅助函数的该处是否有命令
	 */
	Boolean FB[] = { false, false, false, false, false, false, false, false,
			false, false };
	/**
	 * 记录循环数的该处是否有命令
	 */
	Boolean fB[] = { false, false, false, false, false };

	/**
	 * 记录当前命令
	 */
	static int order;

	/**
	 * 运行时，指向当前主函数的命令
	 */
	int pointM = 0;
	/**
	 * 运行时，指向当前辅助函数的命令
	 */
	int pointF = 0;
	/**
	 * 运行时，指向当前for循环的命令
	 */
	int pointf = 0;

	/**
	 * 运行时，判断当前是否为主函数
	 */
	boolean flagM = true;
	/**
	 * 运行时，判断当前是否为辅助函数
	 */
	boolean flagF = false;
	/**
	 * 运行时，判断当前是否为循环函数
	 */
	boolean flagf = false;

	/**
	 * 用二维数组，第一个元素为调用他的函数，第二个元素为中断的位置.保存调用辅助函数的断点
	 */
	int intF[][] = new int[10][2];
	/**
	 * 用二维数组，第一个元素为调用他的函数，第二个元素为中断的位置.保存调用循环函数的断点
	 */
	int intf[][] = new int[10][2];

	/**
	 * 是否是第一次执行
	 */
	static Boolean isFirst = true;

	/**
	 * for循环要循环3次
	 */
	int loop = 3;

	/**
	 * 定时器
	 */
	Timer timer = new Timer();
	/**
	 * 记录定时器是否wait
	 */

	/**
	 * 处理消息
	 */
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				if (flagM) {
					handleM();
				} else if (flagF) {
					handleF();
				} else if (flagf) {
					handlef();
				} else {
					timer.cancel();
				}
			}
		}
	};

	/**
	 * 处理主函数
	 */
	public void handleM() {
		if (pointM < 15) {
			if (MB[pointM]) {
				switch (M[pointM]) {
				case 1:
					order = 1;
					game4.invalidate();
					break;
				case 2:
					order = 2;
					game4.invalidate();
					break;
				case 3:
					order = 3;
					game4.invalidate();
					break;
				case 4:
					flagM = false;
					flagF = true;
					for (int i = 0; i < 10; i++) {
						if (intF[i][0] != 0) {
							continue;
						} else {
							intF[i][0] = 1;
							intF[i][1] = (1 + pointM);
							break;
						}
					}
					handleF();
					break;
				case 5:
					flagM = false;
					flagf = true;
					for (int i = 0; i < 10; i++) {
						if (intf[i][0] != 0) {
							continue;
						} else {
							intf[i][0] = 1;
							intf[i][1] = (1 + pointM);
							break;
						}
					}
					handlef();
					break;
				case 6:
					if (isOK()) {
						handleOK();
					}
					break;
				}
				pointM++;
			} else {
				pointM++;
				handleM();
			}
		}
	}

	/**
	 * 处理辅助函数
	 */
	private void handleF() {
		if (pointF < 10) {
			if (FB[pointF]) {
				switch (F[pointF]) {
				case 1:
					order = 1;
					game4.invalidate();
					break;
				case 2:
					order = 2;
					game4.invalidate();
					break;
				case 3:
					order = 3;
					game4.invalidate();
					break;
				case 4:
					for (int i = 0; i < 10; i++) {
						if (intF[i][0] != 0) {
							continue;
						} else {
							intF[i][0] = 2;
							intF[i][1] = (1 + pointF);
							break;
						}
					}
					pointF = 0;
					handleF();
					break;
				case 5:
					flagF = false;
					flagf = true;
					for (int i = 0; i < 10; i++) {
						if (intf[i][0] != 0) {
							continue;
						} else {
							intf[i][0] = 2;
							intf[i][1] = (1 + pointF);
							break;
						}
					}
					pointf = 0;
					handlef();
					break;
				case 6:
					if (isOK()) {
						handleOK();
					}
					break;
				}
				pointF++;
			} else {
				pointF++;
				handleF();
			}
		}
		if (pointF == 10) {
			pointF = 0;
			flagF = false;
			int temp = 0;
			for (int i = 9; i >= 0; i--) {
				if (intF[i][0] == 0) {
					continue;
				} else {
					temp = i;
					break;
				}
			}
			switch (intF[temp][0]) {
			case 1:
				flagM = true;
				pointM = intF[temp][1];
				break;
			case 2:
				flagF = true;
				pointF = intF[temp][1];
				break;
			case 3:
				flagf = true;
				pointf = intF[temp][1];
				break;
			}
			intF[temp][0] = 0;
			intF[temp][1] = 0;
		}
	}

	/**
	 * 处理循环函数
	 */
	private void handlef() {
		if (pointf < 5 && loop > 0) {
			if (fB[pointf]) {
				switch (f[pointf]) {
				case 1:
					order = 1;
					game4.invalidate();
					break;
				case 2:
					order = 2;
					game4.invalidate();
					break;
				case 3:
					order = 3;
					game4.invalidate();
					break;
				case 4:
					flagf = false;
					flagF = true;
					for (int i = 0; i < 10; i++) {
						if (intF[i][0] != 0) {
							continue;
						} else {
							intF[i][0] = 3;
							intF[i][1] = (1 + pointf);
							break;
						}
					}
					pointF = 0;
					handleF();
					break;
				case 5:
					for (int i = 0; i < 10; i++) {
						if (intf[i][0] != 0) {
							continue;
						} else {
							intf[i][0] = 3;
							intf[i][1] = (1 + pointf);
							break;
						}
					}
					pointf = 0;
					handlef();
					break;
				case 6:
					if (isOK()) {
						handleOK();
					}
					break;
				}
				pointf++;
			} else {
				pointf++;
				handlef();
			}
		}
		if (pointf == 5 && loop > 0) {
			pointf = 0;
			loop--;
		}
		if (loop == 0) {
			flagf = false;
			int temp = 0;
			for (int i = 9; i >= 0; i--) {
				if (intf[i][0] == 0) {
					continue;
				} else {
					temp = i;
					break;
				}
			}
			switch (intf[temp][0]) {
			case 1:
				flagM = true;
				pointM = intf[temp][1];
				break;
			case 2:
				flagF = true;
				pointF = intf[temp][1];
				break;
			case 3:
				flagf = true;
				pointf = intf[temp][1];
				break;
			}
			intf[temp][0] = 0;
			intf[temp][1] = 0;
		}
	}

	/**
	 * 处理完成
	 */
	private void handleOK() {
		timer.cancel();
		initData();

		new AlertDialog.Builder(Level4.this)
				// 这儿用getApplicationContext（）会崩溃
				.setMessage("恭喜你，成功完成了第二关。")
				.setIcon(R.drawable.douzi)
				.setPositiveButton("让我们再来一遍",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
								Intent intentLevel4 = new Intent();
								intentLevel4.setClass(getApplicationContext(),
										Level4.class);
								startActivity(intentLevel4);
							}
						})
				.setNegativeButton("准备下一关",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Level4.this.finish();
							}
						}).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_level4);

		linlayMain = (LinearLayout) findViewById(R.id.linlayMain);
		linlayMain.setBackgroundColor(getResources().getColor(
				R.color.backHanshu));
		/**
		 * 点击主函数
		 * */
		linlayMain.setOnClickListener(new LinearLayout.OnClickListener() {
			@Override
			public void onClick(View v) {
				biaozhi = 1;
				linlayMain.setBackgroundColor(getResources().getColor(
						R.color.backHanshu));
				linlayFun.setBackgroundColor(getResources().getColor(
						R.color.backYuanlai));
				linlayFor.setBackgroundColor(getResources().getColor(
						R.color.backYuanlai));
			}
		});

		linlayFun = (LinearLayout) findViewById(R.id.linlayFun);
		/**
		 * 点击辅助函数
		 * */
		linlayFun.setOnClickListener(new LinearLayout.OnClickListener() {
			@Override
			public void onClick(View v) {
				biaozhi = 2;
				linlayFun.setBackgroundColor(getResources().getColor(
						R.color.backHanshu));
				linlayMain.setBackgroundColor(getResources().getColor(
						R.color.backYuanlai));
				linlayFor.setBackgroundColor(getResources().getColor(
						R.color.backYuanlai));
			}
		});

		linlayFor = (LinearLayout) findViewById(R.id.linlayFor);
		/**
		 * 点击for循环
		 * */
		linlayFor.setOnClickListener(new LinearLayout.OnClickListener() {
			@Override
			public void onClick(View v) {
				biaozhi = 3;
				linlayFor.setBackgroundColor(getResources().getColor(
						R.color.backHanshu));
				linlayFun.setBackgroundColor(getResources().getColor(
						R.color.backYuanlai));
				linlayMain.setBackgroundColor(getResources().getColor(
						R.color.backYuanlai));
			}
		});

		for (int i = 0; i < 15; i++) {// 初始化“主函数”数组
			M[i] = 0;
		}

		// 命令
		imgbtnZhixing = (ImageButton) findViewById(R.id.imgbtnZhixing);
		imgbtnZhixing.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				int i = 0;
				switch (biaozhi) {
				case 1:
					while (i < 15 && M[i] != 0) {// 找到第一个没有命令的方格并且小于15
						i++;
					}
					if (i < 15) {
						M[i] = 1;// 直行为命令1
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.zhixing);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// 找到第一个没有命令的方格并且小于10
						i++;
					}
					if (i < 10) {
						F[i] = 1;// 直行为命令1
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.zhixing);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// 找到第一个没有命令的方格并且小于5
						i++;
					}
					if (i < 5) {
						f[i] = 1;// 直行为命令1
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepf1
								+ i);
						im.setBackgroundResource(R.drawable.zhixing);
						fB[i] = true;
					}
					break;
				}
			}
		});
		imgbtnZuozhuan = (ImageButton) findViewById(R.id.imgbtnZuozhuan);
		imgbtnZuozhuan.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				int i = 0;
				switch (biaozhi) {
				case 1:
					while (i < 15 && M[i] != 0) {// 找到第一个没有命令的方格并且小于15
						i++;
					}
					if (i < 15) {
						M[i] = 2;// 左转为命令2
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.zuozhuan);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// 找到第一个没有命令的方格并且小于10
						i++;
					}
					if (i < 10) {
						F[i] = 2;// 左转为命令2
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.zuozhuan);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// 找到第一个没有命令的方格并且小于5
						i++;
					}
					if (i < 5) {
						f[i] = 2;// 左转为命令2
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepf1
								+ i);
						im.setBackgroundResource(R.drawable.zuozhuan);
						fB[i] = true;
					}
					break;
				}
			}
		});
		imgbtnYouzhuan = (ImageButton) findViewById(R.id.imgbtnYouzhuan);
		imgbtnYouzhuan.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				int i = 0;
				switch (biaozhi) {
				case 1:
					while (i < 15 && M[i] != 0) {// 找到第一个没有命令的方格并且小于15
						i++;
					}
					if (i < 15) {
						M[i] = 3;// 右转为命令3
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.youzhuan);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// 找到第一个没有命令的方格并且小于10
						i++;
					}
					if (i < 10) {
						F[i] = 3;// 右转为命令3
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.youzhuan);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// 找到第一个没有命令的方格并且小于5
						i++;
					}
					if (i < 5) {
						f[i] = 3;// 右转为命令3
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepf1
								+ i);
						im.setBackgroundResource(R.drawable.youzhuan);
						fB[i] = true;
					}
					break;
				}
			}
		});
		imgbtnHanshu = (ImageButton) findViewById(R.id.imgbtnHanshu);
		imgbtnHanshu.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				int i = 0;
				switch (biaozhi) {
				case 1:
					while (i < 15 && M[i] != 0) {// 找到第一个没有命令的方格并且小于15
						i++;
					}
					if (i < 15) {
						M[i] = 4;// 函数为命令4
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.hanshu);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// 找到第一个没有命令的方格并且小于10
						i++;
					}
					if (i < 10) {
						F[i] = 4;// 函数为命令4
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.hanshu);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// 找到第一个没有命令的方格并且小于5
						i++;
					}
					if (i < 5) {
						f[i] = 4;// 函数为命令4
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepf1
								+ i);
						im.setBackgroundResource(R.drawable.hanshu);
						fB[i] = true;
					}
					break;
				}
			}
		});
		imgbtnXunhuan = (ImageButton) findViewById(R.id.imgbtnXunhuan);
		imgbtnXunhuan.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				int i = 0;
				switch (biaozhi) {
				case 1:
					while (i < 15 && M[i] != 0) {// 找到第一个没有命令的方格并且小于15
						i++;
					}
					if (i < 15) {
						M[i] = 5;// 循环为命令5
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.xunhuan);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// 找到第一个没有命令的方格并且小于10
						i++;
					}
					if (i < 10) {
						F[i] = 5;// 循环为命令5
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.xunhuan);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// 找到第一个没有命令的方格并且小于5
						i++;
					}
					if (i < 5) {
						f[i] = 5;// 循环为命令5
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepf1
								+ i);
						im.setBackgroundResource(R.drawable.xunhuan);
						fB[i] = true;
					}
					break;
				}
			}
		});
		imgbtnWancheng = (ImageButton) findViewById(R.id.imgbtnWancheng);
		imgbtnWancheng.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				int i = 0;
				switch (biaozhi) {
				case 1:
					while (i < 15 && M[i] != 0) {// 找到第一个没有命令的方格并且小于15
						i++;
					}
					if (i < 15) {
						M[i] = 6;// 完成为命令6
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.wancheng);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// 找到第一个没有命令的方格并且小于10
						i++;
					}
					if (i < 10) {
						F[i] = 6;// 完成为命令6
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.wancheng);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// 找到第一个没有命令的方格并且小于5
						i++;
					}
					if (i < 5) {
						f[i] = 6;// 完成为命令6
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepf1
								+ i);
						im.setBackgroundResource(R.drawable.wancheng);
						fB[i] = true;
					}
					break;
				}
			}
		});

		// 主函数
		for (int i = 0; i < 15; i++)
			imgbtnstepM[i] = (ImageButton) findViewById(R.id.imgbtnstepM1 + i);
		imgbtnstepM[0].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(0);
			}
		});
		imgbtnstepM[1].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(1);
			}
		});
		imgbtnstepM[2].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(2);
			}
		});
		imgbtnstepM[3].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(3);
			}
		});
		imgbtnstepM[4].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(4);
			}
		});
		imgbtnstepM[5].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(5);
			}
		});
		imgbtnstepM[6].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(6);
			}
		});
		imgbtnstepM[7].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(7);
			}
		});
		imgbtnstepM[8].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(8);
			}
		});
		imgbtnstepM[9].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(9);
			}
		});
		imgbtnstepM[10].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(10);
			}
		});
		imgbtnstepM[11].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(11);
			}
		});
		imgbtnstepM[12].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(12);
			}
		});
		imgbtnstepM[13].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(13);
			}
		});
		imgbtnstepM[14].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickMain(14);
			}
		});

		// 辅助函数
		for (int i = 0; i < 10; i++)
			imgbtnstepF[i] = (ImageButton) findViewById(R.id.imgbtnstepF1 + i);
		imgbtnstepF[0].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFun(0);
			}
		});
		imgbtnstepF[1].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFun(1);
			}
		});
		imgbtnstepF[2].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFun(2);
			}
		});
		imgbtnstepF[3].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFun(3);
			}
		});
		imgbtnstepF[4].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFun(4);
			}
		});
		imgbtnstepF[5].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFun(5);
			}
		});
		imgbtnstepF[6].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFun(6);
			}
		});
		imgbtnstepF[7].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFun(7);
			}
		});
		imgbtnstepF[8].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFun(8);
			}
		});
		imgbtnstepF[9].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFun(9);
			}
		});

		// 循环
		for (int i = 0; i < 5; i++)
			imgbtnstepf[i] = (ImageButton) findViewById(R.id.imgbtnstepf1 + i);
		imgbtnstepf[0].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFor(0);
			}
		});
		imgbtnstepf[1].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFor(1);
			}
		});
		imgbtnstepf[2].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFor(2);
			}
		});
		imgbtnstepf[3].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFor(3);
			}
		});
		imgbtnstepf[4].setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				setClickFor(4);
			}
		});

		game4 = (MyView4) findViewById(R.id.game4);
		/** 开始与停止按钮 **/
		btnStartAndstop = (Button) findViewById(R.id.startAndstop);
		btnStartAndstop.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (btnStartAndstop.getText().equals("开始")) {
					btnStartAndstop.setText("停止");
					isFirst = false;

					timer = new Timer();
					TimerTask timerTask = new TimerTask() {
						@Override
						public void run() {
							Message msg = new Message();
							msg.what = 1;
							handler.sendMessage(msg);
						}
					};
					timer.scheduleAtFixedRate(timerTask, 1000, 1000);
				} else if (btnStartAndstop.getText().equals("停止")) {
					btnStartAndstop.setText("开始");
					initData();
					game4.invalidate();
				}
			}
		});
	}

	/**
	 * 初始化必要数据
	 */
	void initData() {
		timer.cancel();
		Chongzi4.posi[0][0] = 0;
		Chongzi4.posi[0][1] = 1;
		Chongzi4.fangxiang = 1;

		isFirst = true;
		pointM = 0;
		pointF = 0;
		pointf = 0;
		flagM = true;
		flagF = true;
		flagf = true;
		loop = 3;
	}

	/**
	 * 点击主函数区域时，整个主函数区域被设为选中的颜色，其它两部分变为原来,并将此处命令清空
	 */
	private void setClickMain(int i) {
		biaozhi = 1;
		if (MB[i] == true) {
			M[i] = 0;
			imgbtnstepM[i].setBackgroundResource(R.drawable.step);
			MB[i] = false;
		}
		linlayMain.setBackgroundColor(getResources().getColor(
				R.color.backHanshu));
		linlayFun.setBackgroundColor(getResources().getColor(
				R.color.backYuanlai));
		linlayFor.setBackgroundColor(getResources().getColor(
				R.color.backYuanlai));
	}

	/**
	 * 点击辅助函数区域时，整个辅助函数区域被设为选中的颜色，其它两部分变为原来,并将此处命令清空
	 */
	private void setClickFun(int i) {
		biaozhi = 2;
		if (FB[i] == true) {
			F[i] = 0;
			imgbtnstepF[i].setBackgroundResource(R.drawable.step);
			FB[i] = false;
		}
		linlayFun.setBackgroundColor(getResources()
				.getColor(R.color.backHanshu));
		linlayMain.setBackgroundColor(getResources().getColor(
				R.color.backYuanlai));
		linlayFor.setBackgroundColor(getResources().getColor(
				R.color.backYuanlai));
	}

	/**
	 * 点击循环函数区域时，整个循环函数区域被设为选中的颜色，其它两部分变为原来,并将此处命令清空
	 */
	private void setClickFor(int i) {
		biaozhi = 3;
		if (fB[i] == true) {
			f[i] = 0;
			imgbtnstepf[i].setBackgroundResource(R.drawable.step);
			fB[i] = false;
		}
		linlayFor.setBackgroundColor(getResources()
				.getColor(R.color.backHanshu));
		linlayFun.setBackgroundColor(getResources().getColor(
				R.color.backYuanlai));
		linlayMain.setBackgroundColor(getResources().getColor(
				R.color.backYuanlai));
	}

	/**
	 * 到达了OK，是否符合
	 */
	boolean isOK() {
		for (int i = 0; i < MyView4.bean.length; i++) {
			if (MyView4.bean[i])// 还有豆子
				return false;
		}
		return Chongzi4.posi[0][0] == MyView4.x
				&& Chongzi4.posi[0][1] == MyView4.y;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level2, menu);
		return true;
	}

	/*
	 * 屏蔽菜单按钮
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			initData();
			Level4.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}