
/**
 * A program has a time, location, a group size, a contact person, and a 
 * contact number. 
 *
 * @author KaiYuan 
 * @version 2018-03-06
 */
public class Program
{
    //instance fields declaration
    private int startTime; 
    private int endTime; 
    private String location; 
    private int group; 
    private String firstName;
    private String lastName;
    private int phone; 
    
    /**
     * Constract a program with a start time, end time, location, group
     * size, contact name and phone number
     * 
     * @param start the program's start time
     * @param end the program's end time
     * @param loc the program's location
     * @param gro the size of the group participating the program
     * @param first the program's contact's first name
     * @param last the program's contact's last name
     * @param phonenum the program's contact's phone number
     */
    public Program(int start, int end, String loc, int gro, String first, 
    String last, int phonenum)
    {
        //if start time is before 0800 or after 2000, then it is invalid
        startTime = start;
        //if end time is before 0800 or after 2000, then it is invalid
        endTime = end;
        //two program cannot be happening at the same location
        location = loc;
        //a group size cannot be negative
        if(gro < 0)
        {
            group = 0;
        }
        else
        { 
            group = gro;
        }
        
        firstName = first;
        lastName = last;
        phone = phonenum;
    }
    
    /*
     * Accesors
     */
    
    /**
     * Return the program's start time
     * 
     * @return program's start time
     */
    public int getStartTime()
    {
        return startTime;
    }
    
    /**
     * Return the program's end time
     * 
     * @return program's end time
     */
    public int getEndTime()
    {
        return endTime;
    }
    
    /**
     * Display the time period as a String
     * 
     * @return the time period of a program as a String
     */
    public String displayTimePeriod()
    {
        String period = startTime + "-" + endTime;
        return period;
    }
    
    /**
     * Return the program's location
     * 
     * @return program's location
     */
    public String getLocation()
    {
        return location;
    }
    
    /**
     * Return the program's group size
     * 
     * @return program's group size
     */
    public int getGroupSize()
    {
        return group;
    }
    
    /**
     * return the program's contact first name
     * 
     * @return program's contact first name
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * return the program's contact last name
     * 
     * @return program's contact last name
     */
    public String getLastName()
    {
        return lastName;
    }
    
    /**
     * return the whole name of the program's contact
     * 
     * @return the whole name of the program's contact
     */
    public String getWholeName()
    {
        String name = firstName + " " + lastName;
        return name;
    }
    
    /**
     * return the program's contact phone number
     * 
     * @return program's contact phone number
     */
    public int getPhone()
    {
        return phone;
    }
    
    /*
     * programs are provided by another department, it will be taken from
     * an existing formatted file. User is unable to mutate any 
     * information here.
     */
}
