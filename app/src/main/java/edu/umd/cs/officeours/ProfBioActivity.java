package edu.umd.cs.officeours;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Dtrevino on 5/9/2017.
 */

public class ProfBioActivity extends AppCompatActivity {
    //THIS NEEDS TO BE THE SAME IN ProfActivity
    private static final String EXTRA_PROF_ID = "PROF_ID";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bio);
        //USE THIS PROF ID TO GET PROFESSOR BY ID AND UPDATE THE FIELDS THAT ARE
        //ALREADY IN THE LAYOUT.
        String profID = getIntent().getStringExtra(EXTRA_PROF_ID);





        Button homeButton = (Button) findViewById(R.id.bio_home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
