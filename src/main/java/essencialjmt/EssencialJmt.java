package essencialjmt;

public class EssencialJmt {

    private static int counter = 1;

    public static void main(final String[] args) {
        System.out.printf("Existem atualmente %d thread(s) ativa(s) no sistema", Thread.activeCount());

        counter++;

        final long threadId = Thread.currentThread().getId();

        System.out.printf("Thead %d atualizou o valor do contador para %d%n", threadId, counter);
    }
}
