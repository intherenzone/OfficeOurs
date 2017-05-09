package edu.umd.cs.officeours.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;



public class Professor implements Serializable, Comparable<Professor> {
    private Day[] days;
    private String id;
    private String lName;
    private String fName;
    private Bitmap picBitmap;
    private String email;
    private String officeNum;
    private String description;
    private String phoneNumber;
    public List<Course> courses;
    ArrayList<String> feedBacks;

    public Professor() {
        this.id = UUID.randomUUID().toString();
        days = new Day[7];
        int dayIndex = 0;
        for (DayEnum dayEnum : DayEnum.values()) {
            days[dayIndex++] = new Day(dayEnum);
        }
        courses = new LinkedList<>();
        feedBacks = new ArrayList<String>();
    }
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
        feedBacks = new ArrayList<String>();
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setFeedBacks(String feedbacks) {

        feedBacks.add(feedbacks);
    }

    public List<String> getFeedBackList() {
        return feedBacks;
    }
    public void setFName(String fName) {
        this.fName = fName;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setOfficeNum(String officeNum) {
        this.officeNum = officeNum;
    }

    public String getOfficeNum() {return this.officeNum;}

    public String GetOfficeNum() {
        return this.officeNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber; }

    public String getPhoneNumber() { return this.phoneNumber;}

    public String getProfID(){return this.id;}

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
    public int compareTo(Professor professor) {
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
