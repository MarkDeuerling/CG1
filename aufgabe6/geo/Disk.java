package aufgabe6.geo;

import aufgabe6.mathLib.Normal3;
import aufgabe6.mathLib.Point3;
import aufgabe6.ray.Ray;
import aufgabe6.hit.Hit;
import aufgabe6.material.Material;
import java.util.Objects;

/**
 * This class represents a disk
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Disk extends Geometry{
    
    /**
     * The Point on the middle of the disk.
     */
    public final Point3 c;
    
    /**
     * The normal of the disk.
     */
    public final Normal3 n;
    
    /**
     * The radius of the disk.
     */
    public final double r;
    
    /**
     * Construct the disk object.
     * 
     * @param c the point on the middle of the disk.
     * @param n the normal of the disk.
     * @param r the radius of the disk.
     * @param material the given material of this object.
     */
    public Disk(final Point3 c, final Normal3 n, final double r, final Material material) {
        super(material);
        if(c == null){
            throw new IllegalArgumentException("c must not be null");
        }
        
        if(n == null){
            throw new IllegalArgumentException("n must not be null");
        }
        
        this.c = c;
        this.n = n;
        this.r = r;
        
    }

    @Override
    final public Hit hit(final Ray ray) {
        double t = (c.sub(ray.o)).dot(n) / ray.d.dot(n);
        if(t < 0){
            return null;
        }
        Point3 p = ray.at(t);
        
        double d = p.sub(c).magnitude * p.sub(c).magnitude;
        
        return Math.sqrt(d) <= r ? new Hit(t, ray, this, null) : null;
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.c);
        hash = 97 * hash + Objects.hashCode(this.n);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.r) ^ (Double.doubleToLongBits(this.r) >>> 32));
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
        final Disk other = (Disk) obj;
        if (!Objects.equals(this.c, other.c)) {
            return false;
        }
        if (!Objects.equals(this.n, other.n)) {
            return false;
        }
        if (Double.doubleToLongBits(this.r) != Double.doubleToLongBits(other.r)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Disk{" + "c=" + c + ", n=" + n + ", r=" + r + '}';
    }
    
    
}
