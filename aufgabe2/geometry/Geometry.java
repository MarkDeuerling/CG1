package aufgabe2.geometry;

import aufgabe2.color.Color;
import aufgabe2.hit.Hit;
import aufgabe2.ray.Ray;


/**
 * This class represents a geometry
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deueling
 */
public abstract class Geometry {
    
    /**
     * The color of the geometry.
     */
    public final Color color;
    
    /**
     * Construct the geometry object.
     * Note: This class is abstract, use the subclasses.
     * 
     * @param color the color of all geometries, must not be null.
     * @throws IllegalArgumentException is thrown if the given parameter is null.
     */
    public Geometry(final Color color){
        if(color == null){
            throw new IllegalArgumentException("color must not be null");
        }
        this.color = color;
        
    }
    
    /**
     * Handle the hit on objects.
     * 
     * @param ray the ray to check the intersection.
     * @return the hit on the view.
     */
    public abstract Hit hit(final Ray ray);
}
