import java.security.InvalidParameterException;
import java.util.HashMap;

public class Calculator {
    public Double CalculatePrice(HashMap<ProductType, Integer> list) {

        double price = 0.0;

        for (ProductType productType : list.keySet()) {
            price += GetPrice(productType) * list.get(productType);
        }

        int numberOfDiscounts = Math.min(list.get(ProductType.Soup) / 2, list.get(ProductType.Bread));
        price -= 0.4 * numberOfDiscounts;

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
}
