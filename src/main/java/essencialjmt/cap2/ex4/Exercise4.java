package essencialjmt.cap2.ex4;

import essencialjmt.*;
import essencialjmt.cap2.ImageProcessor;

public class Exercise4 implements ImageListener {
    private ImageProcessor imageProcessor = new Ex4ImageProcessor();
    
    public Exercise4(String[] images) {
        imageProcessor.addImageListener(this);
        imageProcessor.loadAlbum(images);
    }

    public static void main(final String[] args) {
        String[] images = new String[] { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
                "/img9.jpg", "/img10.jpg" };
        new Exercise4(images);
    }
    
    @Override
    public void imageProcessed(ImageData data) {
        System.out.println(data);   
    }
}
