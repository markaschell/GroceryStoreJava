import java.security.InvalidParameterException;
import java.util.HashMap;

public class Calculator {
    public Double CalculatePrice(HashMap<ProductType, Integer> basket) {

        double price = 0.0;

        for (ProductType productType : basket.keySet()) {
            price += GetPrice(productType) * basket.get(productType);
        }

        price -= CalculateDiscount(basket);

        // Should we introduce a Money object?
        return Math.round(price * 100.0) / 100.0;
    }

    // These constants will be an issue if prices change.  Move them to a separate class to start?
    private Double GetPrice(ProductType productType) {
        switch (productType)
        {
            case Apple:
                return 0.1;
            case Milk:
                return 1.3;
            case Bread:
                return 0.8;
            case Soup:
                return 0.65;
            default:
                throw new InvalidParameterException();
        }
    }

    private Double CalculateDiscount(HashMap<ProductType, Integer> basket){
        int numberOfDiscounts = Math.min(basket.getOrDefault(ProductType.Soup, 0) / 2, basket.getOrDefault(ProductType.Bread, 0));
        return  (GetPrice(ProductType.Bread) / 2.0) * numberOfDiscounts;
    }
}
