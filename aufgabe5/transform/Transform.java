package aufgabe5.transform;

import aufgabe5.mathLib.Normal3;
import aufgabe5.ray.Ray;
import aufgabe5.mathLib.Mat4x4;
import aufgabe5.mathLib.Vector3;
import java.util.Objects;

/**
 * This class represents a transformation for geometries,
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Transform {
    
    /**
     * The matrix of this class.
     */
    public final Mat4x4 m;
    
    /**
     * The inverse matrix of this class.
     */
    public final Mat4x4 i;
    
    /**
     * Construct a transform object, not for public use.
     * @param m the matrix.
     * @param i the inverse matrix.
     * @throws IllegalArgumentException is thrown if the given parameters are null.
     */
    private Transform(Mat4x4 m, Mat4x4 i){
        if(m == null){
            throw new IllegalArgumentException("m must not be null");
        }
        
        if(i == null){
            throw new IllegalArgumentException("i must not be null");
        }
        this.m = m;
        this.i = i;
        
    }
    
    /**
     * Contruct a transform object.
     */
    public Transform(){
        m = new Mat4x4(1.0, 0, 0, 0, 
                       0, 1.0, 0, 0, 
                       0, 0, 1.0, 0, 
                       0, 0, 0, 1.0);
        
        i = new Mat4x4(1.0, 0, 0, 0, 
                       0, 1.0, 0, 0, 
                       0, 0, 1.0, 0, 
                       0, 0, 0, 1.0);
        
    }
    
    /**
     * Translate the position of a geometry.
     * @param dx the x direction.
     * @param dy the y direction.
     * @param dz the z direction.
     * @return a new transform-object for chaning.
     */
    final public Transform translate(final double dx, final double dy, final double dz){
        return new Transform(m.mul(new Mat4x4(1.0, 0, 0, dx, 
                                        0, 1.0, 0, dy, 
                                        0, 0, 1.0, dz, 
                                        0, 0, 0, 1.0)), 
                             new Mat4x4(1.0, 0, 0, -dx, 
                                        0, 1.0, 0, -dy, 
                                        0, 0, 1.0, -dz, 
                                        0, 0, 0, 1.0).mul(i));
    }
    
    /**
     * Scale a geometry.
     * @param sx scale in x direciton.
     * @param sy scale in y direction.
     * @param sz scale in z direction.
     * @return a new transform-object for chaning.
     */
    final public Transform scale(final double sx, final double sy, final double sz){
        return new Transform(m.mul(new Mat4x4(sx, 0, 0, 0, 
                                        0, sy, 0, 0, 
                                        0, 0, sz, 0, 
                                        0, 0, 0, 1)), 
                            new Mat4x4(1.0/sx, 0, 0, 0, 
                                        0, 1.0/sy, 0, 0, 
                                        0, 0, 1.0/sz, 0, 
                                        0, 0, 0, 1.0).mul(i));
    }
    
    /**
     * Rotation on the x-axis.
     * @param a the angle of rotation.
     * @return a new transform-object for chaning.
     */
    final public Transform rotateX(final double a){
        return new Transform(m.mul(new Mat4x4(1.0, 0, 0, 0, 
                                        0, Math.cos(a), -Math.sin(a), 0, 
                                        0, Math.sin(a), Math.cos(a), 0, 
                                        0, 0, 0, 1.0)), 
                            new Mat4x4(1.0, 0, 0, 0, 
                                        0, Math.cos(a), Math.sin(a), 0, 
                                        0, -Math.sin(a), Math.cos(a), 0, 
                                        0, 0, 0, 1.0).mul(i));
    }
    
    /**
     * Rotation on the y-axis.
     * @param a the angle of rotation.
     * @return a new tranform-object for chaning
     */
    final public Transform rotateY(final double a){
        return new Transform(m.mul(new Mat4x4(Math.cos(a), 0, Math.sin(a), 0, 
                                        0, 1.0, 0, 0, 
                                        -Math.sin(a), 0, Math.cos(a), 0, 
                                        0, 0, 0, 1.0)), 
                            new Mat4x4(Math.cos(a), 0, -Math.sin(a), 0, 
                                        0, 1.0, 0, 0, 
                                        Math.sin(a), 0, Math.cos(a), 0, 
                                        0, 0, 0, 1.0).mul(i));
    }
    
    /**
     * Rotation on the z-axis.
     * @param a the angle of rotation.
     * @return a new tranform-object for chaning
     */
    final public Transform rotateZ(final double a){
        return new Transform(m.mul(new Mat4x4(Math.cos(a), -Math.sin(a), 0, 0, 
                                        Math.sin(a), Math.cos(a), 0, 0, 
                                        0, 0, 1.0, 0, 
                                        0, 0, 0, 1.0)), 
                            new Mat4x4(Math.cos(a), Math.sin(a), 0, 0, 
                                        -Math.sin(a), Math.cos(a), 0, 0, 
                                        0, 0, 1.0, 0, 
                                        0, 0, 0, 1.0).mul(i));
    }
    
    /**
     * Multiple the given normal to get the new normal.
     * @param n the normal to multiple with.
     * @return new normal.
     */
    final public Normal3 mul(final Normal3 n){
        return (i.transpose().mul(new Vector3(n.x, n.y, n.z))).normalized().asNormal();
    }
    
    /**
     * Multiple the given ray to get a new ray.
     * @param r the ray to multiple with.
     * @return new normal.
     */
    final public Ray mul(final Ray r){
        return new Ray(i.mul(r.o), i.mul(r.d));
    }

    @Override
    public String toString() {
        return "Transform{" + "m=" + m + ", i=" + i + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.m);
        hash = 17 * hash + Objects.hashCode(this.i);
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
        final Transform other = (Transform) obj;
        if (!Objects.equals(this.m, other.m)) {
            return false;
        }
        if (!Objects.equals(this.i, other.i)) {
            return false;
        }
        return true;
    }
    
    

}
