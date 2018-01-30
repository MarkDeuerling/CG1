package aufgabe5.world;

import aufgabe2.color.Color;
import aufgabe5.geo.Geometry;
import aufgabe5.hit.Hit;
import aufgabe5.ray.Ray;
import aufgabe5.light.Light;
import java.util.ArrayList;

/**
 * This class represents the world for all geometries and lights.
 * 
 * @author Peter Albrecht, Stefan Streichern, Mark Deuerling
 */
public class World {
    
    /**
     * The list of geometries.
     */
    public ArrayList<Geometry> geoList;
    
    /**
     * The background color of the world.
     */
    public final Color backgroundColor;   
    
    /**
     * The ambient color of the world.
     */
    public final Color ambientColor;
    
    /**
     * The list of lights of the world.
     */
    public ArrayList<Light> lights;
    
    public static final double EPSYLON = .000001;
    
    
    /**
     * Construct the World.
     * 
     * @param backgroundColor the background color of the world.
     * @param ambientColor the ambient color of the world.
     */
    public World(final Color backgroundColor, final Color ambientColor){
        if(backgroundColor == null){
            throw new IllegalArgumentException("background color must not be null");
        }
        if(ambientColor == null){
            throw new IllegalArgumentException("ambient color must not be null");
        }
        
        geoList = new ArrayList<>();
        this.backgroundColor = backgroundColor;
        this.ambientColor = ambientColor;
        lights = new ArrayList<>();
    }
    
    /**
     * The hit of the world.
     * @param ray the ray from a camera object.
     * @return the nearest hit on a geometrie to the camera.
     */
    public Hit hit(final Ray ray){
        Hit hit = null;
        
        for(Geometry geo : geoList){
            if(hit == null || hit.t < EPSYLON){ //schatten wäre unter der ebene?//kann weg, wenn bei ebene t>0 statt t>=0
                hit = geo.hit(ray);
            }else{
                if(geo.hit(ray) != null){
                    if(hit.t > geo.hit(ray).t)
                        hit = geo.hit(ray);   
                }   
            }
        }
        return hit;
        
    }
    
    public void addGeo(Geometry geo){
        geoList.add(geo);
    }
    
    public void addLight(Light light){
        lights.add(light);
    }
}

