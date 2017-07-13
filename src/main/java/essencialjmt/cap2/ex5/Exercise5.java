package essencialjmt.cap2.ex5;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import essencialjmt.ImageManager;
import essencialjmt.Repository;
import essencialjmt.cap2.ex4.Exercise4;

public class Exercise5 {
    Repository repo = new Repository();
    ImageManager imageManager = new ImageManager();

    public void process(String[] names) {
        CompletableFuture<?>[] cfutures = Arrays.stream(names).map(name -> CompletableFuture.supplyAsync(() -> repo.findImageByName(name))
                .thenApply(imageManager::extractDimension).thenApply(imageManager::countColours).thenAccept(System.out::println))
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
