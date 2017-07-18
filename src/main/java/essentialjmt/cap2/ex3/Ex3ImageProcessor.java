package essentialjmt.cap2.ex3;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import essentialjmt.*;
import essentialjmt.cap2.ImageProcessor;

public class Ex3ImageProcessor implements ImageProcessor {
    private Set<ImageListener> listeners = new HashSet<>();
    private Repository repo = new Repository();
    
    @Override
    public void loadAlbum(String... images) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        ExecutorCompletionService<ImageData> service = new ExecutorCompletionService<>(executor);
        List<Future<?>> futures = Arrays.stream(images)
                .map(name -> service.submit(()-> repo.findImageByName(name)))
                .collect(Collectors.toList());

        executor.shutdown();
        try {
            for (int i = 0; i < futures.size(); i++) {
                fireImageProcessed(service.take().get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error reading image", e);
        } 
    }
    
    private void fireImageProcessed(ImageData data) {
        listeners.forEach(listener -> listener.imageProcessed(data));
    }

    @Override
    public void addImageListener(ImageListener listener) {
        listeners.add(listener);
    }
}
