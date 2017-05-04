package edu.umd.cs.officeours.model;

import android.widget.ImageView;



class Course {
    private String courseName;
    private ImageView taOfficeHours;

    Course(String courseName){
        this.courseName = courseName;
    }

    String getCourseName(){
        return this.courseName;
    }

}
