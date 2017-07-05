package essencialjmt.cap2.ex1;

import javax.imageio.ImageIO;

public class ImgV1 {
    static ImageManagerV1 imageManager = new ImageManagerV1();
    static String[] imageNames = { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
            "/img9.jpg", "/img10.jpg" };

    static {
        ImageIO.setUseCache(false);
        imageManager.loadImagesParallel(imageNames);
    }

    public static void main(final String[] args) {
        long startTime = System.currentTimeMillis();
        imageManager.loadImages(imageNames);
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println(totalTime);

        long startTimeParallel = System.currentTimeMillis();
        imageManager.loadImagesParallel(imageNames);
        long totalTimeParallel = System.currentTimeMillis() - startTimeParallel;
        System.out.println(totalTimeParallel);

        String fastSlow = totalTimeParallel < totalTime ? " faster" : " slower";
        System.out.println("Load in parallel was " + Math.abs(((totalTimeParallel * 100 / totalTime) - 100)) + "% " + fastSlow);
    }
}
