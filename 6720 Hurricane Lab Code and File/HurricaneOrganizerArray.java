import java.io.*;
import java.util.*;

/**
 * Models hurricane information, works with Hurricane class
 * and the user to manipulate an array of hurricane data.
 *
 * Data came from http://www.aoml.noaa.gov/hrd/tcfaq/E23.html except for 2018.
 * 2018 data came from https://en.wikipedia.org/wiki/2018_Atlantic_hurricane_season.
 *
 * @author Susan King
 * @author Chenkai Hong
 * @version January 17, 2019
 * @version February 10, 2020 Polished code via variable names
 */
public class HurricaneOrganizerArray
{
    private Hurricane [] hurricanes;

    /**
     * Constructor for instances of the HurricaneOrganizerArray class
     *
     * @param filename  file name to be read
     *
     * @throws IOException  if file is not found
     */
    public HurricaneOrganizerArray(String filename)throws IOException
    {
        readFile(filename);   
    }

    /**
     * Calculates the length of a file.
     *
     * @param filename  The file's name to be counted
     *
     * @return The files's length as an integer. 
     *
     * @throws IOException  if file is not found
     */
    private static int determineFileLength(String filename) throws IOException
    {
        Scanner inFile = new Scanner(new File(filename));
        int counter = 0;

        while(inFile.hasNextLine())
        {
            counter++;
            inFile.nextLine();
        }
        inFile.close();
        return counter;
    }

    /**
     * Reads the file.
     *
     * @param filename  filename to be read
     *
     * @throws IOException  if file is not found
     */
    public void readFile(String filename) throws IOException
    {
        hurricanes = new Hurricane [determineFileLength(filename)];
        int hurYear, hurPressure, hurSpeed;
        String hurName, hurMonth;
        Scanner inFile = new Scanner(new File(filename));

        for(int i = 0; i < hurricanes.length; i++)
        {
            hurYear = inFile.nextInt();
            hurMonth = inFile.next();
            hurPressure = inFile.nextInt();
            hurSpeed = inFile.nextInt();
            String tempName = inFile.nextLine();
            hurName = "";
            for(int k = 0; k < tempName.length(); k++)
            {
                char c = tempName.charAt(k);
                if(('a' <= c && c <= 'z') || ('A' <= c && c <='Z'))
                    hurName += c;
            }
            Hurricane h = new Hurricane(hurYear, hurMonth, hurPressure, hurSpeed, hurName);
            hurricanes [i] = h;
        }
        inFile.close();
    }

    /**
     * Finds the max wind speed of all the wind speeds.
     *
     * @return Max wind speed
     */
    public int findMaxWindSpeed( )
    {
        int max = hurricanes[0].getSpeed();
        for (int i = 1; i < hurricanes.length; i++)
        {
            if(max < hurricanes[i].getSpeed())
            {
                max = hurricanes[i].getSpeed();
            }
        }
        return max;
    }
    /**
     * Finds the max pressure of all the pressures.
     *
     * @return max pressure
     */
    public int findMaxPressure( )
    {
        int maxPressure = hurricanes[0].getPressure();
        for (int i = 1; i < hurricanes.length; i++)
        {
            if(maxPressure < hurricanes[i].getPressure())
            {
                maxPressure = hurricanes[i].getPressure();
            }
        }
        return maxPressure;
    }
    /**
     * Finds the min wind speed of all the wind speeds.
     *
     * @return min wind speed
     */
    public int findMinWindSpeed( )
    {
         int min = hurricanes[0].getSpeed();
        for (int i = 1; i < hurricanes.length; i++)
        {
            if(min > hurricanes[i].getSpeed())
            {
                min = hurricanes[i].getSpeed();
            }
        }
        return min;
    }

    /**
     * Finds the min pressure of all the pressures.
     *
     * @return min pressure
     */
    public int findMinPressure( )
    {
         int min = hurricanes[0].getPressure();
        for (int i = 1; i < hurricanes.length; i++)
        {
            if(min > hurricanes[i].getPressure())
            {
                min = hurricanes[i].getPressure();
            }
        }
        return min;
    }
    /**
     * Finds the average wind speed of all the wind speeds.
     *
     * @return average wind speed
     */
    public double calculateAverageWindSpeed( )
    {
        double averageSpeed = 0;
        for (int i = 0; i < hurricanes.length; i++)
        {
            averageSpeed += hurricanes[i].getSpeed();
        }
        return (averageSpeed / hurricanes.length);
    }
    /**
     * Finds the average wind speed of all the wind speeds.
     *
     * @return average wind speed
     */
    public double calculateAveragePressure( )
    {
        double averagePressure = 0;
        for (int i = 0; i < hurricanes.length; i++)
        {
            averagePressure += hurricanes[i].getPressure();
        }
        return (averagePressure/ hurricanes.length);
    }
    /**
     * Calculates the average category of all hurricanes
     *
     *
     * @return average category
     */
    public double calculateAverageCategory( )
    {
        double averageCategory = 0;
        for (int i = 0; i < hurricanes.length; i++)
        {
            averageCategory += hurricanes[i].getCategory();
        }
        return (averageCategory/ hurricanes.length);
    }

    /**
     * Sorts in an ascendingo order with respect to the hurricanes' years,
     * 
     * The algorithm is selection sort.
     */
    public void sortYears()
    {
        for (int outer = 0; outer < hurricanes.length; outer++)
        {
            int mindex = outer;
            for (int inner = outer+1; inner < hurricanes.length; inner++)
            {
                if(hurricanes[inner].getYear() < hurricanes[mindex].getYear())
                {
                    mindex = inner;
                }
            }
            Hurricane temp = hurricanes[outer];
            hurricanes[outer] = hurricanes[mindex];
            hurricanes[mindex] = temp;
        }
    }

    /**
     * Sorts in an ascendingo order with respect to the hurricanes' names,
     * using insertion sort.
     */
    public void sortNames()
    {
        for (int outer = 0; outer < hurricanes.length; outer++)
        {
            int mindex = outer;
            for (int inner = outer+1; inner < hurricanes.length; inner++)
            {
                if(hurricanes[inner].getName().compareTo(hurricanes[mindex].getName()) < 0)
                {
                    mindex = inner;
                }
            }
            Hurricane temp = hurricanes[outer];
            hurricanes[outer] = hurricanes[mindex];
            hurricanes[mindex] = temp;
        }
    }

    /**
     * Sorts in an ascendingo order with respect to the hurricanes' categories,
     * using selection sort.
     */
    public void sortCategories()
    {
        for (int outer = 0; outer < hurricanes.length; outer++)
        {
            int mindex = outer;
            for (int inner = outer+1; inner < hurricanes.length; inner++)
            {
                if(hurricanes[inner].getCategory() > hurricanes[mindex].getCategory())
                {
                    mindex = inner;
                }
            }
            Hurricane temp = hurricanes[outer];
            hurricanes[outer] = hurricanes[mindex];
            hurricanes[mindex] = temp;
        }
    }  

    /**
     * Sorts descending with respect to pressures using selection sort.
     */
    public void sortPressures()
    {
        for (int outer = 0; outer < hurricanes.length; outer++)
        {
            int mindex = outer;
            for (int inner = outer+1; inner < hurricanes.length; inner++)
            {
                if(hurricanes[inner].getPressure() > hurricanes[mindex].getPressure())
                {
                    mindex = inner;
                }
            }
            Hurricane temp = hurricanes[outer];
            hurricanes[outer] = hurricanes[mindex];
            hurricanes[mindex] = temp;
        }
    }
    
    /**
     * Sorts descending a portion of array based upon pressure,
     * using selection sort.
     *
     * @param   start   the first index to start the sort
     * @param   end     one past the last index to sort; hence, end position
     *                  is excluded in the sort
     */
    private void sortPressuresHelper (int start, int end)
    {
        // write this code
    }

    /**
     * Sorts ascending based upon wind speeds using a recursive merge sort.
     */
    public void sortWindSpeeds(int low, int high)
    {
        // write this code
    }

    /**
     * Merges two consecutive parts of an array, using wind speed as a criteria
     * and a temporary array.  The merge results in an ascending sort between
     * the two given indices.
     *
     * @precondition the two parts are sorted ascending based upon wind speed
     *
     * @param low   the starting index of one part of the array.
     *              This index is included in the first half.
     * @param mid   the starting index of the second part of the array.
     *              This index is included in the second half.
     * @param high  the ending index of the second part of the array.  
     *              This index is included in the merge.
     */
    private void mergeWindSpeedsSortHelper(int low, int mid, int high)
    {
        // write this code
    }

    /**
     * Sequential search for all the hurricanes in a given year.
     *
     * @param   year
     * @return  an array of objects in Hurricane that occured in
     *          the parameter year
     */
    public Hurricane [] searchYear(int year)
    {
        int counter = 0;
        //Find []h length
        // write this code

        Hurricane[] matches = new Hurricane[counter];
        // write the code
        return matches;
    }     

    /**
     * Binary search for a hurricane name.
     *
     * @param  name   hurricane name being search
     * @return a Hurricane array of all objects in hurricanes with specified name.
     *         Returns null if there are no matches
     */
    public Hurricane[ ] searchHurricaneName(String name)
    {
        sortNames();
        return searchHurricaneNameHelper(name, 0, hurricanes.length - 1);
    }

    /**
     * Recursive binary search for a hurricane name.  This is the helper
     * for searchHurricaneName.
     *
     * @precondition  the array must be presorted by the hurricane names
     *
     * @param   name  hurricane name to search for
     * @param   low   the smallest index that needs to be checked
     * @param   high  the highest index that needs to be checked
     * @return  a Hurricane array of all Hurricane objects with a specified name.
     *          Returns null if there are no matches
     */
    private Hurricane[ ] searchHurricaneNameHelper(String name, int low , int high)
    {
        // Test for the base case when a match is not found
        return null;

        // Test for match

        
        
        // Determine if the potential match is in the
        // "first half" of the considered items in the array

        
        
        // The potential match must be in the
        // "second half" of the considered items in the array

        
    }

    /**
     * Supports Binary Search method to get the full range of matches.
     *
     * @precondition  the array must be presorted by the hurricane names
     *
     * @param   name hurricane name being search for
     * @param   index  the index where a match was found
     * @return  a Hurricane array with objects from hurricanes with specified name.
     *          Returns null if there are no matches
     */
    private Hurricane[ ] retrieveMatchedNames (String name, int index)
    {
        // Find the start where the matches start:

        
        // Find the end of the matches:

        
        // Copy the objects whose names match:

        return null;  // correct this line
    }

    /**
     * Prints the header for the information of the huricane.
     */
    public void printHeader()
    {
        System.out.println("\n\n");
        System.out.printf("%-4s %-5s %-15s %-5s %-5s %-5s \n",
            "Year", "Mon.", "Name", "Cat.", "Knots", "Pressure");
    }

    /**
     * Prints out all of the hurricanes using the toString.
     */
    public void printHurricanes()
    {
        printHurricanes(hurricanes);
    }

    /**
     * Prints out a list of the hurrcanes of the given array.
     *
     * @param hurs  array of all of the hurricanes
     */
    public void printHurricanes(Hurricane [] hurs)
    {
        if(hurs.length == 0)
        {
            System.out.println("\nVoid of hurricane data.");
            return;
        }
        printHeader();
        for(Hurricane h: hurs)
        {
            System.out.println(h);
        }
    }

    /**
     * Prints out the menu of options to do with the list of hurricanes.
     */
    public void printMenu()
    {
        System.out.println("\n\nEnter option: ");
        System.out.println("\t 1 - Print all hurricane data \n" +
            "\t 2 - Print maximum and minimum data \n" +
            "\t 3 - Print averages \n" +
            "\t 4 - Sort hurricanes by year \n" +
            "\t 5 - Sort hurricanes by name \n" +
            "\t 6 - Sort hurricanes by category, descending \n" +
            "\t 7 - Sort hurricanes by pressure, descending \n" +
            "\t 8 - Sort hurricanes by speed \n" +
            "\t 9 - Search for hurricanes for a given year \n" +
            "\t10 - Search for a given hurricane by name \n" +
            "\t11 - Quit \n");
    }

    /**
     * Prints the information for the maximum and minumum wind speeds.
     */
    public void printMaxAndMin( )
    {
        System.out.println("Maximum wind speed is " +
            findMaxWindSpeed( ) +
            " knots and minimum wind speed is " +
            findMinWindSpeed( ) + " knots.");
        System.out.println("Maximum pressure is " +
            findMaxPressure( ) +
            " and minimum pressure is " +
            findMinPressure( ) + ".");
    }

    /**
     * Prints out the average wind speed, average pressure, and average category.
     */
    public void printAverages( )
    {
        System.out.printf("Average wind speed is %5.2f knots. \n" ,
            calculateAverageWindSpeed( ));
        System.out.printf("Average pressure is %5.2f. \n" ,
            calculateAveragePressure( ));
        System.out.printf("Average category is %5.2f. \n" ,
            calculateAverageCategory( ));
    }

    /**
     * Takes in the number the the user inputs and runs the corresponding method.
     */
    public boolean interactWithUser( )
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        printMenu();
        int choice = in.nextInt();
        // clear the input buffer
        in.nextLine();

        if(choice == 1)
        {
            printHurricanes( );
        }
        else if (choice == 2)
        {
            printMaxAndMin( );
        }
        else if (choice == 3)
        {
            printAverages( );
        }
        else if(choice == 4)
        {
            sortYears();
            printHurricanes( );
        }
        else if(choice == 5)
        {
            sortNames();
            printHurricanes( );
        }
        else if(choice == 6)
        {
            sortCategories();
            printHurricanes( );
        }
        else if(choice == 7)
        {
            sortPressures();
            printHurricanes( );
        }
        else if(choice == 8)
        {
            sortWindSpeeds(0, hurricanes.length - 1);
            printHurricanes( );
        }
        else if(choice == 9)
        {
            System.out.print("\n\tWhich year do you want to search for?\n\t");
            int year = in.nextInt();
            printHurricanes(searchYear(year));
        }
        else if(choice == 10)
        {
            System.out.print("\n\tWhich name do you want to search for?\n\t");
            String name = in.next();
            printHurricanes(searchHurricaneName(name));
        }
        else if (choice == 11)
        {
            done = true;
        }  
        return done;
    }

    /**
     * Creates a new HurrcaneOrganizerArray and runs the interact method
     * if the user is not done yet.
     *
     * @param args  user's information from the command line
     *
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public static void main (String [] args) throws IOException
    {
        HurricaneOrganizerArray cane = new HurricaneOrganizerArray("hurricanedata.txt");
        boolean areWeDoneYet = false;
        while ( ! areWeDoneYet)
        {
            areWeDoneYet = cane.interactWithUser( );    
        }
    }
}

