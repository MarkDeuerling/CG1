package aufgabe6.light;

import aufgabe2.color.Color;
import aufgabe6.hit.Hit;
import aufgabe6.mathLib.Point3;
import aufgabe6.mathLib.Vector3;
import aufgabe6.ray.Ray;
import aufgabe6.sampling.SamplingPattern;
import aufgabe6.world.World;

/**
 *
 * @author Mark Deuerling
 */
public class AmbientOccluder extends Light{
    
    public final SamplingPattern sp;
    public Vector3 u;
    public Vector3 v;
    public Vector3 w;
    
    public AmbientOccluder(final Color color, final boolean castsShadow, SamplingPattern sp){
        super(color, castsShadow);
        this.sp = sp;
//        sp.mapSamplesToHemisphere(1);
    }

    @Override
    final public boolean illuminates(final Point3 point, World world) {
        if(world == null){
            throw new IllegalArgumentException("world must not be null");
        }
        if(point == null){
            throw new IllegalArgumentException("point must not be null");
        }
        
        int hitCount = 0;
        Ray shadowRay;
        Hit hitShadow;
        boolean isIllu = true;
        
        for(Point3 p : sp.mapSamplesToHemisphere(1)){
            Ray hemisRay = new Ray(point, new Vector3(p.x, p.y, p.z).normalized());
            shadowRay = new Ray(point, directionFrom(p));
            hitShadow = world.hit(shadowRay);
            Hit hit = world.hit(hemisRay);
            
            if(hit != null)
                hitCount++;
            
            if(hitShadow != null && hitShadow.t > World.EPSYLON)
                isIllu = false;
        }
        
        final double pers = sp.hemisSize / 100.0 * hitCount;
        double abs = 1 - (1.0 / 100.0 * pers);
        abs = abs > 1.0 ? 1.0 : abs;
        abs = abs < 0 ? 0 : abs;
        world.aC = world.ambientColor.mul(abs);
        
        if(castsShadow)
           if(!isIllu) 
               return false;
        
        return true;
    }

    @Override
    final public Vector3 directionFrom(Point3 point) {
        Vector3 spU = u.mul(point.x);
        Vector3 spV = v.mul(point.y);
        Vector3 spW = w.mul(point.z);
        return spU.add(spV).add(spW);
    }
    
}
