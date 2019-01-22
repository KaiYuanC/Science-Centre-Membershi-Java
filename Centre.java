import java.io.*;
import java.util.*;
/**
 * Stores information about memberships in the science centre. It can 
 * add, delete a member, check out the programs that is happening on the day,
 * record rented items, lost and found items, and will be able to print
 * out a daily summary of the changes made per visit. 
 * 
 *
 * @author KaiYuan Chi
 * @version 2018-03-07
 */
public class Centre
{
    
    //Constants declaration
    
    private static final int MEMBERSHIP_FIELDS = 6;
    private static final int PROGRAM_FIELDS = 5;
    private static final int LOFO_FIELDS = 7;
    private static final int RENTING_FIELDS = 3;
    private static final String WHEELCHAIR = "Wheelchair";
    private static final String STROLLER = "Stroller";
    private static final int INITIAL_COUNTER = 0;
    
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    
    //Class field declarations 
    
    private static Calendar cal = Calendar.getInstance();
    
    //instance filed declaration
    
    private ArrayList<Membership> memberships;
    private ArrayList<Program> programs;
    private ArrayList<LostFound> lofos;
    private ArrayList<Renting> rentings;
    private ArrayList<Wheelchair> wheels;
    private ArrayList<Stroller> strolls;
    private static int avWheels;
    private static int avStrolls;
    
    private ArrayList<Membership> newMems;
    private ArrayList<Membership> delMems;
    
    /**
     * Constructor a centre object with a given array of members, programs, rentings, lost and founds,
     * number of wheelchair and strollers in stock. 
     * 
     * @param memFile a file with membership information
     * @param proFile a file with programs information
     * @param lofoFile a file with lost and found information
     * @param w number of available wheelchairs
     * @param s number of available strollers.
     */
    public Centre(String memFile, String proFile, String lofoFile, int w, int s)
    {
        //a centre have a initialized number of wheelchair and strolelrs
        // have a file for all the programs
        // have a file for all the memberships
        // have a file for lost and found
        // have a file for rentings 
        //all the additions need to be done here
        //At every start of the day, no strollers or wheelchair is rented yet
        avWheels = w;
        avStrolls = s;
    }
    
    /*
     * About memberships 
     */
    /**
     * Display all the memberships
     * 
     * @return a 2D array of all the membership informations 
     */
    public Object[][] diaplayMembership()
    {
        //String[] memTitle = {"ID:", "Holders", "Adress", "contact", "Membership Level", "Expiring date"}
        Object[][] mem = new Object[memberships.size()][MEMBERSHIP_FIELDS];
        Membership member;
        
        for(int i = 0; i < memberships.size(); i ++)
        {
            member = memberships.get(i);
            mem[i][ONE] = member.getAdults();
            mem[i][TWO] = member.getId();
            mem[i][THREE] = member.getAdress();
            mem[i][FOUR] = member.getPhone();
            mem[i][FIVE] = member.getLevel();
            mem[i][SIX] = member.getExpiringDate();
        }
        
        return mem;
    }
    
    /*
     * memberships cannot be sorted by names since 
     * each membership may have two holders.
     */
    
    /**
     * Sort all the memberships by ID
     */
    public void sortMemById()
    {
        //selection sort, sincethere will not be repeating ids
        for(int i = 0; i < memberships.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < memberships.size(); j ++)
            {
                if(memberships.get(j).getId() < memberships.get(min).getId())
                {
                    min = j;
                }
            }
            //switch
            Membership temp = memberships.get(i);
            memberships.set(i, memberships.get(min));
            memberships.set(min, temp);
        }
    }
    
    /**
     * Sort by the membership's adress
     */
    public void sortMemByAdress()
    {
        //selection sort
        for(int i = 0; i < memberships.size()-1;i++)
        {
            int min = i;
            for(int j = i + 1; j < memberships.size(); j++)
            {
                if((memberships.get(j).getAdress()).compareToIgnoreCase(memberships.get(min).getAdress()) < 0)
                {
                    min = j;
                }
            }
            //switch
            Membership temp = memberships.get(i);
            memberships.set(i, memberships.get(min));
            memberships.set(min, temp);
        }
    }
    
    /**
     * Sort membership by phone number
     */
    public void sortMemByPhone()
    {
        //selection sort
        for(int i = 0; i < memberships.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < memberships.size(); j ++)
            {
                if(memberships.get(j).getPhone() < memberships.get(min).getPhone())
                {
                    min = j;
                }
            }
            //switch
            Membership temp = memberships.get(i);
            memberships.set(i, memberships.get(min));
            memberships.set(min, temp);
        }
    }
    
    /**
     * sort by the first holder in the holder list of a membership
     */
    public void sortMemByHolder()
    {
        //selection sort
        for(int i = 0; i < memberships.size()-1;i++)
        {
            int min = i;
            for(int j = i + 1; j < memberships.size(); j++)
            {
                if((memberships.get(j).getAdults()).compareToIgnoreCase(memberships.get(min).getAdults()) < 0)
                {
                    min = j;
                }
            }
            //switch
            Membership temp = memberships.get(i);
            memberships.set(i, memberships.get(min));
            memberships.set(min, temp);
        }
    }
    
    /**
     * Search membership by holder's first name
     * 
     * @param first target member's first name
     * @return an array list of all memberships that contain these names
     */
    public ArrayList<Membership> searchMemByFName(String first)
    {
        ArrayList<Membership> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < memberships.size(); i++)
        {
            include = false;
            for(int k = 0; k < memberships.get(i).getArAdults().size(); k ++)
            {
                if((memberships.get(i).getArAdults()).get(k).getFirstName().equalsIgnoreCase(first))
                {
                    include = true;
                }
            }
            if(include)
            {
                tars.add(memberships.get(i));
            }
        }
        return tars;
    }
    
    /**
     * Search membership by holder's last name
     * 
     * @param last target member's last name
     * @return an array list of all memberships that contain these names
     */
    public ArrayList<Membership> searchMemByLName(String last)
    {
        ArrayList<Membership> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < memberships.size(); i++)
        {
            include = false;
            for(int k = 0; k < memberships.get(i).getArAdults().size(); k ++)
            {
                if((memberships.get(i).getArAdults()).get(k).getLastName().equalsIgnoreCase(last))
                {
                    include = true;
                }
            }
            if(include)
            {
                tars.add(memberships.get(i));
            }
        }
        return tars;
    }
    
    /**
     * Search membership by ID
     * 
     * @param num the ID number of the target member
     * @return the membership with that ID, null if not found
     */
    public Membership searchMemById(int id)
    {
        //Perform Binary Search, since every Id is different. 
        Membership mem = null;
        this.sortMemById();
        int bottom = INITIAL_COUNTER; 
        int top = memberships.size() -1;
        int middle;
        boolean found = false;
        int location = -1;
        
        while(bottom <= top && !found)
        {
            middle = (bottom + top)/2;
            if(memberships.get(middle).getId() == id)
            {
                found = true;
                location = middle;
            }
            else if(memberships.get(middle).getId() < id)
            {
                bottom = middle+1;
            }
            else
            {
                top = middle -1;
            }
        }
        if(location != -1)
        {
            mem = memberships.get(location);
        }
        
        return mem;
    }
    
    /**
     * search membership by adress
     * 
     * @param ad the adress of the target member
     * @return the array of membership with that ID, null if not found
     */
    public ArrayList<Membership> searchMemByAdress(String ad)
    {
        ArrayList<Membership> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < memberships.size(); i++)
        {
            include = false;
            if((memberships.get(i).getAdress().equalsIgnoreCase(ad)))
            {
                include = true;
            }
            if(include)
            {
                tars.add(memberships.get(i));
            }
        }
        return tars;
    }
    
    /**
     * search membership by contact
     * 
     * @param num the phone of the target member
     * @return an array list of all memberships that contain these p
     */
    public ArrayList<Membership> searchMemPhone(int num)
    {
        ArrayList<Membership> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < memberships.size(); i++)
        {
            include = false;
            if((memberships.get(i).getPhone() == num))
            {
                include = true;
            }
            if(include)
            {
                tars.add(memberships.get(i));
            }
        }
        return tars;
    }
    
    /**
     * Add a membership 
     * 
     * @param phonenum member's phone number
     * @param memadress member's adress
     * @param memlevelt member'level of membership
     * @param first the first name of a member under the membership
     * @param last the last name of a member under the membership 
     */
    public void addMembership(String first, String last,int phonenum, 
    String memadress, String memlevel, int day, int month, int year)
    {
        cal = Calendar.getInstance();
        int calYear = cal.get(Calendar.YEAR);
        int calMonth = cal.get(Calendar.MONTH) + 1;
        int calDay = cal.get(Calendar.DAY_OF_MONTH);
        memberships.add(new Membership(first, last, phonenum, memadress, memlevel, calDay, calMonth, calYear));
        newMems.add(new Membership(first, last, phonenum, memadress, memlevel, calDay, calMonth, calYear));
    }
    
    /**
     * delete a membership by inputing the particular membership.
     * 
     * @param mem the membership which is needed to be removed.
     */
    public void deleteMembership(Membership mem)
    {
        memberships.remove(mem);
    }
    
    /*
     * About programs
     */
    /**
     * Display all the programs. 
     * 
     * @return a 2D array of all the daily programs on that day
     */
    public Object[][] diaplayPrograms()
    {
        //String[] proitle = {"Time:", "Location:", "Group size:", "Contact", "Contact Phone"}
        //should also sort the program
        Object[][] pro = new Object[programs.size()][PROGRAM_FIELDS];
        Program program;
        
        for(int i = 0; i < programs.size(); i ++)
        {
            program = programs.get(i);
            pro[i][ONE] = program.displayTimePeriod();
            pro[i][TWO] = program.getLocation();
            pro[i][THREE] = program.getGroupSize();
            pro[i][FOUR] = program.getWholeName();
            pro[i][FIVE] = program.getPhone();
        }
        
        return pro;
    }
    
    /**
     * Sort programs by time
     */
    public void sortProByTime()
    {
        //selection sort
        for(int i = 0; i < programs.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < programs.size(); j ++)
            {
                if(programs.get(j).getStartTime() < programs.get(min).getStartTime())
                {
                    min = j;
                }
            }
            //switch
            Program temp = programs.get(i);
            programs.set(i, programs.get(min));
            programs.set(min, temp);
        }
    }
    
    /**
     * Sort program by contact name
     */
    public void sortProByName()
    {
        //selection sort
        for(int i = 0; i < programs.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < programs.size(); j ++)
            {
                if((programs.get(j).getWholeName()).compareToIgnoreCase(programs.get(min).getWholeName()) < 0)
                {
                    min = j;
                }
            }
            //switch
            Program temp = programs.get(i);
            programs.set(i, programs.get(min));
            programs.set(min, temp);
        }
    }
    
    /**
     * Sort by group size.
     */
    public void sortProByGroup()
    {
        //selection sort
        for(int i = 0; i < programs.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < programs.size(); j ++)
            {
                if(programs.get(j).getGroupSize() < programs.get(min).getGroupSize())
                {
                    min = j;
                }
            }
            //switch
            Program temp = programs.get(i);
            programs.set(i, programs.get(min));
            programs.set(min, temp);
        }
    }
    
    /**
     * Sort by contact number
     */
    public void sortProByPhone()
    {
        //selection sort
        for(int i = 0; i < programs.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < programs.size(); j ++)
            {
                if(programs.get(j).getPhone() < programs.get(min).getPhone())
                {
                    min = j;
                }
            }
            //switch
            Program temp = programs.get(i);
            programs.set(i, programs.get(min));
            programs.set(min, temp);
        }
    }
    
    /**
     * search programs by Whole name (First, last)
     * 
     * @param name the neame of the target member
     * @return the array of programs, null if not found
     */
    public ArrayList<Program> searchProByName(String name)
    {
        ArrayList<Program> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < programs.size(); i++)
        {
            include = false;
            if((programs.get(i).getWholeName().equalsIgnoreCase(name)))
            {
                include = true;
            }
            if(include)
            {
                tars.add(programs.get(i));
            }
        }
        return tars;
    }
    
    /**
     * Search a program by start time
     * 
     * @param time the time of the target member
     * @return the array of programs at that location
     */
    public ArrayList<Program> searchProByTime(int time)
    {
        ArrayList<Program> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < programs.size(); i++)
        {
            include = false;
            if((programs.get(i).getStartTime() == time))
            {
                include = true;
            }
            if(include)
            {
                tars.add(programs.get(i));
            }
        }
        return tars;
    }
    
    /**
     * search program by location
     * 
     * @param loc the location of the target member
     * @return the array of all programs at that location
     */
    public ArrayList<Program> searchProByLoc(String loc)
    {
        ArrayList<Program> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < programs.size(); i++)
        {
            include = false;
            if((programs.get(i).getLocation().equalsIgnoreCase(loc)))
            {
                include = true;
            }
            if(include)
            {
                tars.add(programs.get(i));
            }
        }
        return tars;
    }
    
    /**
     * search program by contact
     * 
     * @param num the phone number of the target member
     * @return an array list of all memberships that contain these phone numbers
     */
    public ArrayList<Program> searchProByPhone(int num)
    {
        ArrayList<Program> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < programs.size(); i++)
        {
            include = false;
            if((programs.get(i).getPhone() == num))
            {
                include = true;
            }
            if(include)
            {
                tars.add(programs.get(i));
            }
        }
        return tars;
    }
    
    /**
     * Search program by group
     * 
     * @param num the group size of the target member
     * @return an array list of all programs that have the group size
     */
    public ArrayList<Program> searchProByGroup(int num)
    {
        ArrayList<Program> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < programs.size(); i++)
        {
            include = false;
            if((programs.get(i).getGroupSize() == num))
            {
                include = true;
            }
            if(include)
            {
                tars.add(programs.get(i));
            }
        }
        return tars;
    }
    
    /*
     * About renting
     */
    
    /**
     * display all the rentings. 
     * 
     * @return a 2D array of all the renting informations 
     */
    public Object[][] diaplayRenting()
    {
        //String[] renTitle = {"Renting Item:", "Renter:", "Contact number:"}
        Object[][] ren = new Object[wheels.size() + strolls.size()][MEMBERSHIP_FIELDS];
        Wheelchair wheel;
        Stroller stroll;
        
        for(int i = 0; i < wheels.size(); i ++)
        {
            wheel = wheels.get(i);
            ren[i][ONE] = WHEELCHAIR;
            //double check this part
            ren[i][TWO] = wheel.getWholeName();
            ren[i][THREE] = wheel.getPhone();
        }
        int counter = INITIAL_COUNTER;
        for(int k = wheels.size(); k < (wheels.size() + strolls.size()); k ++)
        {
            stroll = strolls.get(counter);
            ren[k][ONE] = STROLLER;
            //double check this part
            ren[k][TWO] = stroll.getWholeName();
            ren[k][THREE] = stroll.getPhone();
            counter++;
        }
        
        return ren;
    }
    
    /**
     * Return the number of available wheelchair
     * 
     * @return the number of available wheelchair
     */
    public int getAvWheel()
    {
        return avWheels;
    }
    
    /**
     * Return the number of available stroller
     * 
     * @return the number of available stroller
     */
    public int getAvStrolls()
    {
        return avStrolls;
    }
    
    /**
     * Add a stroller or wheelchair, only allowed when there is one remaining
     * 
     * @param first renter's first name
     * @param last renter's last name
     * @param phonenum renter's phone number
     * @param type the type states wether it is a stroller or a wheelchair
     */
    public void addRenter(String first, String last, int phonenum, String type)
    {
         if(type == WHEELCHAIR)
         {
             wheels.add(new Wheelchair(first, last , phonenum));
             avWheels = avWheels -1;
         }
         else
         {
             strolls.add(new Stroller(first, last , phonenum));
             avStrolls = avStrolls -1;
         }
         
         rentings = new ArrayList<>();
         this.createRenting();
    }
    
    /**
     * Delete a Wheelchair
     * 
     * @param wheel the wheelchair that needs to be deleted
     */
    public void delWheel(Wheelchair wheel)
    {
       wheels.remove(wheel);
       avWheels = avWheels +1;
    }
    
    /**
     * Delete a stroller
     * 
     * @param stro the stroller that needs to be deleted
     */
    public void delStroll(Stroller stro)
    {
       strolls.remove(stro);
       avStrolls = avStrolls +1;
    }
    
    /**
     * Refrensh the renting list
     */
    public void createRenting()
    {
        for(int i = 0; i < wheels.size(); i ++)
        {
            rentings.add(wheels.get(i));
        }
        for(int i = 0; i < strolls.size(); i ++)
        {
            rentings.add(strolls.get(i));
        }
    }
    
    /**
     * Sort renting by rentor's name
     */
    public void sortRenByName()
    {
        //selection sort
        rentings = new ArrayList<>();
        this.createRenting();
        for(int i = 0; i < rentings.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < rentings.size(); j ++)
            {
                if((rentings.get(j).getWholeName()).compareToIgnoreCase(rentings.get(min).getWholeName()) < 0)
                {
                    min = j;
                }
            }
            //switch
            Renting temp = rentings.get(i);
            rentings.set(i, rentings.get(min));
            rentings.set(min, temp);
        }
    }
    
    /**
     * sort renting by contact number
     */
    public void sortRenByPhone()
    {
        //selection sort
        for(int i = 0; i < rentings.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < rentings.size(); j ++)
            {
                if(rentings.get(j).getPhone() < rentings.get(min).getPhone())
                {
                    min = j;
                }
            }
            //switch
            Renting temp = rentings.get(i);
            rentings.set(i, rentings.get(min));
            rentings.set(min, temp);
        }
    }
    
    /**
     * sort renting by type of item
     */
    public void sortRenByItem()
    {
        rentings = new ArrayList<>();
        this.createRenting();
    }
    
    /**
     * search Renting by Whole name (First, last)
     * 
     * @param name the name of the target member
     * @return the array of Rentings, null if not found
     */
    public ArrayList<Renting> searchRenByName(String name)
    {
        ArrayList<Renting> tars = new ArrayList<>();
        rentings = new ArrayList<>();
        this.createRenting();
        boolean include;
        for(int i = 0; i < rentings.size(); i++)
        {
            include = false;
            if((rentings.get(i).getWholeName().equalsIgnoreCase(name)))
            {
                include = true;
            }
            if(include)
            {
                tars.add(rentings.get(i));
            }
        }
        return tars;
    }
    
    /**
     * search rent by contact number 
     * 
     * @param num the phone number of the target member
     * @return an array list of all rentings that contain these phone numbers
     */
    public ArrayList<Renting> searchRenByPhone(int num)
    {
        ArrayList<Renting> tars = new ArrayList<>();
        rentings = new ArrayList<>();
        this.createRenting();
        boolean include;
        for(int i = 0; i < rentings.size(); i++)
        {
            include = false;
            if((rentings.get(i).getPhone() == num))
            {
                include = true;
            }
            if(include)
            {
                tars.add(rentings.get(i));
            }
        }
        return tars;
    }
    
    /*
     * About lostFound
     */
    
    /**
     * Display lost and found by date
     * 
     * @return a 2D array of all the lost and found information
     */
    public Object[][] diaplayLofo()
    {
        this.refreshLofo();
        //String[] lofoTitle = {"Date:", "Item Description", Contact", "Contact Number:", "Lost?", "Found?", "Location"}
        Object[][] lofo = new Object[lofos.size()][LOFO_FIELDS];
        LostFound item;
        
        for(int i = 0; i < lofos.size(); i ++)
        {
            item = lofos.get(i);
            lofo[i][ONE] = item.getDate();
            lofo[i][TWO] = item.getDescrip();
            lofo[i][THREE] = item.getWholeName();
            lofo[i][FOUR] = item.getPhone();
            lofo[i][FIVE] = item.strLost();
            lofo[i][SIX] = item.strFound();
            lofo[i][SEVEN] = item.getLoc();
        }
        
        return lofo;
    }
    
    /**
     * Add lost and found
     * 
     * @param descrip a general description or note of the item
     * @param first the first name of the contact
     * @param last the last name of the contact
     * @param loc the location of where it is found or lost
     * @param l state whether the object is lost
     * @param f state whether the object is found. 
     * @param phonenum the phone number of the contact
     */
    public void addLofo(String descrip, String first,
    String last, String loc, boolean l, boolean f, int phonenum)
    {
        cal = Calendar.getInstance();
        int calYear = cal.get(Calendar.YEAR);
        int calMonth = cal.get(Calendar.MONTH) + 1;
        int calDay = cal.get(Calendar.DAY_OF_MONTH);
        lofos.add(new LostFound(calDay, calMonth, calYear, descrip, first, last, loc, l, f,phonenum));
    }
    
    /**
     * Refresh lost and found list, remove every item that is both lost 
     * and found, remove any item that is over a month. 
     */
    public void refreshLofo()
    {
        for(int i = 0; i < lofos.size(); i ++)
        {
            if(lofos.get(i).isPaired() || lofos.get(i).isOver(lofos.get(i).getYear(), lofos.get(i).getMonth(), lofos.get(i).getDay()))
            {
                lofos.remove(lofos.get(i));
            }
        }
    }
    
    /**
     * sort lost and found by type
     */
    public void sortLofoByType()
    {
        this.refreshLofo();
        ArrayList<LostFound> losts = new ArrayList<LostFound>();
        ArrayList<LostFound> founds = new ArrayList<LostFound>();
        for(int i = 0; i < lofos.size(); i++)
        {
            if(lofos.get(i).isLost())
            {
                losts.add(lofos.get(i));
            }
            else
            {
                founds.add(lofos.get(i));
            }
        }
        for(int i = 0; i < losts.size(); i++)
        {
            lofos.set(i, losts.get(i));
        }
        int counter = INITIAL_COUNTER;
        for(int i = losts.size(); i < lofos.size(); i++)
        {
            lofos.set(i, founds.get(counter));
            counter++;
        }
    }
    
    /**
     * Sort lost and found by name
     */
    public void sortLofoByName()
    {
        //selection sort
        for(int i = 0; i < lofos.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < lofos.size(); j ++)
            {
                if((lofos.get(j).getWholeName()).compareToIgnoreCase(lofos.get(min).getWholeName()) < 0)
                {
                    min = j;
                }
            }
            //switch
            LostFound temp = lofos.get(i);
            lofos.set(i, lofos.get(min));
            lofos.set(min, temp);
        }
    }
    
    /**
     * sort lost and found by contact number
     */
    public void sortLofoByPhone()
    {
        //selection sort
        for(int i = 0; i < lofos.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < lofos.size(); j ++)
            {
                if(lofos.get(j).getPhone() < lofos.get(min).getPhone())
                {
                    min = j;
                }
            }
            //switch
            LostFound temp = lofos.get(i);
            lofos.set(i,lofos.get(min));
            lofos.set(min, temp);
        }
    }
    
    /**
     * sort lost and found by date
     */
    public void sortLofoByDate()
    {
        this.refreshLofo();
        //since all losts and founds are in less than a month
        int[] days = new int[lofos.size()];
        for(int i = 0; i < lofos.size(); i ++)
        {
            days[i] = lofos.get(i).getDays();
        }
        for(int i = 0; i < lofos.size() -1; i++)
        {
            LostFound temp = lofos.get(i);
            lofos.set(i, lofos.get(findMinDate(days, i)));
            lofos.set(findMinDate(days,i), temp);
        }
    }
    
    /**
     * sort lost and found by description
     */
    public void sortLofoByDescrip()
    {
        //selection sort
        for(int i = 0; i < lofos.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < lofos.size(); j ++)
            {
                if((lofos.get(j).getDescrip()).compareToIgnoreCase(lofos.get(min).getDescrip()) < 0)
                {
                    min = j;
                }
            }
            //switch
            LostFound temp = lofos.get(i);
            lofos.set(i, lofos.get(min));
            lofos.set(min, temp);
        }
    }
    
    /**
     * sort lost and found by locaton
     */
    public void sortLofoByLoc()
    {
        //selection sort
        for(int i = 0; i < lofos.size()-1; i ++)
        {
            int min = i;
            for(int j = i+1; j < lofos.size(); j ++)
            {
                if((lofos.get(j).getLoc()).compareToIgnoreCase(lofos.get(min).getLoc()) < 0)
                {
                    min = j;
                }
            }
            //switch
            LostFound temp = lofos.get(i);
            lofos.set(i, lofos.get(min));
            lofos.set(min, temp);
        }
    }
    
    /**
     * Search lost and found by name
     * 
     * @param name the name of the target member
     * @return the array of Rentings, null if not found
     */
    public ArrayList<LostFound> searchLofoByName(String name)
    {
        ArrayList<LostFound> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < lofos.size(); i++)
        {
            include = false;
            if((lofos.get(i).getWholeName().equalsIgnoreCase(name)))
            {
                include = true;
            }
            if(include)
            {
                tars.add(lofos.get(i));
            }
        }
        return tars;
    }
    
    
    /**
     * search by description key word
     * 
     * @param str the keyword that is wished to look for
     * @return the arraylist of all items with that keyword in description
     */
    public ArrayList<LostFound> searchLofoByKey(String str)
    {
        ArrayList<LostFound> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < lofos.size(); i ++)
        {
            include = false;
            String des = lofos.get(i).getDescrip();
            String[] s = des.split(" ");
            for(int k = 0; k < s.length; k++)
            {
                if(s[k].equalsIgnoreCase(str))
                {
                    include = true;
                }
                if(include)
                {
                    tars.add(lofos.get(i));
                }
            }
        }
        return tars;
    }
    
    /**
     * search Lost and Found by location
     * 
     * @param loc the location of the target member
     * @return the array of locations, null if not found
     */
    public ArrayList<LostFound> searchLofoByLoc(String loc)
    {
        ArrayList<LostFound> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < lofos.size(); i++)
        {
            include = false;
            if((lofos.get(i).getLoc().equalsIgnoreCase(loc)))
            {
                include = true;
            }
            if(include)
            {
                tars.add(lofos.get(i));
            }
        }
        return tars;
    }
    
    /**
     * search Lost and found by contact
     * 
     * @param num the phone number of the target member
     * @return an array list of lost and found that contain these phone numbers
     */
    public ArrayList<LostFound> searchLofoByPhone(int num)
    {
        ArrayList<LostFound> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < lofos.size(); i++)
        {
            include = false;
            if((lofos.get(i).getPhone() == num))
            {
                include = true;
            }
            if(include)
            {
                tars.add(lofos.get(i));
            }
        }
        return tars;
    }
    
    /**
     * search lost and found by date
     * 
     * @param y the year of the item lost/found
     * @param m the month of the item lost/found
     * @param d the day that the item was lost/found
     * @return an array list of lost and found that was lost on this date
     */
    public ArrayList<LostFound> searchLofoByDate(int y, int m, int d)
    {
        ArrayList<LostFound> tars = new ArrayList<>();
        boolean include;
        for(int i = 0; i < lofos.size(); i ++)
        {
            include = false;
            if(lofos.get(i).getYear() == y && lofos.get(i).getMonth() == m && lofos.get(i).getDay() == d)
            {
                include = true;
            }
            if(include)
            {
                tars.add(lofos.get(i));
            }
        }
        return tars; 
    }
    
    /*
     * About display 
     */
    
    /**
     * display the summary of all new members added. 
     * 
     * @return a 2D array of all the new members information
     */
    public Object[][] diaplayNewMem()
    {
        //String[] dmemTitle = {"ID:", "Holders", "Adress", "contact", "Membership Level", "Expiring date"}
        Object[][] nmem = new Object[newMems.size()][MEMBERSHIP_FIELDS];
        Membership member;
        
        for(int i = 0; i < newMems.size(); i ++)
        {
            member = newMems.get(i);
            nmem[i][ONE] = member.getAdults();
            nmem[i][TWO] = member.getId();
            nmem[i][THREE] = member.getAdress();
            nmem[i][FOUR] = member.getPhone();
            nmem[i][FIVE] = member.getLevel();
            nmem[i][SIX] = member.getExpiringDate();
        }
        
        return nmem;
    }
    
    /**
     * display the summary of all new members added. 
     * 
     * @return a 2D array of all the new members information
     */
    public Object[][] diaplayDelMem()
    {
        //String[] dmemTitle = {"ID:", "Holders", "Adress", "contact", "Membership Level", "Expiring date"}
        Object[][] dmem = new Object[delMems.size()][MEMBERSHIP_FIELDS];
        Membership member;
        
        for(int i = 0; i < delMems.size(); i ++)
        {
            member = delMems.get(i);
            dmem[i][ONE] = member.getAdults();
            dmem[i][TWO] = member.getId();
            dmem[i][THREE] = member.getAdress();
            dmem[i][FOUR] = member.getPhone();
            dmem[i][FIVE] = member.getLevel();
            dmem[i][SIX] = member.getExpiringDate();
        }
        
        return dmem;
    }
    
    /**
     * export a daily summary file
     */
    
    /**
     * Read File 
     */
    
    /**
     *  write file 
     */
    
    /*
     * static methods
     */
    
    /**
     * find the earliest lost and found
     */
    public static int findMinDate(int[] nums, int index)
    {
        //recursion method
        int min = index -1;
        if(index < nums.length - 1)
        {
            min = findMinDate(nums, index + 1);
        }
        if(nums[index] < nums[min])
        {
            min = index;
        }
        return min;
    }
}
