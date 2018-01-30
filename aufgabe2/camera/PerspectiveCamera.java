package aufgabe2.camera;

import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.ray.Ray;

/**
 * This class represents the PerspectiveCamera.
 * 
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling
 */
public class PerspectiveCamera extends Camera{
    
    /**
     * The angle for the Camera.
     */
    private double angle;

    /**
     * Construct the PerspectiveCamera object.
     * 
     * @param e : The eye point of the camera.
     * @param g : The gaze vector of the camera.
     * @param t : The tilt vector of the camera.
     * @param angle : The angle of the camera.
     * @throws IllegalArgumentException : Is thrown if the parameter is null.
     */
    public PerspectiveCamera(Point3 e, Vector3 g, Vector3 t, double angle) {
        super(e, g, t);
        this.angle = angle;
        
    }
    
    @Override
    public Ray rayFor(int w, int h, int x, int y) {
        Vector3 assistVec = super.w.mul(-1.0).mul((h / 2.0) / Math.tan(angle)); 
        Vector3 uVec = super.u.mul(x - ((w - 1.0) / 2.0));
        Vector3 vVec = super.v.mul(y - ((h - 1.0) / 2.0));
        Vector3 r = assistVec.add(uVec).add(vVec);
        Vector3 d = r.normalized();
        
        return new Ray(super.e, d);
    }

    @Override
    public String toString() {
        return "PerspectiveCamera{" + "angle=" + angle + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.angle) ^ (Double.doubleToLongBits(this.angle) >>> 32));
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
        final PerspectiveCamera other = (PerspectiveCamera) obj;
        if (Double.doubleToLongBits(this.angle) != Double.doubleToLongBits(other.angle)) {
            return false;
        }
        return true;
    }
    
    
}
