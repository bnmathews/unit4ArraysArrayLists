import java.util.Scanner;

public class MagicSquares
{
    
    public static void main(String[] themostargs)
    {
        int[][] square = new int[4][4];
        
        Scanner s = new Scanner(System.in);
        for ( int row = 0; row < square.length; row++ )
        {
            for ( int col = 0; col < square[row].length; col++)
            {
                System.out.print("Enter a number: ");
                int num = s.nextInt();
                square[row][col] = num;
            }
        }
        
        for ( int row = 0; row < square.length; row++ )
        {
            for ( int val : square[row])
            {
                System.out.print(val + "-");
            }
            System.out.println("");
        }
        
        //int rowSum = 0
        
        //get each row
        for ( int row = 0; row < square.length; row++ )
        {
            for ( int val : square[row]) //for each element in a row
            {
                //rowSum+=val;
            }
        }
    }

}
