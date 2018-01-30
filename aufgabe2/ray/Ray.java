package aufgabe2.ray;

import MathLib.Point3;
import MathLib.Vector3;
import aufgabe5.world.World;
import java.util.Objects;

/**
 * This class represents a ray object.
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Ray {

    /**
     * The origin of the ray.
     */
    public final Point3 o;
    
    /**
     * The direction of the ray.
     */
    public final Vector3 d;

    /**
     * Construct the a ray object.
     * 
     * @param o : the origing of the ray.
     * @param d : the direction of the ray.
     * @throws IllegalArgumentException : is thrown if the given parameter is null.
     */
    public Ray(final Point3 o, final Vector3 d) {
        if(o == null){
            throw new IllegalArgumentException("o must not be null");
        }
        if(d == null){
            throw new IllegalArgumentException("d must not be null");
        }
        this.o = o;
        this.d = d;

    }

    /**
     * The point of a vector line.
     * 
     * @param t : The given parameter of the vector line.
     * @return the point of the vector line.
     */
    public Point3 at(double t) {
        return o.add(d.mul(t));
    }

    /**
     * The value of the parameter of the point.
     * 
     * @param p : The point to get the parameter value.
     * @return the parameter value of the given point.
     * @throws IllegalArgumentException : Is thrown if the given parameter is null.
     */
    public double tOf(final Point3 p) {
        if(p == null){
            throw new IllegalArgumentException("p must not be null");
        }
        return o.sub(p).magnitude / d.magnitude;
        //return o.x-p.x/d.x;
    }

    @Override
    public String toString() {
        return "Ray{" + "o=" + o + ", d=" + d + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.o);
        hash = 83 * hash + Objects.hashCode(this.d);
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
        final Ray other = (Ray) obj;
        if (!Objects.equals(this.o, other.o)) {
            return false;
        }
        if (!Objects.equals(this.d, other.d)) {
            return false;
        }
        return true;
    }
    
    

}
