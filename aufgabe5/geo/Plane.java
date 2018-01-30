package aufgabe5.geo;

import aufgabe5.mathLib.Normal3;
import aufgabe5.mathLib.Point3;
import aufgabe5.ray.Ray;
import aufgabe5.hit.Hit;
import aufgabe5.material.Material;
import static aufgabe5.world.World.EPSYLON;
import java.util.Objects;

/**
 * This class represents the plane object.
 * 
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling
 */
public class Plane extends Geometry{

    /**
     * The point on the plane.
     */
    public final Point3  a;
    
    /**
     * The normal of the plane.
     */
    public final Normal3 n;
    
    /**
     * Construct the plane object.
     * 
     * @param n the normal of the plane.
     * @param a the point of the plane.
     * @param material the material of the plane.
     * @throws IllegalArgumentException is thrown if the given parameter are null;
     */
    public Plane(final Normal3 n, final Point3 a, final Material material) {
        super(material);
        if(n == null){
            throw new IllegalArgumentException("n must not be null");
        }
        if(a == null){
            throw new IllegalArgumentException("a must not be null");
        }
        
        this.a = a;
        this.n = n;
        
    }
    
    public Plane(final Material material){
        super(material);
        a = new Point3(0, 0, 0);
        n = new Normal3(0, 1.0, 0);
        
    }

    @Override
    final public Hit hit(final  Ray ray) {
        double t = ((a.sub(ray.o)).dot(n)) / (ray.d.dot(n));
        
        //t>0 dann kann epsilon aus world raus, da nicht mehr schatten überdeckt wird
        return t >= 0 && t > EPSYLON ? new Hit(t, ray, this, n) : null;        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.a);
        hash = 83 * hash + Objects.hashCode(this.n);
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
        final Plane other = (Plane) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.n, other.n)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Plane{" + "a=" + a + ", n=" + n + '}';
    }
    
}
