
/**
 * Represents a matrix for 3D transformations
 * 
 * @author Connor Ennis 
 * @version 6/12/2018
 */
public class Matrix
{
    private double[][] mat;
    final int WIDTH = 4, HEIGHT = 3;
    
    public Matrix(double[][] vals)
    {
        mat = new double[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                mat[i][j] = vals[i][j];
            }
        }
    }
    
    public double get(int i, int j)
    {
        return mat[i][j];
    }
    
    public void set(int i, int j, double val)
    {
        mat[i][j] = val;
    }
}
