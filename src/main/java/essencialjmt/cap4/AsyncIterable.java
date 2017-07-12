
package essencialjmt.cap4;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import essencialjmt.ImageData;

public class AsyncIterable implements Iterator<ImageData>, Iterable<ImageData>{
    private BlockingQueue<ImageData> source = new LinkedBlockingQueue<>();

    public static final ImageData DEATH_PILL = new ImageData("#/", null);

    @Override
    public boolean hasNext() {
        return "#/".equals(source.peek().getName()) ? false : true;
    }

    @Override
    public ImageData next() {
        return getInput();
    }

    @Override
    public Iterator<ImageData> iterator() {
        return this;
    }
    
    public void add(ImageData input) {
        source.add(input);
    }

    public ImageData getInput() {
        try {
            return source.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
