package com.example.purcella_chan.roost;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by justin on 11/8/2015.
 */

//This class allows a new activity to be launched after an alarm goes off
public class AlarmService extends Service {

    public static String TAG = AlarmService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent alarmIntent = new Intent(getBaseContext(), AlarmScreen.class);
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent.putExtras(intent);
        getApplication().startActivity(alarmIntent);

        AlarmManagerHelper.setAlarms(this);

        return super.onStartCommand(intent, flags, startId);
    }

}
