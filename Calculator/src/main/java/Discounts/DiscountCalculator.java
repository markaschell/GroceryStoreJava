package Discounts;

import Products.ProductType;

import java.util.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DiscountCalculator {
    List<Discount> _discounts = new ArrayList<>();

    public DiscountCalculator() {
        _discounts.add(new TenPercentAppleDiscount());
        _discounts.add(new HalfOffBreadWithTwoSoupDiscount());
    }

    public Double Calculate(HashMap<ProductType, Integer> basket, LocalDate date) {
        double amount = 0.0;

        // Can multiple discounts be applied to the same item?
        for (Discount discount : _discounts) {
            if (date.isBefore(discount.GetStateDate()) || date.isAfter(discount.GetEndDate())) {
                continue;
            }

            amount += discount.GetDiscountAmount() * discount.CalculateNumberOfDiscounts(basket);
        }

        return amount;
    }
}
