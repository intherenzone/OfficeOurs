package edu.umd.cs.officeours;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import edu.umd.cs.officeours.model.DayEnum;
import java.util.Calendar;

/**
 * Created by yufan on 5/7/2017.
 */
public class setProHour extends AppCompatActivity {
    private TextView startTimeTime, endTimeText;
    private Button startTimeButton, endTimeButton, saveButton, cancelButton;
    private int mHour;
    private int mMinute;

    static final int TIME_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID1 = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sethour);

        // capture our View elements
        startTimeTime = (TextView) findViewById(R.id.StartTimeDisplay);
        endTimeText = (TextView) findViewById(R.id.EndTimeDisplay);
        startTimeButton = (Button) findViewById(R.id.startTimePickerButton);
        endTimeButton = (Button) findViewById(R.id.endTimePickerButton);
        saveButton = (Button) findViewById(R.id.timeSaveButton);
        cancelButton = (Button) findViewById(R.id.cancelTimeButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String startTime = startTimeTime.getText().toString();
                String endTime = endTimeText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("MESSAGE", startTime + "," + endTime);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        // add a click listener to the button
        startTimeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

        endTimeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID1);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        // get the current time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // display the current date
        updateDisplay();
        updateDisplay1();

    }

    // updates the time we display in the TextView
    private void updateDisplay() {
        startTimeTime.setText(new
                StringBuilder().append(pad(mHour)).append(":").append(pad(mMinute)));
    }

    private void updateDisplay1() {
        endTimeText.setText(new
                StringBuilder().append(pad(mHour)).append(":").append(pad(mMinute)));
    }


    private Object pad(int mMinute2) {

        if (mMinute2 >= 10)
            return String.valueOf(mMinute2);
        else
            return "0" + String.valueOf(mMinute2);
    }

    // the callback received when the user "sets" the time in the dialog
    private TimePickerDialog.OnTimeSetListener mtimeSetListener = new
            TimePickerDialog.OnTimeSetListener() {

                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    mHour = hourOfDay;
                    mMinute = minute;
                    updateDisplay();
                }
            };
    private TimePickerDialog.OnTimeSetListener mtimeSetListener1 = new
            TimePickerDialog.OnTimeSetListener() {

                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    mHour = hourOfDay;
                    mMinute = minute;
                    updateDisplay1();
                }
            };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mtimeSetListener, mHour, mMinute, false);
            case TIME_DIALOG_ID1:
                return new TimePickerDialog(this, mtimeSetListener1, mHour, mMinute, false);
        }
        return null;
    }
}

