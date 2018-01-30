package aufgabe2.geometry;

import MathLib.Mat3x3;
import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.color.Color;
import aufgabe2.hit.Hit;
import aufgabe2.ray.Ray;

/**
 * This class represents a triangle object.
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Triangle extends Geometry{

    /**
     * An edge of the triangle.
     */
    public final Point3 a;
    
    /**
     * An edge of the triangle.
     */
    public final Point3 b;
    
    /**
     * An edge of the triangle.
     */
    public final Point3 c;
    
    /**
     * Construcht a triangle object.
     * 
     * @param a an edge of the triangle.
     * @param b an edge of the triangle.
     * @param c an edge of the triangle.
     * @param color the color of the triangle.
     * @throws IllegalArgumentException is thrown if the given parameter are null.
     */
    public Triangle(final Point3 a, final Point3 b, final Point3 c, final Color color) {
        super(color);
        if(a == null){
            throw new IllegalArgumentException("a must not be null");
        }
        if(b == null){
            throw new IllegalArgumentException("b must not be null");
        }
        if(b == null){
            throw new IllegalArgumentException("c must not be null");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        
    }

    @Override
    public Hit hit(final Ray ray) {
        final Mat3x3 matrix = new Mat3x3(a.x - b.x, a.x - c.x, ray.d.x, 
                                   a.y - b.y, a.y - c.y, ray.d.y, 
                                   a.z - b.z, a.z - c.z, ray.d.z);
        
        final Vector3 resultABC = a.sub(ray.o);
        
        double beta = matrix.changeCol1(resultABC).determinante / matrix.determinante;
        double gamma = matrix.changeCol2(resultABC).determinante / matrix.determinante;
        double t = matrix.changeCol3(resultABC).determinante / matrix.determinante;

        return (beta > 0 && gamma > 0 && beta + gamma < 1.0) ? new Hit(t, ray, this) : null;
    }
    
    
}
