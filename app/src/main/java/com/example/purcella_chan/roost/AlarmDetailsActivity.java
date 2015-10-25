package com.example.purcella_chan.roost;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import static android.media.RingtoneManager.*;

public class AlarmDetailsActivity extends AppCompatActivity {

    private AlarmModel alarmDetails;

    private void updateModelFromLayout() {

        TimePicker timePicker = (TimePicker) findViewById(R.id.alarm_details_time_picker);
        alarmDetails.timeMinute = timePicker.getMinute();
        alarmDetails.timeHour = timePicker.getHour();

        EditText edtName = (EditText) findViewById(R.id.alarm_details_name);
        alarmDetails.name = edtName.getText().toString();

        CheckBox chkWeekly = (CheckBox) findViewById(R.id.alarm_details_repeat_weekly);
        alarmDetails.repeatWeekly = chkWeekly.isChecked();

        CheckBox chkSunday = (CheckBox) findViewById(R.id.alarm_details_repeat_sunday);
        alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, chkSunday.isChecked());

        CheckBox chkMonday = (CheckBox) findViewById(R.id.alarm_details_repeat_monday);
        alarmDetails.setRepeatingDay(AlarmModel.MONDAY, chkMonday.isChecked());

        CheckBox chkTuesday = (CheckBox) findViewById(R.id.alarm_details_repeat_tuesday);
        alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, chkTuesday.isChecked());

        CheckBox chkWednesday = (CheckBox) findViewById(R.id.alarm_details_repeat_wednesday);
        alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, chkWednesday.isChecked());

        CheckBox chkThursday = (CheckBox) findViewById(R.id.alarm_details_repeat_thursday);
        alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, chkThursday.isChecked());

        CheckBox chkFriday = (CheckBox) findViewById(R.id.alarm_details_repeat_friday);
        alarmDetails.setRepeatingDay(AlarmModel.FRIDAY, chkFriday.isChecked());

        CheckBox chkSaturday = (CheckBox) findViewById(R.id.alarm_details_repeat_saturday);
        alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, chkSaturday.isChecked());

        alarmDetails.isEnabled = true;

        edtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_details);
        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setTitle("Add An Alarm");
        mActionBar.setDisplayHomeAsUpEnabled(true);

        alarmDetails = new AlarmModel();
        final LinearLayout ringToneContainer = (LinearLayout) findViewById(R.id.alarm_ringtone_container);
        ringToneContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_RINGTONE_PICKER);
                startActivityForResult(intent , 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        if (id == R.id.action_save_alarm_details) {
            updateModelFromLayout();

            AlarmDBHelper dbHelper = new AlarmDBHelper(this);
            if (alarmDetails.id < 0) {
                dbHelper.createAlarm(alarmDetails);
            }

            else {
                dbHelper.updateAlarm(alarmDetails);
            }

            setResult(RESULT_OK);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    alarmDetails.alarmTone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                    TextView txtToneSelection = (TextView) findViewById(R.id.alarm_label_tone_selection);
                    txtToneSelection.setText(RingtoneManager.getRingtone(this, alarmDetails.alarmTone).getTitle(this));
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}
