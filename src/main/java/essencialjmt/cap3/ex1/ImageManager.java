package essencialjmt.cap3.ex1;

import java.util.HashSet;
import java.util.Set;

import essencialjmt.ImageData;

public class ImageManager {

    public ImageData countColours(ImageData data) {
        Set<Integer> colors = new HashSet<Integer>();
        int w = data.getBufferedImage().getWidth();
        int h = data.getBufferedImage().getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int pixel = data.getBufferedImage().getRGB(x, y);
                colors.add(pixel);
            }
        }
        data.setNumberOfColors(colors.size());
        return data;
    }

    public ImageData extractDimension(ImageData data) {
        data.setResolution(data.getBufferedImage().getWidth() + " x " + data.getBufferedImage().getHeight());
        return data;
    }
}