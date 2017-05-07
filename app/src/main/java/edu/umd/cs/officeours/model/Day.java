package edu.umd.cs.officeours.model;




import java.util.LinkedList;

public class Day {
    private static final String NO_HOURS_MESSAGE = "This professor is unavailable today.";

    private LinkedList<TimeSlot> timeSlots;
    private DayEnum dayEnum;

    //ALL TIMESLOTS MUST NOT HAVE LEADING 0'S!! IT mess UP THE TIME CONVERSION.
    public class TimeSlot{
        public int startTime;
        public int endTime;
        public TimeSlot(int startTime, int endTime){
            this.startTime = startTime;
            this.endTime = endTime;
            //test this
        }
    }


    public LinkedList<TimeSlot> getTimeSlots(){return this.timeSlots;}

    public static String TimeSlotsToString(LinkedList<TimeSlot> timeSlots){
        if(timeSlots.size() == 0){return NO_HOURS_MESSAGE;}
        else{
            StringBuffer sBuff = new StringBuffer();
            sBuff.append("Today's hours: ");

            for(TimeSlot timeSlot : timeSlots){
                sBuff.append(milToStandard(timeSlot.startTime) + "-" + milToStandard(timeSlot.endTime) + "  ");
            }
            return sBuff.toString();
        }

    }

    //LEADING 0'S MEAN OCTAL (NOT DECIMAL BASE)
    public static String milToStandard(int milTime){

        String output;
        int tempHours = milTime / 100;
        String ampm;
        if(tempHours - 12 < 0){
            if(tempHours == 0){tempHours = 12;}
            ampm = "AM";
        }
        else{
            tempHours = tempHours - 12;
            if(tempHours == 0){tempHours = 12;}
            ampm = "PM";
        }
        output = tempHours+":";
        int tempMins = milTime % 100;
        if(tempMins < 10){ output = output + "0" + tempMins + ampm;}
        else{ output = output + tempMins + ampm;}

        return output;

    }




    public Day(DayEnum dayEnum) {
        this.timeSlots = new LinkedList<TimeSlot>();
        this.dayEnum = dayEnum;
    }

    public boolean setTimeSlot(int startTime, int endTime) {
        this.timeSlots.add(new TimeSlot(startTime, endTime));
        return true;
    }

    public DayEnum getDayEnum() {
        return this.dayEnum;
    }


}
