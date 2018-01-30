package aufgabe3.material;

import aufgabe2.color.Color;
import aufgabe3.hit.Hit;
import aufgabe3.light.Light;
import aufgabe3.world.World;

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
    public Color colorFor(final Hit hit, final World world) {
        if(hit == null){
            throw new IllegalArgumentException("hit must not be null");
        }
        
        if(world == null){
            throw new IllegalArgumentException("world must not be null");
        }
    
        for(Light light : world.lights){
            Color cl = light.color;
            Color cd = this.color;
            Color ca = world.ambientColor;
       
            return cd.mul(ca).add(  cd.mul(cl).mul(Math.max(0, hit.n.dot(light.directionFrom(hit.ray.at(hit.t)).normalized())))  );
       
        }
       
       return color.mul(world.ambientColor);
       
    }


   
    
}
