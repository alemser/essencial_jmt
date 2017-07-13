package essencialjmt.cap2.ex4;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import essencialjmt.Repository;

public class Exercise4 {
    private Repository repo = new Repository();
    
    public void process(String[] names) {
        CompletableFuture<?>[] cfutures = Arrays.stream(names)
                .map(name -> CompletableFuture.supplyAsync(() -> repo.findImageByName(name)).thenAccept(System.out::println))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(cfutures).join();
    }

    public static void main(final String[] args) {
        String[] names = new String[] { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
                "/img9.jpg", "/img10.jpg" };
        long ini = System.currentTimeMillis();
        new Exercise4().process(names);
        System.out.println("took " + (System.currentTimeMillis() - ini));
    }
}
