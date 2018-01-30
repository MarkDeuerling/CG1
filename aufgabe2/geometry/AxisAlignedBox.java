package aufgabe2.geometry;

import MathLib.Normal3;
import MathLib.Point3;
import aufgabe2.color.Color;
import aufgabe2.hit.Hit;
import aufgabe2.ray.Ray;

/**
 * This class represents a Axis-Aligned-Box.
 * 
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling
 */
public class AxisAlignedBox extends Geometry{
    /**
     * The Point on back left.
     */
    public final Point3 lbf;
    
    /**
     * The Point on front right.
     */
    public final Point3 run;
    
    
    /**
     * Construct the Axis-Aligne-Box object.
     * 
     * @param lbf the point on the back left.
     * @param run the point on the front right.
     * @param color the color of the box.
     * @throws IllegalArgumentException is thrown if the given parameter are null;
     */
    public AxisAlignedBox(final Point3 lbf, final Point3 run, final Color color) {
        super(color);
        if(lbf == null){
            throw new IllegalArgumentException("lbf must not be null");
        }
        if(run == null){
            throw new IllegalArgumentException("run must not be null");
        }
        
        this.lbf = lbf;
        this.run = run;
        
    }

    
    @Override
    public Hit hit(Ray ray) {
        
        final Plane left      = new Plane(new Normal3(-1, 0, 0), lbf, color);
        final Plane top       = new Plane(new Normal3(0, -1, 0), lbf, color);
        final Plane back      = new Plane(new Normal3(0, 0, -1), lbf, color);
        final Plane right     = new Plane(new Normal3(1, 0, 0), run, color);
        final Plane bottom    = new Plane(new Normal3(0, 1, 0), run, color);
        final Plane front     = new Plane(new Normal3(0, 0, 1), run, color);
        
        final Plane[] planeArray = {left, top, back, right, bottom, front};
        
        double gr = 0;
        for(final Plane pl : planeArray){
            double t = (pl.a.sub(ray.o)).dot(pl.n) / ray.d.dot(pl.n);
            gr = gr > t ? gr : t;
            
            final Point3 p = ray.at(t);
            
            if((ray.o.sub(pl.a)).dot(pl.n) > 0){
                
                if(pl.n.equals(new Normal3(1, 0, 0))||pl.n.equals(new Normal3(-1, 0, 0))){
                    if(p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) {
                        return new Hit(t, ray, this);
                    }
                }
                if(pl.n.equals(new Normal3(0, 1, 0))||pl.n.equals(new Normal3(0, 1, 0))){
                    if(p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z) {
                        return new Hit(t, ray, this);
                    }
                }
                if(pl.n.equals(new Normal3(0, 0, 1))||pl.n.equals(new Normal3(0, 0, -1))){
                    if(p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y) {
                        return new Hit(t, ray, this);
                    }
                }
            }
        }
        
        return null;
    }
    
}