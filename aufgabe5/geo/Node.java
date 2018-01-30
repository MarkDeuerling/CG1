package aufgabe5.geo;

import aufgabe5.hit.Hit;
import aufgabe5.material.Material;
import aufgabe5.ray.Ray;
import java.util.List;
import aufgabe5.transform.Transform;
import java.util.Objects;

/**
 * This class represents a Node with other geometries.
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling.
 */
public class Node extends Geometry{

    /**
     * The transform of the class.
     */
    public final Transform trans;
    
    /**
     * A list of geometries.
     */
    public final List<Geometry> geoList;
    
    /**
     * Construct a Node to transform geometries.
     * @param material the material of all geometries that will be transformed.
     * @param trans the transformation that will pass to the geometries.
     * @param geoList the list of geometries that will be tranformed.
     */
    public Node(Material material, Transform trans, List<Geometry> geoList) {
        super(material);
        this.trans = trans;
        this.geoList = geoList;
        
    }

    @Override
    public Hit hit(Ray ray) {
        Ray transformedRay = trans.mul(ray);
        Hit hit = null;
        for(Geometry geo : geoList){
            Hit geoHit = geo.hit(transformedRay);
            if(hit == null){
                hit = geoHit;
            }
            if(geoHit != null && hit.t < geoHit.t){
                hit = geoHit;
            }
        }
        
        return hit != null ? new Hit(hit.t, ray, hit.geo, trans.mul(hit.n)) : null;
    }

    @Override
    public String toString() {
        return "Node{" + "trans=" + trans + ", geoList=" + geoList + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.trans);
        hash = 67 * hash + Objects.hashCode(this.geoList);
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
        final Node other = (Node) obj;
        if (!Objects.equals(this.trans, other.trans)) {
            return false;
        }
        if (!Objects.equals(this.geoList, other.geoList)) {
            return false;
        }
        return true;
    }
    
    
    
}
