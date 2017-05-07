package edu.umd.cs.officeours;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class logIn extends AppCompatActivity {
    Button login, cancel;
    EditText user, pass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_log_in);
        login = (Button) findViewById(R.id.loginButton);
        cancel = (Button) findViewById(R.id.cancelButton);
        user = (EditText) findViewById(R.id.userName);
        pass = (EditText) findViewById(R.id.passWord);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().toString().equals("admin") && pass.getText().toString().equals("1")) {
                    Toast.makeText(getApplicationContext(), "connecting...", Toast.LENGTH_SHORT).show();
                    Intent createStoryIntent = new Intent(getBaseContext(), createProfessor.class);
                    finish();
                    startActivity(createStoryIntent);

                } else {
                    Toast.makeText(getApplicationContext(), "wrong information", Toast.LENGTH_SHORT).show();
                }
            }
        });
//comment
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
