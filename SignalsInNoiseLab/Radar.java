
/**
 * The model for radar scan and accumulator
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class Radar
{
    
    // stores whether each cell triggered detection for the current scan of the radar
    private boolean[][] currentScan;
    
    private boolean[][] oldScan;
    
    private int[][] differences;
    
    // value of each cell is incremented for each scan in which that cell triggers detection 
    private int[][] accumulator;
    
    // location of the monster
    private int monsterLocationRow;
    private int monsterLocationCol;

    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;
    
    // number to increment or decrement the monster's x position by
    private int dx;
    
    // number to increment or decrement the monster's y position by
    private int dy;

    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols, int dx, int dy)
    {
        // initialize instance variables
        currentScan = new boolean[rows][cols]; // elements will be set to false

        differences = new int[11][11];
        
        oldScan = new boolean[rows][cols];
        accumulator = new int[rows][cols]; // elements will be set to 0
        
        // randomly set the location of the monster (can be explicity set through the
        //  setMonsterLocation method
        monsterLocationRow = (int)(Math.random() * rows);
        monsterLocationCol = (int)(Math.random() * cols);
        
        this.dx = dx;
        
        this.dy = dy;
        
        noiseFraction = 0.05;
        numScans = 0;
    }
    
    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     */
    public void scan()
    {
        // zero the current scan grid
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                currentScan[row][col] = false;
            }
        }
          
        int predictDx = 0;
        
        int predictDy = 0;
        
        // copy currentScan into previousScan using a for loop
        
        // detect the monster
        currentScan[monsterLocationCol][monsterLocationRow] = true;
        
        // inject noise into the grid
        injectNoise();
        
        // moves the monster
        moveMonster();
        
        // make another accumultor (-5 - 5 row and col) for dx and dy vals.
        
        // udpate the accumulator
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                if(currentScan[row][col] == true)
                {
                   accumulator[row][col]++;
                }
            }
        }
        
        if (numScans > 0)
        {   
            //System.out.println("Checking the old scan");
            for(int row = 0; row < oldScan.length; row++) //we need to look through the old scan
            {
                for(int col = 0; col < oldScan[0].length; col++)
                {
                    if ((oldScan[row][col]) == true) // if an element is found in the previous scan
                    {
                        //System.out.println("Noise found!");
                        for(int row2 = 0; row2 < currentScan.length; row2++) // check the new scan
                        {
                            for(int col2 = 0; col2 < currentScan[0].length; col2++)
                            {
                                if (currentScan[row2][col2] == true) // if an element is found in the new scan
                                {
                                    if (row2-row > -6 && row2-row < 6)
                                    {
                                        if (col2-col > -6 && col2-col < 6)
                                        {
                                            //System.out.println("Short col distance detected!" + (col2-col));
                                            differences[ (col2-col) + 5 ][ (row2-row) + 5 ]++;
                                        }
                                        //System.out.println("Short row distance detected!" + (row2-row));
                                    }
                                    
                                }
                            }
                        }
                    }
                }
            }
            
            
            for(int row = 0; row < oldScan.length; row++) //now clear old scan
            {
                for(int col = 0; col < oldScan[0].length; col++)
                {
                    oldScan[row][col] = false;
                }
            }
            //System.out.println("Old scan cleared");
            for(int row = 0; row < currentScan.length; row++)
            {
                    for(int col = 0; col < currentScan[0].length; col++)
                    {
                        if (currentScan[row][col] == true) // add elements from the current scan to the soon-to-be previous scan
                        {
                            oldScan[row][col] = true;
                        }
                    }
            }
            //System.out.println("Old scan updated");
        }    
        
        // keep track of the total number of scans
        numScans++;
        System.out.println(numScans);
        System.out.println(displayOld());
    }
    
    public String findMotion()
    {
        int[] maxArray = new int[2];
        int max = differences[0][0];
        for( int row = 0; row < differences.length; row++ )
        {
            //table[row].length = number of columns in a row
            for( int col = 0; col < differences[row].length; col++ )
            {
                if (differences[row][col] > max)
                {
                    max = differences[row][col];
                    maxArray[0] = (row - 5);
                    maxArray[1] = (col - 5); 
                }
            }
        }
        
        return ("The monster's change in x is: " + maxArray[0] + "\nIts change in y is: " + maxArray[1]);
    }
    
    public String displayOld()
    {
        String str = "";
        //table.length = number of rows in table
        for( int row = 0; row < differences.length; row++ )
        {
            //table[row].length = number of columns in a row
            for( int col = 0; col < differences[row].length; col++ )
            {
                str += differences[row][col] + "\t";
            }
            str += "\n";
        }
        
        return str;
    }
    
    /**
     * Moves the monster
     * 
     * @param   dx     the number to increment or decrement the monster's x position
     * @param   dy      the number to increment or decrement the monster's y position
     */
    public void moveMonster()
    {
       
        if (dx < 0) // if dx is negative
            {
                if (monsterLocationRow > 0 - dx)
                {
                    monsterLocationRow += dx;
                }
                else
                {
                    monsterLocationRow = currentScan.length + dx;
                }
            }
        else
            {
                if (monsterLocationRow < currentScan.length - dx)
                {
                    monsterLocationRow += dx;
                }
                else
                {
                    monsterLocationRow = 0;
                }
            }
            
        if (dy < 0) // if dy is negative
            {
                if (monsterLocationCol > 0 - dy)
                {
                    monsterLocationCol += dy;
                }
                else
                {
                    monsterLocationCol = currentScan[0].length + dy;
                }
            }
        else
            {
                if (monsterLocationCol < currentScan[0].length - dy)
                {
                    monsterLocationCol += dy;
                }
                else
                {
                    monsterLocationCol = 0;
                }
            }
    
    }

    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation(int row, int col)
    {
        // remember the row and col of the monster's location
        monsterLocationRow = row;
        monsterLocationCol = col;
        
        // update the radar grid to show that something was detected at the specified location
        currentScan[row][col] = true;
    }
    
     /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }
    
    /**
     * Returns true if the specified location in the radar grid triggered a detection.
     * 
     * @param   row     the row of the location to query for detection
     * @param   col     the column of the location to query for detection
     * @return true if the specified location in the radar grid triggered a detection
     */
    public boolean isDetected(int row, int col)
    {
        return currentScan[row][col];
    }
    
    /**
     * Returns the number of times that the specified location in the radar grid has triggered a
     *  detection since the constructor of the radar object.
     * 
     * @param   row     the row of the location to query for accumulated detections
     * @param   col     the column of the location to query for accumulated detections
     * @return the number of times that the specified location in the radar grid has
     *          triggered a detection since the constructor of the radar object
     */
    public int getAccumulatedDetection(int row, int col)
    {
        return accumulator[row][col];
    }
    
    /**
     * Returns the number of rows in the radar grid
     * 
     * @return the number of rows in the radar grid
     */
    public int getNumRows()
    {
        return currentScan.length;
    }
    
    /**
     * Returns the number of columns in the radar grid
     * 
     * @return the number of columns in the radar grid
     */
    public int getNumCols()
    {
        return currentScan[0].length;
    }
    
    /**
     * Returns the number of scans that have been performed since the radar object was constructed
     * 
     * @return the number of scans that have been performed since the radar object was constructed
     */
    public int getNumScans()
    {
        return numScans;
    }
    
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                // each cell has the specified probablily of being a false positive
                if(Math.random() < noiseFraction)
                {
                    currentScan[row][col] = true; // this actually draws the noise
                }
            }
        }
    }
    
}
