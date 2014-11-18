


public class ArrayOperations
{
   public static void main(String[] args)
   {
       double[] x = {8,4,5,21,7,9,18,2,100};
       System.out.println("number of elements: " + x.length);
       System.out.println(x[0]);
       System.out.println(x[8]);
       System.out.println(x[x.length-1]);
       for(int i = 0; i<x.length; i++)
       {
           System.out.println(x[i]);
    
       }
       
       for(int k = 0; k<x.length; k++)
       {
           System.out.println("element at " +k+" is: " + x[k]);
           
       }
       
       for(int j = 1; j<=x.length; j++)
       {
           System.out.println("element at " +(x.length-j)+" is: " + x[x.length-j]);
       }
       
       for(double val: x)
       {
           System.out.println(val);
       }
       
    }

}
