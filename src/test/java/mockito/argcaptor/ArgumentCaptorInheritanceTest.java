package mockito.argcaptor;

import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ArgumentCaptorInheritanceTest {

    @Mock
    private AnimalProcessor animalProcessor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    // https://github.com/mockito/mockito/issues/565
    @Test
    public void shouldProcessDogButFails() {
        Dog dog = new Dog("Rex");
        Cat cat = new Cat("blue");

        ArgumentCaptor<Dog> dogArgumentCaptor = ArgumentCaptor.forClass(Dog.class);

        animalProcessor.processAnimal(dog);
        animalProcessor.processAnimal(cat);

        Mockito.verify(animalProcessor).processAnimal(dogArgumentCaptor.capture());

        Assert.assertEquals("Rex", dogArgumentCaptor.getValue().getName());
    }


    @Test
    public void shouldProcessDog() {
        Dog dog = new Dog("Rex");
        Cat cat = new Cat("blue");

        ArgumentCaptor<Animal> animalCaptor = ArgumentCaptor.forClass(Animal.class);

        animalProcessor.processAnimal(dog);
        animalProcessor.processAnimal(cat);

        Mockito.verify(animalProcessor, Mockito.times(2)).processAnimal(animalCaptor.capture());

        List<Animal> processedAnimals = animalCaptor.getAllValues();
        Optional<Animal> dogOptional = processedAnimals.stream().filter(a -> a instanceof Dog).findFirst();
        Assert.assertTrue(dogOptional.isPresent());
        Assert.assertEquals("Rex", ((Dog) dogOptional.get()).getName());
    }
}
