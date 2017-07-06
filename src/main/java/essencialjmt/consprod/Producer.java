package essencialjmt.consprod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import essencialjmt.ImageRepo;

public class Producer implements Runnable {
    
    private ImageRepo repo = new ImageRepo();
    private Work work = new Work();
    private List<CompletableFuture<?>> futures = new ArrayList<>();
    private Source source;
    private int consumerCount;
    
    public Producer(Source source, int consumerCount) {
        this.source = source;
        this.consumerCount = consumerCount;
        
        for (int i = 0; i < consumerCount; i++) {
            futures.add(CompletableFuture.runAsync(new Consumer(work)));
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                final String name = source.getInput();
                if( getDeathPillId().equals(name)) {
                    break;
                }
                
                System.out.println("Producing " + name);
                work.produce(repo.loadImage(name));
            }
            
            System.out.println("\nEnd of production");
            for (int i = 0; i < consumerCount; i++) {
                work.produce(Consumer.createDeathPill());
            }
            
            for (CompletableFuture<?> completableFuture : futures) {
                completableFuture.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static String getDeathPillId() {
        return "#done#";
    }
}
