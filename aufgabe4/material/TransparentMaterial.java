package aufgabe4.material;

import MathLib.Normal3;
import MathLib.Vector3;
import aufgabe2.color.Color;
import aufgabe2.ray.Ray;
import aufgabe4.hit.Hit;
import aufgabe4.raytracer.Tracer;
import aufgabe4.world.World;

/**
 *
 * @author Mark Deuerling
 */
public class TransparentMaterial implements Material{
    
    public final double indexOfRefraction;
    public int depth;
    
    public TransparentMaterial(final double indexOfRefraction){
        this.indexOfRefraction = indexOfRefraction;
        
    }

    @Override
    public Color colorFor(Hit hit, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        if(hit == null){
            throw new IllegalArgumentException("hit must not be null");
        }
        if(world == null){
            throw new IllegalArgumentException("world must not be null");
        }
        
        Normal3 n = hit.n; //oberfläche normalisiert
        final Vector3 e = hit.ray.d.mul(-1).normalized(); //zum betrachter(deshalb *-1) normaliziert

        final Vector3 rd = e.reflectedOn(n);
        double n1 = world.indexOfRefraction;
        double n2 = indexOfRefraction;
        double cosPhy1 = e.dot(n);                  
        double cosPhy2 = Math.sqrt( 1 - Math.pow(n1 / n2, 2) * (1 - (cosPhy1 * cosPhy1)) );
        Vector3 t = (hit.ray.d.mul(n1 / n2)).sub( n.mul(cosPhy2 - ((n1 / n2) * cosPhy1)) );
        double r0 = Math.pow(((n1 - n2) / (n1 + n2)), 2);
        double tir = 1.0 - (1.0 - cosPhy1 * cosPhy1) / Math.pow(n2 / n1, 2);
        if(n.dot(e) < 0.0){
            n = n.mul(-1);
            cosPhy1 = e.dot(n);                  
            cosPhy2 = Math.sqrt( 1 - (Math.pow(n2 / n1, 2)) * (1 - (cosPhy1 * cosPhy1)) );
            t = (hit.ray.d.mul(n2 / n1)).sub( n.mul(cosPhy2 - ((n2 / n1) * cosPhy1)) );
            r0 = Math.pow(((n2 - n1) / (n2 + n1)), 2);
            tir = 1.0 - (1.0 - cosPhy1 * cosPhy1) / Math.pow(n1 / n2, 2);
        }
                    
        if(tir < 0.0){ // isoliert zb. air bubbles in water
            Ray ray = new Ray(hit.ray.at(hit.t), rd);
            return tracer.traceRay(ray, tracer.depth);
        }else{
            double R = r0 + (1 - r0) * Math.pow(1 - cosPhy1, 5);
            double T = 1 - R;

            Ray rayt = new Ray(hit.ray.at(hit.t), t);  
            Ray ray  = new Ray(hit.ray.at(hit.t), rd);
            
            return tracer.traceRay(ray, tracer.depth+1).mul(R).add(tracer.traceRay(rayt, tracer.depth).mul(T));
        }
    }
}
