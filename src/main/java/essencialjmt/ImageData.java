package essencialjmt;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ImageData {
    private BufferedImage bufferedImage;
    private String name;
    private Set<Color> singleColors;
    private String resolution;
    
    private AtomicInteger likes = new AtomicInteger(0);
    private List<String> comments = new ArrayList<>();
    
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReadLock readLock = lock.readLock();
    private WriteLock writeLock = lock.writeLock();

    public ImageData(final String name, final BufferedImage bufferedImage) {
        this.name = name;
        this.bufferedImage = bufferedImage;
    }

    public Integer getNumberOfColors() {
        return singleColors.size();
    }

    public void setColors(Set<Color> listOfSingleColors) {
        this.singleColors = listOfSingleColors;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(final String resolution) {
        this.resolution = resolution;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + (singleColors != null ? "#colors: " + singleColors.size() : "") + (resolution != null ? "; res: " + resolution : "") + ") ";
    }

    public List<String> getComments() {
        return Collections.unmodifiableList(comments);
    }
    
    public boolean containsComment(String comment) {
        readLock.lock();
        try {
            return comment.contains(comment);
        } finally {
            readLock.unlock();
        }
    }

    public void excludeCommentsWith(String strToExclude) {
        writeLock.lock();
        try {
            comments.removeIf(s -> s.contains(strToExclude));
        } finally {
            writeLock.unlock();
        }
    }

    public void addComment(String... comments) {
        writeLock.lock();
        try {
            if (this.comments.size() < 10) {
                for (String comment : comments) {
                    if (comment != null) {
                        this.comments.add(comment);
                    }
                    if (this.comments.size() > 10) {
                        break;
                    }
                }
            }
        } finally {
            writeLock.unlock();
        }
    }

    public int like() {
        return likes.incrementAndGet();
    }

    public int unlike() {
        return likes.decrementAndGet();
    }

    public int getLikes() {
        return likes.get();
    }

    public Set<Color> getSingleColors() {
        return singleColors;
    }
}
