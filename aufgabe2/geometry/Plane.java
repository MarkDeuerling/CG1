package aufgabe2.geometry;

import MathLib.Normal3;
import MathLib.Point3;
import aufgabe2.color.Color;
import aufgabe2.hit.Hit;
import aufgabe2.ray.Ray;

/**
 * This class represents the plane object.
 * 
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling
 */
public class Plane extends Geometry{

    /**
     * The point on the plane.
     */
    public Point3  a;
    
    /**
     * The normal of the plane.
     */
    public Normal3 n;
    
    /**
     * Construct the plane object.
     * 
     * @param n the normal of the plane.
     * @param a the point of the plane.
     * @param color the color of the plane.
     * @throws IllegalArgumentException is thrown if the given parameter are null;
     */
    public Plane(final Normal3 n, final Point3 a, final Color color) {
        super(color);
        if(n == null){
            throw new IllegalArgumentException("n must not be null");
        }
        if(a == null){
            throw new IllegalArgumentException("a must not be null");
        }
        this.a = a;
        this.n = n;
        
    }

    @Override
    public Hit hit(Ray ray) {
        double t = ((a.sub(ray.o)).dot(n)) / (ray.d.dot(n));
        
        return t >= 0 ? new Hit(t, ray, this) : null;        
    }
    
}
