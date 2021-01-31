package Products;

import java.security.InvalidParameterException;

public class ProductPricer {
    // These constants will be an issue if prices change.  Move them to a separate class to start?
    public Double GetPrice(ProductType productType) {
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
