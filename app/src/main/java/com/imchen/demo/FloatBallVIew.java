package com.imchen.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by imchen on 2017/3/12.
 */

public class FloatBallVIew extends View {

    public String text="hello";

    public static int width=150;

    public static int height=150;

    private  Paint circlePaint;

    private Paint textPaint;

    private Bitmap logo;

    private  boolean Draging=false;

    public FloatBallVIew(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.BLUE);
        circlePaint.setAntiAlias(true);

        textPaint=new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(25);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(true);
        textPaint.measureText(text);

        Bitmap src= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        logo=Bitmap.createScaledBitmap(src,width,height,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!Draging){
            //circle
            canvas.drawCircle(width/2,height/2,width/2,circlePaint);
            //text
            float textWidth=textPaint.measureText(text);
            Paint.FontMetrics fontMetrics=textPaint.getFontMetrics();
            float dy=-(fontMetrics.descent+fontMetrics.ascent)/2;
            canvas.drawText(text,width/2-textWidth/2,height/2+dy,textPaint);
        }else {
            canvas.drawBitmap(logo,0,0,null);
        }

    }

    public  void setIsDrag(boolean isDrag){
        this.Draging=isDrag;
        invalidate();
    }

    public FloatBallVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
