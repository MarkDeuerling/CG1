package aufgabe3.light;

import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.color.Color;
import java.util.Objects;

/**
 * This class represents a directional light.
 * 
 * @author Peter Albrecht, Stefan Streichern, Mark Deuerling
 */
public class DirectionLight extends Light{
    
    /**
     * The direction to whitch the light points.
     */
    public final Vector3 direction;

    /**
     * Construct the directional light.
     * 
     * @param color the color of the light.
     * @param direction the direction to whitch the light points.
     */
    public DirectionLight(final Color color, final Vector3 direction) {
        super(color);
        
        if(direction == null){
            throw new IllegalArgumentException("direction must not be null");
        }
        this.direction = direction;
        
    }

    @Override
    public boolean illuminates(final Point3 point) {
        return true;
    }

    @Override
    public Vector3 directionFrom(final Point3 point) {
        return direction.mul(-1).normalized();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.direction);
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
        final DirectionLight other = (DirectionLight) obj;
        if (!Objects.equals(this.direction, other.direction)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DirectionLight{" + "direction=" + direction + '}';
    }
    
}
