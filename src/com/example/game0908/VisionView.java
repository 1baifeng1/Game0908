package com.example.game0908;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class VisionView extends View {
	Paint paint;
	float bei;
//	float bei=1f;
	float width;
	float height;

	public VisionView(Context context) {
		super(context);
	}

	public VisionView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		bei=((float)this.getMeasuredWidth()-1)/330;
		
		float x = 15*bei,y = 15*bei;
		float xx = 15*bei, yy = 15*bei;
		width = 50*bei;
		height = width;
		
		Paint pnt = new Paint();
		pnt.setStyle(Paint.Style.FILL);
		pnt.setColor(Color.BLACK);
		canvas.drawLine(0, 0, 0, 330*bei, pnt);
		canvas.drawLine(0, 0, 330*bei, 0, pnt);
		canvas.drawLine(330*bei, 0, 330*bei, 330*bei, pnt);
		canvas.drawLine(0, 330*bei, 330*bei, 330*bei, pnt);
		for (int i = 1; i <= 7; i++)
		{
			canvas.drawLine(x, yy, x, yy+width*6 , pnt);// »­ÊúÏß
			x = x + width;
			
			canvas.drawLine(xx, y, xx + height * 6, y, pnt);// »­ºáÏß
			y = y + height;
		}

		// /////////////////////////////////////////////////
//		System.out.println("!!!!!!!!"+this.getMeasuredWidth());
//		System.out.println("!!!!!!!!"+bei);
	}
}