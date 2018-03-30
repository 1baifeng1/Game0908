package com.example.game0908;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

public class MyView1 extends View {
	static float bei;
	int width=50, height=50;
	static float x = 0;
	static float y = 5;
	float xx,yy;
	Paint paint = new Paint();
	Rect rect = new Rect();
	Bitmap bmpChongzir1, bmpChongzib1, bmpChongzil1, bmpChongzit1, bmpDouzi,
			bmpZhongdian;
	Chongzi1 chongzi1 = new Chongzi1();
	static boolean bean = false;
	int beans[] = { 0, 3 };// 第0列第3行

	public MyView1(Context context) {
		super(context);
		bmpChongzir1 = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.huangdour1)).getBitmap();
		bmpChongzib1 = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.huangdoub1)).getBitmap();
		bmpChongzil1 = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.huangdoul1)).getBitmap();
		bmpChongzit1 = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.huangdout1)).getBitmap();
		bmpDouzi = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.huangdou3)).getBitmap();
		bmpZhongdian = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.zhongdian)).getBitmap();
	}

	public MyView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		bmpChongzir1 = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.huangdour1)).getBitmap();
		bmpChongzib1 = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.huangdoub1)).getBitmap();
		bmpChongzil1 = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.huangdoul1)).getBitmap();
		bmpChongzit1 = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.huangdout1)).getBitmap();
		bmpDouzi = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.huangdou3)).getBitmap();
		bmpZhongdian = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.zhongdian)).getBitmap();
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		bei = ((float) this.getMeasuredWidth() - 1) / 330;
		xx = (15 + x * width) * bei;
		yy = (15 + y * height) * bei;

		drawFlag(canvas);

		// 更新虫子
		if (Level1.isFirst) {
			chongzi1.drawChongzi(canvas, bmpChongzir1);
		} else {
			switch (Level1.order) {
			case 1:// 直行
				if (Chongzi1.fangxiang == 1) {
					Chongzi1.posi[0][0] += 1;
					if (islimit(Chongzi1.posi))
						Chongzi1.posi[0][0] -= 1;
					chongzi1.drawChongzi(canvas, bmpChongzir1);
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				} else if (Chongzi1.fangxiang == 2) {
					Chongzi1.posi[0][1] += 1;
					if (islimit(Chongzi1.posi))
						Chongzi1.posi[0][1] -= 1;
					chongzi1.drawChongzi(canvas, bmpChongzib1);
				} else if (Chongzi1.fangxiang == 3) {
					Chongzi1.posi[0][0] -= 1;
					if (islimit(Chongzi1.posi))
						Chongzi1.posi[0][0] += 1;
					chongzi1.drawChongzi(canvas, bmpChongzil1);
				} else if (Chongzi1.fangxiang == 4) {
					Chongzi1.posi[0][1] -= 1;
					if (islimit(Chongzi1.posi))
						Chongzi1.posi[0][1] += 1;
					chongzi1.drawChongzi(canvas, bmpChongzit1);
				}
				break;
			case 2:// 左转
				if (Chongzi1.fangxiang == 1) {
					chongzi1.drawChongzi(canvas, bmpChongzit1);
					Chongzi1.fangxiang = 4;
				} else if (Chongzi1.fangxiang == 2) {
					chongzi1.drawChongzi(canvas, bmpChongzir1);
					Chongzi1.fangxiang = 1;
				} else if (Chongzi1.fangxiang == 3) {
					chongzi1.drawChongzi(canvas, bmpChongzib1);
					Chongzi1.fangxiang = 2;
				} else if (Chongzi1.fangxiang == 4) {
					chongzi1.drawChongzi(canvas, bmpChongzil1);
					Chongzi1.fangxiang = 3;
				}
				break;
			case 3:// 右转
				if (Chongzi1.fangxiang == 1) {
					chongzi1.drawChongzi(canvas, bmpChongzib1);
					Chongzi1.fangxiang = 2;
				} else if (Chongzi1.fangxiang == 2) {
					chongzi1.drawChongzi(canvas, bmpChongzil1);
					Chongzi1.fangxiang = 3;
				} else if (Chongzi1.fangxiang == 3) {
					chongzi1.drawChongzi(canvas, bmpChongzit1);
					Chongzi1.fangxiang = 4;
				} else if (Chongzi1.fangxiang == 4) {
					chongzi1.drawChongzi(canvas, bmpChongzir1);
					Chongzi1.fangxiang = 1;
				}
				break;
			}
		}

		drawBeans(canvas);
	}

	/**
	 * 虫子移动后是否出界
	 */
	public boolean islimit(int[][] p) {
		if (p[0][0] < 0 || p[0][0] > 5 || p[0][1] < 0 || p[0][1] > 5)
			return true;
		return false;
	}

	/**
	 * 绘制豆子
	 */
	public void drawBeans(Canvas canvas) {
		if (Level1.isFirst) {
			bean = true;
		}
		if (bean) {
			if (Chongzi1.posi[0][0] == beans[0]
					&& Chongzi1.posi[0][1] == beans[1]) {
				bean = false;
			} else {
				rect.set((int) ((beans[0] * width + 15) * bei),
						(int) ((beans[1] * height + 15) * bei),
						(int) ((beans[0] * width + width + 15) * bei),
						(int) ((beans[1] * height + height + 15) * bei));
				canvas.drawBitmap(bmpDouzi, null, rect, paint);
			}
		}
	}

	/**
	 * 绘制终点
	 */
	public void drawFlag(Canvas canvas) {
		rect.set((int) xx, (int) yy, (int) (xx + (float) width * bei),
				(int) (yy + (float) height * bei));
		canvas.drawBitmap(bmpZhongdian, null, rect, paint);
	}
}