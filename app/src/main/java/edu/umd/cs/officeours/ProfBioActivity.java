package edu.umd.cs.officeours;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.umd.cs.officeours.model.Professor;
import edu.umd.cs.officeours.services.ProfService;

/**
 * Created by Dtrevino on 5/9/2017.
 */

public class ProfBioActivity extends AppCompatActivity {
    //THIS NEEDS TO BE THE SAME IN ProfActivity
    private static final String EXTRA_PROF_ID = "PROF_ID";
    private ProfService profService;
    Professor currProfessor;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bio);
        //USE THIS PROF ID TO GET PROFESSOR BY ID AND UPDATE THE FIELDS THAT ARE
        //ALREADY IN THE LAYOUT.
        String profID = getIntent().getStringExtra(EXTRA_PROF_ID);

        profService = DependencyFactory.getProfService(getApplicationContext());
        final List<Professor> profList = profService.getAllProfessors();

        for(Professor p : profList){
            if(p.getProfID().compareTo(profID) == 0){
                currProfessor = p;
                break;
            }
        }

        Bitmap picBitmap = currProfessor.getPicBitmap();
        ImageView picImageView = (ImageView)findViewById(R.id.prof_bio_pic);
        picImageView.setImageBitmap(picBitmap);

        TextView profName = (TextView)findViewById(R.id.prof_name);
        profName.setText(currProfessor.getFName() + " " + currProfessor.getLName());

        TextView profNum = (TextView)findViewById(R.id.prof_num);
        profNum.setText(currProfessor.GetOfficeNum());

        TextView profRoomNum = (TextView)findViewById(R.id.prof_office_num);
        profRoomNum.setText(currProfessor.getOfficeNum());

        TextView profEmail = (TextView)findViewById(R.id.prof_email);
        profEmail.setText(currProfessor.getEmail());

        TextView profDescription = (TextView)findViewById(R.id.prof_description);
        profDescription.setText(currProfessor.getDescription());

        Button homeButton = (Button) findViewById(R.id.bio_home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
