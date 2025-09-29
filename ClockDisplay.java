
/**
 * The ClockDisplay class implements a digital clock display for a
 * American-style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock internally is 00:00 (midnight) to 23:59 (one minute before 
 * midnight). But the clock should display 12:00AM - 11:59AM and 
 * 12:00PM - 11:59PM.
 * 
 * The code needs to translate the hours to the equivalent 12 hour time block 
 * and must append the appropriate meridian designation (AM or PM).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Sahar Naz
 * @version 2025-09-27
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private String meridian;        // AM or PM
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        hours.setValue(0);
        minutes.setValue(0);
        meridian = "AM";
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     
       For this clock display the internal hours will stay 
       at 0-23 but only the display will change.
       */
    private void updateDisplay()
    {
        int internalHour = hours.getValue();
        int displayHour;
        
        //AM or PM 
        if (internalHour < 12){
            meridian = "AM";
        }else {
            meridian = "PM";
        }
        
        //convert hours into 12 hour format
        
        displayHour = internalHour % 12;
        if (displayHour == 0){
            displayHour =12;
        }
        
        /*used ternary operator for displayString if value is less than 10,
        then display 0 with hour, if false then display hour with empty string
        */
        displayString = (displayHour < 10 ? "0" + displayHour: "" + 
                        displayHour)+ ":" + minutes.getDisplayValue()
                        + " " + meridian;   
        
    }
}
