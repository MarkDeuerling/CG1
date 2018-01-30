package aufgabe6.cam;

import aufgabe6.mathLib.Point2;
import aufgabe6.mathLib.Point3;
import aufgabe6.mathLib.Vector3;
import aufgabe6.ray.Ray;
import aufgabe6.sampling.SamplingPattern;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Mark Deuerling
 */
public class ThinLens extends Camera{

    public final double lensRadius;
    public final double f; //focal plane distance
    public double d; // distance from view plane
    public final double angle;
    
    public ThinLens(final Point3 e, final Vector3 g, final Vector3 t, final SamplingPattern sp, final double lensRadius, final double f, final double angle, final double d) {
        super(e, g, t, sp);
        this.lensRadius = lensRadius;
        this.f = f;
        this.angle = angle;
        this.d = d;
    }

    @Override
    public Collection<Ray> rayFor(int w, int h, int x, int y) {
        if(d <= 0)
            d = w * Math.cos(angle);
        
        Collection<Ray> rays = new HashSet<>();
        for(Point2 p : sp.points){
            Point2 pp = new Point2(x + p.sx - ((w - 1.0) / 2.0), y + p.sy - ((h - 1.0) / 2.0));
            Point2 lp;
            for (Point2 p2 : sp.mapSamplesToUnitDisk()){
                lp = new Point2(p2.sx * lensRadius, p2.sy * lensRadius);
                Point3 ro = e.add(u.mul(lp.sx)).add(v.mul(lp.sy));
                rays.add(new Ray(ro, rayDirection(pp, lp)));
            }
        }
        
        return rays;
    }
    
    final public Vector3 rayDirection(final Point2 pixelPoint, final Point2 lensPoint){
        final double px = pixelPoint.sx * f / d;
        final double py = pixelPoint.sy * f / d;
        final Point2 p = new Point2(px, py);
        final Vector3 dir = u.mul(p.sx - lensPoint.sx).add(v.mul(p.sy - lensPoint.sy)).sub(w.mul(f));
        
        return dir.normalized();
    }
    
}
