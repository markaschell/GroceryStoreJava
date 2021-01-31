import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Soup, 3);
        basket.put(ProductType.Bread, 2);

        Double price = _calculator.CalculatePrice(basket);

        Assertions.assertEquals(3.15, price);
    }

    @Test
    void Calculate_SixApplesAndOneMilkBoughtToday_NoDiscountApplied()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Apple, 6);
        basket.put(ProductType.Milk, 1);

        Double price = _calculator.CalculatePrice(basket);

        Assertions.assertEquals(1.9, price);
    }

    @Test
    void Calculate_SixApplesAndOneMilkBoughtFiveDaysFromNow_AppleDiscountApplied()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Apple, 6);
        basket.put(ProductType.Milk, 1);

        Double price = _calculator.CalculatePrice(basket, LocalDate.now().plusDays(5));

        Assertions.assertEquals(1.84, price);
    }

    @Test
    void Calculate_ThreeApplesTwoSoupAndOneBreadBoughtFiveDaysFromNow_BothDiscountsApplied()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Apple, 3);
        basket.put(ProductType.Soup, 2);
        basket.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(basket, LocalDate.now().plusDays(5));

        Assertions.assertEquals(1.97, price);
    }
}
