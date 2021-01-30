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
    void Calculate_NoItems()
    {
        Double price = _calculator.CalculatePrice(new HashMap<>());

        Assertions.assertEquals(0.0, price);
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

    @Test
    void Calculate_ApplyAndBread()
    {
        HashMap<ProductType, Integer> list = new HashMap<>();
        list.put(ProductType.Apple, 1);
        list.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(list);

        Assertions.assertEquals(0.9, price);
    }

    @Test
    void Calculate_HalfOffBreadWhenTwoSoups()
    {
        HashMap<ProductType, Integer> list = new HashMap<>();
        list.put(ProductType.Soup, 2);
        list.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(list);

        Assertions.assertEquals(1.7, price);
    }
}
