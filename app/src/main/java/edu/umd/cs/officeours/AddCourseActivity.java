package edu.umd.cs.officeours;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

import edu.umd.cs.officeours.model.Course;

public class AddCourseActivity  extends AppCompatActivity {
    private final static String EXTRA_COURSE_CREATED_NAME = "NAME";
    private final static String EXTRA_COURSE_CREATED_BITMAP = "BITMAP";
    private static final int REQUEST_PHOTO = 1;
    private Course course;

    private EditText course_name;
    private ImageView imageView;
    private ImageButton imageButton;
    private File photoFile;
    private Bitmap bitmap;

    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        bitmap = null;

        imageView = (ImageView)findViewById(R.id.photo);
        imageView.setBackgroundColor(Color.rgb(128,128,128));
        imageButton = (ImageButton)findViewById(R.id.camera);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null){
                    photoFile = getPhotoFile();

                    if (photoFile == null){
                        imageButton.setEnabled(false);
                    }
                    else{
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile)); //specify file location of photo
                        startActivityForResult(intent, REQUEST_PHOTO);
                    }
                }
                else { imageButton.setEnabled(false); }
            }
        });

        saveButton = (Button)findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course_name = (EditText)findViewById(R.id.course_name);
//                course = new Course(course_name.getText().toString());
//                course.setTAOfficeHours(bitmap);
                Intent data = new Intent();
                int height = (int) getApplicationContext().getResources().getDimension(R.dimen.ta_hours_pic_height);
                int width = (int) getApplicationContext().getResources().getDimension(R.dimen.ta_hours_pic_width);

                data.putExtra(EXTRA_COURSE_CREATED_NAME, course_name.getText().toString());
                data.putExtra(EXTRA_COURSE_CREATED_BITMAP, Bitmap.createScaledBitmap(bitmap,width,height,true));
                setResult(RESULT_OK, data);
                finish();
            }
        });

        cancelButton = (Button)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK){
            if (photoFile != null){
                bitmap = BitmapFactory.decodeFile(photoFile.getPath());
                imageView.setImageBitmap(bitmap);
            }

        }
    }

    private File getPhotoFile(){
        File externalPhotoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(externalPhotoDir == null){
            return null;
        }
        return new File(externalPhotoDir,"IMG_"+ System.currentTimeMillis()+".jpg");
    }

    public static Course getCourseCreated(Intent data){
        Bitmap bitmap = data.getParcelableExtra(EXTRA_COURSE_CREATED_BITMAP);
        String name = data.getStringExtra(EXTRA_COURSE_CREATED_NAME);
        Course course = new Course(name);
        course.setTAOfficeHours(bitmap);
        return course;
    }
}
