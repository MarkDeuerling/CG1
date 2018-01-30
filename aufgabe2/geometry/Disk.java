package aufgabe2.geometry;

import MathLib.Normal3;
import MathLib.Point3;
import aufgabe2.color.Color;
import aufgabe2.hit.Hit;
import aufgabe2.ray.Ray;

/**
 * This class represents a disk
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Disk extends Geometry{
    
    /**
     * The Point on the middle of the disk.
     */
    public final Point3 c;
    
    /**
     * The normal of the disk.
     */
    public final Normal3 n;
    
    /**
     * The radius of the disk.
     */
    public double r;
    
    /**
     * Construct the disk object.
     * 
     * @param c the point on the middle of the disk.
     * @param n the normal of the disk.
     * @param r the radius of the disk.
     * @param color the color of the disk.
     */
    public Disk(final Point3 c, final Normal3 n, double r, Color color) {
        super(color);
        this.c = c;
        this.n = n;
        this.r = r;
        
    }

    @Override
    public Hit hit(Ray ray) {
        double t = (c.sub(ray.o)).dot(n) / ray.d.dot(n);
        if(t < 0){
            return null;
        }
        Point3 p = ray.at(t);
        
        double d = p.sub(c).magnitude * p.sub(c).magnitude;
        
        return Math.sqrt(d) <= r ? new Hit(t, ray, this) : null;
        
    }
    
}
