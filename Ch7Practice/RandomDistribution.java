import java.util.Scanner;

public class RandomDistribution
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.print("How many random numbers do you want to generate? ");
        int numVal = s.nextInt();
        System.out.print("What is the number of values for each random draw? ");
        int numRand = s.nextInt();
        int[] values = new int[numRand];
        for(int i = 0; i<numVal; i++)
        {
            int val = (int)(Math.random() * numRand);
            values[val]++;
        }
        
        for(int i = 0; i < numRand; i++)
        {
            System.out.println(i + "\t"+values[i]);
        }
    }

}
