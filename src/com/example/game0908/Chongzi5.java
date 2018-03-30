//Chongzi类
package com.example.game0908;

import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Chongzi5 {

	public static int[][] posi;

	/**
	 * 方向：1为右，2为下，3为左，4为上
	 */
	public static int fangxiang = 1;

	public Chongzi5() {
		posi = new int[1][2];
		posi[0][0] = 0;
		posi[0][1] = 1;
	}

	public void drawChongzi(Canvas canvas, Bitmap bitmap) {
		Rect rect = new Rect();
		int x = 0, y = 0;
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLACK);
		float width = 50 * MyView5.bei;
		float height = width;

		x = (int) ((posi[0][0] * 50 + 15) * MyView5.bei);
		y = (int) ((posi[0][1] * 50 + 15) * MyView5.bei);
		int xx = (int) (x + width);
		int yy = (int) (y + height);
		// rect.set(x, y, x+width, y+height);
		rect.set(x, y, xx, yy);
		canvas.drawBitmap(bitmap, null, rect, paint);
	}
}