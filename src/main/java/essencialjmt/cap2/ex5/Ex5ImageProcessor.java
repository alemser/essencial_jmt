package essencialjmt.cap2.ex5;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import essencialjmt.*;
import essencialjmt.cap2.ImageProcessor;

public class Ex5ImageProcessor implements ImageProcessor {
    private Set<ImageListener> listeners = new HashSet<>();
    private Repository repo = new Repository();
    private ImageManager imageManager = new ImageManager();
    
    @Override
    public void loadAlbum(String... images) {
        CompletableFuture<?>[] cfutures = Arrays.stream(images).map(name -> CompletableFuture.supplyAsync(() -> repo.findImageByName(name))
                .thenApply(imageManager::extractDimension).thenApply(imageManager::countColours).thenAccept(this::fireImageProcessed))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(cfutures).join();
    }
    
    private void fireImageProcessed(ImageData data) {
        listeners.forEach(listener -> listener.imageProcessed(data));
    }

    @Override
    public void addImageListener(ImageListener listener) {
        listeners.add(listener);
    }
}
