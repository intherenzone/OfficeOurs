package edu.umd.cs.officeours;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import edu.umd.cs.officeours.model.DayEnum;
import edu.umd.cs.officeours.model.Course;
import edu.umd.cs.officeours.model.DayEnum;
import edu.umd.cs.officeours.model.Professor;
import edu.umd.cs.officeours.services.ProfService;

import static edu.umd.cs.officeours.R.id.cameraID;
import static edu.umd.cs.officeours.R.id.end;

//need to do professor picture and add course
public class createProfessor extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_OFFICE_HOUR_MONDAY = 2;
    private static final int REQUEST_OFFICE_HOUR_TUESDAY = 3;
    private static final int REQUEST_OFFICE_HOUR_WEDNESDAY = 4;
    private static final int REQUEST_OFFICE_HOUR_THURSDAY = 5;
    private static final int REQUEST_OFFICE_HOUR_FRIDAY = 6;
    private static final int REQUEST_OFFICE_HOUR_SATURDAY = 7;
    private static final int REQUEST_OFFICE_HOUR_SUNDAY = 8;
    private static final int REQUEST_ADD_COURSE = 9;
    private Bitmap imageBitmap;
    private Uri imageUri;
    private ImageView photoView;
    private EditText professorName;
    private EditText professorEmail;
    private EditText professorOfficeNumber;
    private EditText professorDescription;
    private TextView courseListTextView;
    private Button mondayButton, tuesdayButton, wednesdayButton, thursdayButton,
            fridayButton, saturdayButton, sundayButton, saveButton, cancelButton, addCourseButton;
    private ImageButton cameraButton;
    public List<Course> courses;
    private Professor professor;
    Intent createHourIntent;
    private int startTime, endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courses = new LinkedList<>();
        professor = new Professor();
        setContentView(R.layout.activity_createprofessor);
        ProfService listOfProfessors = DependencyFactory.getProfService(getApplication());
        photoView = (ImageView) findViewById(R.id.professorPhotoID);
        cameraButton = (ImageButton) findViewById(cameraID);
        mondayButton = (Button) findViewById(R.id.monday_Button);
        tuesdayButton = (Button) findViewById(R.id.tuesday_Button);
        wednesdayButton = (Button) findViewById(R.id.wednesday_Button);
        thursdayButton = (Button) findViewById(R.id.thursday_Button);
        fridayButton = (Button) findViewById(R.id.friday_Button);
        saturdayButton = (Button) findViewById(R.id.saturday_Button);
        sundayButton = (Button) findViewById(R.id.sunday_Button);
        addCourseButton = (Button) findViewById(R.id.add_course_button);
        saveButton = (Button) findViewById(R.id.save_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        professorName = (EditText) findViewById(R.id.professorNameID);
        professorEmail = (EditText) findViewById(R.id.professorEmailID);
        professorOfficeNumber = (EditText) findViewById(R.id.professorOfficeNumberID);
        professorDescription = (EditText) findViewById(R.id.professorDescriptionID);
        courseListTextView = (TextView) findViewById(R.id.list_of_courses);

        mondayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                createHourIntent = new Intent(getBaseContext(), setProHour.class);
                                                startActivityForResult(createHourIntent, REQUEST_OFFICE_HOUR_MONDAY);
                                                professor.setScheduleForDay(DayEnum.MONDAY, startTime, endTime);
                                            }
                                        }
        );

        tuesdayButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 createHourIntent = new Intent(getBaseContext(), setProHour.class);
                                                 startActivityForResult(createHourIntent, REQUEST_OFFICE_HOUR_TUESDAY);
                                                 professor.setScheduleForDay(DayEnum.TUESDAY, startTime, endTime);
                                             }
                                         }
        );

        wednesdayButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   createHourIntent = new Intent(getBaseContext(), setProHour.class);
                                                   startActivityForResult(createHourIntent, REQUEST_OFFICE_HOUR_WEDNESDAY);
                                                   professor.setScheduleForDay(DayEnum.WEDNESDAY, startTime, endTime);
                                               }
                                           }
        );

        thursdayButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  createHourIntent = new Intent(getBaseContext(), setProHour.class);
                                                  startActivityForResult(createHourIntent, REQUEST_OFFICE_HOUR_THURSDAY);
                                                  professor.setScheduleForDay(DayEnum.THURSDAY, startTime, endTime);
                                              }
                                          }
        );

        fridayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                createHourIntent = new Intent(getBaseContext(), setProHour.class);
                                                startActivityForResult(createHourIntent, REQUEST_OFFICE_HOUR_FRIDAY);
                                                professor.setScheduleForDay(DayEnum.FRIDAY, startTime, endTime);
                                            }
                                        }
        );

        saturdayButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  createHourIntent = new Intent(getBaseContext(), setProHour.class);
                                                  startActivityForResult(createHourIntent, REQUEST_OFFICE_HOUR_SATURDAY);
                                                  professor.setScheduleForDay(DayEnum.SATURDAY, startTime, endTime);
                                              }
                                          }
        );

        sundayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                createHourIntent = new Intent(getBaseContext(), setProHour.class);
                                                startActivityForResult(createHourIntent, REQUEST_OFFICE_HOUR_SUNDAY);
                                                professor.setScheduleForDay(DayEnum.SUNDAY, startTime, endTime);
                                            }
                                        }
        );

        cameraButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    // Do what you want
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        File file = getPhotoFile();
                        if(file != null){
                            imageUri = Uri.fromFile(file);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                            return true;
                        } else {
                            cameraButton.setEnabled(false);
                        }
                    } else {
                        cameraButton.setEnabled(false);
                    }
                }
                return false;
            }
        });

        addCourseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCourseActivity.class);
                startActivityForResult(intent, REQUEST_ADD_COURSE);
            }
        });



        //end ,when saved it clicked
        saveButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              String name = professorName.getText().toString();
                                              String email = professorEmail.getText().toString();
                                              String office = professorOfficeNumber.getText().toString();
                                              String description = professorDescription.getText().toString();
                                              int i = name.indexOf(" "); // 4
                                              String firstName = name.substring(0, i); // from 0 to
                                              String lastName = name.substring(i + 1);
                                              professor.setlName(lastName);
                                              professor.setFName(firstName);
                                              professor.setDescription(description);
                                              professor.setEmail(email);
                                              professor.setOfficeNum(office);
                                              for(Course course : courses){
                                                  professor.setCourse(course);
                                              }
                                              // Image Store
                                              if (imageUri == null || BitmapFactory.decodeFile(imageUri.getPath()) == null){
                                                  professor.setPicBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic));
                                              }
                                              else{
                                                  professor.setPicBitmap(BitmapFactory.decodeFile(imageUri.getPath()));
                                              }


                                              finish();
                                          }
                                      }
        );

        cancelButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                finish();
                                            }
                                        }
        );
    }
    private File getPhotoFile(){
        File externalPhotoDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if(externalPhotoDir==null){
            return null;
        }

        return new File(externalPhotoDir, "IMG_" + System.currentTimeMillis() + ".jpg");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (imageUri == null){
                photoView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.default_profile_pic));

            }
            else{
                imageBitmap = BitmapFactory.decodeFile(imageUri.getPath());
                photoView.setImageBitmap(imageBitmap);
            }
        } else if (requestCode == REQUEST_OFFICE_HOUR_MONDAY && resultCode == RESULT_OK) {
            String toFromTime = data.getStringExtra("MESSAGE");
            int[] intOfTime = parseStringTointFunction(toFromTime);
            startTime = intOfTime[0];
            endTime = intOfTime[1];
        } else if (requestCode == REQUEST_OFFICE_HOUR_TUESDAY && resultCode == RESULT_OK) {
            String toFromTime = data.getStringExtra("MESSAGE");
            int[] intOfTime = parseStringTointFunction(toFromTime);
            startTime = intOfTime[0];
            endTime = intOfTime[1];
        } else if (requestCode == REQUEST_OFFICE_HOUR_WEDNESDAY && resultCode == RESULT_OK) {
            String toFromTime = data.getStringExtra("MESSAGE");
            int[] intOfTime = parseStringTointFunction(toFromTime);
            startTime = intOfTime[0];
            endTime = intOfTime[1];
        } else if (requestCode == REQUEST_OFFICE_HOUR_THURSDAY && resultCode == RESULT_OK) {
            String toFromTime = data.getStringExtra("MESSAGE");
            int[] intOfTime = parseStringTointFunction(toFromTime);
            startTime = intOfTime[0];
            endTime = intOfTime[1];
        } else if (requestCode == REQUEST_OFFICE_HOUR_FRIDAY && resultCode == RESULT_OK) {
            String toFromTime = data.getStringExtra("MESSAGE");
            int[] intOfTime = parseStringTointFunction(toFromTime);
            startTime = intOfTime[0];
            endTime = intOfTime[1];
        } else if (requestCode == REQUEST_OFFICE_HOUR_SATURDAY && resultCode == RESULT_OK) {
            String toFromTime = data.getStringExtra("MESSAGE");
            int[] intOfTime = parseStringTointFunction(toFromTime);
            startTime = intOfTime[0];
            endTime = intOfTime[1];
        } else if (requestCode == REQUEST_OFFICE_HOUR_SUNDAY && resultCode == RESULT_OK) {
            String toFromTime = data.getStringExtra("MESSAGE");
            int[] intOfTime = parseStringTointFunction(toFromTime);
            startTime = intOfTime[0];
            endTime = intOfTime[1];
        } else if (requestCode == REQUEST_ADD_COURSE && resultCode == RESULT_OK){
            Course course = AddCourseActivity.getCourseCreated(data);
            courses.add(course);
            if (courseListTextView.getText() == null || courseListTextView.getText().toString().length() == 0){
                courseListTextView.append(course.getCourseName());
            }
            else{
                courseListTextView.append(System.getProperty("line.separator") + course.getCourseName());
            }

        }
    }

    public int[] parseStringTointFunction(String toFromTime) {
        String[] parts = toFromTime.split(",");
        String[] parts1 = parts[0].split(":");
        String[] parts2 = parts[1].split(":");
        int[] returnArray = new int[2];
        int temp = Integer.parseInt(parts1[1]);
        if (temp <= 10) {
            parts1[1] = parts1[1];
            parts1[0] += parts1[1];
        } else {
            parts1[0] += parts1[1];
        }

        temp = Integer.parseInt(parts2[1]);
        if (temp <= 10) {
            parts2[1] = parts2[1];
            parts2[0] += parts2[1];
        } else {
            parts2[0] += parts2[1];
        }
        returnArray[0] = Integer.parseInt(parts1[0]);
        returnArray[1] = Integer.parseInt(parts2[0]);
        return returnArray;
    }

}


