import Discounts.DiscountCalculator;
import Products.ProductPricer;
import Products.ProductType;

import java.time.LocalDate;
import java.util.HashMap;

// Did they also want an itemized list and not get the total?
public class Basket {
    LocalDate DefaultDate = LocalDate.now();

    ProductPricer _pricer = new ProductPricer();
    DiscountCalculator _discountCalculator = new DiscountCalculator();

    HashMap<ProductType, Integer> _products = new HashMap<>();

    public void AddProduct(ProductType productType, Integer count) {
        _products.put(productType, _products.getOrDefault(productType, 0) + count);
    }

    public HashMap<ProductType, Integer> GetProducts()
    {
        return _products;
    }

    public Double CalculatePrice() {
        return CalculatePrice(DefaultDate);
    }

    public Double CalculatePrice(LocalDate date) {
        double price = 0.0;

        for (ProductType productType : _products.keySet()) {
            price += _pricer.GetPrice(productType) * _products.get(productType);
        }

        if (date == null)
        {
            date = DefaultDate;
        }
        price -= _discountCalculator.Calculate(_products, date);

        return RoundToCents(price);
    }

    private double RoundToCents(double price) {
        return Math.round(price * 100.0) / 100.0;
    }
}