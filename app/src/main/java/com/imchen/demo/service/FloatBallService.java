package com.imchen.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.imchen.demo.manager.FloatBallManager;

/**
 * Created by imchen on 2017/3/12.
 */

public class FloatBallService extends Service {

    private FloatBallManager floatBallManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        floatBallManager=FloatBallManager.getInstance(this);
        floatBallManager.showFloatBall();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        floatBallManager.hideFloatBall();
    }
}
