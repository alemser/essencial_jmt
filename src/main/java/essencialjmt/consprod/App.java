package essencialjmt.consprod;

import java.util.concurrent.*;

public class App implements Source {

    private String[] imageNames = { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
            "/img9.jpg", "/img10.jpg" };
    
    private BlockingQueue<String> source = new LinkedBlockingQueue<>();
    
    public App() throws InterruptedException, ExecutionException {
        new Thread(()-> {
            for (int i = 0; i < imageNames.length; i++) {
                source.add(imageNames[i]);
            }

            source.add(Producer.getDeathPillId());
        }).start();

        Producer producer = new Producer(this, 2);
        CompletableFuture<?> cf = CompletableFuture.runAsync(producer);
        cf.get();
    }

    @Override
    public String getInput() throws InterruptedException {
        return source.take();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new App();
    }    
    
}
