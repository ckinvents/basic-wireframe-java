import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Represents a triangle mesh to be rendered
 * 
 * @author Connor Ennis
 * @version 6/13/2018
 */
public class Mesh
{
    final Triangle[] tri;
    final int FACES;
    
    public Mesh(Triangle[] tris)
    {
        FACES = tris.length;
        tri = new Triangle[FACES];
        for (int i = 0; i < FACES; i++)
        {
            tri[i] = tris[i];
        }
    }
    
    public Mesh(String path)
    {
        tri = loadModel(path);
        FACES = tri.length;
    }
    
    public static Triangle[] loadModel(String file)
    {
        // Initializes a file reader, line scanner, and temporary storage objects
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Scanner scanner;
            ArrayList<Triangle> tris = new ArrayList<Triangle>();
            Triangle[] retTri;
            double[] coords = new double[Triangle.LEN];
            String line;
            // For every line in file...
            while ((line = reader.readLine()) != null)
            {
                // Get each point that makes up a tri
                scanner = new Scanner(line);
                for (int i = 0; i < coords.length; i++)
                {
                    coords[i] = scanner.nextDouble();
                }
                // Add tri to tris
                tris.add(new Triangle(coords));
            }
            // Convert to array
            retTri = new Triangle[tris.size()];
            for (int i = 0; i < retTri.length; i++)
            {
                retTri[i] = tris.get(i);
            }
            return retTri;
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error: file " + file + " not found.");
            return null;
        }
        catch (IOException ex)
        {
            System.out.println("Error: failure while reading file " + file + ".");
            return null;
        }
    }
    
    public Mesh transformMesh(double x, double y, double z, double xA, double yA, double zA, int scaleX, int scaleY, boolean isPerspect)
    {
        Triangle[] worldTris = new Triangle[FACES];
        Triangle tempTriangle;
        for (int i = 0; i < FACES; i++)
        {
            if (isPerspect)
            {
                tempTriangle = tri[i].rotateTri(xA, yA, zA).translateTri(x, y, z).perspectTrans();
            }
            else
            {
                tempTriangle = tri[i].rotateTri(xA, yA, zA).translateTri(x, y, z);
            }
            tempTriangle = new Triangle(new double[] {tempTriangle.get(0) * (scaleY/2) + (scaleX/2),
                                                      tempTriangle.get(1) * (scaleY/2) + (scaleY/2),
                                                      tempTriangle.get(2),
                                                      tempTriangle.get(3) * (scaleY/2) + (scaleX/2),
                                                      tempTriangle.get(4) * (scaleY/2) + (scaleY/2),
                                                      tempTriangle.get(5),
                                                      tempTriangle.get(6) * (scaleY/2) + (scaleX/2),
                                                      tempTriangle.get(7) * (scaleY/2) + (scaleY/2),
                                                      tempTriangle.get(8)});
            worldTris[i] = tempTriangle;
        }
        return new Mesh(worldTris);
    }
    
    public int[][] toScreenCoords()
    {
        int[][] coords = new int[FACES * 3][2];
        int coordCount = 0;
        for (int i = 0; i < FACES; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                coords[coordCount][0] = (int)tri[i].get(j * 3);
                coords[coordCount][1] = (int)tri[i].get(j * 3 + 1);
                coordCount++;
            }
        }
        return coords;
    }
}
