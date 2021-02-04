package Discounts;

import java.time.LocalDate;
import java.util.HashMap;

import Products.ProductType;

public interface Discount {
    LocalDate GetStateDate();
    LocalDate GetEndDate();

    Integer CalculateNumberOfDiscounts(HashMap<ProductType, Integer> basket);
    Double GetDiscountAmount();
}
