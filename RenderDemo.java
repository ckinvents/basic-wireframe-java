import javax.swing.*;
import java.awt.*;
/**
 * Demonstrates the 3D wireframe renderer
 * 
 * @author Connor Ennis
 * @version 6/13/2018
 */
public class RenderDemo extends JPanel
{
    private int width, height, scaleFactor;
    private String drawState;
    private Body octo, topLeftSquare, topRightSquare, bottomLeftSquare, bottomRightSquare;
    private double angle, wordTimer, wordY;
    private long startTime = System.currentTimeMillis();
    private boolean wordDown;
    
    public RenderDemo()
    {
        width = 1000;
        height = 800;
        wordTimer = 0;
        wordY = -40;
        wordDown = false;
        octo = new Body(new Mesh("octo.raw"), true, 0, 0, -10, 0, 0, 0);
        topLeftSquare = new Body(new Mesh("square.raw"), true, -4.25, -3, -5, 0, 0, 0);
        topRightSquare = new Body(new Mesh("square.raw"), true, 4.25, -3, -5, 0, 0, 0);
        bottomLeftSquare = new Body(new Mesh("square.raw"), true, -4.25, 3, -5, 0, 0, 0);
        bottomRightSquare = new Body(new Mesh("square.raw"), true, 4.25, 3, -5, 0, 0, 0);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);//new Color(99, 187, 221));
    }
    
    public void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);
        for (int y = 0; y < height; y += 40)
        {
            gr.drawLine(0, y, width, y);
        }
        for (int x = 0; x < width; x += 40)
        {
            gr.drawLine(x, 0, x, height);
        }
        Color orange = new Color(255, 222, 107);
        Color blue = new Color(159, 114, 255);
        Color green = new Color(62, 173, 52);
        if ((Math.sin(angle) < 0) && drawState.equals("epic"))
        {
            draw3DPoly(topLeftSquare.getDrawPolys(width, height, true), true, false, green, Color.BLACK, gr);
            draw3DPoly(topRightSquare.getDrawPolys(width, height, true), true, false, green, Color.BLACK, gr);
            draw3DPoly(bottomLeftSquare.getDrawPolys(width, height, true), true, false, green, Color.BLACK, gr);
            draw3DPoly(bottomRightSquare.getDrawPolys(width, height, true), true, false, green, Color.BLACK, gr);
        }
        // Outer polygons
        Polygon topLeft = new Polygon();
        Polygon topRight = new Polygon();
        Polygon bottomLeft = new Polygon();
        Polygon bottomRight = new Polygon();
        
        topLeft.addPoint(0, 0);
        topLeft.addPoint(500, 0);
        topLeft.addPoint(500, 160);
        topLeft.addPoint(240, 160);
        topLeft.addPoint(240, 80);
        topLeft.addPoint(80, 80);
        topLeft.addPoint(80, 240);
        topLeft.addPoint(160, 240);
        topLeft.addPoint(160, 400);
        topLeft.addPoint(0, 400);
        
        bottomLeft.addPoint(0, 800);
        bottomLeft.addPoint(500, 800);
        bottomLeft.addPoint(500, 640);
        bottomLeft.addPoint(240, 640);
        bottomLeft.addPoint(240, 720);
        bottomLeft.addPoint(80, 720);
        bottomLeft.addPoint(80, 560);
        bottomLeft.addPoint(160, 560);
        bottomLeft.addPoint(160, 400);
        bottomLeft.addPoint(0, 400);
        
        topRight.addPoint(1000, 0);
        topRight.addPoint(500, 0);
        topRight.addPoint(500, 160);
        topRight.addPoint(760, 160);
        topRight.addPoint(760, 80);
        topRight.addPoint(920, 80);
        topRight.addPoint(920, 240);
        topRight.addPoint(840, 240);
        topRight.addPoint(840, 400);
        topRight.addPoint(1000, 400);
        
        bottomRight.addPoint(1000, 800);
        bottomRight.addPoint(500, 800);
        bottomRight.addPoint(500, 640);
        bottomRight.addPoint(760, 640);
        bottomRight.addPoint(760, 720);
        bottomRight.addPoint(920, 720);
        bottomRight.addPoint(920, 560);
        bottomRight.addPoint(840, 560);
        bottomRight.addPoint(840, 400);
        bottomRight.addPoint(1000, 400);
        
        gr.setColor(blue);
        gr.fillPolygon(topLeft);
        gr.fillPolygon(bottomRight);
        gr.setColor(orange);
        gr.fillPolygon(topRight);
        gr.fillPolygon(bottomLeft);
        gr.setColor(Color.BLACK);
        gr.drawPolygon(topLeft);
        gr.drawPolygon(topRight);
        gr.drawPolygon(bottomLeft);
        gr.drawPolygon(bottomRight);
        
        Polygon topLeftIn = new Polygon();
        Polygon topRightIn = new Polygon();
        Polygon bottomLeftIn = new Polygon();
        Polygon bottomRightIn = new Polygon();
        
        // Polygons not drawn around oval because no possible way to
        // Since the octohedron is in the oval, and not a required shape anyway, also not outlined
        topLeftIn.addPoint(500, 400);
        topLeftIn.addPoint(500, 160);
        topLeftIn.addPoint(240, 160);
        topLeftIn.addPoint(240, 240);
        topLeftIn.addPoint(160, 240);
        topLeftIn.addPoint(160, 400);
        
        bottomLeftIn.addPoint(500, 400);
        bottomLeftIn.addPoint(500, 640);
        bottomLeftIn.addPoint(240, 640);
        bottomLeftIn.addPoint(240, 560);
        bottomLeftIn.addPoint(160, 560);
        bottomLeftIn.addPoint(160, 400);
        
        topRightIn.addPoint(500, 400);
        topRightIn.addPoint(500, 160);
        topRightIn.addPoint(760, 160);
        topRightIn.addPoint(760, 240);
        topRightIn.addPoint(840, 240);
        topRightIn.addPoint(840, 400);
        
        bottomRightIn.addPoint(500, 400);
        bottomRightIn.addPoint(500, 640);
        bottomRightIn.addPoint(760, 640);
        bottomRightIn.addPoint(760, 560);
        bottomRightIn.addPoint(840, 560);
        bottomRightIn.addPoint(840, 400);
        
        gr.setColor(orange);
        gr.fillPolygon(topLeftIn);
        gr.fillPolygon(bottomRightIn);
        gr.setColor(blue);
        gr.fillPolygon(topRightIn);
        gr.fillPolygon(bottomLeftIn);
        gr.setColor(Color.BLACK);
        gr.drawPolygon(topLeft);
        gr.drawPolygon(topRight);
        gr.drawPolygon(bottomLeft);
        gr.drawPolygon(bottomRight);
        //Lines
        gr.setColor(Color.WHITE);
        gr.drawLine(500, 401, 0, 401);
        gr.drawLine(500, 400, 0, 400);
        gr.drawLine(500, 399, 0, 399);
        gr.drawLine(500, 400, 500, 0);
        gr.drawLine(501, 400, 501, 0);
        gr.drawLine(500, 400, 500, 0);
        gr.drawLine(499, 400, 499, 0);
        gr.drawLine(500, 401, 1000, 401);
        gr.drawLine(500, 400, 1000, 400);
        gr.drawLine(500, 399, 1000, 399);
        gr.drawLine(501, 400, 501, 800);
        gr.drawLine(500, 400, 500, 800);
        gr.drawLine(499, 400, 499, 800);
        // Oval
        gr.setColor(new Color(191, 244, 255));
        gr.fillOval(320, 220, 360, 360);
        gr.setColor(Color.BLACK);
        gr.drawOval(320, 220, 360, 360);
        // Name
        gr.setColor(Color.BLACK);
        gr.drawString("Connor Ennis", 5, 15);
        // Squares
        if (drawState.equals("basic"))
        {
            gr.setColor(green);
            gr.fillRect(80, 80, 160, 160);
            gr.fillRect(760, 80, 160, 160);
            gr.fillRect(80, 560, 160, 160);
            gr.fillRect(760, 560, 160, 160);
            gr.setColor(Color.BLACK);
            gr.drawRect(80, 80, 160, 160);
            gr.drawRect(760, 80, 160, 160);
            gr.drawRect(80, 560, 160, 160);
            gr.drawRect(760, 560, 160, 160);
        }
        else if (Math.sin(angle) > 0)
        {
            draw3DPoly(topLeftSquare.getDrawPolys(width, height, true), true, false, green, Color.BLACK, gr);
            draw3DPoly(topRightSquare.getDrawPolys(width, height, true), true, false, green, Color.BLACK, gr);
            draw3DPoly(bottomLeftSquare.getDrawPolys(width, height, true), true, false, green, Color.BLACK, gr);
            draw3DPoly(bottomRightSquare.getDrawPolys(width, height, true), true, false, green, Color.BLACK, gr);
        }
        draw3DPoly(octo.getDrawPolys(width, height, true), true, true, Color.RED, Color.BLACK, gr);
        gr.setColor(Color.WHITE);
        gr.fillRect(430, (int)(wordY - 25), 140, 40);
        gr.setColor(Color.BLACK);
        gr.drawRect(430, (int)(wordY - 25), 140, 40);
        gr.drawString("Thank you!" ,435, (int)wordY);
    }
    
    private void draw3DPoly(Polygon[] poly, boolean isFilled, boolean isOutlined, Color fillColor, Color lineColor, Graphics gr)
    {
        Color oldColor = gr.getColor();
        gr.setColor(fillColor);
        if (isFilled)
        {
            for (int i = 0; i < poly.length; i++)
            {
                gr.fillPolygon(poly[i]);
            }
        }
        gr.setColor(lineColor);
        if (isOutlined)
        {
            for (int i = 0; i < poly.length; i++)
            {
                gr.drawPolygon(poly[i]);
            }
        }
        gr.setColor(oldColor);
    }
    
    public void update(double dt)
    {
        long execTime = System.currentTimeMillis() - startTime;
        if (execTime < 2000)
        {
            drawState = "basic";
        }
        else
        {
            drawState = "epic";
            angle += dt;
            if (wordY < 165 && !wordDown)
            {
                wordY += dt * 40;
            }
            else
            {
                wordDown = true;
                wordY = (165 + Math.sin(wordTimer) * 10);
                wordTimer += dt;
            }
        }
        octo.updatePos(0.0, 0.0, -4, angle, angle, angle);
        topLeftSquare.updatePos(-4.25, -3, -5 + Math.sin(angle), 0, angle, 0);
        topRightSquare.updatePos(4.25, -3, -5 + Math.sin(angle), 0, angle, 0);
        bottomLeftSquare.updatePos(-4.25, 3, -5 + Math.sin(angle), 0, angle, 0);
        bottomRightSquare.updatePos(4.25, 3, -5 + Math.sin(angle), 0, angle, 0);
    }
}
