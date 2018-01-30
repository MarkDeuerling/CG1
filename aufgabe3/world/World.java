/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe3.world;

import aufgabe2.color.Color;
import aufgabe3.geo.Geometry;
import aufgabe3.hit.Hit;
import aufgabe2.ray.Ray;
import aufgabe3.light.Light;
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
            
            if(hit == null){
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

