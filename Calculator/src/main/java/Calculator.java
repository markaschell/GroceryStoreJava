import java.security.InvalidParameterException;
import java.util.HashMap;

public class Calculator {
    public Double CalculatePrice(HashMap<ProductType, Integer> list) {
        ProductType productType = list.keySet().stream().findFirst().get();

        return GetPrice(productType) * list.get(productType);
    }

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
