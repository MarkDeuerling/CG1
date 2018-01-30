package MathLib;

/**
 *
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Mat3x3 {
    
   /**
     * Get the matrix coordinate in first raw, first column.
     */
    public final double m11;
    
    /**
     * Get the matrix coordinate in first raw, second column.
     */
    public final double m12;
    
    /**
     * Get the matrix coordinate in first raw, third column.
     */
    public final double m13;
    
    /**
     * Get the matrix coordinate in second raw, first colum.
     */
    public final double m21;
    
    /**
     * Get the matrix coordinate in second raw, second colum.
     */
    public final double m22;
    
    /**
     * Get the matrix coordinate in second raw, third colum.
     */
    public final double m23;
    
    /**
     * Get the matrix coordinate in third raw, first colum.
     */
    public final double m31;
    
    /**
     * Get the matrix coordinate in third raw, second colum.
     */
    public final double m32;
    
    /**
     * Get the matrix coordinate in third raw, third colum.
     */
    public final double m33;
    
    /**
     * Get the matrix determinant.
     */
    public final double determinante;
   
    
    
    /**
     * Constructor of class Mat3x3 to create a matrix.
     *
     * @param m11 Coordinate of first row, first column.
     * @param m12 Coordinate of first row, second column.
     * @param m13 Coordinate of first row, third column.
     * @param m21 Coordinate of second row, first column.
     * @param m22 Coordinate of second row, second column.
     * @param m23 Coordinate of second row, third column.
     * @param m31 Coordinate of third row, first column.
     * @param m32 Coordinate of third row, second column.
     * @param m33 Coordinate of third row, third column.
     *
     */
    public Mat3x3(double m11,double m12, double m13, double m21, double m22, double m23, double m31, double m32, double m33){
        
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        
        determinante = (m11 * m22 * m33) + (m12 * m23 * m31) + (m13 * m21 * m32)
                     - (m31 * m22 * m13) - (m32 * m23 * m11) - (m33 * m21 * m12);
    }
    
    /**
     * Multiply two matrix with each other.
     * 
     * @param l Matrix that will be multiplicated with.
     * @return Instance of Mat3x3 as result of method. 
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    public Mat3x3 mul(final Mat3x3 l){
        if(l == null){
            throw new IllegalArgumentException("l must not be null");
        }
        double t11,t12,t13;
        double t21,t22,t23;
        double t31,t32,t33;
        
        t11 = (m11*l.m11)+(m12*l.m21)+(m13*l.m31);
        t12 = (m11*l.m12)+(m12*l.m22)+(m13*l.m32);
        t13 = (m11*l.m13)+(m12*l.m23)+(m13*l.m33);
        
        t21 = (m21*l.m11)+(m22*l.m21)+(m23*l.m31);
        t22 = (m21*l.m12)+(m22*l.m22)+(m23*l.m32);
        t23 = (m21*l.m13)+(m22*l.m23)+(m23*l.m33);
        
        t31 = (m31*l.m11)+(m32*l.m21)+(m33*l.m31);
        t32 = (m31*l.m12)+(m32*l.m22)+(m33*l.m32);
        t33 = (m31*l.m13)+(m32*l.m23)+(m33*l.m33);
        
        return new Mat3x3(t11, t12, t13, 
                          t21, t22, t23, 
                          t31, t32, t33);
    }
    
    /**
     * Multiply a matrix with a vector.
     * 
     * @param v vector to multiplicate the matrix.
     * @return Instance of Vector3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    public Vector3 mul(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
        
        double vx, vy, vz;
        
        vx = m11*v.x + m12*v.y + m13*v.z;
        vy = m21*v.x + m22*v.y + m23*v.z;
        vz = m31*v.x + m32*v.y + m33*v.z;
        
        return new Vector3(vx, vy, vz); 
    }
    
    /** 
     * Multiply a matrix with a point.
     * 
     * @param p point to multiplicate the matrix.
     * @return Instance of Point3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    public Point3 mul(final Point3 p){
        if(p == null){
            throw new IllegalArgumentException("p must not be null");
        }
        double px, py, pz;
        
        px = m11*p.x + m12*p.y + m13*p.z;
        py = m21*p.x + m22*p.y + m23*p.z;
        pz = m31*p.x + m32*p.y + m33*p.z;
        
        return new Point3(px, py, pz); 
    }
    
    /**
     * Refill column1 with new values from vector.
     * 
     * @param v vector to refill column1.
     * @return Instance of Mat3x3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null
     **/
    public Mat3x3 changeCol1(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
        return new Mat3x3(v.x, m12, m13, 
                          v.y, m22, m23, 
                          v.z, m32, m33);
    }
        
    /**
     * Refill column2 with new values from vector.
     * 
     * @param v vector to refill column1.
     * @return Instance of Mat3x3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    public Mat3x3 changeCol2(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
        return new Mat3x3(m11, v.x, m13, 
                          m21, v.y, m23, 
                          m31, v.z, m33);
    }
    
    /**
     * Refill column3 with new values from vector.
     * 
     * @param v vector to refill column1.
     * @return Instance of Mat3x3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    public Mat3x3 changeCol3(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
        return new Mat3x3(m11, m12, v.x, 
                          m21, m22, v.y, 
                          m31, m32, v.z);
    }
    
    /**
     * Get the String of Mat3x3.
     * 
     * @return String value.
     */
    @Override
    public String toString(){
        return m11+" "+m12+" "+m13+"\n"+
               m21+" "+m22+" "+m23+"\n"+
               m31+" "+m32+" "+m33;
    }

    /**
     * Generates hashCode.
     * 
     * @return int hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.m11) ^ (Double.doubleToLongBits(this.m11) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.m12) ^ (Double.doubleToLongBits(this.m12) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.m13) ^ (Double.doubleToLongBits(this.m13) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.m21) ^ (Double.doubleToLongBits(this.m21) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.m22) ^ (Double.doubleToLongBits(this.m22) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.m23) ^ (Double.doubleToLongBits(this.m23) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.m31) ^ (Double.doubleToLongBits(this.m31) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.m32) ^ (Double.doubleToLongBits(this.m32) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.m33) ^ (Double.doubleToLongBits(this.m33) >>> 32));
        return hash;
    }

    /**
     * Compares matrix with an object.
     * 
     * @param obj
     * @return true if matching.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mat3x3 other = (Mat3x3) obj;
        if (Double.doubleToLongBits(this.m11) != Double.doubleToLongBits(other.m11)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m12) != Double.doubleToLongBits(other.m12)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m13) != Double.doubleToLongBits(other.m13)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m21) != Double.doubleToLongBits(other.m21)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m22) != Double.doubleToLongBits(other.m22)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m23) != Double.doubleToLongBits(other.m23)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m31) != Double.doubleToLongBits(other.m31)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m32) != Double.doubleToLongBits(other.m32)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m33) != Double.doubleToLongBits(other.m33)) {
            return false;
        }
        return true;
    }
    
}

