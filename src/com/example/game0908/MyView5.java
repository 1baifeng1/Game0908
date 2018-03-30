//VisionView类
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

public class MyView5 extends View {
	static float bei;
	int width = 50, height = 50;
	static int x = 2;
	static int y = 5;
	float xx, yy;
	Paint paint = new Paint();
	Rect rect = new Rect();
	Bitmap bmpChongzir1, bmpChongzib1, bmpChongzil1, bmpChongzit1, bmpDouzi,
			bmpZhongdian, bmpZhangai;
	Chongzi5 chongzi = new Chongzi5();
	static boolean bean[] = { false, false, false, false, false, false };
	int beans[][] = { { 1, 0 }, { 5, 0 }, { 2, 1 }, { 0, 3 }, { 3, 3 },
			{ 5, 5 } };
	int blocks[][] = { { 2, 0 }, { 3, 1 }, { 5, 1 }, { 1, 2 }, { 2, 3 },
			{ 5, 3 }, { 3, 4 }, { 0, 5 }, { 4, 5 } };

	public MyView5(Context context) {
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
		bmpZhangai = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.zhangai)).getBitmap();
	}

	public MyView5(Context context, AttributeSet attrs) {
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
		bmpZhangai = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.zhangai)).getBitmap();
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		bei = ((float) this.getMeasuredWidth() - 1) / 330;
		xx = (15 + x * width) * bei;
		yy = (15 + y * height) * bei;

		drawFlag(canvas);

		// 更新虫子
		if (Level5.isFirst) {
			chongzi.drawChongzi(canvas, bmpChongzir1);
		} else {
			switch (Level5.order) {
			case 1:
				if (Chongzi5.fangxiang == 1) {
					Chongzi5.posi[0][0] += 1;
					if (islimit(Chongzi5.posi) || isBlock(Chongzi5.posi))
						Chongzi5.posi[0][0] -= 1;
					chongzi.drawChongzi(canvas, bmpChongzir1);
				} else if (Chongzi5.fangxiang == 2) {
					Chongzi5.posi[0][1] += 1;
					if (islimit(Chongzi5.posi) || isBlock(Chongzi5.posi))
						Chongzi5.posi[0][1] -= 1;
					chongzi.drawChongzi(canvas, bmpChongzib1);
				} else if (Chongzi5.fangxiang == 3) {
					Chongzi5.posi[0][0] -= 1;
					if (islimit(Chongzi5.posi) || isBlock(Chongzi5.posi))
						Chongzi5.posi[0][0] += 1;
					chongzi.drawChongzi(canvas, bmpChongzil1);
				} else if (Chongzi5.fangxiang == 4) {
					Chongzi5.posi[0][1] -= 1;
					if (islimit(Chongzi5.posi) || isBlock(Chongzi5.posi))
						Chongzi5.posi[0][1] += 1;
					chongzi.drawChongzi(canvas, bmpChongzit1);
				}
				break;
			case 2:
				if (Chongzi5.fangxiang == 1) {
					chongzi.drawChongzi(canvas, bmpChongzit1);
					Chongzi5.fangxiang = 4;
				} else if (Chongzi5.fangxiang == 2) {
					chongzi.drawChongzi(canvas, bmpChongzir1);
					Chongzi5.fangxiang = 1;
				} else if (Chongzi5.fangxiang == 3) {
					chongzi.drawChongzi(canvas, bmpChongzib1);
					Chongzi5.fangxiang = 2;
				} else if (Chongzi5.fangxiang == 4) {
					chongzi.drawChongzi(canvas, bmpChongzil1);
					Chongzi5.fangxiang = 3;
				}
				break;
			case 3:
				if (Chongzi5.fangxiang == 1) {
					chongzi.drawChongzi(canvas, bmpChongzib1);
					Chongzi5.fangxiang = 2;
				} else if (Chongzi5.fangxiang == 2) {
					chongzi.drawChongzi(canvas, bmpChongzil1);
					Chongzi5.fangxiang = 3;
				} else if (Chongzi5.fangxiang == 3) {
					chongzi.drawChongzi(canvas, bmpChongzit1);
					Chongzi5.fangxiang = 4;
				} else if (Chongzi5.fangxiang == 4) {
					chongzi.drawChongzi(canvas, bmpChongzir1);
					Chongzi5.fangxiang = 1;
				}
				break;
			}
		}

		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLUE);

		drawBeans(canvas);
		drawBlock(canvas);
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
	 * 虫子移动后是否遇到障碍物
	 */
	public boolean isBlock(int[][] p) {
		if (p[0][0] == 2 && p[0][1] == 0)
			return true;
		else if (p[0][0] == 3 && p[0][1] == 1)
			return true;
		else if (p[0][0] == 5 && p[0][1] == 1)
			return true;
		else if (p[0][0] == 1 && p[0][1] == 2)
			return true;
		else if (p[0][0] == 2 && p[0][1] == 3)
			return true;
		else if (p[0][0] == 5 && p[0][1] == 3)
			return true;
		else if (p[0][0] == 3 && p[0][1] == 4)
			return true;
		else if (p[0][0] == 0 && p[0][1] == 5)
			return true;
		else if (p[0][0] == 4 && p[0][1] == 5)
			return true;
		return false;
	}

	/**
	 * 绘制豆子
	 */
	public void drawBeans(Canvas canvas) {
		if (Level5.isFirst) {
			for (int i = 0; i < 6; i++)
				bean[i] = true;
		}
		for (int i = 0; i < 6; i++) {
			if (bean[i]) {
				if (Chongzi5.posi[0][0] == beans[i][0]
						&& Chongzi5.posi[0][1] == beans[i][1]) {
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

	/**
	 * 绘制障碍
	 */
	public void drawBlock(Canvas canvas) {
		for (int i = 0; i < blocks.length; i++) {
			rect.set((int) ((blocks[i][0] * width + 15) * bei),
					(int) ((blocks[i][1] * height + 15) * bei),
					(int) ((blocks[i][0] * width + 15 + width) * bei),
					(int) ((blocks[i][1] * height + 15 + height) * bei));
			canvas.drawBitmap(bmpZhangai, null, rect, paint);
		}
	}
}