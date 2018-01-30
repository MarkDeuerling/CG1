package aufgabe4.raytracer;

import aufgabe2.color.Color;
import aufgabe2.ray.Ray;
import aufgabe4.hit.Hit;
import aufgabe4.world.World;

/**
 *
 * @author Mark Deuerling
 */
public class Tracer {
    
    public final World world;
    public int depth;
    
    public Tracer(final World world){
        this.world = world;
        this.depth = 0;
        
    }
    
    public Color traceRay(final Ray ray, int depth){
        
        if(depth > world.depth){
            return world.backgroundColor;
        }else{
            Hit hit = world.hit(ray);
            if(hit != null){
                this.depth = depth;
                return hit.geo.material.colorFor(hit, world, this);
            }else{
                return world.backgroundColor;
            }
        }
    }
    
}
