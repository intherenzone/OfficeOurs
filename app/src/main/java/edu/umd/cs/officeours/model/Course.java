package edu.umd.cs.officeours.model;
import android.graphics.Bitmap;




public class Course {
    private String courseName;
    private Bitmap taOfficeHours;

    public Course(String courseName){
        this.courseName = courseName;
    }

    public String getCourseName(){
        return this.courseName;
    }

    public Bitmap getTAOfficeHours(){
        return taOfficeHours;
    }

    public void setTAOfficeHours(Bitmap taOfficeHours){
        this.taOfficeHours = taOfficeHours;
    }

}
