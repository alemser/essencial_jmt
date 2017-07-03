package essencialjmt;

import java.awt.image.BufferedImage;

public class ImageData {
    private String name;
    private Integer numberOfColors;
    private String resolution;
    private BufferedImage bufferedImage;

    public ImageData(final String name, final BufferedImage bufferedImage) {
        this.name = name;
        this.bufferedImage = bufferedImage;
    }

    public Integer getNumberOfColors() {
        return numberOfColors;
    }

    public void setNumberOfColors(final Integer numberOfColors) {
        this.numberOfColors = numberOfColors;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(final String resolution) {
        this.resolution = resolution;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name + " (" + (numberOfColors != null ? "#colors: " + numberOfColors : "") + ") ";
    }
}
