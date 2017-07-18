package essentialjmt.cap2;

import essentialjmt.ImageListener;

public interface ImageProcessor {
    
    void loadAlbum(String... images);
    void addImageListener(ImageListener listener);
}
