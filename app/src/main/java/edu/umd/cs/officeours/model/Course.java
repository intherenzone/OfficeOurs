package edu.umd.cs.officeours.model;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class Course implements Serializable, Parcelable {
    private String courseName;
    private Bitmap taOfficeHours;
    private int mData;

    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>(){
        public Course createFromParcel(Parcel in){
            return new Course(in);
        }
        public Course[] newArray(int size){
            return new Course[size];
        }
    };

    private Course(Parcel in){
        mData = in.readInt();
    }
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
