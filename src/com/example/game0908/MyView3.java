package com.example.game0908;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

public class MyView3 extends View {

	static float bei;
	int width = 50, height = 50;
	static int x = 3;
	static int y = 5;
	float xx, yy;
	Paint paint = new Paint();
	Rect rect = new Rect();
	Bitmap bmpChongzir1, bmpChongzib1, bmpChongzil1, bmpChongzit1, bmpDouzi,
			bmpZhongdian;
	Chongzi3 chongzi3 = new Chongzi3();
	static boolean bean[] = { true, true, true, true, true };
	int beans[][] = { { 1, 1 }, { 1, 3 }, { 2, 2 }, { 5, 0 }, { 5, 5 } };

	public MyView3(Context context) {
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

	public MyView3(Context context, AttributeSet attrs) {
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
		if (Level3.isFirst) {
			chongzi3.drawChongzi(canvas, bmpChongzir1);
		} else {
			switch (Level3.order) {
			case 1:
				if (Chongzi3.fangxiang == 1) {
					Chongzi3.posi[0][0] += 1;
					if (islimit(Chongzi3.posi))
						Chongzi3.posi[0][0] -= 1;
					chongzi3.drawChongzi(canvas, bmpChongzir1);
				} else if (Chongzi3.fangxiang == 2) {
					Chongzi3.posi[0][1] += 1;
					if (islimit(Chongzi3.posi))
						Chongzi3.posi[0][1] -= 1;
					chongzi3.drawChongzi(canvas, bmpChongzib1);
				} else if (Chongzi3.fangxiang == 3) {
					Chongzi3.posi[0][0] -= 1;
					if (islimit(Chongzi3.posi))
						Chongzi3.posi[0][0] += 1;
					chongzi3.drawChongzi(canvas, bmpChongzil1);
				} else if (Chongzi3.fangxiang == 4) {
					Chongzi3.posi[0][1] -= 1;
					if (islimit(Chongzi3.posi))
						Chongzi3.posi[0][1] += 1;
					chongzi3.drawChongzi(canvas, bmpChongzit1);
				}
				break;
			case 2:
				if (Chongzi3.fangxiang == 1) {
					chongzi3.drawChongzi(canvas, bmpChongzit1);
					Chongzi3.fangxiang = 4;
				} else if (Chongzi3.fangxiang == 2) {
					chongzi3.drawChongzi(canvas, bmpChongzir1);
					Chongzi3.fangxiang = 1;
				} else if (Chongzi3.fangxiang == 3) {
					chongzi3.drawChongzi(canvas, bmpChongzib1);
					Chongzi3.fangxiang = 2;
				} else if (Chongzi3.fangxiang == 4) {
					chongzi3.drawChongzi(canvas, bmpChongzil1);
					Chongzi3.fangxiang = 3;
				}
				break;
			case 3:
				if (Chongzi3.fangxiang == 1) {
					chongzi3.drawChongzi(canvas, bmpChongzib1);
					Chongzi3.fangxiang = 2;
				} else if (Chongzi3.fangxiang == 2) {
					chongzi3.drawChongzi(canvas, bmpChongzil1);
					Chongzi3.fangxiang = 3;
				} else if (Chongzi3.fangxiang == 3) {
					chongzi3.drawChongzi(canvas, bmpChongzit1);
					Chongzi3.fangxiang = 4;
				} else if (Chongzi3.fangxiang == 4) {
					chongzi3.drawChongzi(canvas, bmpChongzir1);
					Chongzi3.fangxiang = 1;
				}
				break;
			}
		}

		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLUE);

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
		if (Level3.isFirst) {
			for (int i = 0; i < 5; i++)
				bean[i] = true;
		}
		for (int i = 0; i < 5; i++) {
			if (bean[i]) {
				if (Chongzi3.posi[0][0] == beans[i][0]
						&& Chongzi3.posi[0][1] == beans[i][1]) {
					bean[i] = false;
				} else {
					rect.set((int) ((beans[i][0] * width + 15) * bei),
							(int) ((beans[i][1] * height + 15) * bei),
							(int) ((beans[i][0] * width + width + 15) * bei),
							(int) ((beans[i][1] * height + height + 15) * bei));
					canvas.drawBitmap(bmpDouzi, null, rect, paint);
				}
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