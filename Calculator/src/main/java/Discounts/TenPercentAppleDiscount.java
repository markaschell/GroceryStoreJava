package Discounts;

import Products.ProductPricer;
import Products.ProductType;

import java.time.LocalDate;
import java.util.HashMap;

public class TenPercentAppleDiscount implements Discount {

    ProductPricer _pricer = new ProductPricer();

    public LocalDate GetStateDate() {
        return LocalDate.now().plusDays(3);
    }

    public LocalDate GetEndDate() {
        return LocalDate.now().plusMonths(2).withDayOfMonth(1).minusDays(1);
    }

    public Integer CalculateNumberOfDiscounts(HashMap<ProductType, Integer> basket) {
        return basket.getOrDefault(ProductType.Apple, 0);
    }

    public Double GetDiscountAmount() {
        return _pricer.GetPrice(ProductType.Apple) * 0.1;
    }
}
