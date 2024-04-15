import java.awt.Color;
/**
 * The Tetrad class constituents the appearance of a tetrad.
 * There are 7 different shapes and colors possible for a tetrad. 
 *
 * @author Chenkai Hong
 * @version 03/28/2023
 */
public class Tetrad
{
    private MyBoundedGrid<Block> grid;
    private Block[] blocks;
    /**
     * Constructor for objects of class Tetrad.
     * Randomly chooses a a color for the 4 blocks and places them in the blocks array.
     * @param gr grid in which the Tetrad is placed. 
     */
    public Tetrad(MyBoundedGrid<Block> gr)
    {
        blocks = new Block[4];
        grid = gr;
        int rand = (int)(Math.random()*7)+1;
        Color color = Color.RED;
        Location[] loc = new Location[4];
        int col = gr.getNumCols()/2-1;
        if(rand == 1)
        {
            loc[0] = new Location(1, col);
            loc[1] = new Location(0, col);
            loc[2] = new Location(2, col);
            loc[3] = new Location(3, col);
            color = Color.RED;
        }
        else if(rand == 2)
        {
            loc[0] = new Location(0, col);
            loc[1] = new Location(0, col+1);
            loc[2] = new Location(0, col-1);
            loc[3] = new Location(1, col);
            color = Color.GRAY;
        }
        else if(rand == 3)
        {
            loc[0] = new Location(0, col);
            loc[1] = new Location(0, col+1);
            loc[2] = new Location(1, col);
            loc[3] = new Location(1, col+1);
            color = Color.CYAN;
        }
        else if(rand == 4)
        {
            loc[0] = new Location(1, col);
            loc[1] = new Location(0, col);
            loc[2] = new Location(2, col);
            loc[3] = new Location(2, col+1);
            color = Color.YELLOW;
        }
        else if(rand == 5)
        {
            loc[0] = new Location(1, gr.getNumCols()/2);
            loc[1] = new Location(0, gr.getNumCols()/2);
            loc[2] = new Location(2, gr.getNumCols()/2);
            loc[3] = new Location(2, gr.getNumCols()/2-1);
            color = Color.MAGENTA;
        }
        else if(rand == 6)
        {
            loc[0] = new Location(0, col);
            loc[1] = new Location(0, col+1);
            loc[2] = new Location(1, col);
            loc[3] = new Location(1, col-1);
            color = Color.BLUE;
        }
        else
        {
            loc[0] = new Location(0, col);
            loc[1] = new Location(0, col-1);
            loc[2] = new Location(1, col);
            loc[3] = new Location(1, col+1);
            color = Color.GREEN;
        }
        for(int i = 0; i < blocks.length; i++)
        {
            blocks[i] = new Block();
            blocks[i].setColor(color);
        }
        addToLocations(gr,loc);
    }
    
    /**
     * Adds this tetrad to the four specified locations in the grid.
     * 
     * @param grid  the grid of this tetrad object
     * @param locs locations that the tetrad will be added to
     */
    private void addToLocations(MyBoundedGrid<Block> grid,Location[] locs)
    {
        for(int i = 0; i < locs.length; i++)
        {
            blocks[i].putSelfInGrid(grid, locs[i]);
        }
        this.grid = grid;
    }   
    
     /**
     * Removes blocks from the grid and returns their original locations.
     * @return An array containing locations of the blocks, which were removed. 
     */
     private Location[] removeBlocks()
    {
        Location[] oldLocs = new Location[4];
        for (int loc = 0; loc < 4; loc++)
        {
            oldLocs[loc] = blocks[loc].getLocation();
            blocks[loc].removeSelfFromGrid();
        }
        return oldLocs;
    }
    
    /**
     * Evaluates whether all Locations in locs are valid and empty.  
     * @param grid grid of this tetrad
     * @param locs the array that stores the locations to check
     * @return true if all Locations in locs are valid and empty; otherwise false.
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for(int loc = 0; loc<4; loc++)
        {
            if (!grid.isValid(locs[loc]) || grid.get(locs[loc]) != null)
                return false;
        }
        return true;
    }

    
    /**
     * Moves this Tetrad by deltaRows and deltaCol.
     * @param deltaRow how many rows to move
     * @param deltaCol how many columns to move
     * @return true if the tetrad is moved; otherwise, false
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        Location[] oldLocs = removeBlocks();
        Location[] newLocs = new Location[4];
        for(int loc = 0; loc<4; loc++)
        {
            newLocs[loc] = new Location(oldLocs[loc].getRow()+deltaRow, oldLocs[loc].getCol()+deltaCol);
        }
        if(!areEmpty(grid, newLocs))
        {
            addToLocations(grid, oldLocs);
            return false;
        }
        addToLocations(grid, newLocs);
        return true;
    }
    
    /**
     * Rotates a Tetrad. 
     * @return Returns true, if the tetrad successfully rotates; 
     *         false, otherwise.
     */
    public boolean rotate()
    {
        Location[] oldLocs = removeBlocks();
        Location[] newLocs = new Location[4]; 
        newLocs[0] = new Location(oldLocs[0].getRow(), oldLocs[0].getCol());
        int row0 = oldLocs[0].getRow();
        int col0 = oldLocs[0].getCol();
        if(blocks[0].getColor().equals(Color.CYAN))
        {
            return true; 
        }
        for(int i = 1; i < oldLocs.length; i++)
        {
            int row = oldLocs[i].getRow();
            int col = oldLocs[i].getCol();
            newLocs[i] = new Location(row0-col0+col,
                row0+col0-row);
        }
        if(areEmpty(grid, newLocs))
        {
            addToLocations(grid, newLocs);
            return true;
        }
        return false;    
    }
}