import javax.swing.JFrame;
import java.util.Scanner;

/**
 * Class that contains the main method for the program and creates the frame containing the component.
 * 
 * @author @bnmathews
 * @version 16 December 2014
 */
public class RadarViewer
{
    /**
     * main method for the program which creates and configures the frame for the program
     *
     */
    public static void main(String[] args) throws InterruptedException
    {
        // array for user inputted dx and dy, to be transfered into the radar class
        int[] monsterMotion = new int[2];
        
        // user input
        Scanner s = new Scanner(System.in);
        
        System.out.print("What is the dx of your monster? ");
        monsterMotion[0] = s.nextInt();
        
        System.out.print("What is the dy of your monster? ");
        monsterMotion[1] = s.nextInt();
        
        // create the radar, set the monster location, and perform the initial scan
        final int ROWS = 100;
        final int COLS = 100;
        Radar radar = new Radar(ROWS, COLS, monsterMotion[0], monsterMotion[1]);
        radar.setNoiseFraction(0.015);
        radar.scan();
        
        JFrame frame = new JFrame();
        
        frame.setTitle("Signals in Noise Lab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // a frame contains a single component; create the radar component and add it to the frame
        RadarComponent component = new RadarComponent(radar);
        frame.add(component);
        
        // set the size of the frame to encompass the contained component
        frame.pack();
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
        
        // perform 100 scans of the radar wiht a slight pause between each
        // after each scan, instruct the Java Run-Time to redraw the window
        for(int i = 0; i < 100; i++)
        {
            Thread.sleep(100); // sleep 100 milliseconds (1/10 second)
            
            radar.scan();
            
            frame.repaint();
        }
        
        // find and print the motion of the monster
        System.out.println("The monster's change in x is:" + radar.findMotion()[0]);
        System.out.println("The monster's change in y is:" + radar.findMotion()[1]);
    }

}
