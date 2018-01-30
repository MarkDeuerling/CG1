package aufgabe3.material;

import aufgabe2.color.Color;
import aufgabe3.hit.Hit;
import aufgabe3.world.World;

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
