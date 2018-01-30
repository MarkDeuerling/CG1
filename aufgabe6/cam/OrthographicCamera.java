package aufgabe6.cam;

import aufgabe6.mathLib.Point2;
import aufgabe6.mathLib.Point3;
import aufgabe6.mathLib.Vector3;
import aufgabe6.ray.Ray;
import aufgabe6.sampling.SamplingPattern;
import java.util.Collection;
import java.util.HashSet;

/**
 * This class represents an OrthographicCamera.
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class OrthographicCamera extends Camera{

    /**
     * The scaling value.
     */
    final private double s;
    
    /**
     * Construct the OrthographicCamera.
     * 
     * @param e : The eye Point.
     * @param g : The gaze Vector.
     * @param t : The tilt Vector.
     * @param s : The scale value.
     * @throws IllegalArgumentException : is thrown if the parameter is null.
     */
    public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t, double s, final SamplingPattern sp){
        super(e, g, t, sp);
        this.s = s;
       
    }

//    @Override
//    final public Ray rayFor(int w, int h, int x, int y) {
//        
//        Vector3 d = super.w.mul(-1);
//        double correctX = (x - ((w - 1.0) / 2.0)) / (w - 1.0);
//        double correctY = (y - ((h - 1.0) / 2.0)) / (h - 1.0);
//        
//        double a = (double)w / (double)h;
//        Vector3 correctWidht = super.u.mul(a * s * correctX);
//        Vector3 correctHeight = super.v.mul(s * correctY);
//        Point3 originOnWidth = super.e.add(correctWidht);
//        Point3 o = originOnWidth.add(correctHeight);
//        
//        return new Ray(o, d);
//        
//    }

    @Override
    public Collection<Ray> rayFor(int w, int h, int x, int y) {
        Collection<Ray> rays = new HashSet<>();
        for(Point2 p : sp.points){
            Vector3 d = super.w.mul(-1);
            double correctX = (x + p.sx - ((w - 1.0) / 2.0)) / (w - 1.0);
            double correctY = (y + p.sy - ((h - 1.0) / 2.0)) / (h - 1.0);

            double a = (double)w / (double)h;
            Vector3 correctWidht = super.u.mul(a * s * correctX);
            Vector3 correctHeight = super.v.mul(s * correctY);
            Point3 originOnWidth = super.e.add(correctWidht);
            Point3 o = originOnWidth.add(correctHeight);

            rays.add(new Ray(o, d));
        }
        
        return rays;
        
    }
    
    

    @Override
    public String toString() {
        return "OrthographicCamera{" + "s=" + s + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.s) ^ (Double.doubleToLongBits(this.s) >>> 32));
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
        final OrthographicCamera other = (OrthographicCamera) obj;
        if (Double.doubleToLongBits(this.s) != Double.doubleToLongBits(other.s)) {
            return false;
        }
        return true;
    }
    
    
}
