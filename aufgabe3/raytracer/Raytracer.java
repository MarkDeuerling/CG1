/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe3.raytracer;

import MathLib.Normal3;
import MathLib.Point3;
import MathLib.Vector3;
import aufgabe2.camera.Camera;
import aufgabe2.camera.PerspectiveCamera;
import aufgabe2.color.Color;
import aufgabe3.geo.AxisAlignedBox;
import aufgabe3.geo.Plane;
import aufgabe3.geo.Sphere;
import aufgabe3.geo.Triangle;
import aufgabe3.light.DirectionLight;
import aufgabe3.light.PointLight;
import aufgabe3.light.SpotLight;
import aufgabe3.material.LambertMaterial;
import aufgabe3.material.PhongMaterial;
import aufgabe3.material.SingleColorMaterial;
import aufgabe3.world.World;
import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author admin
 */
public class Raytracer {
    public static void main(String args[]){
        renderTestScreen();
        renderScreenLambertMaterialPointLight();
        renderScreenPhongMaterialPointLight();
        renderScreenPhongMaterialDirectionalLight();
        renderScreenPhongMaterialSpotLight();
        renderScreenPhongMaterialSpotLightAmbientLight();
        renderDemoSceen();
        
    }
    
    public static void renderTestScreen(){
        final Camera persCam = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1.0, -1.0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);                                               
        final Plane plane = new Plane(new Normal3(0.0, 1.0, 0.0), new Point3(0, 0, 0), new SingleColorMaterial(new Color(1, 0, 0)));
        final Sphere sphere = new Sphere(new Point3(1.0, 1.0, 1.0), .5, new SingleColorMaterial(new Color(0, 1, 0)));
        final AxisAlignedBox aaBox = new AxisAlignedBox(new Point3(-1.5, .5, .5), new Point3(-.5, 1.5, 1.5), new SingleColorMaterial(new Color(0, 0, 1)));
        final Triangle triangle = new Triangle(new Point3(0, 0, -1.0), new Point3(1.0, 0, -1.0), new Point3(1.0, 1.0, -1.0), new SingleColorMaterial(new Color(1, 1, 0)));
        final World world = new World(new Color(0, 0, 0), new Color(0, 0, 0));
        
        world.addGeo(plane);
        world.addGeo(triangle);
        world.addGeo(aaBox);
        world.addGeo(sphere);
        renderWindow(world, persCam);
    }
     
    public static void renderScreenLambertMaterialPointLight(){
        final Camera persCam = new PerspectiveCamera(new Point3(4.0, 4.0, 4.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);                                               
        final Plane plane = new Plane(new Normal3(0, 1.0, 0), new Point3(0, 0, 0), new LambertMaterial(new Color(1, 0, 0)));
        final Sphere sphere = new Sphere(new Point3(1.0, 1.0, 1.0), .5, new LambertMaterial(new Color(0, 1, 0)));
        final AxisAlignedBox aaBox = new AxisAlignedBox(new Point3(-1.5, .5, .5), new Point3(-.5, 1.5, 1.5), new LambertMaterial(new Color(0, 0, 1)));
        final Triangle triangle = new Triangle(new Point3(0, 0, -1.0), new Point3(1.0, 0, -1.0), new Point3(1.0, 1.0, -1.0), new LambertMaterial(new Color(1, 1, 0)));
        final World world = new World(new Color(0, 0, 0), new Color(0, 0, 0));
        
        world.addGeo(plane);
        world.addGeo(triangle);
        world.addGeo(aaBox);
        world.addGeo(sphere);
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4)));
        renderWindow(world, persCam);
    }
     
    public static void renderScreenPhongMaterialPointLight(){
        final Camera persCam = new PerspectiveCamera(new Point3(4.0, 4.0, 4.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);                                               
        final Plane plane = new Plane(new Normal3(0, 1.0, 0), new Point3(0, 0, 0), new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64));
        final Sphere sphere = new Sphere(new Point3(1.0, 1.0, 1.0), .5, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64));
        final AxisAlignedBox aaBox = new AxisAlignedBox(new Point3(-1.5, .5, .5), new Point3(-.5, 1.5, 1.5), new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64));
        final Triangle triangle = new Triangle(new Point3(0, 0, -1.0), new Point3(1.0, 0, -1.0), new Point3(1.0, 1.0, -1.0), new PhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64));
        final World world = new World(new Color(0, 0, 0), new Color(0, 0, 0));
        
        world.addGeo(plane);
        world.addGeo(triangle);
        world.addGeo(aaBox);
        world.addGeo(sphere);
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4)));
        renderWindow(world, persCam);
    }
     
    public static void renderScreenPhongMaterialDirectionalLight(){
        final Camera persCam = new PerspectiveCamera(new Point3(4.0, 4.0, 4.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);                                               
        final Plane plane = new Plane(new Normal3(0, 1.0, 0), new Point3(0, 0, 0), new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64));
        final Sphere sphere = new Sphere(new Point3(1.0, 1.0, 1.0), .5, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64));
        final AxisAlignedBox aaBox = new AxisAlignedBox(new Point3(-1.5, .5, .5), new Point3(-.5, 1.5, 1.5), new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64));
        final Triangle triangle = new Triangle(new Point3(0, 0, -1.0), new Point3(1.0, 0, -1.0), new Point3(1.0, 1.0, -1.0), new PhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64));
        final World world = new World(new Color(0, 0, 0), new Color(0, 0, 0));
        
        world.addGeo(plane);
        world.addGeo(triangle);
        world.addGeo(aaBox);
        world.addGeo(sphere);
        world.addLight(new DirectionLight(new Color(1, 1, 1), new Vector3(-1, -1, -1)));
        renderWindow(world, persCam);
    }
     
    public static void renderScreenPhongMaterialSpotLight(){
        final Camera persCam = new PerspectiveCamera(new Point3(4.0, 4.0, 4.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);                                               
        final Plane plane = new Plane(new Normal3(0, 1.0, 0), new Point3(0, 0, 0), new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64));
        final Sphere sphere = new Sphere(new Point3(1.0, 1.0, 1.0), .5, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64));
        final AxisAlignedBox aaBox = new AxisAlignedBox(new Point3(-1.5, .5, .5), new Point3(-.5, 1.5, 1.5), new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64));
        final Triangle triangle = new Triangle(new Point3(0, 0, -1.0), new Point3(1.0, 0, -1.0), new Point3(1.0, 1.0, -1.0), new PhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64));
        final World world = new World(new Color(0, 0, 0), new Color(0, 0, 0));
        
        world.addGeo(plane);
        world.addGeo(triangle);
        world.addGeo(aaBox);
        world.addGeo(sphere);
        world.addLight(new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), (Math.PI / 14)));
        renderWindow(world, persCam);
    }
     
    public static void renderScreenPhongMaterialSpotLightAmbientLight(){
        final Camera persCam = new PerspectiveCamera(new Point3(4.0, 4.0, 4.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);                                               
        final Plane plane = new Plane(new Normal3(0, 1.0, 0), new Point3(0, 0, 0), new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64));
        final Sphere sphere = new Sphere(new Point3(1.0, 1.0, 1.0), .5, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64));
        final AxisAlignedBox aaBox = new AxisAlignedBox(new Point3(-1.5, .5, .5), new Point3(-.5, 1.5, 1.5), new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64));
        final Triangle triangle = new Triangle(new Point3(0, 0, -1.0), new Point3(1.0, 0, -1.0), new Point3(1.0, 1.0, -1.0), new PhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64));
        final World world = new World(new Color(0, 0, 0), new Color(.25, .25, .25));
        
        world.addGeo(plane);
        world.addGeo(triangle);
        world.addGeo(aaBox);
        world.addGeo(sphere);
        world.addLight(new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), (Math.PI / 14)));
        renderWindow(world, persCam);
    }
     
    public static void renderDemoSceen(){
        final Camera persCam = new PerspectiveCamera(new Point3(4.0, 4.0, 4.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0, 1.0, 0), Math.PI / 8.0);                                               
        final Plane plane = new Plane(new Normal3(0, 1.0, 0), new Point3(0, 0, 0), new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64));
        final Sphere sphere = new Sphere(new Point3(1.0, 1.0, 1.0), .5, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64));
        final AxisAlignedBox aaBox = new AxisAlignedBox(new Point3(-1.5, .5, .5), new Point3(-.5, 1.5, 1.5), new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64));
        final AxisAlignedBox aaBox_lit = new AxisAlignedBox(new Point3(-1, .5, -3), new Point3(3, 1.5, -2), new PhongMaterial(new Color(0, 1, 1), new Color(1, 1, 1), 64));
        final Sphere sphere_lit = new Sphere(new Point3(1, 2, 3), 0.3, new PhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64));
        final World world = new World(new Color(0, 0, 0), new Color(0, 0, 0));
        
        world.addGeo(plane);
        world.addGeo(aaBox);
        world.addGeo(sphere);
        world.addGeo(aaBox_lit);
        world.addGeo(sphere_lit);
        world.addLight(new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), (Math.PI / 8)));
        world.addLight(new PointLight(new Color(1, 1, 1), new Point3(8, 4, 0)));
        renderWindow(world, persCam);
    }
    
    public static void renderWindow(final World world, final Camera cam){
        final JFrame frame = new JFrame();
        final Container con = frame.getContentPane();
        con.add(new RenderImageCanvas(world, cam, frame));
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
