package aufgabe4.raytracer;

import MathLib.Normal3;
import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.camera.Camera;
import aufgabe2.camera.PerspectiveCamera;
import aufgabe2.color.Color;
import aufgabe4.geo.AxisAlignedBox;
import aufgabe4.geo.Geometry;
import aufgabe4.geo.Plane;
import aufgabe4.geo.Sphere;
import aufgabe4.geo.Triangle;
import aufgabe4.light.DirectionLight;
import aufgabe4.light.PointLight;
import aufgabe4.light.SpotLight;
import aufgabe4.material.LambertMaterial;
import aufgabe4.material.PhongMaterial;
import aufgabe4.material.ReflectiveMaterial;
import aufgabe4.material.TransparentMaterial;
import aufgabe4.world.World;
import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author Peter Albrecht, Stefan Streichern, Mark Deuerling
 */
public class Raytracer {
    
    public static void main(String[] args) {
//        renderBoxWithShadow();
//        renderReflectionSphere();
        renderRefractionSceen();
    }
    
    final public static void renderRefractionSceen(){
        Camera persCam = new PerspectiveCamera(new Point3(8, 8, 8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 8);
        
        Geometry plane = new Plane(new Normal3(0, 1, 0), new Point3(0, 0, 0), new ReflectiveMaterial(new Color(1, 1, 1), new Color(1, 1, 1), 10, new Color(1, 1, 1)));
        
        Geometry sphereRefl1 = new Sphere(new Point3(0, 1, 0), .5, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 10, new Color(1, .5, .5)));
        Geometry sphereRefl2 = new Sphere(new Point3(-1.5, 1, 0), .5, new ReflectiveMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 10, new Color(1, .5, .5)));
        Geometry sphereRefl3 = new Sphere(new Point3(1.5, 1, 0), .5, new ReflectiveMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 10, new Color(1, .5, .5)));
        Geometry sphereRefl4 = new Sphere(new Point3(0, 1, -1.5), .5, new ReflectiveMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 10, new Color(1, .5, .5)));
        Geometry sphereRefl5 = new Sphere(new Point3(-1.5, 1, -1.5), .5, new ReflectiveMaterial(new Color(1, 0, 1), new Color(1, 1, 1), 10, new Color(1, .5, .5)));
        Geometry sphereRefl6 = new Sphere(new Point3(1.5, 1, -1.5), .5, new ReflectiveMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 10, new Color(1, .5, .5)));
        
        Geometry sphereRefra1 = new Sphere(new Point3(0, 2, 1.5), .5, new TransparentMaterial(1.33));
        Geometry sphereRefra2 = new Sphere(new Point3(-1.5, 2, 1.5), .5, new TransparentMaterial(1.33));
        Geometry sphereRefra3 = new Sphere(new Point3(1.5, 2, 1.5), .5, new TransparentMaterial(1.33));
        
        Geometry box = new AxisAlignedBox(new Point3(-.5, 0, 3), new Point3(.5, 1, 4), new TransparentMaterial(1.33));
        
        Geometry triangle = new Triangle(new Point3(.7, .5, 3), new Point3(1.3, .5, 3), new Point3(.7, .5, 4), new PhongMaterial(new Color(0, 1, 0), new Color(0, 1, 0), 20), new Normal3(0, 1, 0), new Normal3(0, 1, 0), new Normal3(0, 1, 0));
        
        World world = new World(new Color(0, 0, 0), new Color(.1, .1, .1), 10, 1.0);
        world.addLight(new SpotLight(new Color(.3, .3, .3), new Point3(0, 5, -10), new Vector3(0, -1, 0), Math.PI / 8, true));
        world.addLight(new PointLight(new Color(.3, .3, .3), new Point3(5, 5, -10), true));
        world.addLight(new DirectionLight(new Color(.3, .3, .3), new Vector3(1, -1, 0), true));
        
        world.addGeo(sphereRefra1);
        world.addGeo(sphereRefra2);
        world.addGeo(sphereRefra3);
        world.addGeo(sphereRefl1);
        world.addGeo(sphereRefl2);
        world.addGeo(sphereRefl3);
        world.addGeo(sphereRefl4);
        world.addGeo(sphereRefl5);
        world.addGeo(sphereRefl6);
        world.addGeo(box);
        world.addGeo(triangle);
        world.addGeo(plane);
        renderWindow(world, persCam);
        
    }
    
    final public static void renderReflectionSphere(){
        Camera persCam = new PerspectiveCamera(new Point3(8.0, 8.0, 8.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0, 1, 0), Math.PI / 8);
        
        Geometry plane = new Plane(new Normal3(0, 1, 0), new Point3(0, 0, 0), new ReflectiveMaterial(new Color(.1, .1, .1), new Color(0, 0, 0), 64, new Color(.5, .5, .5)));
        Geometry sphere1 = new Sphere(new Point3(-3.0, 1.0, 0), 1.0, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(.5, .5, .5)));
        Geometry sphere2 = new Sphere(new Point3(0, 1.0, 0), 1.0, new ReflectiveMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64, new Color(.5, .5, .5)));
        Geometry sphere3 = new Sphere(new Point3(3.0, 1.0, 0), 1.0, new ReflectiveMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64, new Color(.5, .5, .5)));
        World world = new World(new Color(0, 0, 0), new Color(.25, .25, .25), 3);
        
        world.addGeo(plane);
        world.addGeo(sphere1);
        world.addGeo(sphere2);
        world.addGeo(sphere3);
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(8, 8, 8), false));
        renderWindow(world, persCam);
        
    }
    
    final public static void renderBoxWithShadow(){
        Camera persCam = new PerspectiveCamera(new Point3(8.0, 8.0, 8.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);
        Geometry plane = new Plane(new Normal3(0, 1, 0), new Point3(0, 0, 0), new LambertMaterial(new Color(.8, .8, .8)));
        Geometry box = new AxisAlignedBox(new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5), new LambertMaterial(new Color(1, 0, 0)));
        World world = new World(new Color(0, 0, 0), new Color(0.25, 0.25, 0.25));
        world.addGeo(plane);
        world.addGeo(box);
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(8, 8, 0), true));
        renderWindow(world, persCam);
        
    }
    
    final public static void renderWindow(final World world, final Camera cam){
        JFrame frame = new JFrame();
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container con = frame.getContentPane();
        con.add(new RenderThread(world, cam, frame, 2));
        frame.setVisible(true);
    }
    
}
