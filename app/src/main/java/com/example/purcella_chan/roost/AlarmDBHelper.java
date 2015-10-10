package com.example.purcella_chan.roost;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;

/**
 * Created by cpurcella on 10/9/15.
 */
public abstract class AlarmDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "alarmclock.db";

    private static final String SQL_CREATE_ALARM = "CREATE TABLE " + AlarmContract.Alarm.TABLE_NAME +
            AlarmContract.Alarm._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_NAME + " TEXT," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_TIME_HOUR + " INTEGER," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_TIME_MINUTE + " INTEGER," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_REPEAT_DAYS + " TEXT," +
            AlarmContract.Alarm.COLUMN_NAME_REPEAT_WEEKLY + " BOOLEAN," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_TONE + " TEXT," +
            AlarmContract.Alarm.COLUMN_NAME_ALARM_ENABLED + " BOOLEAN" + " )";

    private static final String SQL_DELETE_ALARM = "DROP TABLE IF EXISTS " +
            AlarmContract.Alarm.TABLE_NAME;

    public AlarmDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ALARM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion) {
        db.execSQL(SQL_DELETE_ALARM);
        onCreate(db);
    }
}
