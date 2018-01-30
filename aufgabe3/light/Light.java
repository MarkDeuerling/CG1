package aufgabe3.light;

import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.color.Color;

/**
 * This class represtens the abstract light.
 * 
 * @author Peter Albrecht, Stefan Streichern, Mark Deuerling
 */
public abstract class Light {
    
    /**
     * The color of the light.
     */
    public final Color color;
    
    /**
     * Construct the Light(use the subclasses for delegation).
     * 
     * @param color the color of the light.
     */
    public Light(final Color color){
        if(color == null){
            throw new IllegalArgumentException("color must not be null");
        }
        
        this.color = color;
        
    }
    /**
     * The illuminates place of the sceen.
     * @param point the point that check to illuminate.
     * @return true if the point is illuminates.
     */
    public abstract boolean illuminates(final Point3 point);
    public abstract Vector3 directionFrom(final Point3 point);
}
