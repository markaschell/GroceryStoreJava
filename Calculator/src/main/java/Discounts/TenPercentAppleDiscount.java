package Discounts;

import Products.ProductPricer;
import Products.ProductType;

import java.time.LocalDate;
import java.util.HashMap;

public class TenPercentAppleDiscount implements Discount {

    ProductPricer _pricer = new ProductPricer();

    public LocalDate GetStateDate() {
        LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);

        return threeDaysFromNow;
    }

    public LocalDate GetEndDate() {
        LocalDate twoMonthsFromNow = LocalDate.now().plusMonths(2);
        LocalDate firstDayOfTheMonthTwoMonthsFromNow = twoMonthsFromNow.withDayOfMonth(1);
        LocalDate lastDayOfTheMonthOneMonthFromNow = firstDayOfTheMonthTwoMonthsFromNow.minusDays(1);

        return lastDayOfTheMonthOneMonthFromNow;
    }

    public Integer CalculateNumberOfDiscounts(HashMap<ProductType, Integer> basket) {
        return basket.getOrDefault(ProductType.Apple, 0);
    }

    public Double GetDiscountAmount() {
        return _pricer.GetPrice(ProductType.Apple) * 0.1;
    }
}
