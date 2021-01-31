import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class CalculatorScenarioTests {

    Calculator _calculator;

    @BeforeEach
    void setup()
    {
        _calculator = new Calculator();
    }

    @Test
    void Calculate_ThreeSoupsAndTwoLoavesBoughtToday_OneDiscountApplied()
    {
        HashMap<ProductType, Integer> list = new HashMap<>();
        list.put(ProductType.Soup, 3);
        list.put(ProductType.Bread, 2);

        Double price = _calculator.CalculatePrice(list);

        Assertions.assertEquals(3.15, price);
    }

    @Test
    void Calculate_SixApplesAndOneMilkBoughtToday_NoDiscountApplied()
    {
        HashMap<ProductType, Integer> list = new HashMap<>();
        list.put(ProductType.Apple, 6);
        list.put(ProductType.Milk, 1);

        Double price = _calculator.CalculatePrice(list);

        Assertions.assertEquals(1.9, price);
    }

/*    @Test
    void Calculate_SixApplesAndOneMilkBoughtFiveDaysFromNow_AppleDiscountApplied()
    {
        HashMap<ProductType, Integer> list = new HashMap<>();
        list.put(ProductType.Apple, 6);
        list.put(ProductType.Milk, 1);

        Double price = _calculator.CalculatePrice(list);

        Assertions.assertEquals(1.84, price);
    }

    @Test
    void Calculate_ThreeApplesTwoSoupAndOneBreadBoughtFiveDaysFromNow_BothDiscountsApplied()
    {
        HashMap<ProductType, Integer> list = new HashMap<>();
        list.put(ProductType.Apple, 3);
        list.put(ProductType.Soup, 2);
        list.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(list);

        Assertions.assertEquals(1.97, price);
    } */
}
