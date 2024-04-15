
/**
 * The Tetris class displays a tab that a user can play tetris in. 
 * In the game tetris, a tetrad of a random color, out of 7 options
 * and a random shape, out of 7 options, spawn at the top of the window.
 * A user has the ability to manipulate each tetrad by rotating them
 * and moving them side to side. When a row of tetrad blocks is filled, 
 * the row disappears. 
 * A tetris game finishes when the user fails to clear enough rows in time, 
 * and a block touchs the top of the screen.
 * The objective of the game is to last as long as possible.
 *
 * @author Chenkai Hong
 * @version 03/28/2023
 */
public class Tetris implements ArrowListener
{
    // instance variables - replace the example below with your own
    private MyBoundedGrid<Block> grid;
    private static BlockDisplay display;
    private static boolean finished = false;
    private static Tetrad tet;
    private static int points = 0; 
    private static int level = 1;
    private static int totalRowsCleared = 0; 
    /**
     * Constructor for objects of class Tetris
     */
    public Tetris()
    {
        grid = new MyBoundedGrid<Block>(20,10);
        display = new BlockDisplay(grid);
        display.setTitle("Tetris");
        display.setArrowListener(this);
        tet = new Tetrad(grid);
        display.showBlocks();
    }

    /**
     * Shifts the tetrad up by one unit. 
     */
    @Override
    public void upPressed()
    {
        tet.rotate();
        display.showBlocks();
    }

    /**
     * Shifts the tetrad down by one unit. 
     */
    @Override
    public void downPressed()
    {
        tet.translate(1, 0);
        display.showBlocks();
    }

    /**
     * Shifts the tetrad to the left by one unit. 
     */
    @Override
    public void leftPressed()
    {
        tet.translate(0, -1);
        display.showBlocks();
    }

    /**
     * Shifts the tetrad to the right by one unit. 
     */
    @Override
    public void rightPressed()
    {
        tet.translate(0, 1);
        display.showBlocks();
    }

    /**
     * Shifts the tetrad down by one unit until it encounters an obstacle.
     */
    @Override
    public void spacePressed()
    {
        boolean check = true;

        while (check)
            check = tet.translate(1, 0);

        display.showBlocks();
    }
    
    /**
     * Plays the Tetris class. 
     * Moves each tetrad down the display, with its speed dependent on
     * the level its on. Creates a new one when it touches another 
     * tetrad or the bottom of display.
     */
    public void play()
    {
        try
        
        {
            //Pause for 1000 milliseconds.
            if(level == 1)
            {
            Thread.sleep(1000);
            }
            if(level == 2)
            {
                Thread.sleep(500);
            }
            if(level==3)
            {
                Thread.sleep(250);
            }
            if(level == 4)
            {
                Thread.sleep(125);
            }
            if(!tet.translate(1,0))
            {
                clearCompletedRows();
                tet = new Tetrad(grid);
            }
            display.showBlocks();
        }
        catch(InterruptedException e)
        {
            //ignore
        }
    }
    
    
    /**
     * Checks if a row is completed. 
     * 
     * @param row   the row that is being checked right now.
     */
     private boolean isCompletedRow(int row)
    {
        for (int c = 0; c < grid.getNumCols(); c++)
        {
            Location loc = new Location(row,c);
            if (grid.get(loc)==null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Clear all completed rows
     */
    private void clearCompletedRows()
    {
        totalRowsCleared = 0;
        for (int row = 0; row < grid.getNumRows(); row++)
        {
            if (isCompletedRow(row))
            {
                clearRow(row);
                level++;
                totalRowsCleared++;
            }
        }
        if(totalRowsCleared == 1)
        {
            points += 40;
        }
        else if(totalRowsCleared == 2)
        {
            points += 100;
        }
        else if(totalRowsCleared == 3)
        {
            points += 300;
        }
        else if (totalRowsCleared == 4)
        {
            points += 1200;
        }
        
    }
    /**
     * Clears one row. 
     * 
     * @param row the row being cleared. 
     */
    private void clearRow(int row)
    {
        for (int col = 0; col < grid.getNumCols(); col++)
        {
            Location loc = new Location(row,col);
            Block bl = grid.get(loc);
            bl.removeSelfFromGrid();
        }
        for (int r = row-1; r >= 0; r--)
        {
            for (int c = 0; c < grid.getNumCols(); c++)
            {
                Location loc = new Location(r,c);
                if (grid.get(loc)!=null)
                {
                    Location newLoc = new Location(r+1,c);
                    grid.get(loc).moveTo(newLoc);
                }   
            }
        }
    }
    
    /**
     * Runs the Tetris class
     *
     * @param args arguments
     */
    
    public static void main(String args[])
    {
        Tetris tetris = new Tetris();
        while(!finished)
        {
            tetris.play();
            display.setTitle("Level: " + level + "& Points: " + points);
        }
    }
}
