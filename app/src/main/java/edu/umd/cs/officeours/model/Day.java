package edu.umd.cs.officeours.model;



 class Day {
    private int startTime;
    private int endTime;
    private DayEnum dayEnum;

    Day(DayEnum dayEnum){
        this.dayEnum = dayEnum;
    }

    boolean setTimeSlot(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
        return true;
    }

    DayEnum getDayEnum(){
        return this.dayEnum;
    }


}
