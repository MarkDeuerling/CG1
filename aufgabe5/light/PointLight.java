package aufgabe5.light;

import aufgabe5.mathLib.Point3;
import aufgabe5.mathLib.Vector3;
import aufgabe2.color.Color;
import aufgabe5.ray.Ray;
import aufgabe5.hit.Hit;
import aufgabe5.world.World;
import java.util.Objects;

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
     * @param castsShadow if true a shadow will be shown on geometry.
     * @param position the position of the point light.
     */
    public PointLight(final Color color, final Point3 position, final boolean castsShadow){
        super(color, castsShadow);
        
        if(position == null){
            throw new IllegalArgumentException("position must not be null");
        }
        
        this.position = position;
        
    }

    @Override
    public boolean illuminates(final Point3 point, final World world) {
        if(point == null){
            throw new IllegalArgumentException("point must not be null");
        }
        if(world == null){
            throw new IllegalArgumentException("world must not be null");
        }
        if(castsShadow){
            Vector3 l = position.sub(point).normalized(); // umkehren, da strahl richtung lichtquelle, normalizieren, da richtiung
            double t_l =  position.sub(point).magnitude / l.magnitude;
            
            Ray shadowRay = new Ray(point, l);
            Hit hit = world.hit(shadowRay);
            
            if(hit != null && hit.t < t_l && hit.t > World.EPSYLON)
                return false;
            
        }
        return true;
    }

    @Override
    public Vector3 directionFrom(Point3 point) {
        return position.sub(point).normalized();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.position);
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
        final PointLight other = (PointLight) obj;
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PointLight{" + "position=" + position + '}';
    }
    
}
