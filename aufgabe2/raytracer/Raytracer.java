package aufgabe2.raytracer;

import MathLib.Normal3;
import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.camera.Camera;
import aufgabe2.camera.OrthographicCamera;
import aufgabe2.camera.PerspectiveCamera;
import aufgabe2.color.Color;
import aufgabe2.geometry.AxisAlignedBox;
import aufgabe2.geometry.Disk;
import aufgabe2.geometry.Plane;
import aufgabe2.geometry.Sphere;
import aufgabe2.geometry.Triangle;
import aufgabe2.world.World;
import java.awt.Container;
import javax.swing.JFrame;

/**
 * This class has the main-methode to render the geometry on screen.
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class Raytracer{
    
    public static void main(String[] args){
        renderPlane();
        renderSphere();
        renderTwoSphere();
        renderTwoSphereOrthographic();
        renderTriangle();
        renderAABox();
        renderDisk();
        
    }
    
    /**
     * Render a single Plane with perspective camera.
     */
     public static void renderPlane(){
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);
        Plane plane = new Plane(new Normal3(0, 1, 0), new Point3(0, -1, 0), new Color(0, 1, 0));
        World world = new World(new aufgabe2.color.Color(0, 0, 0));
        world.addGeo(plane);
        renderWindow(world, persCam);
    }
     
    /**
     * Render a single sphere with perspective camera.
     */
    public static void renderSphere(){
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);                                               
        Sphere sphere = new Sphere(new Point3(0, 0, -3.0), 0.5, new Color(1.0, 0, 0));
        World world = new World(new aufgabe2.color.Color(0, 0, 0));
        world.addGeo(sphere);
        renderWindow(world, persCam);
    }
    
    /**
     * Render two sphere with a perspective camera.
     */
    public static void renderTwoSphere(){
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 8.0);
        Sphere sphereNear = new Sphere(new Point3(-1, 0, -3), 0.5, new Color(1, 0, 0));
        Sphere sphereFar = new Sphere(new Point3(1, 0, -6), 0.5, new Color(1, 0, 0));
        World world = new World(new Color(0, 0, 0));
        world.addGeo(sphereNear);
        world.addGeo(sphereFar);
        renderWindow(world, persCam);
    }   
    
    /**
     * Render two sphere with a orthographic camera.
     */
    public static void renderTwoSphereOrthographic(){
        Camera camOrtho = new OrthographicCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 3);
        Sphere sphereNear = new Sphere(new Point3(-1, 0, -3), 0.5, new Color(1, 0, 0));
        Sphere sphereFar = new Sphere(new Point3(1, 0, -6), 0.5, new Color(1, 0, 0));
        World world = new World(new Color(0, 0, 0));
        world.addGeo(sphereNear);
        world.addGeo(sphereFar);
        renderWindow(world, camOrtho);
    }
    
    /**
     * Render a triangle with a perspectiv camera.
     */
    public static void renderTriangle(){
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8);
        Triangle triangle = new Triangle(new Point3(-0.5, 0.5, -3.0), new Point3(0.5, 0.5, -3.0), new Point3(0.5, -0.5, -3.0), new Color(1.0, 0, 1.0));
        World world = new World(new Color(0, 0, 0));
        world.addGeo(triangle);
        renderWindow(world, persCam);
    }
    
    /**
     * Render a Axis-Aligne-Box with a perspective camera.
     */
    public static void renderAABox(){
        Camera persCam = new PerspectiveCamera(new Point3(3, 3, 3), new Vector3(-3, -3, -3.0), new Vector3(0, 1.0, 0), Math.PI / 8);
        AxisAlignedBox aaBox = new AxisAlignedBox(new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5), new Color(0, 0, 1));
        World world = new World(new Color(0, 0, 0));
        world.addGeo(aaBox);
        renderWindow(world, persCam);
    }
    
    /**
     * Render a disk with a perspective camera.
     */
    public static void renderDisk(){
        Camera persCam = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);                                               
        Disk disk = new Disk(new Point3(0, -.5, -3), new Normal3(0, 1, 0), .5, new Color(1, 1, 0));
        World world = new World(new Color(0, 0, 0));
        world.addGeo(disk);
        renderWindow(world, persCam);
    }
    
    /**
     * The frame to be rendered.
     * @param world the world where the geometry are to be rendered.
     * @param cam the cam to handle the perspective.
     */
    public static void renderWindow(World world, Camera cam){
        JFrame frame = new JFrame();
        Container con = frame.getContentPane();
        con.add(new RenderImageCanvas(world, cam, frame));
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
