package MathLib;

/**
 *
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Normal3 {
    
    /**
     * Get the x-coordinate of the normal.
     */
    public final double x;
    
    /**
     * Get the y-coordinate of the normal.
     */
    public final double y;
    
    /**
     * Get the z-coordinate of the normal.
     */
    public final double z;
    
    /**
     * Constructor of normal to create a normal.
     * 
     * @param x x-Coordinate.
     * @param y y-Coordinate.
     * @param z z-Coordinate.
     */
    public Normal3(double x,double y,double z){
        
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Multiply a normal with a double value.
     * 
     * @param n number that will be multiplicated with normal.
     * @return  Instance of Normal3 as result of method. 
     **/
    public Normal3 mul(double n){
        return new Normal3(x*n,y*n,z*n);
    }
    
    /**
     * Add two normals with each other.
     * 
     * @param n normal that will be added to normal.
     * @return Instance of Normal3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    public Normal3 add(final Normal3 n){
        if(n == null){
            throw new IllegalArgumentException("n must not be null");
        }
        return new Normal3(n.x+x,n.y+y,n.z+z);
    }
    
    /**
     * Calculate the scalar product of the normal and a vector.
     * 
     * @param v vector to scalar multiplicate with normal.
     * @return scalar product.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    public double dot(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
        return (x*v.x + y*v.y + z*v.z);
    }

    /**
     * Get the string of the normal.
     * 
     * @return String value.
     */
    @Override
    public String toString(){
        return  x+"\n"+y+"\n"+z;
    }

    /**
     * Generates hashCode.
     * 
     * @return int hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        return hash;
    }

    /**
     * Compares the normal with an object.
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
        final Normal3 other = (Normal3) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        return true;
    }
    
  
}
