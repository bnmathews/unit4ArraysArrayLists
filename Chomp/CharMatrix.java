// Implements a 2-D array of characters

public class CharMatrix
{
    
    
  // Instance variables:

  private char[][] grid = new char[0][0];

  // Constructor: creates a grid with dimensions rows, cols,
  // and fills it with spaces
  public CharMatrix(int rows, int cols)
  {
    grid = new char[rows][cols]; // this should be the inst var.
    for( int row = 0; row < grid.length; row++ )
        {
            for( int col = 0; row < grid[row].length; col++ )
            {
                grid[row][col] = ' ';
            }
        }
  }

  // Constructor: creates a grid with dimensions rows , cols ,
  // and fills it with the fill  character
  public CharMatrix(int rows, int cols, char fill)
  {
    grid = new char[rows][cols];
    for( int row = 0; row < grid.length; row++ )
        {
            for( int col = 0; col < grid[row].length; col++ )
            {
                grid[row][col] = fill;
            }
        }
  }

  // Returns the number of rows in grid
  public int numRows()
  {
    int totalRow = 0;
    for( int row = 0; row < grid.length; row++ )
    {
        totalRow++;
    }
    return totalRow;
  }

  // Returns the number of columns in grid
  public int numCols()
  {
    int totalCol = 0;
    for( int col = 0; col < grid[numRows() - 1].length; col++ )
    {
        totalCol++;
    }
    return totalCol;
  }

  // Returns the character at row, col location
  public char charAt(int row, int col)
  {
    return grid[row][col];
  }

  // Sets the character at row, col location to ch
  public void setCharAt(int row, int col, char ch)
  {
    grid[row][col] = ch;
  }

  // Returns true if the character at row, col is a space,
  // false otherwise
  public boolean isEmpty(int row, int col)
  {
    if ( grid[row][col] == ' ' )
    {
        return true;
    }
    else
    {
        return false;
    }
  }

  // Fills the given rectangle with fill  characters.
  // row0, col0 is the upper left corner and row1, col1 is the
  // lower right corner of the rectangle.
  public void fillRect(int row0, int col0, int row1, int col1, char fill)
  {
      for ( int row = row0; row <= row1; row++ )
      {
          for ( int col = col0; col <= col1; col++ ) 
          {
              grid[row][col] = fill;
          }
      }
  }

  // Fills the given rectangle with SPACE characters.
  // row0, col0 is the upper left corner and row1, col1 is the
  // lower right corner of the rectangle.
  public void clearRect(int row0, int col0, int row1, int col1)
  {
    for ( int row = row0; row <= row1; row++ )
      {
          for ( int col = col0; col <= col1; col++ ) 
          {
              grid[row][col] = ' ';
          }
      }
  }

  // Returns the count of all non-space characters in row 
  public int countInRow(int row)
  {
    int count = 0;
    for ( int val : grid[row] )
    {
        if ( val != ' ' )
        {
            count++;
        }
    }
    return count;
  }

  // Returns the count of all non-space characters in col 
  public int countInCol(int col)
  {
    int count = 0;
    for ( int row = 0; row < grid.length; row++ )
    {
        if ( grid[row][col] != ' ' )
        {
            count++;
        }
    }
    return count;
  }
}
