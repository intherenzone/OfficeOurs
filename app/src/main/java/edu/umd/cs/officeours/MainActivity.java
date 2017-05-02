package edu.umd.cs.officeours;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button) findViewById(R.id.Available_Knock_Button);
        b2=(Button) findViewById(R.id.Prof_Back_In_Five_Min_Button);
        b3=(Button) findViewById(R.id.Open_Prof_Interface_Button);
        b4=(Button) findViewById(R.id.Away_Button);
        b5=(Button) findViewById(R.id.Do_Not_Disturb_button);
        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Knock on my door",
                        Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "I will be back in few minutes",
                        Toast.LENGTH_SHORT).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ProfActivity.class);
                startActivity(intent);
            }
        });

        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Away",
                        Toast.LENGTH_SHORT).show();
            }
        });

        b5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Do_Not_Disturb",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
