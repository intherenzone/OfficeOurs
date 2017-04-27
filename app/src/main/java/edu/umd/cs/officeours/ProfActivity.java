package edu.umd.cs.officeours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ProfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_select);
        String[] courses = {"CMSC420","CMSC69","CMSC850"};
        findViewById(R.id.course1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.buttonLayout);
                ImageView picture = new ImageView(getApplicationContext());
                picture.setImageResource(android.R.drawable.star_big_on);
                picture.setMinimumWidth(layout.getWidth());
                picture.setMinimumHeight(layout.getHeight());
                layout.removeView(findViewById(R.id.buttonCluster));
                layout.addView(picture);

            }
        });


    }
}
