package essencialjmt.cap3.ex2;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import essencialjmt.cap3.Source;

public class AppEx2 {
    
    private Source source = new Ex2Source(Producer.getDeathPillId());    

    public AppEx2() throws InterruptedException, ExecutionException {
        Producer producer = new Producer(source, 2);
        runProduction(producer, 3);
    }

    public void runProduction(Producer producer, int retries) throws InterruptedException, ExecutionException {
        CompletableFuture<?> cf = CompletableFuture.runAsync(producer);
        cf.get();

        List<String> failures = producer.getFailures();
        if (!failures.isEmpty()) {
            System.out.println("The following images failed to process: " + failures);
            if (retries > 0) {
                System.out.println("Trying again..." + retries);
                retries--;
                failures.stream().forEach(s -> source.add(s));
                source.add(Producer.getDeathPillId());
                runProduction(producer, retries);
            } else {
                System.out.println("System has reached the max number o retries");
            }
        }
        
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new AppEx2();
    }
}
