import javax.swing.*;
import java.awt.*;
/**
 * Expiremental renderer
 * 
 * @author Connor Ennis
 * @version 6/12/2018
 */
public class Renderer
{
    public static void main(String[] args)
    {
        long oldTime;
        double dt = 0;
        AnimDemo anim = new AnimDemo();
        JFrame frame = new JFrame("Anim Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(anim);
        frame.pack();
        frame.setVisible(true);
        while (true)
        {
            oldTime = System.currentTimeMillis();
            anim.update(dt);
            anim.repaint();
            dt = (double)(System.currentTimeMillis() - oldTime) * 0.001;
        }
    }
}
