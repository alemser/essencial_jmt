package essencialjmt.cap2.ex2;

import javax.imageio.ImageIO;

public class ImgV2 {
    static ImageManagerV2 imageManager = new ImageManagerV2();
    static String[] imageNames = { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
            "/img9.jpg", "/img10.jpg" };

    static {
        ImageIO.setUseCache(false);
        imageManager.loadImages(imageNames);
    }

    public static void main(final String[] args) {
        long ini = System.currentTimeMillis();
        imageManager.loadImages(imageNames);
        System.out.println(System.currentTimeMillis() - ini);
    }

}
