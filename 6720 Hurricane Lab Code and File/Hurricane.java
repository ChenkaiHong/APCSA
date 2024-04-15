import java.io.*;
/**
 * Models hurricane information, including categories.  
 * Works with HurricaneOrganizer, provides object and comparison skeletons.
 * 
 * @author Susan King, Chenkai Hong
 * @version 01/12/2022
 */
public class Hurricane
{
    private int year;
    private String month;
    private int pressure;
    private int speed;
    private String name;
    private int category;

    /**
     * Initializes a Hurricane object with no information.
     */
    public Hurricane()
    {
    }

    /**
     * Initializes a Hurricane object with historical information.
     * 
     * @param year      year the hurricane took place
     * @param month     month in String format
     * @param pressure  hurricane's pressure
     * @param speed     hurricane's speed in knots
     * @param name      hurricane's name
     */
    public Hurricane(int year, String month, int pressure, int speed, String name)
    {
        this.speed = speed;
        this.name = name;
        this.year = year;
        this.month = month;
        this.pressure = pressure;
        category = determineCategory( speed );
    }

    /**
     * Based upon Saffir/Simpson Hurricane Scale, figures out
     * the category using wind speed in knots.
     * 
     * Use https://en.wikipedia.org/wiki/Saffir%E2%80%93Simpson_scale.
     * 
     * @param knots     wind speed in knots
     * @return Saffir/Simpson Hurricane Scale category
     */
    public int determineCategory(int knots)
    {
        if ( knots <= 63 )
            return 0;
        if ( knots <= 82 )
            return 1;
        if ( knots <= 95 )
            return 2;
        if ( knots <= 112 )
            return 3;
        if ( knots <= 136 )
            return 4;
        return 5;
    }

    //Getters

    /**
     * Retrieves the name of Hurricane.
     * 
     * @return Name of hurricane.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Retrieves the month of Hurricane.
     * 
     * @return Month of hurricane.
     */
    public String getMonth()
    {
        return month;
    }

    /**
     * Retrieves the pressure of Hurricane.
     * 
     * @return Pressure of hurricane.
     */
    public int getPressure()
    {
        return pressure;
    }

    /**
     * Retrieves the speed of Hurricane.
     * 
     * @return Speed of hurricane.
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Retrieves the year of Hurricane.
     * 
     * @return Year of hurricane.
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Retrieves the category of Hurricane.
     * 
     * @return Category of hurricane.
     */
    public int getCategory()
    {
        return category;
    }

    /**
     * Comment this method even though you did not write it.
     */
    public void print()
    {
        System.out.println( toString() );
    }

    /**
     * Returns inportant information about an object belong to the 
     * Hurricane class.
     * 
     * @return String containing information about the object. 
     */
    public String toString()
    {
        return String.format("%-4d %-5s %-15s %-5d %5d %5d ", 
               year, month, name, category, speed, pressure);
    }

    /**
     * Calculates and returns the year between the current hurricane 
     * object and hurricane h. 
     * 
     * @return Time between this hurricane and hurricane h in year. 
     */
    public int compareYearTo(Hurricane h)
    {
        return year - h.getYear();
    }

    /**
     * Compares the name of this hurricane to hurricane h. 
     * 
     * @return <0, if this name < h's name
     *          0, if this name = h's name         
     *         >0, if this name > h's name
     * 
     */
    public int compareNameTo(Hurricane h)
    {
        return name.compareTo( h.getName() );
    }

    /**
     * Calculates and returns the difference in pressure between 
     * this hurricane and hurricane h.
     * 
     * @return Difference in pressure between this hurricane and h in int
     */
    public int comparePressureTo(Hurricane h)
    {
        return pressure - h.getPressure();
    }

    /**
     * Calculates and returns the difference in speed between 
     * this hurricane and hurricane h.
     * 
     * @return Difference in speed between this hurricane and h in int
     */
    public int compareSpeedTo(Hurricane h)
    {
        return speed - h.getSpeed();
    }

    /**
     * Calculates and returns the difference in category between 
     * this hurricane and hurricane h.
     * 
     * @return Difference in category between this hurricane and h in int
     */
    public int compareCategoryTo(Hurricane h)
    {
        return category - h.getCategory();
    }
}
