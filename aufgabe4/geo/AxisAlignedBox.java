package aufgabe4.geo;

import MathLib.Normal3;
import MathLib.Point3;
import aufgabe4.hit.Hit;
import aufgabe2.ray.Ray;
import aufgabe4.material.Material;
import aufgabe4.world.World;
import java.util.ArrayList;
import java.util.List;
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
    
    public final Plane[] planeArray;
    
    
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
        
        final Plane left      = new Plane(new Normal3(-1, 0, 0), lbf, material);
        final Plane top       = new Plane(new Normal3(0, -1, 0), lbf, material);
        final Plane back      = new Plane(new Normal3(0, 0, -1), lbf, material);
        final Plane right     = new Plane(new Normal3(1, 0, 0), run, material);
        final Plane bottom    = new Plane(new Normal3(0, 1, 0), run, material);
        final Plane front     = new Plane(new Normal3(0, 0, 1), run, material);
        planeArray = new Plane[]{left, front, bottom, back, top, right};
        
        
    }
    
    @Override
    public Hit hit(final Ray ray) {
        List<Hit> hits = new ArrayList<>();
        for(final Plane plane : planeArray){
            hits.add(plane.hit(ray));
        }
        
        List<Hit> hList = new ArrayList<>();
        for(Hit hit : hits){
            if(hit == null) continue;
            
            final Point3 p = ray.at(hit.t);
            
            if(hit.n.equals(new Normal3(1, 0, 0))||hit.n.equals(new Normal3(-1, 0, 0))){
                if(p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) {
                    hList.add(hit);
                }
            }
            if(hit.n.equals(new Normal3(0, 1, 0))||hit.n.equals(new Normal3(0, -1, 0))){
                if(p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z) {
                    hList.add(hit);
                }
            }
            if(hit.n.equals(new Normal3(0, 0, 1))||hit.n.equals(new Normal3(0, 0, -1))){
                if(p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y) {
                    hList.add(hit);
                }
            }
        }
        Hit h = null;
        for(Hit hit1 : hList){
            if(h == null){
                h = hit1;
            }else{
                if(h != null && h.t > hit1.t)
                    h = hit1;
            }
        }
        return h;
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