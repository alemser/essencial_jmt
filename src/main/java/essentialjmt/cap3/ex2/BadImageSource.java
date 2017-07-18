
package essentialjmt.cap3.ex2;

import java.util.List;

import essentialjmt.cap3.Source;

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
