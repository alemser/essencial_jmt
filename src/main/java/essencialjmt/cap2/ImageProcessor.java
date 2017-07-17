package essencialjmt.cap2;

import essencialjmt.ImageListener;

public interface ImageProcessor {
    
    void loadAlbum(String... images);
    void addImageListener(ImageListener listener);
}
