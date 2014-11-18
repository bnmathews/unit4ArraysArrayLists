import java.util.Scanner;
public class ArrayTraceII
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.print("What for loop do you want to try? [a-h] ");
        String choice = s.next();
        int[] a = { 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };
        if (choice.equals("a"))
        {
            for (int i = 1; i < 10; i++)
            {
                a[i] = a[i - 1];
                System.out.print(a[i] + " ");
            }
        }
        else if (choice.equals("b"))
        {
            for (int i = 9; i > 0; i--)
            {
                a[i] = a[i - 1];
                System.out.print(a[i] + " ");
            }
        }
        else if (choice.equals("c"))
        {
            for (int i = 0; i < 9; i++)
            {
                a[i] = a[i + 1];
                System.out.print(a[i] + " ");
            }
        }
        else if (choice.equals("d"))
        {
            for (int i = 8; i >= 0; i--)
            {
                a[i] = a[i + 1];
                System.out.print(a[i] + " ");
            }
        }
        else if (choice.equals("e"))
        {
            for (int i = 1; i < 10; i++)
            {
                a[i] = a[i] + a[i-1];
                System.out.print(a[i] + " ");
            }
        }
        else if (choice.equals("f"))
        {
            for (int i = 1; i < 10; i=i+2)
            {
                a[i] = 0;
                System.out.print(a[i] + " ");
            }
        }
        else if (choice.equals("g"))
        {
            for (int i = 0; i < 5; i++)
            {
                a[i] = a[i + 1];
                System.out.print(a[i] + " ");
            }
        }
        else if (choice.equals("h"))
        {
            for (int i = 1; i < 5; i++)
            {
                a[i] = a[9 - i];
                System.out.print(a[i] + " ");
            }
        }
    }

}
