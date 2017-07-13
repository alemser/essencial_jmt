package essencialjmt.cap3.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import essencialjmt.Repository;
import essencialjmt.cap3.Source;

public class Producer implements Runnable {
    
    private Repository repo = new Repository();
    private Work work = new Work();
    private List<CompletableFuture<?>> futures = new ArrayList<>();
    private Source source;
    private int consumerCount;
    
    public Producer(Source source, int consumerCount) {
        this.source = source;
        this.consumerCount = consumerCount;
        
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < consumerCount; i++) {
            futures.add(CompletableFuture.runAsync(new Consumer(work), executor));
        }
        executor.shutdown();
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
                work.produce(repo.findImageByName(name));
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
