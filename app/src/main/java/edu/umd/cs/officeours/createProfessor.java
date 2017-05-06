package edu.umd.cs.officeours;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by yufan on 5/6/2017.
 */
public class createProfessor extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofessor);
        Toast.makeText(getBaseContext(), "test this", Toast.LENGTH_SHORT).show();
    }
}
