package mockito.argcaptor;

public class Dog extends Animal {

    private String name;

    public Dog(String name) {
        super("Dog");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
