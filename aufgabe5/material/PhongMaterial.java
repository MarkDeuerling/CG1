package aufgabe5.material;

import aufgabe5.mathLib.Normal3;
import aufgabe5.mathLib.Vector3;
import aufgabe2.color.Color;
import aufgabe5.hit.Hit;
import aufgabe5.light.Light;
import aufgabe5.world.World;
import java.util.Objects;

/**
 * This class represents a phong material.
 * 
 * @author Peter Albrecht, Stefan Streichern, Mark Deuerling.
 */
public class PhongMaterial implements Material{
   
    /**
     * The diffuse color.
     */
    public final Color diffuse;
    
    /**
     * The specular color.
     */
    public final Color specular;
    
    /**
     * The phong exponent.
     */
    public final int exponent;

    /**
     * Construct the phong material.
     * 
     * @param diffuse the diffuse color.
     * @param specular the specular color.
     * @param exponent the phong exponent.
     */
    public PhongMaterial(final Color diffuse, final Color specular, final int exponent) {
        if(diffuse == null){
            throw new IllegalArgumentException("diffuse must not be null");
        }
        if(specular == null){
            throw new IllegalArgumentException("specular must not be null");
        }
        
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
    }
    
    @Override
    final public Color colorFor(final Hit hit, final World world) {
        if(hit == null){
            throw new IllegalArgumentException("hit must not be null");
        }
        if(world == null){
            throw new IllegalArgumentException("world must not be null");
        }
        
//        Color cd = diffuse;
//        Color cs = specular;
        final int p = exponent;
        final Normal3 n = hit.n; //oberfläche normalisiert
        final Vector3 e = hit.ray.d.mul(-1).normalized(); //zum betrachter(deshalb *-1) normaliziert
        Color c = new Color(0, 0, 0);

        int count = 0;
        for(Light light : world.lights){
            if(light.illuminates(hit.ray.at(hit.t), world)){
                count++;
                final Vector3 l = light.directionFrom(hit.ray.at(hit.t)).normalized(); //lichtquelle normaliziert
                final Vector3 r = light.directionFrom(hit.ray.at(hit.t)).reflectedOn(n); //reflektiert
                final double ln = n.dot(l); //winkel ln
                final double er = e.dot(r); //winkel er 
                
                // c = cd ca + [cd * cl * max(0, n.*l) + cs * cl * (max(0, e.*r)^p)] <- muss durch lichter iteriert
                c = c.add(diffuse.mul(world.ambientColor).add( light.color.mul(diffuse).mul(Math.max(0, ln)) ).add(  light.color.mul(specular.mul(Math.pow(Math.max(0, er), p)))  ));
                
                if(count == world.lights.size())
                    return c;
                
             }
            c = c.add(diffuse.mul(world.ambientColor).mul(light.color));
        }
//       return diffuse.mul(world.ambientColor);
        return c;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.diffuse);
        hash = 97 * hash + Objects.hashCode(this.specular);
        hash = 97 * hash + this.exponent;
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
        final PhongMaterial other = (PhongMaterial) obj;
        if (!Objects.equals(this.diffuse, other.diffuse)) {
            return false;
        }
        if (!Objects.equals(this.specular, other.specular)) {
            return false;
        }
        if (this.exponent != other.exponent) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PhongMaterial{" + "diffuse=" + diffuse + ", specular=" + specular + ", exponent=" + exponent + '}';
    }
    
}
