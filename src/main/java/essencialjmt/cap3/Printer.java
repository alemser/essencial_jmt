package essencialjmt.cap3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Printer implements Runnable {

    private BlockingQueue<Object> queue = new LinkedBlockingQueue<>();
    
    public void print(Object data) {
        queue.add(data);
    }

    public void end() {
        queue.add("bye");
    }

    @Override
    public void run() {
        Object data;
        try {
            while (!"bye".equals((data = queue.take()))) {
                System.out.println(data);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
