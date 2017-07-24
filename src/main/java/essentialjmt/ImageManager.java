package essentialjmt;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ImageManager {
    
    public ImageData countColours(ImageData data) {
        Set<Color> colors = new HashSet<Color>();
        int w = data.getBufferedImage().getWidth();
        int h = data.getBufferedImage().getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                colors.add(new Color(data.getBufferedImage().getRGB(x, y)));                
            }
        }
        data.setColors(colors);
        return data;
    }

    public Collection<Color> getRgbColours(ImageData data) {
        Collection<Color> colors = new ArrayList<>();
        int w = data.getBufferedImage().getWidth();
        int h = data.getBufferedImage().getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                colors.add(new Color(data.getBufferedImage().getRGB(x, y)));                
            }
        }
        return colors;
    }
    
    public ImageData extractDimension(ImageData data) {
        data.setResolution(data.getBufferedImage().getWidth() + " x " + data.getBufferedImage().getHeight());
        return data;
    }
}