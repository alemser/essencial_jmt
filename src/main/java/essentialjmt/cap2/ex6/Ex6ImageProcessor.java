package essentialjmt.cap2.ex6;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import essentialjmt.*;
import essentialjmt.cap2.ImageProcessor;

public class Ex6ImageProcessor implements ImageProcessor {
    private Set<ImageListener> listeners = new HashSet<>();
    private Repository repo = new Repository();
    
    @Override
    public void loadAlbum(String... images) {
        CompletableFuture<?>[] cfutures = Arrays.stream(images).map(name -> CompletableFuture.supplyAsync(() -> repo.findImageByName(name))
                .thenAccept(this::fireImageProcessed)).toArray(CompletableFuture[]::new);
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
