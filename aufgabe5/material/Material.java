package aufgabe5.material;

import aufgabe2.color.Color;
import aufgabe5.hit.Hit;
import aufgabe5.world.World;

/**
 * This interface represents a material.
 * 
 * @author Peter Albrecht, Stefan Streichern, Mark Deuering
 */
public interface Material {
    /**
     * The color for the specifical material.
     * 
     * @param hit the hit on the material.
     * @param world need a world with ambient color.
     * @return the color of the material.
     */
    public Color colorFor(final Hit hit, final World world);
}
