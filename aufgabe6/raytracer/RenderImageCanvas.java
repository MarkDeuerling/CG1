package aufgabe6.raytracer;

import aufgabe6.ray.Ray;
import aufgabe6.cam.Camera;
import aufgabe6.hit.Hit;
import aufgabe6.world.World;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Collection;
import javax.swing.JFrame;

/**
 * This class represents the Image of the calculation from the intersection.
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class RenderImageCanvas extends Canvas {
    
    /**
     * The world where the geometries are.
     */
    protected final World world;
    
    /**
     * The camera that shoot.
     */
    protected final Camera cam;
    
    /**
     * The Frame where the image is redered.
     */
    protected final JFrame frame;
    
    /**
     * Construct the Image object.
     * 
     * @param world where the geometries are.
     * @param cam the camera that shoot.
     * @param frame the frame to be rendered.
     */
    public RenderImageCanvas(final World world, final Camera cam, final JFrame frame){
        if(world == null){
            throw new IllegalArgumentException("world must not be null");
        }
        if(cam == null){
            throw new IllegalArgumentException("cam must not be null");
        }
        if(frame == null){
            throw new IllegalArgumentException("frame must not be null");
        }
        
        this.world = world;
        this.cam = cam;
        this.frame = frame;
        
    }

    /**
     * Rendering the Image.
     * 
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        
        final BufferedImage bimg = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);

        final WritableRaster raster = bimg.getRaster();
        final ColorModel model = bimg.getColorModel();

        for (int x = 0; x < bimg.getWidth(); x++) {
            for (int y = 0; y < bimg.getHeight(); y++) {
                raster.setDataElements(x, bimg.getHeight() - 1 - y, model.getDataElements(renderColor(x, y).getRGB(), null));
                
            }
            g.drawImage(bimg, 0, 0, null);
        }
        
//        g.drawImage(bimg, 0, 0, null);
//        repaint();
    }
    
    /**
     * Rendering the color on given coordinate.
     * @param x X-Coordinate.
     * @param y Y-Coordinate.
     * @return the color on the pixel.
     */
    protected java.awt.Color renderColor(final int x, final int y){
        aufgabe2.color.Color geoColor = null;
        Collection<Ray> col = cam.rayFor(frame.getWidth(), frame.getHeight(), x, y);
        for(Ray r : col){
            Hit worldHit = world.hit(r);
            if(geoColor == null && worldHit != null){
                geoColor = worldHit.geo.material.colorFor(worldHit, world);
            }else{
                if(worldHit != null){
                    if(geoColor != null){
                        aufgabe2.color.Color test = worldHit.geo.material.colorFor(worldHit, world);
                        geoColor.b += test.b;
                        geoColor.g += test.g;
                        geoColor.r += test.r;
        //                    System.out.println(worldHit.geo.material.colorFor(worldHit, world));
                    }
                }else{
                    if(geoColor != null)
                        geoColor.add(world.backgroundColor);
                }
            }
        }
        
        double size = col.size();
        if(geoColor != null){
            geoColor.b = geoColor.b/size;
            geoColor.g = geoColor.g/size;
            geoColor.r = geoColor.r/size;
            geoColor.b = Math.min(geoColor.b, 1);
            geoColor.g = Math.min(geoColor.g, 1);
            geoColor.r = Math.min(geoColor.r, 1);
            
            return changeToColor(geoColor);
        }
        
        return changeToColor(world.backgroundColor);
        
        
        
//        Hit worldHit = world.hit(cam.rayFor(frame.getWidth(), frame.getHeight(), x, y));
//        aufgabe2.color.Color geoColor = worldHit.geo.material.colorFor(worldHit, world);
//        if(worldHit != null){
//            if(geoColor != null)
//                return changeToColor(geoColor);
//        }
//        
//        return changeToColor(world.backgroundColor);
    }
    
    /**
     * Change to java.awt.Color
     * 
     * @param col the color of the geometry.
     * @return the java.awt.Color.
     */
    protected java.awt.Color changeToColor(final aufgabe2.color.Color col){
        return new java.awt.Color((float)col.r,(float)col.g, (float)col.b);
    }
    
    
}
