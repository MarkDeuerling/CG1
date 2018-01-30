package aufgabe2.hit;

import aufgabe2.geometry.Geometry;
import aufgabe2.ray.Ray;

/**
 * This class represents the hit.
 * 
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling.
 */
public class Hit {
    
    /**
     * The parameter of the hit point.
     */
    public double t;
    
    /**
     * The ray of the hit point.
     */
    public final Ray ray;
    
    /**
     * The geometry to check the hit point.
     */
    public final Geometry geo;
    
    /**
     * Check of an geometry object is intersected.
     * 
     * @param t the parameter of the hit point.
     * @param ray the ray of the hit point.
     * @param geo the geometry to be checked the hit point.
     * @throws IllegalArgumentException is thrown if the given parameter are null.
     */
    public Hit(double t, Ray ray, Geometry geo){
        if(ray == null){
            throw new IllegalArgumentException("ray must not be null");
        }
        if(geo == null){
            throw new IllegalArgumentException("geo must not be null");
        }
        
        this.t = t;
        this.ray = ray;
        this.geo = geo;
        
    }
    
}
