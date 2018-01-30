package aufgabe2.geometry;

import MathLib.Point3;
import aufgabe2.color.Color;
import aufgabe2.hit.Hit;
import aufgabe2.ray.Ray;

/**
 * This class represents a sphere object.
 * 
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling
 */
public class Sphere extends Geometry{

    /**
     * The point on the middle of the sphere.
     */
    public final Point3 c;
    
    /**
     * The radius of the sphere object.
     */
    public double r;
    
    /**
     * Construct a sphere object.
     * 
     * @param c the point on the middle of the sphere.
     * @param r the radius of the sphere.
     * @param color the color of the sphere.
     * @throws IllegalArgumentException is thrown if the given parameter are null.
     */
    public Sphere(final Point3 c, double r, final Color color) {
        super(color);
        if(c == null){
            throw new IllegalArgumentException("c must not be null");
        }
        this.c = c;
        this.r = r;
    }

    @Override
    public Hit hit(final Ray ray) {
        double t;
        double assistA = ray.d.dot(ray.d);
        double assistB = ray.d.dot(ray.o.sub(c).mul(2.0));
        double assistC = ray.o.sub(c).dot(ray.o.sub(c)) - r * r;
        
        double intersect = assistB * assistB - 4.0 * assistA * assistC;
       
        double t_plus  = (-assistB + Math.sqrt(intersect)) / 2.0 * assistA; 
        double t_minus = (-assistB - Math.sqrt(intersect)) / 2.0 * assistA;
       
        t = Math.min(t_plus, t_minus);
        
        return t > 0 ? new Hit(t, ray, this) : null;    
    }
    
}
