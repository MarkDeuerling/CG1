package aufgabe3.geo;

import MathLib.Normal3;
import MathLib.Point3;
import aufgabe3.hit.Hit;
import aufgabe2.ray.Ray;
import aufgabe3.material.Material;
import java.util.Objects;

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
     * @param material the given material for this box.
     * @throws IllegalArgumentException is thrown if the given parameter are null;
     */
    public AxisAlignedBox(final Point3 lbf, final Point3 run, final Material material) {
        super(material);
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
    public Hit hit(final Ray ray) {
        
        final Plane left      = new Plane(new Normal3(-1, 0, 0), lbf, material);
        final Plane top       = new Plane(new Normal3(0, -1, 0), lbf, material);
        final Plane back      = new Plane(new Normal3(0, 0, -1), lbf, material);
        final Plane right     = new Plane(new Normal3(1, 0, 0), run, material);
        final Plane bottom    = new Plane(new Normal3(0, 1, 0), run, material);
        final Plane front     = new Plane(new Normal3(0, 0, 1), run, material);
        
        final Plane[] planeArray = {left, top, back, right, bottom, front};
        
        double gr = 0;
        for(final Plane pl : planeArray){
            double t = (pl.a.sub(ray.o)).dot(pl.n) / ray.d.dot(pl.n);
            gr = gr > t ? gr : t;
            
            final Point3 p = ray.at(t);
            
            if((ray.o.sub(pl.a)).dot(pl.n) > 0){
                
                if(pl.n.equals(new Normal3(1, 0, 0))||pl.n.equals(new Normal3(-1, 0, 0))){
                    if(p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) {
                        return new Hit(t, ray, this, pl.n);
                    }
                }
                if(pl.n.equals(new Normal3(0, 1, 0))||pl.n.equals(new Normal3(0, -1, 0))){
                    if(p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z) {
                        return new Hit(t, ray, this, pl.n);
                    }
                }
                if(pl.n.equals(new Normal3(0, 0, 1))||pl.n.equals(new Normal3(0, 0, -1))){
                    if(p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y) {
                        return new Hit(t, ray, this, pl.n);
                    }
                }
            }
        }
        
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.lbf);
        hash = 89 * hash + Objects.hashCode(this.run);
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
        final AxisAlignedBox other = (AxisAlignedBox) obj;
        if (!Objects.equals(this.lbf, other.lbf)) {
            return false;
        }
        if (!Objects.equals(this.run, other.run)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AxisAlignedBox{" + "lbf=" + lbf + ", run=" + run + '}';
    }
    
    
}