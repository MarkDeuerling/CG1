package aufgabe6.mathLib;
/**
 *
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Point3 {
    
    /**
     * Get the x-coordinate of the point.
     */
    public final double x;
    
    /**
     * Get the y-coordinate of the point.
     */
    public final double y;
    
    /**
     * Get the z-coordinate of the point.
     */
    public final double z;
    
    /**
     * Constructor of point to create a point.
     * 
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @param z z-Coordinate
     **/
    public Point3(double x, double y, double z){
        
        this.z = z;
        this.y = y;
        this.x = x;
        
    }
    
    /**
     * Subtract two points with each other.
     * 
     * @param p Point that will be subtracted from instance point.
     * @return instance of Vector3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    final public Vector3 sub(final Point3 p){
        if(p == null){
            throw new IllegalArgumentException("p must not be null");
        }
        return new Vector3(x-p.x, y-p.y, z-p.z);
    }
    
    /**
     * Substract the point from a vector.
     * 
     * @param v Vector you will subtract from point.
     * @return Instance of Point3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    final public Point3 sub(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
       return new Point3(x-v.x, y-v.y, z-v.z); 
    }
    
    /**
     * Add a vector to the point.
     * 
     * @param v Vector you want to add to your point.
     * @return  Instance of Point3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    final public Point3 add(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
        return new Point3(x+v.x, y+v.y, z+v.z);
    }

    /**
     * Get the String of Point3.
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
        int hash = 5;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        return hash;
    }

    /**
     * Compares the point with an object.
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
        final Point3 other = (Point3) obj;
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
