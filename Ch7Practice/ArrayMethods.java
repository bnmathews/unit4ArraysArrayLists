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
        int prevVal = values[0];
        for (int i = 1; i < values.length - 1; i++)
        {
            int tempVal = values[i];
            //
            prevVal = tempVal;
        }
    }
    
    public void removeMiddle()
    {
        if (values.length % 2 == 0)
        {
            values[(int)(values.length / 2)] = 0;
            values[(int)((values.length / 2) - 1)] = 0;
        }
        else
        {
            values[values.length / 2] = 0;
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
    
    public void moveEvens()
    {
        int[] newArray = new int[values.length];
        int arrayCount = 0;
        String str = "";
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] % 2 == 0)
            {
                newArray[arrayCount] = values[i];
                arrayCount++;
            }
        }
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] % 2 != 0)
            {
                newArray[arrayCount] = values[i];
                arrayCount++;
            }
        }
        values = newArray;
    }
    
    public int secondLargest()
    {
        int large = 0;
        int larger = 0;
        for (int i = 0; i < values.length; i++)
        {
            for (int k = 0; k < values.length; k++)
            {
                
            }
        }
        
        return large;
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
