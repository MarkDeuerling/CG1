package aufgabe2.color;

/**
 * This class represents a color.
 * 
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling
 */
public class Color {

    /**
     * The red color.
     */
    public double r;
    
    /**
     * The green color.
     */
    public double g;
    
    /**
     * The blue color.
     */
    public double b;
    
    /**
     * Construct a color object.
     * @param r : The red color must in space 0 - 1.
     * @param g : The green color must in space 0 - 1.
     * @param b : The blue color must in space 0 - 1.
     */
    public Color(final double r, final double g, final double b){
        this.r = r;
        this.g = g;
        this.b = b;
        
    }

    /**
     * Add a given color to this color.
     * @param c : The color to be added.
     * @return the new color as result.
     * @throws IllegalArgumentException : Is thrown if the given parameter is null.
     */
    public Color add(final Color c){
        if(c == null){
            throw new IllegalArgumentException("c must not be null");
        }
        return new Color(Math.min(r+c.r, 1), Math.min(g+c.g, 1), Math.min(b+c.b, 1));
    }
    
    /**
     * Subtract a given color with this color.
     * @param c : The color to be subtracted.
     * @return the new color as result.
     * @throws IllegalArgumentException : Is thrown if the given parameter is null.
     */
    public Color sub(final Color c){
        if(c == null){
            throw new IllegalArgumentException("c must not be null");
        }
        return new Color(Math.max(r-c.r, 0), Math.max(g-c.g, 0), Math.max(b-c.b, 0));
    }
    
    /**
     * Multiplicate a given color with this color.
     * @param c : The color to be multiplicated.
     * @return the new color as result.
     * @throws IllegalArgumentException : Is thrown if the given parameter is null.
     */
    public Color mul(final Color c){
        if(c == null){
            throw new IllegalArgumentException("c must not be null");
        }
        return new Color(Math.min(r*c.r, 1), Math.min(g*c.g, 1), Math.min(b*c.b, 1));
    }
    
    /**
     * Multiplicate the color with the given value.
     * @param c : The value to be multiplicat. Must in space 0 - 1.
     * @return the new color as result.
     */
    public Color mul(final double c){
        return new Color(Math.min(r*c, 1), Math.min(g*c, 1), Math.min(b*c, 1));
    }

    @Override
    public String toString() {
        return "Color{" + "r=" + r + ", g=" + g + ", b=" + b + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.r) ^ (Double.doubleToLongBits(this.r) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.g) ^ (Double.doubleToLongBits(this.g) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.b) ^ (Double.doubleToLongBits(this.b) >>> 32));
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
        final Color other = (Color) obj;
        if (Double.doubleToLongBits(this.r) != Double.doubleToLongBits(other.r)) {
            return false;
        }
        if (Double.doubleToLongBits(this.g) != Double.doubleToLongBits(other.g)) {
            return false;
        }
        if (Double.doubleToLongBits(this.b) != Double.doubleToLongBits(other.b)) {
            return false;
        }
        return true;
    }
    
    
}
