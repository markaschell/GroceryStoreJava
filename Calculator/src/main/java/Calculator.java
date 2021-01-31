import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.HashMap;

public class Calculator {
    public Double CalculatePrice(HashMap<ProductType, Integer> basket) {
        return CalculatePrice(basket, LocalDate.now());
    }

    public Double CalculatePrice(HashMap<ProductType, Integer> basket, LocalDate date) {

        double price = 0.0;

        for (ProductType productType : basket.keySet()) {
            price += GetPrice(productType) * basket.get(productType);
        }

        price -= CalculateDiscount(basket, date);

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

    private Double CalculateDiscount(HashMap<ProductType, Integer> basket, LocalDate date){
        return CalculateSoupBreadDiscount(basket, date) + CalculateAppleDiscount(basket, date);
    }

    private Double CalculateSoupBreadDiscount(HashMap<ProductType, Integer> basket, LocalDate date) {
        LocalDate discountStart = LocalDate.now().minusDays(1);

        if (date.isBefore(discountStart) || date.isAfter(discountStart.plusDays(6))) {
            return 0.0;
        }

        int numberOfDiscounts = Math.min(basket.getOrDefault(ProductType.Soup, 0) / 2, basket.getOrDefault(ProductType.Bread, 0));
        return (GetPrice(ProductType.Bread) / 2.0) * numberOfDiscounts;
    }

    private Double CalculateAppleDiscount(HashMap<ProductType, Integer> basket, LocalDate date) {
        LocalDate discountStart = LocalDate.now().plusDays(3);

        if (date.isBefore(discountStart) || date.isAfter(LocalDate.now().plusMonths(2).withDayOfMonth(1).minusDays(1))) {
            return 0.0;
        }

        int numberOfDiscounts = basket.getOrDefault(ProductType.Apple, 0);
        return (GetPrice(ProductType.Apple) * 0.1) * numberOfDiscounts;
    }
}
