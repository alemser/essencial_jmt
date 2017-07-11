
package essencialjmt.cap3;

import java.util.Iterator;

public class IterableSource extends Source implements Iterator<String>, Iterable<String>{

    private static final String DEATH_PILL = "#/";

    public IterableSource() {
        super(DEATH_PILL);
    }
    
    @Override
    public boolean hasNext() {
        return DEATH_PILL.equals(source.peek()) ? false : true;
    }

    @Override
    public String next() {
        return getInput();
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }
}
