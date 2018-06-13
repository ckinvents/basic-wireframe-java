
/**
 * Represents matrix-tri operations
 * Quaternions were too confusing
 * 
 * @author Connor Ennis
 * @version 6/12/2018
 */
public class Triangle
{
    static Matrix IDENT = new Matrix(new double[][]{
        {1, 0, 0, 0},
        {0, 1, 0, 0},
        {0, 0, 1, 0}
    });
    final static int LEN = 9;
    
    final double[] tri;
    
    public Triangle(double[] coords)
    {
        tri = new double[LEN];
        for (int i = 0; i < LEN; i++)
        {
            tri[i] = coords[i];
        }
    }
    
    public double get(int coord)
    {
        return tri[coord];
    }
    
    private Triangle multiply(Matrix mat)
    {
        return new Triangle(new double[]{
            (mat.get(0,0)*get(0) + mat.get(0,1)*get(1) + mat.get(0,2)*get(2) + mat.get(0, 3)),
            (mat.get(1,0)*get(0) + mat.get(1,1)*get(1) + mat.get(1,2)*get(2) + mat.get(1, 3)),
            (mat.get(2,0)*get(0) + mat.get(2,1)*get(1) + mat.get(2,2)*get(2) + mat.get(2, 3)),
            (mat.get(0,0)*get(3) + mat.get(0,1)*get(4) + mat.get(0,2)*get(5) + mat.get(0, 3)),
            (mat.get(1,0)*get(3) + mat.get(1,1)*get(4) + mat.get(1,2)*get(5) + mat.get(1, 3)),
            (mat.get(2,0)*get(3) + mat.get(2,1)*get(4) + mat.get(2,2)*get(5) + mat.get(2, 3)),
            (mat.get(0,0)*get(6) + mat.get(0,1)*get(7) + mat.get(0,2)*get(8) + mat.get(0, 3)),
            (mat.get(1,0)*get(6) + mat.get(1,1)*get(7) + mat.get(1,2)*get(8) + mat.get(1, 3)),
            (mat.get(2,0)*get(6) + mat.get(2,1)*get(7) + mat.get(2,2)*get(8) + mat.get(2, 3))
        });
    }
    
    public Triangle translateTri(double x, double y, double z)
    {
        Matrix transMatrix = new Matrix(new double[][]{
            {1, 0, 0, x},
            {0, 1, 0, y},
            {0, 0, 1, z}
        });
        return multiply(transMatrix);
    }
    
    public Triangle rotateTri(double xA, double yA, double zA)
    {
        Matrix transMatrixX;
        Matrix transMatrixY;
        Matrix transMatrixZ;
        if (xA != 0)
        {
            transMatrixX = new Matrix(new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(xA), -Math.sin(xA), 0},
                {0, Math.sin(xA), Math.cos(xA), 0}
            });
        }
        else
        {
            transMatrixX = IDENT;
        }
        if (yA != 0)
        {
            transMatrixY = new Matrix(new double[][]{
                {Math.cos(yA), 0, Math.sin(yA), 0},
                {0, 1, 0, 0},
                {-Math.sin(yA), 0, Math.cos(yA), 0}
            });
        }
        else
        {
            transMatrixY = IDENT;
        }
        if (zA != 0)
        {
            transMatrixZ = new Matrix(new double[][]{
                {Math.cos(zA), -Math.sin(zA), 0, 0},
                {Math.sin(zA), Math.cos(zA), 0, 0},
                {0, 0, 1, 0}
            });
        }
        else
        {
            transMatrixZ = IDENT;
        }
        return multiply(transMatrixX).multiply(transMatrixY).multiply(transMatrixZ);
    }
    
    public Triangle perspectTrans()
    {
        return new Triangle(new double[]{
            get(0) / get(2),
            get(1) / get(2),
            get(2),
            get(3) / get(5),
            get(4) / get(5),
            get(5),
            get(6) / get(8),
            get(7) / get(8),
            get(8)
        });
    }
            
}