package com.example.game0908;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Chongzi3 {

	public static int[][] posi;
	
	/**
	 * 方向：1为右，2为下，3为左，4为上
	 */
	public static int fangxiang=1;

	public Chongzi3() {
		posi = new int[1][2];
		posi[0][0] = 0;
		posi[0][1] = 0;
	}
	
	public void drawChongzi(Canvas canvas, Bitmap bitmap) {
		Rect rect=new Rect();
		int x=0,y=0;
		Paint paint=new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLACK);
		float width = 50 * MyView3.bei;
		float height = width;

		x = (int) ((posi[0][0] * 50 + 15) * MyView3.bei);
		y = (int) ((posi[0][1] * 50 + 15) * MyView3.bei);
		int xx = (int) (x + width);
		int yy = (int) (y + height);
		// rect.set(x, y, x+width, y+height);
		rect.set(x, y, xx, yy);
		canvas.drawBitmap(bitmap, null, rect, paint);
	}
}
