
/**
 * A member object has a name, and it is under a membership. 
 *
 * @author KaiYun   
 * @version 2018-03-06
 */
public abstract class Member
{
    //instance fields declaration
    private String firstName;
    private String lastName;
    

    /**
     * Constructs A memeber with a first name and a last name
     */
    public Member(String first, String last)
    {
        firstName = first;
        lastName = last; 
    }
    
    /*
     * Accessors
     */

    /**
     * Return the first name of a member
     * 
     * @return the first name of a member
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * Return the last name of a member
     * 
     * @return the last name of a member
     */
    public String getLastName()
    {
        return lastName;
    }
}
