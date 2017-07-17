package essencialjmt.cap2.ex1;

public class Exercise1 {
    private Ex1ImageProcessor imageProcessor = new Ex1ImageProcessor();

    public Exercise1(String... images) {
        //sequential
        long startTime = System.currentTimeMillis();        
        imageProcessor.processSingleThread(images);
        long totalTime = System.currentTimeMillis() - startTime;

        //parallel
        long startTimeParallel = System.currentTimeMillis();
        imageProcessor.loadAlbum(images);
        long totalTimeParallel = System.currentTimeMillis() - startTimeParallel;

        //difference
        String fastSlow = totalTimeParallel < totalTime ? " faster" : " slower";
        System.out.println("Load in parallel was " + Math.abs(((totalTimeParallel * 100 / totalTime) - 100)) + "% " + fastSlow);
    }

    public static void main(final String[] args) {
        String[] images = new String[] { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
                "/img9.jpg", "/img10.jpg" };
        new Exercise1(images);
    }
}
