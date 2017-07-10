package essencialjmt.cap2.ex5;

public class ImgV5 {
    static ImageManagerV5 imageManager = new ImageManagerV5();
    static String[] imageNames = { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
            "/img9.jpg", "/img10.jpg" };

    public static void main(final String[] args) {
        long ini = System.currentTimeMillis();
        imageManager.loadImages(imageNames);
        System.out.println(System.currentTimeMillis() - ini);
    }

}
