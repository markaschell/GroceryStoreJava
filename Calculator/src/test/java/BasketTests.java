import Products.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BasketTests {

    Basket _basket;

    @BeforeEach
    void setup()
    {
        _basket = new Basket();
    }

    @Test
    void Calculate_NoItems()
    {
        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(0.0, price);
    }

    @Test
    void Calculate_OneApple()
    {
        _basket.AddProduct(ProductType.Apple, 1);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(0.1, price);
    }

    @Test
    void Calculate_OneMilk()
    {
        _basket.AddProduct(ProductType.Milk, 1);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(1.3, price);
    }

    @Test
    void Calculate_OneBread()
    {
        _basket.AddProduct(ProductType.Bread, 1);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(0.8, price);
    }

    @Test
    void Calculate_TwoApples()
    {
        _basket.AddProduct(ProductType.Apple, 1);
        _basket.AddProduct(ProductType.Apple, 1);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(0.2, price);
    }

    @Test
    void Calculate_AppleAndBread()
    {
        _basket.AddProduct(ProductType.Apple, 1);
        _basket.AddProduct(ProductType.Bread, 1);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(0.9, price);
    }

    @Test
    void Calculate_DateIsNull_AssumeToday()
    {
        _basket.AddProduct(ProductType.Soup, 2);
        _basket.AddProduct(ProductType.Bread, 1);

        Double price = _basket.CalculatePrice(null);

        Assertions.assertEquals(1.7, price);
    }

    @Test
    void Calculate_TwoSoupAndOneBread_DiscountApplied()
    {
        _basket.AddProduct(ProductType.Soup, 2);
        _basket.AddProduct(ProductType.Bread, 1);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(1.7, price);
    }

    @Test
    void Calculate_OneSoupAndOneBread_NoDiscount()
    {
        _basket.AddProduct(ProductType.Soup, 1);
        _basket.AddProduct(ProductType.Bread, 1);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(1.45, price);
    }

    @Test
    void Calculate_TwoSoup_NoDiscount()
    {
        _basket.AddProduct(ProductType.Soup, 2);
        _basket.AddProduct(ProductType.Bread, 0);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(1.3, price);
    }

    @Test
    void Calculate_FourSoupAndTwoBread_DiscountAppliedTwice()
    {
        _basket.AddProduct(ProductType.Soup, 4);
        _basket.AddProduct(ProductType.Bread, 2);

        Double price = _basket.CalculatePrice();

        Assertions.assertEquals(3.4, price);
    }

    @Test
    void Calculate_TwoSoupAndOneBread_BeforeDiscount_NoDiscount()
    {
        _basket.AddProduct(ProductType.Soup, 2);
        _basket.AddProduct(ProductType.Bread, 1);

        Double price = _basket.CalculatePrice(LocalDate.now().minusDays(2));

        Assertions.assertEquals(2.1, price);
    }

    @Test
    void Calculate_TwoSoupAndOneBread_DiscountStartDate_DiscountApplied()
    {
        _basket.AddProduct(ProductType.Soup, 2);
        _basket.AddProduct(ProductType.Bread, 1);

        Double price = _basket.CalculatePrice(LocalDate.now().minusDays(1));

        Assertions.assertEquals(1.7, price);
    }

    @Test
    void Calculate_TwoSoupAndOneBread_DiscountEndDate_DiscountApplied()
    {
        _basket.AddProduct(ProductType.Soup, 2);
        _basket.AddProduct(ProductType.Bread, 1);

        Double price = _basket.CalculatePrice(LocalDate.now().plusDays(5));

        Assertions.assertEquals(1.7, price);
    }

    @Test
    void Calculate_TwoSoupAndOneBread_AfterDiscount_NoDiscount()
    {
        _basket.AddProduct(ProductType.Soup, 2);
        _basket.AddProduct(ProductType.Bread, 1);

        Double price = _basket.CalculatePrice(LocalDate.now().plusDays(6));

        Assertions.assertEquals(2.1, price);
    }

    @Test
    void Calculate_Apple_BeforeDiscount_NoDiscount()
    {
        _basket.AddProduct(ProductType.Apple, 1);

        Double price = _basket.CalculatePrice(LocalDate.now().plusDays(2));

        Assertions.assertEquals(.1, price);
    }

    @Test
    void Calculate_Apple_DiscountStartDate_DiscountApplied()
    {
        _basket.AddProduct(ProductType.Apple, 1);

        Double price = _basket.CalculatePrice(LocalDate.now().plusDays(3));

        Assertions.assertEquals(0.09, price);
    }

    @Test
    void Calculate_TwoApples_DiscountStartDate_DiscountApplied()
    {
        _basket.AddProduct(ProductType.Apple, 2);

        Double price = _basket.CalculatePrice(LocalDate.now().plusDays(3));

        Assertions.assertEquals(0.18, price);
    }

    // Need to ask the client if this is the next month after today or next month 3 days from now
    @Test
    void Calculate_Apple_DiscountEndDate_DiscountApplied()
    {
        _basket.AddProduct(ProductType.Apple, 1);

        // Given the interface I am not sure now to set this a bit more clear.  I could mock out today
        LocalDate lastDayOfNextMonth = LocalDate.now().plusMonths(2).withDayOfMonth(1).minusDays(1);

        Double price = _basket.CalculatePrice(lastDayOfNextMonth);

        Assertions.assertEquals(0.09, price);
    }

    @Test
    void Calculate_Apple_AfterDiscount_NoDiscount()
    {
        _basket.AddProduct(ProductType.Apple, 1);

        LocalDate firstDayOfTwoMonthsFromNow = LocalDate.now().plusMonths(2).withDayOfMonth(1);

        Double price = _basket.CalculatePrice(firstDayOfTwoMonthsFromNow);

        Assertions.assertEquals(0.1, price);
    }
}
