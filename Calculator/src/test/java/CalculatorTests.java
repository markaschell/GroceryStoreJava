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
        Double price = _calculator.CalculatePrice(new HashMap<ProductType, Integer>() {{ put(ProductType.Apple, 1); }});

        Assertions.assertEquals(0.1, price);
    }

    @Test
    void Calculate_OneMilk()
    {
        Double price = _calculator.CalculatePrice(new HashMap<ProductType, Integer>() {{ put(ProductType.Milk, 1); }});

        Assertions.assertEquals(1.3, price);
    }

    @Test
    void Calculate_OneBread()
    {
        Double price = _calculator.CalculatePrice(new HashMap<ProductType, Integer>() {{ put(ProductType.Bread, 1); }});

        Assertions.assertEquals(0.8, price);
    }

    @Test
    void Calculate_TwoApples()
    {
        Double price = _calculator.CalculatePrice(new HashMap<ProductType, Integer>() {{ put(ProductType.Apple, 2); }});

        Assertions.assertEquals(0.2, price);
    }
}
