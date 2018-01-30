/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe2.camera;

import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.ray.Ray;

/**
 * This class represents an OrthographicCamera.
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class OrthographicCamera extends Camera{

    /**
     * The scaling value.
     */
    private double s;
    
    /**
     * Construct the OrthographicCamera.
     * 
     * @param e : The eye Point.
     * @param g : The gaze Vector.
     * @param t : The tilt Vector.
     * @param s : The scale value.
     * @throws IllegalArgumentException : is thrown if the parameter is null.
     */
    public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t, double s){
        super(e, g, t);
        this.s = s;
       
    }

    @Override
    public Ray rayFor(int w, int h, int x, int y) {
        
        Vector3 d = super.w.mul(-1);
        double correctX = (x - ((w - 1.0) / 2.0)) / (w - 1.0);
        double correctY = (y - ((h - 1.0) / 2.0)) / (h - 1.0);
        
        double a = (double)w / (double)h;
        Vector3 correctWidht = super.u.mul(a * s * correctX);
        Vector3 correctHeight = super.v.mul(s * correctY);
        Point3 originOnWidth = super.e.add(correctWidht);
        Point3 o = originOnWidth.add(correctHeight);
        
        return new Ray(o, d);
        
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
