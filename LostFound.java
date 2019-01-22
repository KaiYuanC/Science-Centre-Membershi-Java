import java.io.*;
import java.util.*;
/**
 * A lostFound object is an lost or found item with a date, description
 * contact name, contact number, identification of whether it is a lost
 * item or a found item, and the location which it was lost of found.
 *
 * @author KaiYuna
 * @version 2018-03-06
 */
public class LostFound
{
    //instance fields declaration
    
    private int day;
    private int month;
    private int year;
    private String description;
    private String firstName;
    private String lastName;
    private int phone;
    private String location;
    private boolean lost; 
    private boolean found;
    private int days;
    //class fields decalrations
    private static Calendar cal = Calendar.getInstance();
    
    //contant declarations
    private static final int DECEMBER = 12;
    private static final int MAX_DAY_THIRTY = 30;
    private static final int MAX_DAY_THIRTY_ONE = 31;
    private static final int MAX_DAY_TWENTY_EIGHT = 28;
    
    /**
     * Constructs a lostFound profile with a date, description
     * contact name, contact number, identification of whether it is a lost
     * item or a found item, and the location which it was lost of found.
     * 
     * @param d the date that the item is reported.
     * @param m the month that the item is reported.
     * @param y the year that the item is reported.
     * @param descrip a general description or note of the item
     * @param first the first name of the contact
     * @param last the last name of the contact
     * @param loc the location of where it is found or lost
     * @param l state whether the object is lost
     * @param f state whether the object is found. 
     * @param phonenum the phone number of the contact
     */
    public LostFound(int d, int m, int y, String descrip, String first,
    String last, String loc, boolean l, boolean f, int phonenum)
    {
        //if the object originally is lost and found, then lostFound profile will not be created
        day = d;
        month = m;
        year = y;
        description = descrip;
        firstName = first;
        lastName = last;
        location = loc;
        lost = l;
        found = f;
        phone = phonenum;
        days = 1;
    }
    
    /*
     * Accesors 
     */
    
    /**
     * Returns the day that the item is lost of found
     * 
     * @return the day that the item is lost of found
     */
    public int getDay()
    {
        return day;
    }
    
    /**
     * Returns the month that the item is lost of found
     * 
     * @return the month that the item is lost of found
     */
    public int getMonth()
    {
        return month;
    }
    
    /**
     * Returns the year that the item is lost of found
     * 
     * @return the year that the item is lost of found
     */
    public int getYear()
    {
        return year;
    }
    
    /**
     * Returns the number of days that the item is left in lost and found
     * 
     * @return number of days that the item is left in lost and found
     */
    public int getDays()
    {
        int days;
        cal = Calendar.getInstance();
        int calYear = cal.get(Calendar.YEAR);
        int calMonth = cal.get(Calendar.MONTH) + 1;
        int calDay = cal.get(Calendar.DAY_OF_MONTH);
        
        if(month != calMonth)
        {
            int maxDayThatMonth;
            switch(month) 
            {
                case 1:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 2:
                    maxDayThatMonth = MAX_DAY_TWENTY_EIGHT;
                    break;
                case 3:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 4:
                    maxDayThatMonth = MAX_DAY_THIRTY;
                    break;
                case 5:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 6:
                    maxDayThatMonth = MAX_DAY_THIRTY;
                    break;
                case 7:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 8:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 9:
                    maxDayThatMonth = MAX_DAY_THIRTY;
                    break;
                case 10:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 11:
                    maxDayThatMonth = MAX_DAY_THIRTY;
                    break;
                case 12:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                default: 
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
            }
            days = calDay + (maxDayThatMonth - day);
        }
        else
        {
            days = calDay - day;
        }
        
        return days;
    }
    
    /**
     * Returns the date of theitem recorded
     * 
     * @return the date of theitem recorded
     */
    public String getDate()
    {
        String date = year + "/" + month + "/" + day;
        return date;
    }
    
    /**
     * Returns the description of the lost of found object
     * 
     * @return the description of the lost of found object
     */
    public String getDescrip()
    {
        return description;
    }
    
    /**
     * Returns the first name of the contact
     * 
     * @return the first name of the contact
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * Returns the last name of the contact
     * 
     * @return the last name of the contact
     */
    public String getlastName()
    {
        return lastName;
    }
    
    /**
     * return the whole name of the contact
     * 
     * @return the whole name of the contact
     */
    public String getWholeName()
    {
        String name = firstName + " " + lastName;
        return name;
    }
    
    /**
     * Returns the location of the object when it is found/lst
     * 
     * @return the location of the object when it is found/lst
     */
    public String getLoc()
    {
        return location;
    }
    
    /**
     * Returns the phone of the object when it is found/lst
     * 
     * @return the phone of the object when it is found/lst
     */
    public int getPhone()
    {
        return phone;
    }
    
    /**
     * Returns whether the item is lost or found as a boolean statement
     * 
     * @returnwhether the item is lost or found
     */
    public boolean isLost()      
    {
        return lost;
    }
    
    /**
     * Return yes of no to represent whether the item is lost
     * 
     * @return yes of no to represent whether the item is lost
     */
    public String strLost()
    {
        String str;
        if(lost)
        {
            str = "Yes";
        }
        else
        {
            str = "NO";
        }
        return str;
    }
    
    /**
     * Return yes of no to represent whether the item is found
     * 
     * @return yes of no to represent whether the item is found
     */
    public String strFound()
    {
        String str;
        if(found)
        {
            str = "Yes";
        }
        else
        {
            str = "NO";
        }
        return str;
    }
    
    /**
     * Returns whether the item is lost or found as a boolean statement
     * 
     * @returnwhether the item is lost or found
     */
    public boolean isFound()      
    {
        return found;
    }
    
    /*
     * Mutators
     */
    /*
     * Checks if the object is both lost and found
     * check if the day is over thirty days.
     * 
     */
    
    /**
     * Checks if the item is both lost and found by returning 
     * a boolean expression 
     * 
     * @return if the item is both lost and found
     */
    public boolean isPaired()
    {
        if(lost == true && found == true)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    /**
     * Checks if the item is over 30 days by returning a boolean
     * expression once the method is called
     * 
     * @param year the year that the item is recorded
     * @param month the month that the item is recorded
     * @param day the day that the item is recorded
     * @return if the item is over 30 days. 
     */
    public boolean isOver(int year, int month, int day)
    {
        boolean isO;
        cal = Calendar.getInstance();
        int calYear = cal.get(Calendar.YEAR);
        int calMonth = cal.get(Calendar.MONTH) + 1;
        int calDay = cal.get(Calendar.DAY_OF_MONTH);
        if(year > 0 && year + 1 < calYear)
        {
            isO = true;
        }
        else if(year > 0 && (year + 1) == calYear && month != DECEMBER) 
        {
            isO = true;
        }
        else if(year > 0 && (year + 1) == calYear && month == DECEMBER) 
        {
            if(calMonth == 1)
            {
                isO = false;
            }
            else
            {
                isO = true;
            }
        }
        else if(year > 0 && year== calYear && (month + 1) == calMonth && day <= calDay)
        {
            isO = true;
        }
        else
        {
            isO = false;
        }
        return isO;
    }
}
