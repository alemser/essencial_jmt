package essencialjmt.cap2.ex1;

import static essencialjmt.cap1.ThreadUtil.*;

import java.util.Arrays;

import essencialjmt.Repository;

public class Exercise1 {
    private Repository repo = new Repository();

    public void process(String... names) {
        //sequential
        long startTime = System.currentTimeMillis();        
        Arrays.stream(names).forEach(repo::findImageByName);
        long totalTime = System.currentTimeMillis() - startTime;

        //parallel
        long startTimeParallel = System.currentTimeMillis();
        Thread[] threads = Arrays.stream(names).map(name -> new Thread(() -> repo.findImageByName(name))).toArray(Thread[]::new);
        startAndJoin(threads);
        long totalTimeParallel = System.currentTimeMillis() - startTimeParallel;

        //difference
        String fastSlow = totalTimeParallel < totalTime ? " faster" : " slower";
        System.out.println("Load in parallel was " + Math.abs(((totalTimeParallel * 100 / totalTime) - 100)) + "% " + fastSlow);
    }

    public static void main(final String[] args) {
        String[] names = new String[] { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg",
                "/img9.jpg", "/img10.jpg" };
        new Exercise1().process(names);
    }
}
