import org.junit.Test;

public class MultipleConditionIf {

    boolean firstCondition() {
        System.out.println("First condition");
        return true;
    }

    boolean secondCondition() {
        System.out.println("Second condition");
        return false;
    }

    @Test
    public void shouldNotInvokeSecondCondition() {
        if (firstCondition() || secondCondition()) {
            System.out.println("*");
        }
    }
}
