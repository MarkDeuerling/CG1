package aufgabe6.cam;

import aufgabe6.mathLib.Point2;
import aufgabe6.mathLib.Point3;
import aufgabe6.mathLib.Vector3;
import aufgabe6.ray.Ray;
import aufgabe6.sampling.SamplingPattern;
import java.util.Collection;
import java.util.HashSet;

/**
 * This class represents the PerspectiveCamera.
 * 
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling
 */
public class PerspectiveCamera extends Camera{
    
    /**
     * The angle for the Camera.
     */
    final private double angle;

    /**
     * Construct the PerspectiveCamera object.
     * 
     * @param e : The eye point of the camera.
     * @param g : The gaze vector of the camera.
     * @param t : The tilt vector of the camera.
     * @param angle : The angle of the camera.
     * @param sp : 
     * @throws IllegalArgumentException : Is thrown if the parameter is null.
     */
    public PerspectiveCamera(Point3 e, Vector3 g, Vector3 t, double angle, final SamplingPattern sp) {
        super(e, g, t, sp);
        this.angle = angle;
        
    }
    
//    @Override
//    final public Ray rayFor(int w, int h, int x, int y) {
//        Vector3 assistVec = super.w.mul(-1.0).mul((h / 2.0) / Math.tan(angle)); 
//        Vector3 uVec = super.u.mul(x - ((w - 1.0) / 2.0));
//        Vector3 vVec = super.v.mul(y - ((h - 1.0) / 2.0));
//        Vector3 r = assistVec.add(uVec).add(vVec);
//        Vector3 d = r.normalized();
//        
//        return new Ray(super.e, d);
//    }
    
    @Override
    public Collection<Ray> rayFor(final int w, final int h, final int x, final int y) {
        Collection<Ray> rays = new HashSet<>();
        for(Point2 p : sp.points){
            Vector3 assistVec = super.w.mul(-1.0).mul((h / 2.0) / Math.tan(angle)); 
            Vector3 uVec = super.u.mul(x + p.sx - ((w - 1.0) / 2.0));
            Vector3 vVec = super.v.mul(y + p.sy - ((h - 1.0) / 2.0));
            Vector3 r = assistVec.add(uVec).add(vVec);
            Vector3 d = r.normalized();
            rays.add(new Ray(e, d));
        }
        
        return rays;
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
