/**
 * Created by cpurcella on 10/9/15.
 */
package com.example.purcella_chan.roost;
import android.net.Uri;

/* Simple alarm class that holds the data for the user's alarms.
   Holds a bool to indicate if the alarm should play on each day
   of the week, and information about the time and tone to be
   played by the alarm.
 */
public class AlarmModel {

    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;

    public long id;
    public int timeHour;
    public int timeMinute;
    private boolean repeatingDays[];
    public boolean repeatWeekly;
    public Uri alarmTone;
    public String name;
    public boolean isEnabled;

    // Create a new alarm model with an array of 7 booleans.
    public AlarmModel() {
        repeatingDays = new boolean[7];
    }

    // Set each value in the array of bools to true or false
    // if the user indicated they want the alarm to play on
    // that day or not.
    public void setRepeatingDay(int dayOfWeek, boolean value) {
        repeatingDays[dayOfWeek] = value;
    }


    // Getter for repeating days.
    public boolean getRepeatingDay(int dayOfWeek) {
        return repeatingDays[dayOfWeek];
    }
}




