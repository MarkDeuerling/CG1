/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe1.imageload;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * This class represents a filter for PNG and JPEG files
 *
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class FileFilterPNGAndJPEG extends FileFilter {

    /**
     * Check if the given file is a PNG or JPEG
     *
     * @param f
     * @return true if the file is a PNG or JPEG
     */
    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith("png")
                || f.getName().toLowerCase().endsWith("jpeg");
    }

    /**
     * Returns the new format that will be accepted on the FileChooser
     *
     * @return Strings of the accepted formats
     */
    @Override
    public String getDescription() {
        return "PNG and JPEG";
    }

}
