import java.io.*;
import java.util.*;
/**
 * A membership of the science centre has a name, an ID, a phone number
 * a family adress (in Canada), an level of membership with an expiring 
 * date, and list of family member under this ID up to 2 adults and 4
 * children. 
 *
 * @author KaiYuan 
 * @version 2018-03-06
 */
public class Membership
{
    //Constants declaration
    
    //Each number corresponds to a certain level of membership
    private static final String LUNAR = "Lunar"; 
    private static final String PLANETARY = "Planetary"; 
    private static final String STELLAR = "Stellar"; 
    private static final String GALACTIC = "Galactic"; 
    private static final String COSMIC = "Cosmic"; 
    private static final int INITIAL_ID = 0;
    private static final int ID_INCREMENT = 1;
    private static final int EXPIRE_YEAR = 1;
    private static final int LUNAR_CHILD = 2;
    private static final int PLA_STE_CHILD = 4;
    private static final int GAL_COS_CHILD = 6;
    
    //static fields declaration
    private static int lastId = 0; 
    private static Calendar cal = Calendar.getInstance();
    //instance fields decalration
    
    private String adress;
    private int exDay;
    private int exMonth;
    private int exYear;
    private int id;
    //membership level is identified by numbers; 
    private String level; 
    private int phone;
    private int children;
    private ArrayList<Adult> adults;
    
    
    /**
     * Constructs a member with a first name, last name, an ID, 
     * a phone number, an adress (in Canada), and holds an membership 
     * with an expiring date. 
     * 
     * @param phonenum member's phone number
     * @param memadress member's adress
     * @param memlevelt member'level of membership
     * @param day the day that the membership will expire (expires in one year)
     * @param month the month that the membership will expire (expires in one year)
     * @param year the year that the membership will expire (expires in one year)
     * @param first the first name of a member under the membership
     * @param last the last name of a member under the membership 
     */
    public Membership(String first, String last,int phonenum, 
    String memadress, String memlevel, int day, int month, int year)
    {
        // the validity of each field is checked in the main method. 
        id = lastId + ID_INCREMENT;
        lastId = id;
        phone = phonenum;
        adress = memadress;
        level = memlevel;
        exDay = day;
        exMonth = month;
        exYear = year;
        
        if(level == LUNAR)
        {
            children = LUNAR_CHILD;
        }
        else if(level == PLANETARY || level == STELLAR)
        {
            children = PLA_STE_CHILD; 
        }
        else
        {
            children = GAL_COS_CHILD;
        }
            
        adults = new ArrayList<>();
        this.addAdult(first, last);
    }
    
    
    /*
     * Accessors 
     */
    
    /**
     * Returns member's adress
     * 
     * @return member's adress
     */
    public String getAdress()
    {
        return adress;
    }
    
    /**
     * Returns member's expiring day
     * 
     * @return member's expiring day
     */
    public int getExDay()
    {
        return exDay;
    }
    
    /**
     * Returns member's expiring month
     * 
     * @return member's expiring month
     */
    public int getExMonth()
    {
        return exMonth;
    }
    
    /**
     * Returns member's expiring year
     * 
     * @return member's expiring year
     */
    public int getExYear()
    {
        return exYear;
    }
    
    /**
     * Returns the expiring date of the membership
     * 
     * @return the expiring date of the membership
     */
    public String getExpiringDate()
    {
        String date = exYear + "/" + exMonth + "/" + exDay;
        return date;
    }
    
    /**
     * Returns member's ID
     * 
     * @return member's ID
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Returns the membership level of this membership
     * 
     * @return member's membership level
     */
    public String getLevel()
    {
        return level;
    }
    
    /**
     * Returns the family's home phone number
     * 
     * @return member's home phone number
     */
    public int getPhone()
    {
        return phone;
    }
    
    /**
     * Returns the number of children
     * 
     * @return the number of children
     */
    public int getChildren()
    {
        return children;
    }
    
    /**
     * Returns all the Adults' name that is under this membership
     * 
     * @return all the adults' name that is under this membership
     */
    public String getAdults()
    {
        String output = "";
        for(int i = 0; i < adults.size(); i++)
        {
            output = output + adults.get(i).getFirstName() + " " + adults.get(i).getLastName();
            if(i != (adults.size() -1))
            {
                output = output + ", ";
            }
        }
        return output;
    }
    
    /**
     * return all the adults in an arrayList
     * 
     * @return all the adults in an arrayList
     */
    public ArrayList<Adult> getArAdults()
    {
        ArrayList<Adult> a = new ArrayList<>();
        for(int i = 0; i < adults.size(); i ++)
        {
            a.add(adults.get(i));
        }
        return a;
    }
    
     /**
     * Returns a string representation of the fields of a membership.
     * 
     * @return a string representation of the fields of a mebership
     */
    public String toString()
    {
        return 
        "\nID: " + id +
        "\nPhone Number: " + phone + 
        "\nAdress: " + adress + 
        "\nExpiring date: " + exYear + " " + exMonth + " " + exDay;
    }// end toString()
    
    /**
     * Check if the card is expired
     * 
     * @return if the card is expired or not
     */
    public boolean isExpired()
    {
        boolean isEx;
        cal = Calendar.getInstance();
        int calYear = cal.get(Calendar.YEAR);
        int calMonth = cal.get(Calendar.MONTH) + 1;
        int calDay = cal.get(Calendar.DAY_OF_MONTH);
        
        if(exYear > 0 && (exYear + EXPIRE_YEAR) < calYear)
        {
            isEx = true;
        }
        else if(exYear > 0 && (exYear + EXPIRE_YEAR) == calYear && exMonth < calMonth) 
        {
            isEx = true;
        }
        else if(exYear > 0 && (exYear + EXPIRE_YEAR) == calYear && exMonth == calMonth && exDay < calDay) 
        {
            isEx = true;
        }
        else
        {
            isEx = false;
        }
        
        return isEx;
    }
    
    /*
     * Mutators
     */
    
    /**
     * change the adress of the membership
     * 
     * @param ad the new adress
     */
    public void setAdress(String ad)
    {
        adress = ad;
    }
    
    /**
     * change the phone of the membership
     * 
     * @param num new phone number
     */
    public void setPhone(int num)
    {
        phone = num;
    }
    
    /**
     * change the level of the membership
     * 
     * @param lev the new level of membership
     */
    public void setLevel(String lev)
    {
        //it is assumed that all the information entered is correct
        level = lev;
        if(level == LUNAR)
        {
            children = LUNAR_CHILD;
        }
        else if(level == PLANETARY || level == STELLAR)
        {
            children = PLA_STE_CHILD; 
        }
        else
        {
            children = GAL_COS_CHILD;
        }
    }
    
    /**
     * Renew the card for a year
     */
    public void renew()
    {
        exYear = exYear + EXPIRE_YEAR;
    }
    
    /**
     * Add an adult member to the this membership id
     * 
     * @param first member's first name
     * @param last member's last name
     */
    public void addAdult(String first, String last)
    {
        adults.add(new Adult(first, last));
    }
    
    /**
     * Delete an adult from this membership
     * 
     * @param adul the adult which need to be removed
     */
    public void deleteAdult(Adult adul)
    {
        adults.remove(adul);
    }//end of deleteCustomerByName(String name)
}
