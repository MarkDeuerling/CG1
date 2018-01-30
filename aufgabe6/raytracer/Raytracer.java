package aufgabe6.raytracer;

import aufgabe6.mathLib.*;
import aufgabe2.color.Color;
import aufgabe6.geo.Geometry;
import aufgabe6.light.PointLight;
import aufgabe6.material.LambertMaterial;
import aufgabe6.cam.Camera;
import aufgabe6.cam.PerspectiveCamera;
import aufgabe6.cam.ThinLens;
import aufgabe6.geo.AxisAlignedBox;
import aufgabe6.geo.Node;
import aufgabe6.geo.Plane;
import aufgabe6.geo.Sphere;
import aufgabe6.light.AmbientOccluder;
import aufgabe6.material.PhongMaterial;
import aufgabe6.mathLib.Vector3;
import aufgabe6.transform.Transform;
import aufgabe6.world.World;
import aufgabe6.sampling.SamplingPattern;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling.
 */
public class Raytracer {
    
    public static void main(String[] args) {
        renderTransformedSphere();
//        renderTransformedBox();
//        
//        renderTransformedBoxReg();
        
//        renderDeepOfField();
        renderAmbientOcclusion();
        
    }
    
    final public static void renderTransformedSphere(){
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 8.0), new Vector3(0, 0, -8.0), new Vector3(0, 1.0, 0), Math.PI / 8.0, new SamplingPattern(PointsRandom(100)));
        Geometry sphere = new Sphere(new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64));
        List<Geometry> geoList = new ArrayList<>();
        geoList.add(sphere);
        
        Transform t = new Transform().rotateX(Math.toRadians(0)).rotateY(Math.toRadians(-45)).rotateZ(Math.toRadians(-45)).scale(1.8, 0.4, 1.8);
        Geometry node = new Node(new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64), t, geoList);
        World world = new World(new Color(0, 0, 0), new Color(0, 0, 0));
        world.addGeo(node);
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(0, 0, 8), true));
        renderWindow(world, persCam);
        
    }
    
    final public static void renderTransformedSphereReg(){
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 8.0), new Vector3(0, 0, -8.0), new Vector3(0, 1.0, 0), Math.PI / 8.0, new SamplingPattern(PointsRandom(1)));
        Geometry sphere = new Sphere(new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64));
        List<Geometry> geoList = new ArrayList<>();
        geoList.add(sphere);
        
        Transform t = new Transform().rotateX(Math.toRadians(0)).rotateY(Math.toRadians(-45)).rotateZ(Math.toRadians(-45)).scale(1.8, 0.4, 1.8);
        Geometry node = new Node(new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64), t, geoList);
        World world = new World(new Color(0, 0, 0), new Color(0, 0, 0));
        world.addGeo(node);
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(0, 0, 8), true));
        renderWindow(world, persCam);
        
    }
    final public static void renderTransformedBox(){
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 8.0), new Vector3(0, 0, -8.0), new Vector3(0, 1.0, 0), Math.PI / 8.0, new SamplingPattern(PointsRandom(100)));
        Geometry aab = new AxisAlignedBox(new LambertMaterial(new Color(1, 1, 0)));
        List<Geometry> geoList = new ArrayList<>();
        geoList.add(aab);

        Transform t = new Transform().rotateX(Math.toRadians(-60)).rotateY(Math.toRadians(40)).rotateZ(Math.toRadians(45)).scale(4, 1.2, .3);
        Geometry node = new Node(new LambertMaterial(new Color(1, 1, 0)), t, geoList);
        World world = new World(new Color(0, 0, 0), new Color(.25, .25, .25));
        world.addGeo(node);
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(0, 0, 8), true));
        renderWindow(world, persCam);
    }
    
    final public static void renderTransformedBoxReg(){
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 8.0), new Vector3(0, 0, -8.0), new Vector3(0, 1.0, 0), Math.PI / 8.0, new SamplingPattern(pointsRegular(10, 10)));
        Geometry aab = new AxisAlignedBox(new LambertMaterial(new Color(1, 1, 0)));
        List<Geometry> geoList = new ArrayList<>();
        geoList.add(aab);

        Transform t = new Transform().rotateX(Math.toRadians(-60)).rotateY(Math.toRadians(40)).rotateZ(Math.toRadians(45)).scale(4, 1.2, .3);
        Geometry node = new Node(new LambertMaterial(new Color(1, 1, 0)), t, geoList);
        World world = new World(new Color(0, 0, 0), new Color(.25, .25, .25));
        world.addGeo(node);
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(0, 0, 8), true));
        renderWindow(world, persCam);
    
    }
    
    final public static void renderDeepOfField(){
        SamplingPattern sp = new SamplingPattern(256, PointsRandom(10));
        sp.generateSamples_NRoot();
        Camera thinLens = new ThinLens(new Point3(0, 0, 8), new Vector3(0, 0, -8), new Vector3(0, 1, 0), sp, 1.0, 6.0, Math.PI / 8, 0);
        Geometry sphere1 = new Sphere(new Point3(1.5, 0, 0), .5, new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64));
        Geometry sphere2 = new Sphere(new Point3(0, 0, 2), .5, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64));
        Geometry sphere3 = new Sphere(new Point3(-1.5, 0, 4), .5, new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64));
        World world = new World(new Color(0, 0, 0), new Color(.3, .3, .3));
        world.addGeo(sphere3);
        world.addGeo(sphere2);
        world.addGeo(sphere1);
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(0, 2, 8), true));
        renderWindow(world, thinLens);
    }
    
    final public static void renderAmbientOcclusion(){
        SamplingPattern sp = new SamplingPattern(256, PointsRandom(5));
        sp.generateSamples_NRoot();
        Camera cam = new PerspectiveCamera(new Point3(0, 0, 8), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 8, sp);
//        Geometry sphere1 = new Sphere(new Point3(1.5, 0, 0), .5, new LambertMaterial(new Color(1, 0, 0)));
//        Geometry sphere2 = new Sphere(new Point3(0, 0, 2), .5, new LambertMaterial(new Color(0, 1, 0)));
//        Geometry sphere3 = new Sphere(new Point3(-1.5, 0, 4), .5, new LambertMaterial(new Color(0, 0, 1)));
        
        Geometry sphere1 = new Sphere(new Point3(1.5, 0, 0), .5, new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64));
        Geometry sphere2 = new Sphere(new Point3(0, 0, 2), .5, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64));
        Geometry sphere3 = new Sphere(new Point3(-1.5, 0, 4), .5, new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64));
      
        Geometry plane = new Plane(new Normal3(0, 1, 0), new Point3(0, -.5, 0), new LambertMaterial(new Color(.8, .8, .8)));
//        World world = new World(new Color(0, 0, 0), new Color(.8, .8, .8)); //<--auskommentieren für normale beleuchtung
        World world = new World(new Color(0, 0, 0), new Color(.25, .25, .25));
        world.addGeo(sphere1);
        world.addGeo(sphere2);
        world.addGeo(sphere3);
        world.addGeo(plane);
        world.addLight(new AmbientOccluder(new Color(0, 0, 0), false, sp)); //<-- kommentieren für normale beleuchtung
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(0, 8, 8), true));
        renderWindow(world, cam);
    }
    
    
    final public static void renderWindow(final World world, final Camera cam){
        JFrame frame = new JFrame();
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container con = frame.getContentPane();
        con.add(new RenderThread(world, cam, frame, RenderThread.RENDER_WITH_TWO_THREADS));
        frame.setVisible(true);
    }
    
    final public static Collection<Point2> PointsRandom(int count){
        Collection<Point2> points = new HashSet<>();
        while(count != 0){
            double sx = Math.random() - .5;
            double sy = Math.random() - .5;
            
            points.add(new Point2(sx, sy));
            count--;
        }
        return points;
    }

    
    final public static List<Point2> pointsRandom(int count){
        List<Point2> points = new ArrayList<>();
        while(count != 0){
            double sx = Math.random() - .5;
            double sy = Math.random() - .5;
            
            points.add(new Point2(sx, sy));
            count--;
        }
        return points;
    }
    
    final public static Collection<Point2> pointsRegular(final int row, final int col){
        Collection<Point2> points = new HashSet<>();
        
        double partRow = 1.0 / row;
        double partCol = 1.0 / col;
        int counterRow = row / 2;
        int counterCol = col / 2;
        for(double x = 0; x < row; x++){
            for(double y = 0; y < col; y++){
                if(counterRow != 0){
                    points.add(new Point2(-partRow * x, -partCol * y));
                    counterRow--;
                }else{
                    points.add(new Point2(partRow * x, partCol * y));
                }
                if(counterCol != 0){
                    points.add(new Point2(-partRow * x, -partCol * y));
                    counterCol--;
                }else{
                    points.add(new Point2(partRow * x, partCol * y));
                }
            }
        }
    
        return points;
    }
}
