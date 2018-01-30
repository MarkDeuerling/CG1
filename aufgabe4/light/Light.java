package aufgabe4.light;

import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.color.Color;
import aufgabe4.world.World;
import java.util.Objects;

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
    
    public final boolean castsShadow;
    /**
     * Construct the Light(use the subclasses for delegation).
     * @param castsShadow if true a shadow will be shown on geometry.
     * @param color the color of the light.
     */
    public Light(final Color color, final boolean castsShadow){
        if(color == null){
            throw new IllegalArgumentException("color must not be null");
        }
        
        this.color = color;
        this.castsShadow = castsShadow;
        
    }
    /**
     * The illuminates place of the sceen.
     * @param point the point that check to illuminate.
     * @param world the world to get the geometries intersection.
     * @return true if the point is illuminates.
     */
    public abstract boolean illuminates(final Point3 point, final World world);
    
    /**
     * The direction to which the light points.
     * @param point of the geometry.
     * @return the normalized direction.
     */
    public abstract Vector3 directionFrom(final Point3 point);

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.color);
        hash = 89 * hash + (this.castsShadow ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Light other = (Light) obj;
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (this.castsShadow != other.castsShadow) {
            return false;
        }
        return true;
    }
    
}
