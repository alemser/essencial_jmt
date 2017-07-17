package essencialjmt.cap3.ex2;

import java.util.concurrent.ExecutionException;

import essencialjmt.cap3.Source;

public class AppEx2 {
    
    private Source source = new BadImageSource(Producer.getDeathPillId());    

    public void process() throws InterruptedException, ExecutionException {
        Producer producer = new Producer(source, 2);
        runProduction(producer, 3);
    }

    public void runProduction(Producer producer, int retries) throws InterruptedException, ExecutionException {
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new AppEx2().process();
    }
}
