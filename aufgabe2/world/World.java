/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe2.world;

import aufgabe2.color.Color;
import aufgabe2.geometry.Geometry;
import aufgabe2.hit.Hit;
import aufgabe2.ray.Ray;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class World {
    
    public ArrayList<Geometry> geoList;
    public Color backgroundColor;   
    
    public World(Color backgroundColor){
        geoList = new ArrayList<>();
        this.backgroundColor = backgroundColor;
    
    }
    
    public Hit hit(Ray ray){
        
        return geoList.get(0).hit(ray);
    }
    
    public void addGeo(Geometry geo){
        geoList.add(geo);
    }
}

