
package essencialjmt.cap3;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Source {

    private BlockingQueue<String> source = new LinkedBlockingQueue<>();

    public Source(String deathPill) {
        new Thread(() -> {
            getImageNames().stream().forEach(source::add);
            source.add(deathPill);
        }).start();
    }

    protected List<String> getImageNames() {
        return new ArrayList<>(Arrays.asList("/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
                "/img9.jpg", "/img10.jpg"));
    }

    public void add(String... inputs) {
        for (String input : inputs) {
            source.add(input);
        }
    }

    public String getInput() {
        try {
            return source.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
