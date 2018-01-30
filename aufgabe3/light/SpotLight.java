/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe3.light;

import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.color.Color;

/**
 * This class represents a spotlight.
 * 
 * @author Peter Albrecht, Stefan Streichern, Mark Deuerling
 */
public class SpotLight extends Light{

    /**
     * The position of the sportlight.
     */
    public final Point3 position;
    
    /**
     * The directeon to which the spotlight is pointing.
     */
    public final Vector3 direction;
    
    /**
     * The angle of the spotlight, the place that should be illuminates.
     */
    public double halfAngle;
    
    /**
     * Construct the spotlight.
     * @param color the color of the spotlight.
     * @param position the position of the spotlight.
     * @param direction the direction of the sportlight.
     * @param halfAngle the angle of the spotlight.
     */
    public SpotLight(final Color color, final Point3 position, final Vector3 direction, double halfAngle) {
        super(color);
        
        if(position == null){
            throw new IllegalArgumentException("position must not be null");
        }
        if(direction == null){
            throw new IllegalArgumentException("direction must not be null");
        }
        
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
        
    }

    @Override
    public boolean illuminates(Point3 point) {
        Vector3 v1 = point.sub(position).normalized();
        Vector3 v2 = direction;
        double angle = v1.dot(v2) / (v1.magnitude * v2.magnitude);
        angle = Math.acos(angle);
        
        return angle < halfAngle;

    }

    @Override
    public Vector3 directionFrom(Point3 point) {
        //point never used
        return position.sub(point).normalized();
    }
    
}
