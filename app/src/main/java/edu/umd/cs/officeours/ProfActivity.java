package edu.umd.cs.officeours;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import edu.umd.cs.officeours.model.Course;
import edu.umd.cs.officeours.model.Professor;
import edu.umd.cs.officeours.services.ProfService;

public class ProfActivity extends AppCompatActivity {

    private static final String EXTRA_PROF = "PROF";
    private ProfService profService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_select);

        profService = DependencyFactory.getProfService(getApplicationContext());
        List<Professor> profList = profService.getAllProfessors();
        String profID = getIntent().getStringExtra(EXTRA_PROF);
        Professor currProfessor = null;

        for(Professor p : profList){
            if(p.getProfID().compareTo(profID) == 0){
                currProfessor = p;
                break;
            }
        }
        TextView profNameTextView = (TextView) findViewById(R.id.professor_name);
        profNameTextView.setText(currProfessor.getFName() + " " + currProfessor.getLName());

        TextView officeHoursTextView = (TextView) findViewById(R.id.office_hour_text);
        Calendar calendar = Calendar.getInstance();
        String dayOfWeekNumber = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));

        //GONNA CHANGE THIS TO SHOW OFFICE HOURS FOR THAT PARTICULAR DAY
        switch(dayOfWeekNumber){
            case "1":
                officeHoursTextView.setText("Sunday");
                break;
            case "2":
                officeHoursTextView.setText("Monday");
                break;
            case "3":
                officeHoursTextView.setText("Tuesday");
                break;
            case "4":
                officeHoursTextView.setText("Wednesday");
                break;
            case "5":
                officeHoursTextView.setText("Thursday");
                break;
            case "6":
                officeHoursTextView.setText("Friday");
                break;
            default:
                officeHoursTextView.setText("Saturday");
                break;
        }


        final LinearLayout buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
        LinearLayout buttonCluster = (LinearLayout)findViewById(R.id.buttonCluster);
        for(final Course course : currProfessor.courses){
            Button button =  new Button(getApplicationContext());
            button.setText(course.getCourseName());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            button.setLayoutParams(params);


            button.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    LinearLayout layout = (LinearLayout) findViewById(R.id.buttonLayout);
                    Bitmap taHoursBitmap = course.getTAOfficeHours();
                    ImageView taHoursImageView = new ImageView(getApplicationContext());
                    taHoursImageView.setImageBitmap(taHoursBitmap);
                    taHoursImageView.setMinimumWidth(layout.getWidth());
                    taHoursImageView.setMinimumHeight(layout.getHeight());
                    buttonLayout.removeView(findViewById(R.id.buttonCluster));
                    buttonLayout.addView(taHoursImageView);
                }
            });

            buttonCluster.addView(button);
        }


        //DELETED STRING ARRAY THING
//        findViewById(R.id.course1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LinearLayout layout = (LinearLayout) findViewById(R.id.buttonLayout);
//                ImageView picture = new ImageView(getApplicationContext());
//                picture.setImageResource(android.R.drawable.star_big_on);
//                picture.setMinimumWidth(layout.getWidth());
//                picture.setMinimumHeight(layout.getHeight());
//                layout.removeView(findViewById(R.id.buttonCluster));
//                layout.addView(picture);
//
//            }
//        });


    }


    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, ProfActivity.class);
        intent.putExtra(EXTRA_PROF, id);
        return intent;
    }




}
