package aufgabe4.material;

import MathLib.Normal3;
import MathLib.Vector3;
import aufgabe2.color.Color;
import aufgabe2.ray.Ray;
import aufgabe4.hit.Hit;
import aufgabe4.light.Light;
import aufgabe4.raytracer.Tracer;
import aufgabe4.world.World;

/**
 *
 * @author Mark Deuerling
 */
public class ReflectiveMaterial implements Material{
    
    public final Color diffuse;
    public final Color specular;
    public final int exponent;
    public final Color reflection;
    
    public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection){
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
        this.reflection = reflection;
        
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
        
        final int p = exponent;
        final Normal3 n = hit.n; //oberfläche normalisiert
        final Vector3 e = hit.ray.d.mul(-1).normalized(); //zum betrachter(deshalb *-1) normaliziert
        Color c = new Color(0, 0, 0);
        Color cr = reflection;

        for(Light light : world.lights){
            if(light.illuminates(hit.ray.at(hit.t), world)){
                final Vector3 l = light.directionFrom(hit.ray.at(hit.t)).normalized(); //lichtquelle normaliziert
                final Vector3 r = light.directionFrom(hit.ray.at(hit.t)).reflectedOn(n); //reflektiert
                final double ln = n.dot(l); //winkel ln
                final double er = e.dot(r); //winkel er 
                
                c = c.add(diffuse.mul(light.color.mul(Math.max(0, ln))) ).add( specular.mul(light.color.mul(Math.pow(Math.max(0, er), exponent))) );   
            }
        }
        
        final Vector3 rd = e.reflectedOn(n);
        Ray ray = new Ray(hit.ray.at(hit.t), rd);
        return (diffuse.mul(world.ambientColor)).add(c).add(cr.mul(tracer.traceRay(ray, tracer.depth)));
    }
    
}
