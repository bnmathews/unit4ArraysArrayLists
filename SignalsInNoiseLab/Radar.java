
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
        
        differences = new int[11][11]; // elements will be set to false
        
        oldScan = new boolean[rows][cols]; // elements will be set to 0
        
        accumulator = new int[rows][cols]; // elements will be set to 0
        
        // randomly set the location of the monster (can be explicity set through the
        // setMonsterLocation method
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
        
        // detect the monster
        currentScan[monsterLocationCol][monsterLocationRow] = true;
        
        // inject noise into the grid
        injectNoise();
        
        // moves the monster
        moveMonster();
        
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
        
        if (numScans > 0) //check to see if one scan has already happened
        {   
            for(int row = 0; row < oldScan.length; row++) // look through the old scan
            {
                for(int col = 0; col < oldScan[0].length; col++)
                {
                    if ((oldScan[row][col]) == true) // if an element is found in the previous scan (might be noise)
                    {
                        for(int row2 = 0; row2 < currentScan.length; row2++) // look through the new scan
                        {
                            for(int col2 = 0; col2 < currentScan[0].length; col2++)
                            {
                                if (currentScan[row2][col2] == true) // if an element is found in the new scan
                                {
                                    if (row2-row > -6 && row2-row < 6) // check if the difference in rows is between -5 and 5
                                    {
                                        if (col2-col > -6 && col2-col < 6) // check if the difference in columns is between -5 and 5
                                        {
                                            // increment a spot in the differenes 2D array
                                            differences[ (row2-row) + 5 ][ (col2-col) + 5 ]++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            for(int row = 0; row < oldScan.length; row++) // clear the values in the old scan
            {
                for(int col = 0; col < oldScan[0].length; col++)
                {
                    oldScan[row][col] = false;
                }
            }
            
            for(int row = 0; row < currentScan.length; row++) // look through the current scan on screen
            {
                    for(int col = 0; col < currentScan[0].length; col++)
                    {
                        if (currentScan[row][col] == true) // add elements from the current scan to the soon-to-be previous scan
                        {
                            oldScan[row][col] = true;
                        }
                    }
            }
        }    
        
        // keep track of the total number of scans
        numScans++;
        
        //System.out.println(numScans); 
        //System.out.println(displayDifferences());
    }
    /**
     * Looks through the 2D array of possible dx and dy pairs and gets the correct value
     * @return an array of the detected monster's dx and dy values
     */
    public int[] findMotion()
    {
        // create a 2 element array, the first will be the dx, the second will be the dy
        int[] maxArray = new int[2]; 
        
        // create the maximum number, takes in the very first value of differences
        int max = differences[0][0];
        
        // assume the value at point (0,0) in the differences 2D array is dx -5 and dy -5
        maxArray[0] = -5;
        maxArray[1] = -5; 
        
        for( int row = 0; row < differences.length; row++ )
        {
            for( int col = 0; col < differences[row].length; col++ )
            {
                if (differences[row][col] > max)
                {
                    // increase the max
                    max = differences[row][col];
                    
                    // set the first position of maxArray to the current row, minus 5
                    maxArray[1] = (row - 5);
                    
                    // set the second position of maxArray to the current col, minus 5
                    maxArray[0] = (col - 5); 
                }
            }
        }
        
        return maxArray;
    }
    
    /**
     * Displays the 2D array of various dx and dy pairs
     * 
     */
    public String displayDifferences()
    {
        String str = "";
        //differences.length = number of rows in differences
        for( int row = 0; row < differences.length; row++ )
        {
            //differences[row].length = number of columns in a row
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
