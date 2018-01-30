package aufgabe5.raytracer;

import aufgabe5.mathLib.*;
import aufgabe2.color.Color;
import aufgabe5.geo.Geometry;
import aufgabe5.light.PointLight;
import aufgabe5.material.LambertMaterial;
import aufgabe5.cam.Camera;
import aufgabe5.cam.PerspectiveCamera;
import aufgabe5.geo.AxisAlignedBox;
import aufgabe5.geo.Node;
import aufgabe5.geo.Sphere;
import aufgabe5.material.PhongMaterial;
import aufgabe5.mathLib.Vector3;
import aufgabe5.transform.Transform;
import aufgabe5.world.World;
import category.b.multiThreading.RenderThread;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling.
 */
public class Raytracer {
    
    public static void main(String[] args) {
        renderTransformedSphere();
        renderTransformedBox();
        
    }
    
    final public static void renderTransformedSphere(){
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 8.0), new Vector3(0, 0, -8.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);
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
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 8.0), new Vector3(0, 0, -8.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);
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
    
    final public static void renderWindow(final World world, final Camera cam){
        JFrame frame = new JFrame();
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container con = frame.getContentPane();
        con.add(new RenderThread(world, cam, frame, RenderThread.RENDER_WITH_TWO_THREADS));
        frame.setVisible(true);
    }
}
