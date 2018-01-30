package aufgabe4.geo;

import MathLib.Normal3;
import MathLib.Point3;
import aufgabe2.ray.Ray;
import aufgabe4.hit.Hit;
import aufgabe4.material.Material;
import aufgabe5.world.World;
import java.util.Objects;

/**
 * This class represents a sphere object.
 * 
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling
 */
public class Sphere extends Geometry{

    /**
     * The point on the middle of the sphere.
     */
    public final Point3 c;
    
    /**
     * The radius of the sphere object.
     */
    public double r;
    
    /**
     * Construct a sphere object.
     * 
     * @param c the point on the middle of the sphere.
     * @param r the radius of the sphere.
     * @param material the material of the sphere.
     * @throws IllegalArgumentException is thrown if the given parameter are null.
     */
    public Sphere(final Point3 c, double r, Material material) {
        super(material);
        if(c == null){
            throw new IllegalArgumentException("c must not be null");
        }
        this.c = c;
        this.r = r;
    }

    @Override
    public Hit hit(final Ray ray) {
        
        double assistA = ray.d.dot(ray.d);
        double assistB = ray.d.dot(ray.o.sub(c).mul(2.0));
        double assistC = ray.o.sub(c).dot(ray.o.sub(c)) - r * r;
        
        double intersect = assistB * assistB - 4.0 * assistA * assistC;
       
        double t_plus  = (-assistB + Math.sqrt(intersect)) / (2.0 * assistA);
        double t_minus = (-assistB - Math.sqrt(intersect)) / (2.0 * assistA);
        
        if(intersect < 0){
            return null;
        }
        
        if(intersect > 0){
            if(t_minus > World.EPSYLON){ //smaller root
                Normal3 n = ray.at(t_minus).sub(this.c).normalized().asNormal();
                return new Hit(t_minus, ray, this, n);
            }
            if(t_plus > World.EPSYLON){ // larger root
                Normal3 n = ray.at(t_plus).sub(this.c).normalized().asNormal();
                return new Hit(t_plus, ray, this, n);
            }
        }
        return null; 
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.c);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.r) ^ (Double.doubleToLongBits(this.r) >>> 32));
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
        final Sphere other = (Sphere) obj;
        if (!Objects.equals(this.c, other.c)) {
            return false;
        }
        if (Double.doubleToLongBits(this.r) != Double.doubleToLongBits(other.r)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sphere{" + "c=" + c + ", r=" + r + '}';
    }
    
}
