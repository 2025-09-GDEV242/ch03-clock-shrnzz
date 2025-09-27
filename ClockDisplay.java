
/**
 * The ClockDisplay class implements a digital clock display for a
 * American-style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock internally is 00:00  to 11:59. The clock 
 * needs to keep track of whether it is AM or PM.
 * 
 * The constructor needs to record this information.
 * 
 * The zero parameter constructor should set the meridian to AM. 
 * 
 * The code needs to track when hours rolls over to change from (AM to PM)
 *
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
