public class ArrayMethods
{
    private int[] values;
    
    public ArrayMethods(int[] initialValues)
    {
        values = initialValues;
    }
    
    public void replaceEvens()
    {
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] % 2 == 0)
            {
                values[i] = 0;
            }
        }
    }
    
    public void replaceNeighbors()
    {
        for (int i = 0; i < values.length; i++)
        {
            if ( i != 0 && i != (values.length - 1) )
            {
                if ( (values[i - 1] > values[i]) && (values[i - 1] > values[i + 1]) )
                {
                    values[i] = values[i - 1];
                }
                else if ( (values[i + 1] > values[i]) && (values[i + 1] > values[i - 1]) )
                {
                    values[i] = values[i + 1];
                }
            }
        }
    }
    
    public void removeMiddle()
    {
        if (values.length % 2 == 0)
        {
            
        }
        else
        {
            System.out.println("The middle value is: " + values.length/2 + ", or" + values[values.length / 2]); 
        }
    }
    
    public void shiftRight()
    {
        int firstVal = values[0];
        for (int i = 0; i < values.length - 1; i++)
        {
            values[i] = values[i + 1];
        }
        values[values.length - 1] = firstVal;
    }
    
    public void swapFirstAndLast()
    {
        int tempVal = values[0];
        values[0] = values[values.length - 1];
        values[values.length - 1] = tempVal;
    }
    
    public String toString()
    {
        String str = "";
        for (int i = 0; i < values.length; i++)
        {
            if (i != values.length - 1)
            {
                str += values[i]+",";
            }
            else
            {
                str+= values[i];
            }
        }
        return str;
    }
    
}
