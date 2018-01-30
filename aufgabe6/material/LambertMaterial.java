package aufgabe6.material;

import aufgabe2.color.Color;
import aufgabe6.hit.Hit;
import aufgabe6.light.AmbientOccluder;
import aufgabe6.light.Light;
import aufgabe6.mathLib.Vector3;
import aufgabe6.world.World;
import java.util.Objects;

/**
 * This class represents the lambert material.
 * 
 * @author Peter Albrecht, Stefan Streichern, Mark Deuerling
 */
public class LambertMaterial implements Material{
    
    /**
     * The color of the lamber material.
     */
    public final Color color;
    
    /**
     * Construct the lamber material.
     * 
     * @param color the color of the lamber material.
     */
    public LambertMaterial(final Color color){
        if(color == null){
            throw new IllegalArgumentException("color must not be null");
        }
        
        this.color = color;
    }

    @Override
    final public Color colorFor(final Hit hit, final World world) {
        if(hit == null){
            throw new IllegalArgumentException("hit must not be null");
        }
        
        if(world == null){
            throw new IllegalArgumentException("world must not be null");
        }
    
        Color cd = this.color;
        Color cl;
        Color c = new Color(0, 0, 0);
        
        for(Light light : world.lights){
            if(light instanceof AmbientOccluder){
                AmbientOccluder ao = (AmbientOccluder)light;
                ao.w = new Vector3(hit.n.x, hit.n.y, hit.n.z);
                ao.v = ao.w.x(new Vector3(.0072, 1.0, .0034));
                ao.v.normalized();
                ao.u = ao.v.x(ao.w);
                ao.u.normalized();
            }
            if(light.illuminates(hit.ray.at(hit.t), world)){
                cl = light.color;
//                if(light instanceof AmbientOccluder)continue;
                c = c.add(  cd.mul(cl).mul(Math.max(0, hit.n.dot(light.directionFrom(hit.ray.at(hit.t)).normalized())))  );
            }
        }
       return c.add(cd.mul(world.aC));

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.color);
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
        final LambertMaterial other = (LambertMaterial) obj;
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LambertMaterial{" + "color=" + color + '}';
    }
    
}
