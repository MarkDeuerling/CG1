package aufgabe3.geo;

import aufgabe3.hit.Hit;
import aufgabe2.ray.Ray;
import aufgabe3.material.Material;


/**
 * This class represents a geometry
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deueling
 */
public abstract class Geometry {
    
    
    public Material material;
    
    /**
     * Construct the geometry object.
     * Note: This class is abstract, use the subclasses.
     * 
     * 
     * @throws IllegalArgumentException is thrown if the given parameter is null.
     */
    public Geometry(Material material){
        if(material == null){
            throw new IllegalArgumentException("material must not be null");
        }
        this.material = material;
        
    }
    
    /**
     * Handle the hit on objects.
     * 
     * @param ray the ray to check the intersection.
     * @return the hit on the view.
     */
    public abstract Hit hit(final Ray ray);
}
