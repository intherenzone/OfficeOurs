package edu.umd.cs.officeours;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.umd.cs.officeours.model.DayEnum;
import edu.umd.cs.officeours.model.Professor;
import edu.umd.cs.officeours.services.ProfService;

//need to do professor picture and add course
public class createProfessor extends AppCompatActivity {

    private EditText professorName;
    private EditText professorEmail;
    private EditText professorOfficeNumber;
    private EditText professorDescription;
    private Button mondayButton, tuesdayButton, wednesdayButton, thursdayButton,
            fridayButton, saturdayButton, sundayButton, saveButton, cancelButton;
    private Professor professor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofessor);
        ProfService listOfProfessors = DependencyFactory.getProfService(getApplication());
        mondayButton = (Button) findViewById(R.id.monday_Button);
        tuesdayButton = (Button) findViewById(R.id.tuesday_Button);
        wednesdayButton = (Button) findViewById(R.id.wednesday_Button);
        thursdayButton = (Button) findViewById(R.id.thursday_Button);
        fridayButton = (Button) findViewById(R.id.friday_Button);
        saturdayButton = (Button) findViewById(R.id.saturday_Button);
        sundayButton = (Button) findViewById(R.id.sunday_Button);
        saveButton = (Button) findViewById(R.id.save_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        professorName = (EditText) findViewById(R.id.professorNameID);
        professorEmail = (EditText) findViewById(R.id.professorEmailID);
        professorOfficeNumber = (EditText) findViewById(R.id.professorOfficeNumberID);
        professorDescription = (EditText) findViewById(R.id.professorDescriptionID);

        mondayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent createHourIntent = new Intent(getBaseContext(), setProHour.class);
                                                createHourIntent.putExtra("day", DayEnum.MONDAY);
                                                startActivityForResult(createHourIntent, 2);
                                            }
                                        }
        );

        tuesdayButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 2) {
//            String message=data.getStringExtra("MESSAGE");
//            textView1.setText(message);
        }
    }
}
