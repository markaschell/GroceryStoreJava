import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class CalculatorTests {

    Calculator _calculator;

    @BeforeEach
    void setup()
    {
        _calculator = new Calculator();
    }

    @Test
    void Calculate_OneApple()
    {
        Double price = _calculator.CalculatePrice(new HashMap<String, Integer>() {{ put("apple", 1); }});

        Assertions.assertEquals(0.1, price);
    }
}
