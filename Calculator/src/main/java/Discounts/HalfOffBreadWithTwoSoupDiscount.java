package Discounts;

import Products.ProductPricer;
import Products.ProductType;

import java.time.LocalDate;
import java.util.HashMap;

public class HalfOffBreadWithTwoSoupDiscount implements Discount {
    ProductPricer _pricer = new ProductPricer();

    public LocalDate GetStateDate() {
        return LocalDate.now().minusDays(1);
    }

    public LocalDate GetEndDate() {
        return this.GetStateDate().plusDays(6);
    }

    public Integer CalculateNumberOfDiscounts(HashMap<ProductType, Integer> basket, LocalDate date) {
        return Math.min(basket.getOrDefault(ProductType.Soup, 0) / 2, basket.getOrDefault(ProductType.Bread, 0));
    }

    public Double GetDiscountAmount() {
        return _pricer.GetPrice(ProductType.Bread) / 2.0;
    }
}
