package essencialjmt.intro.img.v3;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import essencialjmt.*;

public class ImageManagerV3 {
    private ImageRepo imageRepo = new ImageRepo();
    private ImageViewer imageViewer = new ImageViewer();

    public void loadImages(String... imageNames) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        ExecutorCompletionService<ImageData> service = new ExecutorCompletionService<>(executor);
        List<Future<?>> futures = Arrays.stream(imageNames)
                .map(name -> service.submit(()->imageRepo.loadImage(name)))
                .collect(Collectors.toList());
        
        executor.shutdown();
        try {
            for (int i = 0; i < futures.size(); i++) {
                imageViewer.display(service.take().get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error reading image", e);
        }        
    }
}