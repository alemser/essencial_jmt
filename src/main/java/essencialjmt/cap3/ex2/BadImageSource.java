
package essencialjmt.cap3.ex2;

import java.util.List;

import essencialjmt.cap3.Source;

public class BadImageSource extends Source {
    
    public BadImageSource(String deathPill) {
        super(deathPill);
    }

    @Override
    protected List<String> getImageNames() {
        List<String> imagesNames = super.getImageNames();
        imagesNames.add("/img5.x.jpg");
        return imagesNames;
    }
    
}
