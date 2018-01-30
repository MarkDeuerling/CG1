package aufgabe5.mathLib;

/**
 *
 * @author admin
 */
public class Mat4x4 {
    
    public final double a1;
    public final double a2;
    public final double a3;
    public final double a4;
    public final double b1;
    public final double b2;
    public final double b3;
    public final double b4;
    public final double c1;
    public final double c2;
    public final double c3;
    public final double c4;
    public final double d1;
    public final double d2;
    public final double d3;
    public final double d4;
    
    public Mat4x4(final double a1, final double a2, final double a3, final double a4, 
                  final double b1, final double b2, final double b3, final double b4, 
                  final double c1, final double c2, final double c3, final double c4, 
                  final double d1, final double d2, final double d3, final double d4)
    {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        
    }
    
    final public Mat4x4 mul(final Mat4x4 m){
        final double a1 = this.a1 * m.a1 + this.a2 * m.b1 + this.a3 * m.c1 + this.a4 * m.d1;
        final double a2 = this.a1 * m.a2 + this.a2 * m.b2 + this.a3 * m.c2 + this.a4 * m.d2;
        final double a3 = this.a1 * m.a3 + this.a2 * m.b3 + this.a3 * m.c3 + this.a4 * m.d3;
        final double a4 = this.a1 * m.a4 + this.a2 * m.b4 + this.a3 * m.c4 + this.a4 * m.d4;
        
        final double b1 = this.b1 * m.a1 + this.b2 * m.b1 + this.b3 * m.c1 + this.b4 * m.d1;
        final double b2 = this.b1 * m.a2 + this.b2 * m.b2 + this.b3 * m.c2 + this.b4 * m.d2;
        final double b3 = this.b1 * m.a3 + this.b2 * m.b3 + this.b3 * m.c3 + this.b4 * m.d3;
        final double b4 = this.b1 * m.a4 + this.b2 * m.b4 + this.b3 * m.c4 + this.b4 * m.d4;
        
        final double c1 = this.c1 * m.a1 + this.c2 * m.b1 + this.c3 * m.c1 + this.c4 * m.d1;
        final double c2 = this.c1 * m.a2 + this.c2 * m.b2 + this.c3 * m.c2 + this.c4 * m.d2;
        final double c3 = this.c1 * m.a3 + this.c2 * m.b3 + this.c3 * m.c3 + this.c4 * m.d3;
        final double c4 = this.c1 * m.a4 + this.c2 * m.b4 + this.c3 * m.c4 + this.c4 * m.d4;
        
        final double d1 = this.d1 * m.a3 + this.d2 * m.b3 + this.d3 * m.c3 + this.d4 * m.d1;
        final double d2 = this.d1 * m.a3 + this.d2 * m.b3 + this.d3 * m.c3 + this.d4 * m.d2;
        final double d3 = this.d1 * m.a3 + this.d2 * m.b3 + this.d3 * m.c3 + this.d4 * m.d3;
        final double d4 = this.d1 * m.a4 + this.d2 * m.b4 + this.d3 * m.c4 + this.d4 * m.d4;
        
        return new Mat4x4(a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4);
 
    }

    
    //w = 0
    final public Vector3 mul(final Vector3 vec){
        final double x = a1 * vec.x + a2 * vec.y + a3 * vec.z;
        final double y = b1 * vec.x + b2 * vec.y + b3 * vec.z;
        final double z = c1 * vec.x + c2 * vec.y + c3 * vec.z;
        
        return new Vector3(x, y, z);
    }
    
    
    //w = 1
    final public Point3 mul(final Point3 p){
        final double x = a1 * p.x + a2 * p.y + a3 * p.z;
        final double y = b1 * p.x + b2 * p.y + b3 * p.z;
        final double z = c1 * p.x + c2 * p.y + c3 * p.z;
        
        return new Point3(x, y, z);
    }
      
    final public Mat4x4 transpose(){
        
        return new Mat4x4(a1, b1, c1, d1, 
                          a2, b2, c2, d2, 
                          a3, b3, c3, d3, 
                          a4, b4, c4, d4);
                
    }

}
