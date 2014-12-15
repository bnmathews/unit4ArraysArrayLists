import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a description of test class Test01 here.
 * 
 * @author @bnmathews
 * @version 15 December 2014
 */
public class RadarTest
{
    /** description of instance variable x (add comment for each instance variable) */
    private int x;

    /**
     * Default constructor for objects of class RadarTest
     */
    public RadarTest()
    {
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testFinalMotion()
    {
        int[] monsterMotion = new int[2];
        
        final int ROWS = 100;
        final int COLS = 100;
        Radar radar = new Radar(ROWS, COLS, 1, 1);
        radar.setNoiseFraction(0.015);
        radar.scan();
        
        RadarComponent component = new RadarComponent(radar);
        
        for(int i = 0; i < 100; i++)
        {
            radar.scan();
        }
       
        //if (radar.findMotion()[0] != 1)
        //{
            assertEquals(1,radar.findMotion()[0]);
        //}
        //if (radar.findMotion()[1] != 1)
        //{
            assertEquals(1,radar.findMotion()[1]);
        //}
    }
}
