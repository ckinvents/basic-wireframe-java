import java.awt.*;
/**
 * 3D object that can be positioned/rendered
 * 
 * @author Connor Ennis
 * @version 6/13/2018
 */
public class Body
{
    private Mesh mesh;
    private double x, y, z, xA, yA, zA;
    private boolean isVisible;
    
    public Body(Mesh mesh, boolean isVisible, double x, double y, double z, double xA, double yA, double zA)
    {
        this.mesh = mesh;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xA = xA;
        this.yA = yA;
        this.zA = zA;
        this.isVisible = isVisible;
    }
    
    public void updatePos(double x, double y, double z, double xA, double yA, double zA)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.xA = xA;
        this.yA = yA;
        this.zA = zA;
    }
    
    public Polygon[] getDrawPolys(int scaleX, int scaleY, boolean isPerspective)
    {
        int[][] coords = mesh.transformMesh(x, y, z, xA, yA, zA, scaleX, scaleY, isPerspective).toScreenCoords();
        Polygon[] polys = new Polygon[coords.length / 3];
        for (int i = 0; i < polys.length; i++)
        {
            Polygon tri = new Polygon();
            tri.addPoint(coords[i * 3][0], coords[i * 3][1]);
            tri.addPoint(coords[i * 3 + 1][0], coords[i * 3 + 1][1]);
            tri.addPoint(coords[i * 3 + 2][0], coords[i * 3 + 2][1]);
            polys[i] = tri;
        }
        return polys;
    }
}
