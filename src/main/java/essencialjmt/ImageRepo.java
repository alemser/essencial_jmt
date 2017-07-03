package essencialjmt;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

/**
 * Simulates a repository of images.
 */
public class ImageRepo {
    private Map<String, ImageData> repo = new HashMap<>();
    
    public ImageData loadImage(final String name) {
        try {                
            BufferedImage bi = ImageIO.read(getClass().getResourceAsStream(name));
            ImageData imageData = new ImageData(name, bi); 
            repo.put(name, imageData);            
            return imageData;
        } catch (IOException e) {
            throw new RuntimeException("Error reading image", e);
        }
    }
    
    public Collection<ImageData> getImages() {
        return repo.values();
    }
}
