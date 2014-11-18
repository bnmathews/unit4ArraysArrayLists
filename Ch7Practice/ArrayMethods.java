public class ArrayMethods
{
    private int[] values;
    
    public ArrayMethods()
    {
        int[] values = { 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };
    }
    
    public void swapFirstAndLast()
    {
        int tempVal = values[0];
        values[0] = values[values.length - 1];
        values[values.length - 1] = tempVal;
    }
}
