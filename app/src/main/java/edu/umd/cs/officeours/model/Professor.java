package edu.umd.cs.officeours.model;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;



public class Professor implements Serializable, Comparable<Professor> {
    private Day[] days;
    private String id;
    private String lName;
    private String fName;
    private Bitmap picBitmap;
    private List<Course> courses;

    //This can handle professors with identical names. Might be a problem if searching by name.
    public Professor(String fName,String lName){
        //Storing every name in upper case for easy comparison.
        this.fName = fName.toUpperCase();
        this.lName = lName.toUpperCase();
        this.id = UUID.randomUUID().toString();
        days = new Day[7];
        int dayIndex = 0;
        for(DayEnum dayEnum : DayEnum.values()){
            days[dayIndex++] = new Day(dayEnum);
        }
        courses = new LinkedList<>();
    }

    public void setPicBitmap(Bitmap bitmapImage){
        this.picBitmap = bitmapImage;
    }

    public Bitmap getPicBitmap(){
        return this.picBitmap;
    }


    public boolean setScheduleForDay(DayEnum dayEnum, int startTime,int endTime){
        //Access day in array.
        int i;
        for(i = 0; i < days.length; i++){
            if(days[i].getDayEnum() == dayEnum){
                break;
            }
        }

        return (days[i].setTimeSlot(startTime, endTime));

    }

    public Day getScheduleForDay(DayEnum dayEnum){
        int i;
        for(i = 0; i < days.length; i++){
            if(days[i].getDayEnum() == dayEnum){
                break;
            }
        }
        return days[i];
    }

    public boolean setCourse(Course newCourse){

        for(Course course : courses){
            if(newCourse.getCourseName().compareTo(course.getCourseName()) == 0){
                return false;
            }
        }
        courses.add(newCourse);
        return true;
    }

    //CAN RETURN NULL
    public Course getCourse(String courseName){

        for(Course course : courses){
            if(courseName.compareTo(course.getCourseName())== 0){
                return course;
            }
        }
        return null;
    }
    //CAN RETURN NULL
    public String getFName(){
        return this.fName;
    }
    public String getLName(){
        return this.lName;
    }

    @Override
    public int compareTo(@NonNull Professor professor){
        if(this.getLName().compareTo(professor.getLName()) > 0){
            return 1;
        }else if(this.getLName().compareTo(professor.getLName()) < 0){
            return -1;
        }else{
            if(this.getFName().compareTo(professor.getFName()) > 0){
                return 1;
            }else if(this.getFName().compareTo(professor.getFName()) < 0) {
                return -1;
            }
            return 0;
        }
    }


}
