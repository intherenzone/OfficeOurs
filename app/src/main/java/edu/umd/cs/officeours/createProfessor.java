package edu.umd.cs.officeours;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import edu.umd.cs.officeours.model.Course;
import edu.umd.cs.officeours.model.DayEnum;
import edu.umd.cs.officeours.model.Professor;
import edu.umd.cs.officeours.services.ProfService;

import static edu.umd.cs.officeours.R.id.cameraID;

//need to do professor picture and add course
public class createProfessor extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_ADD_COURSE = 2;
    private static final int REQUEST_OFFICE_HOUR_MONDAY = 2;
    private static final int REQUEST_OFFICE_HOUR_TUESDAY = 3;
    private static final int REQUEST_OFFICE_HOUR_WEDNESDAY = 4;
    private static final int REQUEST_OFFICE_HOUR_THURSDAY = 5;
    private static final int REQUEST_OFFICE_HOUR_FRIDAY = 6;
    private static final int REQUEST_OFFICE_HOUR_SATURDAY = 7;
    private static final int REQUEST_OFFICE_HOUR_SUNDAY = 8;
    private Bitmap imageBitmap;
    private Uri imageUri;
    private ImageView photoView;
    private EditText professorName;
    private EditText professorEmail;
    private EditText professorOfficeNumber;
    private EditText professorDescription;
    private Button mondayButton, tuesdayButton, wednesdayButton, thursdayButton,
            fridayButton, saturdayButton, sundayButton, saveButton, cancelButton, addCourseButton;
    private ImageButton cameraButton;
    public List<Course> courses;
    private Professor professor;
    Intent createHourIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courses = new LinkedList<>();
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

        mondayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                createHourIntent = new Intent(getBaseContext(), setProHour.class);
                                                startActivityForResult(createHourIntent, REQUEST_OFFICE_HOUR_MONDAY);
                                            }
                                        }
        );

        tuesdayButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 createHourIntent = new Intent(getBaseContext(), setProHour.class);
                                                 startActivityForResult(createHourIntent, REQUEST_OFFICE_HOUR_TUESDAY);
                                             }
                                         }
        );

        wednesdayButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {

                                               }
                                           }
        );

        thursdayButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {

                                              }
                                          }
        );

        fridayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        }
        );

        saturdayButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {

                                              }
                                          }
        );

        sundayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

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
                                              professor = new Professor(firstName, lastName);
                                              professor.setDescription(description);
                                              professor.setEmail(email);
                                              professor.setOfficeNum(office);
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
            Toast.makeText(getApplicationContext(), "we made it to monday" + toFromTime, Toast.LENGTH_SHORT).show();

        } else if (requestCode == REQUEST_OFFICE_HOUR_TUESDAY && resultCode == RESULT_OK) {
            String toFromTime = data.getStringExtra("MESSAGE");
            Toast.makeText(getApplicationContext(), "we made it to TUESDAY" + toFromTime, Toast.LENGTH_SHORT).show();

        }
        else if (requestCode == REQUEST_ADD_COURSE && resultCode == RESULT_OK){
            Course course = AddCourseActivity.getCourseCreated(data);
            courses.add(course);
        }
    }

    public int[] parseStringTointFunction(String toFromTime) {
        String[] parts = toFromTime.split(",");
        String[] parts1 = parts[0].split(":");
        String[] parts2 = parts[1].split(":");
        String[] returnArray = new String[parts1.length + parts2.length];
        for (int i = 0; i < returnArray.length; i++) {

        }
        return null;
    }

}


