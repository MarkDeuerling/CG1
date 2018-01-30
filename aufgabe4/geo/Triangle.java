package aufgabe4.geo;


import MathLib.Mat3x3;
import MathLib.Normal3;
import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.ray.Ray;
import aufgabe4.hit.Hit;
import aufgabe4.material.Material;
import java.util.Objects;

/**
 * This class represents a triangle object.
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Triangle extends Geometry{

    /**
     * An edge of the triangle.
     */
    public final Point3 a;
    
    /**
     * An edge of the triangle.
     */
    public final Point3 b;
    
    /**
     * An edge of the triangle.
     */
    public final Point3 c;
    
    /**
     * The normal for the a edge.
     */
    public final Normal3 na;
    
    /**
     * The normal of the b edge.
     */
    public final Normal3 nb;
    
    /**
     * The normal of the b edge.
     */
    public final Normal3 nc;
    
    /**
     * Construct a triangle object.
     * 
     * @param a an edge of the triangle.
     * @param b an edge of the triangle.
     * @param c an edge of the triangle.
     * @param material the material of the triangle.
     * @throws IllegalArgumentException is thrown if the given parameter are null.
     */
    public Triangle(final Point3 a, final Point3 b, final Point3 c, final Material material) {
        super(material);
        if(a == null){
            throw new IllegalArgumentException("a must not be null");
        }
        if(b == null){
            throw new IllegalArgumentException("b must not be null");
        }
        if(b == null){
            throw new IllegalArgumentException("c must not be null");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        
        this.na = b.sub(a).x(c.sub(a)).normalized().asNormal();
        this.nb = c.sub(b).x(a.sub(b)).normalized().asNormal();
        this.nc = b.sub(c).x(a.sub(c)).normalized().asNormal();
    }
    
    public Triangle(final Point3 a, final Point3 b, final Point3 c, final Material material, Normal3 na, Normal3 nb, Normal3 nc) {
        super(material);
        if(a == null){
            throw new IllegalArgumentException("a must not be null");
        }
        if(b == null){
            throw new IllegalArgumentException("b must not be null");
        }
        if(b == null){
            throw new IllegalArgumentException("c must not be null");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        
        this.na = na;
        this.nb = nb;
        this.nc = nc;
        
    }

    @Override
    public Hit hit(final Ray ray) {
        final Mat3x3 matrix = new Mat3x3(a.x - b.x, a.x - c.x, ray.d.x, 
                                         a.y - b.y, a.y - c.y, ray.d.y, 
                                         a.z - b.z, a.z - c.z, ray.d.z);
        
        final Vector3 resultABC = a.sub(ray.o);
        
        final double beta = matrix.changeCol1(resultABC).determinante / matrix.determinante;
        final double gamma = matrix.changeCol2(resultABC).determinante / matrix.determinante;
        final double t = matrix.changeCol3(resultABC).determinante / matrix.determinante;
        
        final double alpha = 1 - beta + gamma;
        final Normal3 n = na.mul(alpha).add((nb.mul(beta)).add(nc.mul(gamma)));
        
        return (beta > 0 && gamma > 0 && beta + gamma < 1.0) ? new Hit(t, ray, this, n) : null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.a);
        hash = 59 * hash + Objects.hashCode(this.b);
        hash = 59 * hash + Objects.hashCode(this.c);
        hash = 59 * hash + Objects.hashCode(this.na);
        hash = 59 * hash + Objects.hashCode(this.nb);
        hash = 59 * hash + Objects.hashCode(this.nc);
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
        final Triangle other = (Triangle) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.b, other.b)) {
            return false;
        }
        if (!Objects.equals(this.c, other.c)) {
            return false;
        }
        if (!Objects.equals(this.na, other.na)) {
            return false;
        }
        if (!Objects.equals(this.nb, other.nb)) {
            return false;
        }
        if (!Objects.equals(this.nc, other.nc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Triangle{" + "a=" + a + ", b=" + b + ", c=" + c + ", na=" + na + ", nb=" + nb + ", nc=" + nc + '}';
    }
    
}
