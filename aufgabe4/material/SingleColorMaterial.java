package aufgabe4.material;

import aufgabe2.color.Color;
import aufgabe4.hit.Hit;
import aufgabe4.light.Light;
import aufgabe4.raytracer.Tracer;
import aufgabe4.world.World;
import java.util.Objects;

/**
 * This class represents an single color as material.
 * 
 * @author Peter Albrecht, Stefan Streichern, Mark Deuerling.
 */
public class SingleColorMaterial implements Material{

    /**
     * The color of the material.
     */
    public final Color color;
    
    
    /**
     * Construct the single color material.
     * 
     * @param color the color of hte material.
     */
    public SingleColorMaterial(final Color color){
        if(color == null){
            throw new IllegalArgumentException("color must not be null");
        }
        this.color = color;
        
    }
    
    /**
     * Get the color of this material.
     * 
     * @param hit has no use, should be null.
     * @param world has no use, should be null.
     * @return the color of the material
     */
    @Override
    public Color colorFor(final Hit hit, final World world) {
        
        for(Light light : world.lights){
            if(light.illuminates(hit.ray.at(hit.t), world)){
                return color;
            }
        }
        return color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.color);
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
        final SingleColorMaterial other = (SingleColorMaterial) obj;
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SingleColorMaterial{" + "color=" + color + '}';
    }

    @Override
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
