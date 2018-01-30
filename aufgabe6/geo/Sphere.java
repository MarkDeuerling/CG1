package aufgabe6.geo;

import aufgabe6.mathLib.Normal3;
import aufgabe6.mathLib.Point3;
import aufgabe6.ray.Ray;
import aufgabe6.hit.Hit;
import aufgabe6.material.Material;
import aufgabe6.world.World;
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
    public Sphere(final Point3 c, double r, final Material material) {
        super(material);
        if(c == null){
            throw new IllegalArgumentException("c must not be null");
        }
        this.c = c;
        this.r = r;
    }
    
    public Sphere(final Material material){
        super(material);
        c = new Point3(0, 0, 0);
        r = 1.0;
    }

    @Override
    final public Hit hit(final Ray ray) {
        double t;
        double assistA = ray.d.dot(ray.d);
        double assistB = ray.d.dot(ray.o.sub(c).mul(2.0));
        double assistC = ray.o.sub(c).dot(ray.o.sub(c)) - r * r;
        
        double intersect = assistB * assistB - 4.0 * assistA * assistC;
       
        double t_plus  = (-assistB + Math.sqrt(intersect)) / (2.0 * assistA); 
        double t_minus = (-assistB - Math.sqrt(intersect)) / (2.0 * assistA);
        
        t = Math.min(t_plus, t_minus);
        
        Normal3 n = ray.at(t).sub(this.c).normalized().asNormal();
        
        return t > World.EPSYLON ? new Hit(t, ray, this, n) : null;    
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
