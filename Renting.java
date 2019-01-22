
/**
 * A renting object is either a wheelchair or a stroller, it is has the 
 * name of the renter, and the contact number of the renter. 
 *
 * @author KaiYuan
 * @version 2018-03-07
 */
public abstract class Renting
{
    //instance fileds declaration
    
    private String firstName;
    private String lastName;
    private int phone;

    /**
     * Constructs renting object is either a wheelchair or a stroller, it is has the 
     * name of the renter, and the contact number of the renter. 
     * 
     * @param last the last name of the contact
     * @param loc the location of where it is found or lost
     * @param phonenum the phone number of the contact
     */
    public Renting(String first, String last, int phonenum)
    {
        firstName = first;
        lastName = last;
        phone = phonenum;
    }
    
    /*
     * Accesors
     */
    
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
     * return the whole name of the renter
     * 
     * @return the whole name of the renter
     */
    public String getWholeName()
    {
        String name = firstName + " " + lastName;
        return name;
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
}
