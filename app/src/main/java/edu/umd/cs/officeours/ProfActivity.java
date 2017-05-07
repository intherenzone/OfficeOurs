package edu.umd.cs.officeours;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import edu.umd.cs.officeours.model.Course;
import edu.umd.cs.officeours.model.Day;
import edu.umd.cs.officeours.model.DayEnum;
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
        final List<Professor> profList = profService.getAllProfessors();
        final String profID = getIntent().getStringExtra(EXTRA_PROF);
        Professor currProfessor = null;

        for(Professor p : profList){
            if(p.getProfID().compareTo(profID) == 0){
                currProfessor = p;
                break;
            }
        }

        final Professor currProfessorFinal = currProfessor;
        final TextView profNameTextView = (TextView) findViewById(R.id.professor_name);
        profNameTextView.setText(currProfessor.getFName() + " " + currProfessor.getLName());

        Button homeButton = (Button) findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });

        Button bioButton = (Button) findViewById(R.id.bio_button);
        bioButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(findViewById(R.id.BIO_ID) != null){return;}
                LinearLayout buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
                Bitmap picBitmap = currProfessorFinal.getPicBitmap();
                ImageView picImageView = new ImageView(getApplicationContext());
                picImageView.setImageBitmap(picBitmap);
                picImageView.setMinimumWidth(buttonLayout.getWidth());
                picImageView.setMinimumHeight(buttonLayout.getHeight());
                picImageView.setId(R.id.BIO_ID);
                if(findViewById(R.id.buttonCluster) != null){buttonLayout.removeView(findViewById(R.id.buttonCluster));}
                if(findViewById(R.id.TA_HOURS_ID) != null){buttonLayout.removeView(findViewById(R.id.TA_HOURS_ID));}
                buttonLayout.addView(picImageView);
            }
        });

        Button taHoursButton = (Button) findViewById(R.id.ta_hours_button);
        final LinearLayout buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
        taHoursButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(findViewById(R.id.buttonCluster) != null){return;}
                LinearLayout buttonCluster = new LinearLayout(getApplicationContext());
                buttonCluster.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER;

//                TextView courseHeader = new TextView(getApplicationContext());
//                courseHeader.setText(R.string.courses_header);
//                courseHeader.setTypeface(null, Typeface.BOLD);
//                courseHeader.setGravity(Gravity.CENTER);
//                ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//
//                courseHeader.setLayoutParams(params2);
//                buttonCluster.addView(courseHeader);


                for(final Course course : currProfessorFinal.courses){
                    Button button =  new Button(getApplicationContext());
                    button.setText(course.getCourseName());
                    LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    params3.gravity = Gravity.CENTER;
                    button.setLayoutParams(params3);


                    button.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View view){
                            if(findViewById(R.id.TA_HOURS_ID) != null){return;}

                            Bitmap taHoursBitmap = course.getTAOfficeHours();
                            ImageView taHoursImageView = new ImageView(getApplicationContext());
                            taHoursImageView.setImageBitmap(taHoursBitmap);
                            taHoursImageView.setMinimumWidth(buttonLayout.getWidth());
                            taHoursImageView.setMinimumHeight(buttonLayout.getHeight());
                            taHoursImageView.setId(R.id.TA_HOURS_ID);
                            if(findViewById(R.id.buttonCluster) != null){buttonLayout.removeView(findViewById(R.id.buttonCluster));}
                            if(findViewById(R.id.BIO_ID) != null){buttonLayout.removeView(findViewById(R.id.BIO_ID));}
                            buttonLayout.addView(taHoursImageView);
                        }
                    });
                    buttonCluster.addView(button);
                }
                buttonCluster.setId(R.id.buttonCluster);
                buttonCluster.setLayoutParams(params);
                if(findViewById(R.id.BIO_ID) != null){buttonLayout.removeView(findViewById(R.id.BIO_ID));}
                if(findViewById(R.id.TA_HOURS_ID) != null){buttonLayout.removeView(findViewById(R.id.TA_HOURS_ID));}
                buttonLayout.addView(buttonCluster);
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                profService.deleteProfessorFromList(currProfessorFinal);
                Toast.makeText(getApplicationContext(), "Delete" + " " + currProfessorFinal.getFName().toString()
                        + " " +currProfessorFinal.getLName().toString() + " " + "Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        TextView officeHoursTextView = (TextView) findViewById(R.id.office_hour_text);
        Calendar calendar = Calendar.getInstance();
        String dayOfWeekNumber = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));


        Day day;
        switch(dayOfWeekNumber){
            case "1":
                day = currProfessor.getScheduleForDay(DayEnum.SUNDAY);
                officeHoursTextView.setText(Day.TimeSlotsToString(day.getTimeSlots()));
                break;
            case "2":
                day = currProfessor.getScheduleForDay(DayEnum.MONDAY);
                officeHoursTextView.setText(Day.TimeSlotsToString(day.getTimeSlots()));
                break;
            case "3":
                day = currProfessor.getScheduleForDay(DayEnum.TUESDAY);
                officeHoursTextView.setText(Day.TimeSlotsToString(day.getTimeSlots()));
                break;
            case "4":
                day = currProfessor.getScheduleForDay(DayEnum.WEDNESDAY);
                officeHoursTextView.setText(Day.TimeSlotsToString(day.getTimeSlots()));
                break;
            case "5":
                day = currProfessor.getScheduleForDay(DayEnum.THURSDAY);
                officeHoursTextView.setText(Day.TimeSlotsToString(day.getTimeSlots()));
                break;
            case "6":
                day = currProfessor.getScheduleForDay(DayEnum.FRIDAY);
                officeHoursTextView.setText(Day.TimeSlotsToString(day.getTimeSlots()));
                break;
            default:
                day = currProfessor.getScheduleForDay(DayEnum.SATURDAY);
                officeHoursTextView.setText(Day.TimeSlotsToString(day.getTimeSlots()));
                break;
        }


//        final LinearLayout buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
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
                    if(findViewById(R.id.TA_HOURS_ID) != null){return;}
                    Bitmap taHoursBitmap = course.getTAOfficeHours();
                    ImageView taHoursImageView = new ImageView(getApplicationContext());
                    taHoursImageView.setImageBitmap(taHoursBitmap);
                    taHoursImageView.setMinimumWidth(buttonLayout.getWidth());
                    taHoursImageView.setMinimumHeight(buttonLayout.getHeight());
                    taHoursImageView.setId(R.id.TA_HOURS_ID);
                    if(findViewById(R.id.buttonCluster) != null){buttonLayout.removeView(findViewById(R.id.buttonCluster));}
                    if(findViewById(R.id.BIO_ID) != null){buttonLayout.removeView(findViewById(R.id.BIO_ID));}
                    buttonLayout.addView(taHoursImageView);
                }
            });

            buttonCluster.addView(button);
        }



    }


    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, ProfActivity.class);
        intent.putExtra(EXTRA_PROF, id);
        return intent;
    }


}
