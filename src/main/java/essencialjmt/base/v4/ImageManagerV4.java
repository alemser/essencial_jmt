package essencialjmt.base.v4;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import essencialjmt.ImageRepo;
import essencialjmt.ImageViewer;

public class ImageManagerV4 {

    private ImageRepo imageRepo = new ImageRepo();
    private ImageViewer imageViewer = new ImageViewer();

    public void loadImages(String... imageNames) {        
        CompletableFuture<?>[] cfutures = Arrays.stream(imageNames)
                .map(name -> CompletableFuture.supplyAsync(() -> imageRepo.loadImage(name)).thenAccept(imageViewer::display))
                .toArray(CompletableFuture[]::new);
        
        CompletableFuture.allOf(cfutures).join();
    }
}