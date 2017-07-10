package essencialjmt.cap2.ex5;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import essencialjmt.*;

public class ImageManagerV5 {

    private ImageRepo imageRepo = new ImageRepo();
    private ImageViewer imageViewer = new ImageViewer();

    public void loadImages(String... imageNames) {
        CompletableFuture<?>[] cfutures = Arrays.stream(imageNames).map(name -> CompletableFuture.supplyAsync(() -> imageRepo.loadImage(name))
                .thenApply(this::extractDimension).thenApply(this::countColours).thenAccept(imageViewer::display)).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(cfutures).join();
    }

    ImageData countColours(ImageData data) {
        Set<Integer> colors = new HashSet<Integer>();
        int w = data.getBufferedImage().getWidth();
        int h = data.getBufferedImage().getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int pixel = data.getBufferedImage().getRGB(x, y);
                colors.add(pixel);
            }
        }
        data.setColors(colors);
        return data;
    }

    ImageData extractDimension(ImageData data) {
        data.setResolution(data.getBufferedImage().getWidth() + " x " + data.getBufferedImage().getHeight());
        return data;
    }
}