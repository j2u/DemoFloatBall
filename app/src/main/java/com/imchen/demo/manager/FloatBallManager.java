package com.imchen.demo.manager;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.imchen.demo.FloatBallVIew;

/**
 * Created by imchen on 2017/3/12.
 */

public class FloatBallManager {

    public Context mContext;

    private FloatBallVIew floatBallVIew;

    private WindowManager windowManager;

    private static FloatBallManager floatBallManager;

    private WindowManager.LayoutParams layoutParams;
    
    private  final String TAG="imc";

    private float startX;
    private float startY;
    private float tempX;
    private float tempY;
    private int screenWidth;
    private int scteenHeight;

    private FloatBallManager(Context context) {
        this.mContext=context;
        floatBallVIew=getFlaotBall();
        windowManager=getWindowManager(mContext);
        getScreenWidth();
    }

    public static  FloatBallManager getInstance(Context context){
        if (floatBallManager==null){
            floatBallManager=new FloatBallManager(context);
            return floatBallManager;
        }
        return floatBallManager;
    }

    public void showFloatBall(){

        if (layoutParams==null){
            layoutParams=new WindowManager.LayoutParams();
            layoutParams.x=0;
            layoutParams.y= 0;
            layoutParams.width=FloatBallVIew.width;
            layoutParams.height=FloatBallVIew.height;
            layoutParams.type= WindowManager.LayoutParams.TYPE_PHONE;
            layoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            layoutParams.format= PixelFormat.RGBA_8888;
            floatBallVIew.setLayoutParams(layoutParams);
            getWindowManager(mContext).addView(floatBallVIew,layoutParams);
        }

        floatBallVIew.setOnTouchListener(listener);
    }

    View.OnTouchListener listener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    floatBallVIew.setIsDrag(true);
                    startX=event.getRawX();
                    startY=event.getRawY();
                    tempX=event.getRawX();
                    tempY=event.getRawY();
                    Log.d(TAG, "onTouch: action_down");
                    break;
                case MotionEvent.ACTION_MOVE:
                    layoutParams.x+= event.getRawX()-startX;
                    layoutParams.y+= event.getRawY()-startY;
                    startX=event.getRawX();
                    startY=event.getRawY();
                    Log.d(TAG, "onTouch: action_move");
                    windowManager.updateViewLayout(floatBallVIew,layoutParams);
                    break;
                case MotionEvent.ACTION_UP:
                    float endX=event.getRawX();
                    float endY=event.getRawY();
                    if (endX>=screenWidth/2){
                        endX=screenWidth-FloatBallVIew.width;
                    }else {
                        endX=0;
                    }
                    layoutParams.x= (int) endX;

                    windowManager.updateViewLayout(floatBallVIew,layoutParams);
                    floatBallVIew.setIsDrag(false);
                    Log.d(TAG, "onTouch: action_up");
                    if (Math.abs(endX-tempX)>6&&Math.abs(endY-tempY)>6){
                        return  true;
                    }
                    break;
            }
            Log.d(TAG, "onTouch: touch");
            return false;
        }
    };

    public void hideFloatBall(){
        if (floatBallVIew!=null){
            windowManager.removeView(floatBallVIew);
            layoutParams=null;
        }
    }

    private FloatBallVIew getFlaotBall(){
        if (floatBallVIew==null){
            floatBallVIew=new FloatBallVIew(mContext);
        }
        return floatBallVIew;
    }

    private WindowManager getWindowManager(Context context){
        if (windowManager==null){
            windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            return windowManager;
        }
        return windowManager;

    }

    public void getScreenWidth(){
        Point point=new Point();
        windowManager.getDefaultDisplay().getSize(point);
        screenWidth= point.x;
        scteenHeight=point.y;
    }
}
