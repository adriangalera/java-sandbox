import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.Test;

public class MapTest {

    @Test
    public void testJava8Map() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");

        Optional.ofNullable(map.get("b"))
            .orElseThrow( () -> new IllegalStateException());
    }
}
