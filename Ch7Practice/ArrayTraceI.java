public class ArrayTraceI
{
    public static void main(String[] args)
    {
        int[] a = {1,2,3,4,5,4,3,2,1,0};
        int total = 0;
        for (int i = 0; i < 10; i++)
        {
            total = total + a[i];
        }
        System.out.println(total);
        total = 0;
        for (int i = 0; i < 10; i=i+2)
        {
            total = total + a[i];
        }
        System.out.println(total);
        total = 0;
        for (int i = 1; i < 10; i=i+2)
        {
            total = total + a[i];
        }
        System.out.println(total);
        total = 0;
        for (int i = 2; i <= 10; i++)
        {
            total = total + a[i-1];
        }
        System.out.println(total);
        total = 0;
        for (int i = 2; i < 10; i++)
        {
            total = total + a[i];
        }
        System.out.println(total);
        total = 0;
        for (int i = 10; i < 10; i=2*i)
        {
            total = total + a[i];
        }
        System.out.println(total);
        total = 0;
        for (int i = 9; i >= 0; i--)
        {
            total = total + a[i];
        }
        System.out.println(total);
        total = 0;
        for (int i = 9; i >= 0; i=i-2)
        {
            total = total + a[i];
        }
        System.out.println(total);
        total = 0;
        for (int i = 0; i < 10; i++)
        {
            total = a[i] - total;
        }
    }

}
