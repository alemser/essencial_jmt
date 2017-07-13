package essencialjmt.cap2.ex2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import essencialjmt.ImageData;
import essencialjmt.Repository;

public class Exercise2 {
    private Repository repo = new Repository();
    
    public void process(String[] names) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        
        List<Future<ImageData>> futures = Arrays.stream(names)
                .map(name -> service.submit(() -> repo.findImageByName(name)))
                .collect(Collectors.toList());        
        service.shutdown();
        
        try {
            for (Future<ImageData> future : futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error reading image", e);
        }
    }

    public static void main(final String[] args) {
        String[] names = new String[] { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
                "/img9.jpg", "/img10.jpg" };
        long ini = System.currentTimeMillis();
        new Exercise2().process(names);
        System.out.println("took " + (System.currentTimeMillis() - ini));
    }

}
