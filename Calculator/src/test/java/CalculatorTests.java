import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
    void Calculate_AppleAndBread()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Apple, 1);
        basket.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(basket);

        Assertions.assertEquals(0.9, price);
    }

    @Test
    void Calculate_TwoSoupAndOneBread_DiscountApplied()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Soup, 2);
        basket.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(basket);

        Assertions.assertEquals(1.7, price);
    }

    @Test
    void Calculate_OneSoupAndOneBread_NoDiscount()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Soup, 1);
        basket.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(basket);

        Assertions.assertEquals(1.45, price);
    }

    @Test
    void Calculate_TwoSoup_NoDiscount()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Soup, 2);
        basket.put(ProductType.Bread, 0);

        Double price = _calculator.CalculatePrice(basket);

        Assertions.assertEquals(1.3, price);
    }

    @Test
    void Calculate_FourSoupAndTwoBread_DiscountAppliedTwice()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Soup, 4);
        basket.put(ProductType.Bread, 2);

        Double price = _calculator.CalculatePrice(basket);

        Assertions.assertEquals(3.4, price);
    }

    @Test
    void Calculate_TwoSoupAndOneBread_BeforeDiscount_NoDiscount()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Soup, 2);
        basket.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(basket, LocalDate.now().minusDays(2));

        Assertions.assertEquals(2.1, price);
    }

    @Test
    void Calculate_TwoSoupAndOneBread_DiscountStartDate_DiscountApplied()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Soup, 2);
        basket.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(basket, LocalDate.now().minusDays(1));

        Assertions.assertEquals(1.7, price);
    }

    @Test
    void Calculate_TwoSoupAndOneBread_DiscountEndDate_DiscountApplied()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Soup, 2);
        basket.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(basket, LocalDate.now().plusDays(5));

        Assertions.assertEquals(1.7, price);
    }

    @Test
    void Calculate_TwoSoupAndOneBread_AfterDiscount_NoDiscount()
    {
        HashMap<ProductType, Integer> basket = new HashMap<>();
        basket.put(ProductType.Soup, 2);
        basket.put(ProductType.Bread, 1);

        Double price = _calculator.CalculatePrice(basket, LocalDate.now().plusDays(6));

        Assertions.assertEquals(2.1, price);
    }
}
