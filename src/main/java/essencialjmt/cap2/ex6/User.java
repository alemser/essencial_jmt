package essencialjmt.cap2.ex6;

public abstract class User implements Runnable {

    String name;
    String imageName;

    public User(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }

}
