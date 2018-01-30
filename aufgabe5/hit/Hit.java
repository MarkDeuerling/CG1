package aufgabe5.hit;

import aufgabe5.mathLib.Normal3;
import aufgabe5.ray.Ray;
import aufgabe5.geo.Geometry;
import java.util.Objects;

/**
 * This class represents the hit.
 * 
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling.
 */
public class Hit {
    
    /**
     * The parameter of the hit point.
     */
    public final double t;
    
    /**
     * The ray of the hit point.
     */
    public final Ray ray;
    
    /**
     * The geometry to check the hit point.
     */
    public final Geometry geo;
    
    /**
     * The normal of a geometry.
     */
    public final Normal3 n;
    
    /**
     * Check of an geometry object is intersected.
     * 
     * @param t the parameter of the hit point.
     * @param ray the ray of the hit point.
     * @param geo the geometry to be checked the hit point.
     * @param n the normal of the geometry.
     * @throws IllegalArgumentException is thrown if the given parameter are null.
     */
    public Hit(double t, final Ray ray, final Geometry geo, final Normal3 n){
        if(ray == null){
            throw new IllegalArgumentException("ray must not be null");
        }
        if(geo == null){
            throw new IllegalArgumentException("geo must not be null");
        }
        
        if(n == null){
            throw new IllegalArgumentException("n must not be null");
        }
        
        this.t = t;
        this.ray = ray;
        this.geo = geo;
        this.n = n;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.t) ^ (Double.doubleToLongBits(this.t) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.ray);
        hash = 41 * hash + Objects.hashCode(this.geo);
        hash = 41 * hash + Objects.hashCode(this.n);
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
        final Hit other = (Hit) obj;
        if (Double.doubleToLongBits(this.t) != Double.doubleToLongBits(other.t)) {
            return false;
        }
        if (!Objects.equals(this.ray, other.ray)) {
            return false;
        }
        if (!Objects.equals(this.geo, other.geo)) {
            return false;
        }
        if (!Objects.equals(this.n, other.n)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hit{" + "t=" + t + ", ray=" + ray + ", geo=" + geo + ", n=" + n + '}';
    }
    
}
