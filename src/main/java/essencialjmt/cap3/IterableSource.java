
package essencialjmt.cap3;

import java.util.Iterator;

public class IterableSource extends Source implements Iterator<String>, Iterable<String>{

    private static final String DEATH_PILL = "#/";
    private String currentInput;

    public IterableSource() {
        super(DEATH_PILL);
    }
    
    @Override
    public boolean hasNext() {
        currentInput = getInput();
        return currentInput.equals(DEATH_PILL) ? false : true;
    }

    @Override
    public String next() {
        return currentInput;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }
}
