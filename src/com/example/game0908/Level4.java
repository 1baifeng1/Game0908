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
	 * ��Ϸ����
	 */
	MyView4 game4;

	// �Ҳ�
	int M[] = new int[15];
	int F[] = new int[10];
	int f[] = new int[5];

	// ���λͼ
	Bitmap bmpr1, bmpr2, bmpr3;
	Bitmap[] bmpR = { bmpr1, bmpr2, bmpr3, bmpr2, bmpr1 };
	int bmpindex = 0;// bmpR������

	/**
	 * ��¼�ĸ��������ڱ༭״̬ ��1Ϊ��������2Ϊ����������3Ϊѭ��
	 */
	int biaozhi = 1;

	/**
	 * ��ʼ��ֹͣ��ť
	 */
	Button btnStartAndstop;

	/**
	 * �Ҳ������ͼƬ��ť
	 * */
	LinearLayout linlayMain, linlayFun, linlayFor;

	/**
	 * 1Ϊֱ�У�2Ϊ��ת��3Ϊ��ת��4Ϊ����������5Ϊѭ����6Ϊ���
	 */
	public ImageButton imgbtnZhixing, imgbtnZuozhuan, imgbtnYouzhuan,
			imgbtnHanshu, imgbtnXunhuan, imgbtnWancheng;
	/**
	 * �������е�15������
	 */
	ImageButton imgbtnstepM[] = new ImageButton[15];
	/**
	 * ���������е�10������
	 */
	ImageButton imgbtnstepF[] = new ImageButton[10];
	/**
	 * forѭ���е�5������
	 */
	ImageButton imgbtnstepf[] = new ImageButton[5];

	/**
	 * ��¼�������ĸô��Ƿ�������
	 */
	Boolean MB[] = { false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false };
	/**
	 * ��¼���������ĸô��Ƿ�������
	 */
	Boolean FB[] = { false, false, false, false, false, false, false, false,
			false, false };
	/**
	 * ��¼ѭ�����ĸô��Ƿ�������
	 */
	Boolean fB[] = { false, false, false, false, false };

	/**
	 * ��¼��ǰ����
	 */
	static int order;

	/**
	 * ����ʱ��ָ��ǰ������������
	 */
	int pointM = 0;
	/**
	 * ����ʱ��ָ��ǰ��������������
	 */
	int pointF = 0;
	/**
	 * ����ʱ��ָ��ǰforѭ��������
	 */
	int pointf = 0;

	/**
	 * ����ʱ���жϵ�ǰ�Ƿ�Ϊ������
	 */
	boolean flagM = true;
	/**
	 * ����ʱ���жϵ�ǰ�Ƿ�Ϊ��������
	 */
	boolean flagF = false;
	/**
	 * ����ʱ���жϵ�ǰ�Ƿ�Ϊѭ������
	 */
	boolean flagf = false;

	/**
	 * �ö�ά���飬��һ��Ԫ��Ϊ�������ĺ������ڶ���Ԫ��Ϊ�жϵ�λ��.������ø��������Ķϵ�
	 */
	int intF[][] = new int[10][2];
	/**
	 * �ö�ά���飬��һ��Ԫ��Ϊ�������ĺ������ڶ���Ԫ��Ϊ�жϵ�λ��.�������ѭ�������Ķϵ�
	 */
	int intf[][] = new int[10][2];

	/**
	 * �Ƿ��ǵ�һ��ִ��
	 */
	static Boolean isFirst = true;

	/**
	 * forѭ��Ҫѭ��3��
	 */
	int loop = 3;

	/**
	 * ��ʱ��
	 */
	Timer timer = new Timer();
	/**
	 * ��¼��ʱ���Ƿ�wait
	 */

	/**
	 * ������Ϣ
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
	 * ����������
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
	 * ����������
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
	 * ����ѭ������
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
	 * �������
	 */
	private void handleOK() {
		timer.cancel();
		initData();

		new AlertDialog.Builder(Level4.this)
				// �����getApplicationContext���������
				.setMessage("��ϲ�㣬�ɹ�����˵ڶ��ء�")
				.setIcon(R.drawable.douzi)
				.setPositiveButton("����������һ��",
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
				.setNegativeButton("׼����һ��",
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
		 * ���������
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
		 * �����������
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
		 * ���forѭ��
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

		for (int i = 0; i < 15; i++) {// ��ʼ����������������
			M[i] = 0;
		}

		// ����
		imgbtnZhixing = (ImageButton) findViewById(R.id.imgbtnZhixing);
		imgbtnZhixing.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				int i = 0;
				switch (biaozhi) {
				case 1:
					while (i < 15 && M[i] != 0) {// �ҵ���һ��û������ķ�����С��15
						i++;
					}
					if (i < 15) {
						M[i] = 1;// ֱ��Ϊ����1
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.zhixing);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// �ҵ���һ��û������ķ�����С��10
						i++;
					}
					if (i < 10) {
						F[i] = 1;// ֱ��Ϊ����1
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.zhixing);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// �ҵ���һ��û������ķ�����С��5
						i++;
					}
					if (i < 5) {
						f[i] = 1;// ֱ��Ϊ����1
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
					while (i < 15 && M[i] != 0) {// �ҵ���һ��û������ķ�����С��15
						i++;
					}
					if (i < 15) {
						M[i] = 2;// ��תΪ����2
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.zuozhuan);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// �ҵ���һ��û������ķ�����С��10
						i++;
					}
					if (i < 10) {
						F[i] = 2;// ��תΪ����2
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.zuozhuan);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// �ҵ���һ��û������ķ�����С��5
						i++;
					}
					if (i < 5) {
						f[i] = 2;// ��תΪ����2
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
					while (i < 15 && M[i] != 0) {// �ҵ���һ��û������ķ�����С��15
						i++;
					}
					if (i < 15) {
						M[i] = 3;// ��תΪ����3
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.youzhuan);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// �ҵ���һ��û������ķ�����С��10
						i++;
					}
					if (i < 10) {
						F[i] = 3;// ��תΪ����3
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.youzhuan);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// �ҵ���һ��û������ķ�����С��5
						i++;
					}
					if (i < 5) {
						f[i] = 3;// ��תΪ����3
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
					while (i < 15 && M[i] != 0) {// �ҵ���һ��û������ķ�����С��15
						i++;
					}
					if (i < 15) {
						M[i] = 4;// ����Ϊ����4
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.hanshu);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// �ҵ���һ��û������ķ�����С��10
						i++;
					}
					if (i < 10) {
						F[i] = 4;// ����Ϊ����4
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.hanshu);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// �ҵ���һ��û������ķ�����С��5
						i++;
					}
					if (i < 5) {
						f[i] = 4;// ����Ϊ����4
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
					while (i < 15 && M[i] != 0) {// �ҵ���һ��û������ķ�����С��15
						i++;
					}
					if (i < 15) {
						M[i] = 5;// ѭ��Ϊ����5
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.xunhuan);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// �ҵ���һ��û������ķ�����С��10
						i++;
					}
					if (i < 10) {
						F[i] = 5;// ѭ��Ϊ����5
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.xunhuan);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// �ҵ���һ��û������ķ�����С��5
						i++;
					}
					if (i < 5) {
						f[i] = 5;// ѭ��Ϊ����5
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
					while (i < 15 && M[i] != 0) {// �ҵ���һ��û������ķ�����С��15
						i++;
					}
					if (i < 15) {
						M[i] = 6;// ���Ϊ����6
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepM1
								+ i);
						im.setBackgroundResource(R.drawable.wancheng);
						MB[i] = true;
					}
					break;
				case 2:
					while (i < 10 && F[i] != 0) {// �ҵ���һ��û������ķ�����С��10
						i++;
					}
					if (i < 10) {
						F[i] = 6;// ���Ϊ����6
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepF1
								+ i);
						im.setBackgroundResource(R.drawable.wancheng);
						FB[i] = true;
					}
					break;
				case 3:
					while (i < 5 && f[i] != 0) {// �ҵ���һ��û������ķ�����С��5
						i++;
					}
					if (i < 5) {
						f[i] = 6;// ���Ϊ����6
						ImageButton im = (ImageButton) findViewById(R.id.imgbtnstepf1
								+ i);
						im.setBackgroundResource(R.drawable.wancheng);
						fB[i] = true;
					}
					break;
				}
			}
		});

		// ������
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

		// ��������
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

		// ѭ��
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
		/** ��ʼ��ֹͣ��ť **/
		btnStartAndstop = (Button) findViewById(R.id.startAndstop);
		btnStartAndstop.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (btnStartAndstop.getText().equals("��ʼ")) {
					btnStartAndstop.setText("ֹͣ");
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
				} else if (btnStartAndstop.getText().equals("ֹͣ")) {
					btnStartAndstop.setText("��ʼ");
					initData();
					game4.invalidate();
				}
			}
		});
	}

	/**
	 * ��ʼ����Ҫ����
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
	 * �������������ʱ������������������Ϊѡ�е���ɫ�����������ֱ�Ϊԭ��,�����˴��������
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
	 * ���������������ʱ��������������������Ϊѡ�е���ɫ�����������ֱ�Ϊԭ��,�����˴��������
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
	 * ���ѭ����������ʱ������ѭ������������Ϊѡ�е���ɫ�����������ֱ�Ϊԭ��,�����˴��������
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
	 * ������OK���Ƿ����
	 */
	boolean isOK() {
		for (int i = 0; i < MyView4.bean.length; i++) {
			if (MyView4.bean[i])// ���ж���
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
	 * ���β˵���ť
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