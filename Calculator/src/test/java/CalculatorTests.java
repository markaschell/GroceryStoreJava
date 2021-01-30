import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
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

    @Test
    void Calculate_OneMilk()
    {
        Double price = _calculator.CalculatePrice(new HashMap<String, Integer>() {{ put("milk", 1); }});

        Assertions.assertEquals(1.3, price);
    }

    @Test
    void Calculate_OneBread()
    {
        Double price = _calculator.CalculatePrice(new HashMap<String, Integer>() {{ put("bread", 1); }});

        Assertions.assertEquals(0.8, price);
    }

    @Test
    void Calculate_UnknownProduct()
    {
        String productType = "tomato";

        Assertions.assertThrows(InvalidParameterException.class,
                                () -> _calculator.CalculatePrice(new HashMap<String, Integer>() {{ put(productType, 1); }}));
    }

}
