package aufgabe6.mathLib;

/**
 *
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Vector3 {
    
    /**
     * Get the x-coordinate of the vector.
     */
    public final double x;
    
    /**
     * Get the y-coordinate of the vector.
     */
    public final double y;
    
    /**
     * Get the z-coordinate of the vector.
     */
    public final double z;
    
    /**
     * Get the magnitude (length) of the vector.
     */
    public final double magnitude;
    
    /**
     * Constructor of Vector3 to create a vector.
     * 
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @param z z-Coordinate
     **/
    public Vector3(double x, double y,double z){
        
        double pytago;
        
        this.x = x;
        this.y = y;
        this.z = z;
        
        pytago = (x*x) + (y*y) + (z*z);
        
        magnitude = Math.sqrt(pytago);
    }
    
    /**
     * Add a normal to the vector.
     * 
     * @param n normal that will be added to vector.
     * @return Instance of Vector3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     */
    final public Vector3 add(final Normal3 n){
        if(n == null){
            throw new IllegalArgumentException("n must not be null");
        }
        return new Vector3(x + n.x, y + n.y, z + n.z);
    }
    
    /**
     * Add two vector with each other.
     * 
     * @param v vector that will be added to vector.
     * @return  Instance of Vector3 as result of methode. 
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    final public Vector3 add(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }
    
    /**
     * Subtract the vector from a normal.
     * 
     * @param n normal that will be subtract from vector.
     * @return  Instance of Vector3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    final public Vector3 sub(final Normal3 n){
        if(n == null){
            throw new IllegalArgumentException("n must not be null");
        }
        return new Vector3(x - n.x, y - n.y, z - n.z);
    }
    
    final public Vector3 sub(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }
    /**
     * Multiply the vector with a double value.
     * 
     * @param c scalar that will be multiplicated with vector.
     * @return Instance of Vector3 as result of methode.
     **/
    final public Vector3 mul(double c){
        return new Vector3(x*c, y*c, z*c);
    }
    
    /**
     * Calculate the scalar product from a the vector and a normal.
     * 
     * @param n normal to compute scalar product.
     * @return double value as scalar product.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    final public double dot(final Normal3 n){
        if(n == null){
            throw new IllegalArgumentException("n must not be null");
        }
        return (x*n.x + y*n.y + z*n.z);
    }
    
    /**
     * Calculate the scalar product from the vector and an other vector.
     * 
     * @param v vector to compute scalar product.
     * @return double value as scalar product.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    final public double dot(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
        return (x*v.x + y*v.y + z*v.z);
    }

    /**
     * Calculate the Normalized vector.
     * 
     * @return instance of Vector3 as result of method.
     **/
    final public Vector3 normalized(){
        return new Vector3(x/magnitude, y/magnitude, z/magnitude);
    }
    
    /**
     * Transfers vector values to normal.
     * 
     * @return instance of Normal3 as result of method.
     **/
    final public Normal3 asNormal(){
        return new Normal3(x, y, z);
    }
    
    /**
     * Reflect a vector on a normal.
     * 
     * @param n Normal3 to be reflected on.
     * @return Instance of Vector3 as result of method.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    final public Vector3 reflectedOn(final Normal3 n){
        if(n == null){
            throw new IllegalArgumentException("n must not be null");
        }

        Normal3 normal = n.mul(this.dot(n)).mul(2);
        //-l + 2(l * n)n
        return new Vector3(-x + normal.x, -y + normal.y, -z + normal.z);
        
    }
    
    /**
     * Calculate the product with the vector and an other vector.
     * 
     * @param v Vector3 for cross product.
     * @return Instance of Vector3 as cross product.
     * @throws IllegalArgumentException Get thrown if the parameter is null.
     **/
    final public Vector3 x(final Vector3 v){
        if(v == null){
            throw new IllegalArgumentException("v must not be null");
        }
        return new Vector3((y*v.z)-(z*v.y), (z*v.x)-(x*v.z), (x*v.y)-(y*v.x)); 
    }

    /**
     * Get the string of Vector3.
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
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.magnitude) ^ (Double.doubleToLongBits(this.magnitude) >>> 32));
        return hash;
    }

    /**
     * Compares vector with an object.
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
        final Vector3 other = (Vector3) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        if (Double.doubleToLongBits(this.magnitude) != Double.doubleToLongBits(other.magnitude)) {
            return false;
        }
        return true;
    }
    
}
