package aufgabe5.material;

import aufgabe2.color.Color;
import aufgabe5.hit.Hit;
import aufgabe5.light.Light;
import aufgabe5.world.World;
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
        Color ca = world.ambientColor;
        Color cl;
        
        for(Light light : world.lights){
            if(light.illuminates(hit.ray.at(hit.t), world)){
                cl =light.color;
                
                return cd.mul(ca).add(  cd.mul(cl).mul(Math.max(0, hit.n.dot(light.directionFrom(hit.ray.at(hit.t)).normalized())))  );
            }
        }
       return color.mul(world.ambientColor);

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
