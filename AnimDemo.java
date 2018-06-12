import javax.swing.*;
import java.awt.*;
/**
 * Test to observe how animation can be implemented minimally
 * 
 * @author Connor Ennis
 * @version 6/12/2018
 */
public class AnimDemo extends JPanel
{
    private int width, height, x, y, scaleFactor;
    private double angle;
    final int CIRC_WIDTH = 200;
    final Color CIRC_COL = Color.RED;
    
    public AnimDemo()
    {
        width = 1000;
        height = 800;
        angle = Math.PI / 2;
        scaleFactor = height/2 - CIRC_WIDTH/2 - 10;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
        updateCoords();
    }
    
    public void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);
        gr.setColor(Color.BLACK);
        gr.drawLine(width/2, height/2, x, y);
        gr.setColor(Color.RED);
        gr.fillOval(x - CIRC_WIDTH/2, y - CIRC_WIDTH/2, CIRC_WIDTH, CIRC_WIDTH);
    }
    
    private void updateCoords()
    {
        x = (int)(Math.cos(angle) * scaleFactor) + width/2;
        y = (int)(Math.sin(angle) * scaleFactor) + height/2;
    }
    
    public void update(double dt)
    {
        angle += 1 * dt;
        updateCoords();
    }
}
