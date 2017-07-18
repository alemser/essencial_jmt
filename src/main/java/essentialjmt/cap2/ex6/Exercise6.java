package essentialjmt.cap2.ex6;

import java.util.concurrent.CompletableFuture;

import essentialjmt.*;
import essentialjmt.cap2.ImageProcessor;
import essentialjmt.cap2.ex4.Exercise4;

public class Exercise6 implements ImageListener {
    private ImageManager imageManager = new ImageManager();
    private ImageProcessor imageProcessor = new Ex6ImageProcessor();

    public Exercise6(String[] images) {
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
        CompletableFuture.runAsync(() -> imageManager.countColours(data)).thenRun(()-> imageManager.extractDimension(data)).thenAccept(System.out::print);
    }
}
