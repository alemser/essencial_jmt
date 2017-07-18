package essentialjmt.cap3.ex1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import essentialjmt.cap3.Source;

public class AppEx1 {

    public void process () throws InterruptedException, ExecutionException {
        Source source = new Source(Producer.getDeathPillId());
        
        Producer producer = new Producer(source, 2);
        
        CompletableFuture<?> cf = CompletableFuture.runAsync(producer);
        cf.get();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new AppEx1().process();
    }
}
