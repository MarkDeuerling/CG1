package aufgabe3.light;

import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.color.Color;

/**
 * This class represents the Point light.
 * 
 * @author Peter Albrecht, Stefan Streichern, Mark Deuerling
 */
public class PointLight extends Light{
    
    /**
     * The position of the point light.
     */
    public final Point3 position;
    
    
    /**
     * Construcht the point light.
     * 
     * @param color the color of the point light.
     * @param position the position of the point light.
     */
    public PointLight(final Color color, final Point3 position){
        super(color);
        
        if(position == null){
            throw new IllegalArgumentException("position must not be null");
        }
        
        this.position = position;
        
    }

    @Override
    public boolean illuminates(Point3 point) {
       
       return true;
    }

    @Override
    public Vector3 directionFrom(Point3 point) {
        return position.sub(point).normalized();
    }
    
}
