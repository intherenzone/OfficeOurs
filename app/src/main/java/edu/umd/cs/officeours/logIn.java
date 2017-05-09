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
    private static final int REQUEST_CREATE_PROFESSOR = 9;
    private static final String DELETE_PROF = "DELETE";
    private static final String DELETE_OK = "delete_ok";
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
                    if (getIntent().getStringExtra(DELETE_PROF) != null && getIntent().getStringExtra(DELETE_PROF).equals(DELETE_OK)){
                        setResult(RESULT_OK);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "connecting...", Toast.LENGTH_SHORT).show();
                        Intent createProfessorIntent = new Intent(getBaseContext(), createProfessor.class);
                        startActivityForResult(createProfessorIntent, REQUEST_CREATE_PROFESSOR);
                    }




                } else {
                    Toast.makeText(getApplicationContext(), "wrong information", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CREATE_PROFESSOR && resultCode == RESULT_OK) {
            setResult(RESULT_OK, data);
            finish();
        }

    }
}
