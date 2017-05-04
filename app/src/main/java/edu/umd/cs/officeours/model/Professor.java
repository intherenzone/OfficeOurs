package edu.umd.cs.officeours.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;



public class Professor implements Serializable, Comparable<Professor> {
    private Day[] days;
    private String id;
    private String name;
    private List<Course> courses;

    //This can handle professors with identical names. Might be a problem if searching by name.
    public Professor(String name){
        //Storing every name in upper case for easy comparison.
        this.name = name.toUpperCase();
        this.id = UUID.randomUUID().toString();
        days = new Day[7];
        int dayIndex = 0;
        for(DayEnum dayEnum : DayEnum.values()){
            days[dayIndex++] = new Day(dayEnum);
        }
        courses = new LinkedList<>();
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

    public boolean setCourse(String courseName){

        for(Course course : courses){
            if(courseName.compareTo(course.getCourseName()) == 0){
                return false;
            }
        }

        courses.add(new Course(courseName));
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
    public String getName(){
        return this.name;
    }
    @Override
    public int compareTo(@NonNull Professor professor){
        if(this.getName().compareTo(professor.getName()) > 0){
            return 1;
        }else if(this.getName().compareTo(professor.getName()) < 0){
            return -1;
        }else{
            return 0;
        }
    }
}
