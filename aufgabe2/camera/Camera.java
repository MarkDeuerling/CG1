package aufgabe2.camera;

import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.ray.Ray;
import java.util.Objects;

/**
 * This class represents an abstract Camera.
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public abstract class Camera {
    /**
     * The eye point of the camera.
     */
    public final Point3  e;
    
    /**
     * The gaze vector of the camera.
     */
    public final Vector3 g;
    
    /**
     * The tilt vector of the camera.
     */
    public final Vector3 t;
    
    /**
     * The vector to calculate the raw of the view plane.
     */
    public final Vector3 u;
    
    /**
     * The vector to calculate the column of the view plane.
     */
    public final Vector3 v;
    
    /**
     * The vector that direct to the back of the camera.
     */
    public final Vector3 w;
    
    /**
     * Construct the Camera.
     * This class can only by created with they sub classes.
     * 
     * @param e : The eye point of the camera.
     * @param g : The gaze vector of the camera.
     * @param t : The tilt vector of the camera.
     * @throws IllegalArgumentException : is thrown if the given parameter is null.
     */
    public Camera(final Point3 e, final Vector3 g, final Vector3 t){
        if(e == null){
            throw new IllegalArgumentException("e must not be null");
        }
        if(g == null){
            throw new IllegalArgumentException("g must not be null");
        }
        if(t == null){
            throw new IllegalArgumentException("t must not be null");
        }
        this.e = e;
        this.g = g;
        this.t = t;
        
        w = g.normalized().mul(-1);
        u = t.x(w).normalized();
        v = w.x(u);
    }
    
    /**
     * Get the ray of view plane.
     * @param w : The width of the view plane.
     * @param h : The Hight of the view plane.
     * @param x : The x-position of the view plane.
     * @param y : The y-position of the view plane.
     * @return the ray of view plane.
     */
    public abstract Ray rayFor(int w, int h, int x, int y);

    @Override
    public String toString() {
        return "Camera{" + "e=" + e + ", g=" + g + ", t=" + t + ", u=" + u + ", v=" + v + ", w=" + w + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.e);
        hash = 97 * hash + Objects.hashCode(this.g);
        hash = 97 * hash + Objects.hashCode(this.t);
        hash = 97 * hash + Objects.hashCode(this.u);
        hash = 97 * hash + Objects.hashCode(this.v);
        hash = 97 * hash + Objects.hashCode(this.w);
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
        final Camera other = (Camera) obj;
        if (!Objects.equals(this.e, other.e)) {
            return false;
        }
        if (!Objects.equals(this.g, other.g)) {
            return false;
        }
        if (!Objects.equals(this.t, other.t)) {
            return false;
        }
        if (!Objects.equals(this.u, other.u)) {
            return false;
        }
        if (!Objects.equals(this.v, other.v)) {
            return false;
        }
        if (!Objects.equals(this.w, other.w)) {
            return false;
        }
        return true;
    }
    
    
}
