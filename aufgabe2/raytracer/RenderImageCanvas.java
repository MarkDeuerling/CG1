package aufgabe2.raytracer;

import aufgabe2.camera.Camera;
import aufgabe2.geometry.Geometry;
import aufgabe2.world.World;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
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
        }
        
        g.drawImage(bimg, 0, 0, null);
    }
    
    /**
     * Rendering the color on given coordinate.
     * @param x X-Coordinate.
     * @param y Y-Coordinate.
     * @return the color on the pixel.
     */
    protected java.awt.Color renderColor(int x, int y){
        
        for(Geometry geo : world.geoList){
            
            if(geo.hit(cam.rayFor(frame.getWidth(), frame.getHeight(), x, y)) != null){
                
                return changeToColor(geo.color);
            }
        }
        
        return changeToColor(world.backgroundColor);
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
