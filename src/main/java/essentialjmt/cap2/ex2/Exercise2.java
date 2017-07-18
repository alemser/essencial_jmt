package essentialjmt.cap2.ex2;

import essentialjmt.cap2.ImageProcessor;

public class Exercise2 {
    private ImageProcessor imageProcessor = new Ex2ImageProcessor();
    
    public Exercise2(String[] images) {
        imageProcessor.loadAlbum(images);
    }

    public static void main(final String[] args) {
        String[] names = new String[] { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
                "/img9.jpg", "/img10.jpg" };
        new Exercise2(names);
    }

}
