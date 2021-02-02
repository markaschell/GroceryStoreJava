import Discounts.DiscountCalculator;
import Products.ProductPricer;
import Products.ProductType;

import java.time.LocalDate;
import java.util.HashMap;

// Should I have created a basket object instead of his calculator?
// Did they also want an itemized list and not get the total?
public class Basket {

    ProductPricer _pricer = new ProductPricer();
    DiscountCalculator _discountCalculator = new DiscountCalculator();

    public Double CalculatePrice(HashMap<ProductType, Integer> basket) {
        return CalculatePrice(basket, LocalDate.now());
    }

    public Double CalculatePrice(HashMap<ProductType, Integer> basket, LocalDate date) {
        double price = 0.0;

        for (ProductType productType : basket.keySet()) {
            // Push this logic down into the pricer - Should a product be aware of a basket?
            price += _pricer.GetPrice(productType) * basket.get(productType);
        }

        price -= _discountCalculator.Calculate(basket, date);

        // Should we introduce a Money object?  Is this still needed?
        return Math.round(price * 100.0) / 100.0;
    }
}