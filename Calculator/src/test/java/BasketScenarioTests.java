import Products.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BasketScenarioTests {

    Basket _basket;

    @BeforeEach
    void setup()
    {
        _basket = new Basket();
    }

    @Test
    void Calculate_ThreeSoupsAndTwoLoavesBoughtToday_OneDiscountApplied()
    {
        _basket.AddProduct(ProductType.Soup, 3);
        _basket.AddProduct(ProductType.Bread, 2);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(3.15, price);
    }

    @Test
    void Calculate_SixApplesAndOneMilkBoughtToday_NoDiscountApplied()
    {
        _basket.AddProduct(ProductType.Apple, 6);
        _basket.AddProduct(ProductType.Milk, 1);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(1.9, price);
    }

    @Test
    void Calculate_SixApplesAndOneMilkBoughtFiveDaysFromNow_AppleDiscountApplied()
    {
        _basket.AddProduct(ProductType.Apple, 6);
        _basket.AddProduct(ProductType.Milk, 1);

        Double price = _basket.CalculatePrice(LocalDate.now().plusDays(5));

        Assertions.assertEquals(1.84, price);
    }

    @Test
    void Calculate_ThreeApplesTwoSoupAndOneBreadBoughtFiveDaysFromNow_BothDiscountsApplied()
    {
        _basket.AddProduct(ProductType.Apple, 3);
        _basket.AddProduct(ProductType.Soup, 2);
        _basket.AddProduct(ProductType.Bread, 1);

        Double price = _basket.CalculatePrice(LocalDate.now().plusDays(5));

        Assertions.assertEquals(1.97, price);
    }
}
