package pathTracer;

import aufgabe2.color.Color;
import aufgabe6.hit.Hit;
import aufgabe6.ray.Ray;
import aufgabe6.world.World;

/**
 *
 * @author Mark Deueling
 */
public class PathTracer {
    
    public final World world;
    public int depth;
    public Ray ray;
    
    public PathTracer(final World world, final Ray ray){
        this.world = world;
        this.ray = ray;
        
    }
    
    final public Color traceRay(final Ray ray, final int depth){
        if(depth > world.depth){
            return world.backgroundColor;
        }else{
            Hit hit = world.hit(ray);
            if(hit != null){
                this.depth = depth;
                this.ray = ray;
                return hit.geo.material.colorFor(hit, world); // path shade
            }else{
                return world.backgroundColor;
            }
        }
    }
    
}
