import Discounts.DiscountCalculator;
import Products.ProductPricer;
import Products.ProductType;

import java.time.LocalDate;
import java.util.HashMap;

public class Calculator {

    ProductPricer _pricer = new ProductPricer();
    DiscountCalculator _discountCalculator = new DiscountCalculator();

    public Double CalculatePrice(HashMap<ProductType, Integer> basket) {
        return CalculatePrice(basket, LocalDate.now());
    }

    public Double CalculatePrice(HashMap<ProductType, Integer> basket, LocalDate date) {

        double price = 0.0;

        for (ProductType productType : basket.keySet()) {
            price += _pricer.GetPrice(productType) * basket.get(productType);
        }

        price -= _discountCalculator.Calculate(basket, date);

        // Should we introduce a Money object?
        return Math.round(price * 100.0) / 100.0;
    }
}
