package essencialjmt.base;

import static essencialjmt.base.ThreadUtil.*;

public class Intro1_0 {

    private static int counter;

    public static void main(final String[] args) {
        printDetails();
        printThreadPossibleStates();

        counter++;

        System.out.println("counter = " + counter);
    }
}
