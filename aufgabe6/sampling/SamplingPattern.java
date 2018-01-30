package aufgabe6.sampling;

import aufgabe6.mathLib.Point2;
import aufgabe6.mathLib.Point3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class SamplingPattern {
    public final Collection<Point2> points;
    public final List<Point2> pointList; 
    public final List<Point3> hemisphereSamples;
    public final int numSamples;
    public int hemisSize;
    
    public SamplingPattern(final Collection<Point2> points){
        this.points = points;
        pointList = null;
        hemisphereSamples = null;
        this.numSamples = 256;
        
    }
    
    public SamplingPattern(final List<Point2> pointList){
        this.pointList = pointList;
        hemisphereSamples = new ArrayList<>();
        points = null;
        numSamples = 256;
        
    }
    
    public SamplingPattern(final int numSamples, final Collection<Point2> points){
        this.numSamples = numSamples;
        pointList = new ArrayList<>();
        hemisphereSamples = null;
        this.points = points;
        
    }
    
    final public List<Point3> mapSamplesToHemisphere(final double e){
        ArrayList<Point3> hemSamp = new ArrayList<>();
        hemSamp.ensureCapacity(pointList.size());
        for (Point2 pointList1 : pointList) {
            final double cosPhy = Math.cos(2.0 * Math.PI * pointList1.sx);
            final double sinPhy = Math.sin(2.0 * Math.PI * pointList1.sx);
            final double cosTheta = Math.pow(1.0 - pointList1.sy, 1.0 / (e + 1.0));
            final double sinTheta = Math.sqrt(1.0 - cosTheta * cosTheta);
            final double pu = sinTheta * cosPhy;
            final double pv = sinTheta * sinPhy;
            final double pw = cosTheta;
//            hemisphereSamples.add(new Point3(pu, pv, pw));
            hemSamp.add(new Point3(pu, pv, pw));
        }
        hemisSize = hemSamp.size();
        return hemSamp;
    }
    
    final public void generateSamples_NRoot(){
        Random rand = new Random();
        for(int i = 0; i < numSamples; i++){
            double px = (i + rand.nextDouble()) / numSamples;
            double py = (i + rand.nextDouble()) / numSamples;
            Point2 p = new Point2(px, py);
            pointList.add(p);
        }
        shuffleXCoordinate();
        shuffleYCoordinate();
    }
    
    final public void shuffleXCoordinate(){
        Random rand = new Random();
        for (int i = 0; i < numSamples - 1; i++) {
            int target = rand.nextInt(numSamples);
            double temp = pointList.get(i+1).sx;
            pointList.get(i+1).sx = pointList.get(target).sx;
            pointList.get(target).sx = temp;
        }
    }
    
    final public void shuffleYCoordinate(){
        Random rand = new Random();
        for (int i = 0; i < numSamples - 1; i++) {
            int target = rand.nextInt(numSamples);
            double temp = pointList.get(i+1).sy;
            pointList.get(i+1).sy = pointList.get(target).sy;
            pointList.get(target).sy = temp;
        }
    }
    
    final public List<Point2> mapSamplesToUnitDisk(){
        ArrayList<Point2> diskSamples = new ArrayList<>();
        int size = pointList.size();
        diskSamples.ensureCapacity(size);
        double r;
        double phi;
        Point2 sp;
       
        for (int i = 0; i < size; i++) {
            double px = 2.0 * pointList.get(i).sx - 1.0;
            double py = 2.0 * pointList.get(i).sy - 1.0;
            sp = new Point2(px, py);
            if(sp.sx > -sp.sy){
                if(sp.sx > sp.sy){
                    r = sp.sy;
                    phi = sp.sy / sp.sx;
                }else{
                    r = sp.sy;
                    phi = 2 - sp.sx / sp.sy;
                }
            }else{
                if(sp.sx < sp.sy){
                    r = -sp.sx;
                   phi = 4 + sp.sy / sp.sx; 
                }else{
                    r = -sp.sy;
                    if(sp.sy != 0.0){
                        phi = 6 - sp.sx / sp.sy;
                    }else{
                        phi = 0.0;
                    }
                }
            }
            phi  *= Math.PI / 4.0;
            diskSamples.add(new Point2(r * Math.cos(phi), r * Math.sin(phi)));
            
        }
        return diskSamples;
    }
}
