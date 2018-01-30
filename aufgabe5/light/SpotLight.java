package aufgabe5.light;

import aufgabe5.mathLib.Point3;
import aufgabe5.mathLib.Vector3;
import aufgabe2.color.Color;
import aufgabe5.ray.Ray;
import aufgabe5.hit.Hit;
import aufgabe5.world.World;
import java.util.Objects;

/**
 * This class represents a spotlight.
 * 
 * @author Peter Albrecht, Stefan Streichern, Mark Deuerling
 */
public class SpotLight extends Light{

    /**
     * The position of the sportlight.
     */
    public final Point3 position;
    
    /**
     * The directeon to which the spotlight is pointing.
     */
    public final Vector3 direction;
    
    /**
     * The angle of the spotlight, the place that should be illuminates.
     */
    public double halfAngle;
    
    /**
     * Construct the spotlight.
     * @param color the color of the spotlight.
     * @param position the position of the spotlight.
     * @param direction the direction of the sportlight.
     * @param halfAngle the angle of the spotlight.
     * @param castsShadow if true a shadow will be shown on geometry.
     */
    public SpotLight(final Color color, final Point3 position, final Vector3 direction, double halfAngle, final boolean castsShadow) {
        super(color, castsShadow);
        
        if(position == null){
            throw new IllegalArgumentException("position must not be null");
        }
        if(direction == null){
            throw new IllegalArgumentException("direction must not be null");
        }
        
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
        
    }

    @Override
    public boolean illuminates(final Point3 point, final World world) {
        if(point == null){
            throw new IllegalArgumentException("point must not be null");
        }
        if(world == null){
            throw new IllegalArgumentException("world must not be null");
        }
        
        Vector3 v1 = point.sub(position).normalized();
        Vector3 v2 = direction;
        double angle = v1.dot(v2) / (v1.magnitude * v2.magnitude);
        angle = Math.acos(angle);
        
        if(castsShadow){
            Vector3 l = position.sub(point).normalized();
            double t_l = position.sub(point).magnitude / l.magnitude;
            Ray shadowRay = new Ray(point, l);
            Hit hit = world.hit(shadowRay);
            
            if(hit != null && angle < halfAngle && hit.t < t_l && hit.t > World.EPSYLON)
                return false;
        }
        return angle < halfAngle;

    }

    @Override
    public Vector3 directionFrom(Point3 point) {
        //point never used
        return position.sub(point).normalized();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.position);
        hash = 53 * hash + Objects.hashCode(this.direction);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.halfAngle) ^ (Double.doubleToLongBits(this.halfAngle) >>> 32));
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
        final SpotLight other = (SpotLight) obj;
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (!Objects.equals(this.direction, other.direction)) {
            return false;
        }
        if (Double.doubleToLongBits(this.halfAngle) != Double.doubleToLongBits(other.halfAngle)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SpotLight{" + "position=" + position + ", direction=" + direction + ", halfAngle=" + halfAngle + '}';
    }
    
}
