package Discounts;

import Products.ProductPricer;
import Products.ProductType;

import java.time.LocalDate;
import java.util.HashMap;

public class HalfOffBreadWithTwoSoupDiscount implements Discount {
    ProductPricer _pricer = new ProductPricer();

    public LocalDate GetStateDate() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        return yesterday;
    }

    public LocalDate GetEndDate() {
        LocalDate SixDaysAfterStartDate = this.GetStateDate().plusDays(6);

        return SixDaysAfterStartDate;
    }

    public Integer CalculateNumberOfDiscounts(HashMap<ProductType, Integer> basket) {
        int numberOfPotentialDiscountsForSoup = basket.getOrDefault(ProductType.Soup, 0) / 2;
        int numberOfPotentialDiscountsForBread = basket.getOrDefault(ProductType.Bread, 0);

        return Math.min(numberOfPotentialDiscountsForSoup, numberOfPotentialDiscountsForBread);
    }

    public Double GetDiscountAmount() {
        return _pricer.GetPrice(ProductType.Bread) / 2.0;
    }
}
