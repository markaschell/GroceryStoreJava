import Products.ProductType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    static int NumberOfArgumentsInGroup = 2;

    public Basket ParseBasket(String[] args) throws IllegalArgumentException {
        ValidateEvenNumberOfArguments(args);

        Basket basket = new Basket();
        for (int i = 0; i < args.length; i = i + NumberOfArgumentsInGroup) {
            ProductType productType;

            switch (args[i]) {
                case "-a":
                    productType = ProductType.Apple;
                    break;
                case "-b":
                    productType = ProductType.Bread;
                    break;
                case "-m":
                    productType = ProductType.Milk;
                    break;
                case "-s":
                    productType = ProductType.Soup;
                    break;
                case "-d":
                    continue;
                default:
                    throw new IllegalArgumentException("Unknown option (" + args[i] + ")");
            }

            try {
                basket.AddProduct(productType, Integer.parseInt(args[i + 1]));
            }
            catch (NumberFormatException e){
                throw new IllegalArgumentException("Count not an integer (" + args[i + 1] + ")");
            }
        }

        return basket;
    }

    private void ValidateEvenNumberOfArguments(String[] args) throws IllegalArgumentException {
        if (args.length % NumberOfArgumentsInGroup != 0) {
            throw new IllegalArgumentException("Must be an even number of arguments");
        }
    }

    public LocalDate ParseDate(String[] args) throws IllegalArgumentException {
        LocalDate date = null;

        ValidateEvenNumberOfArguments(args);

        for (int i = 0; i < args.length; i = i + NumberOfArgumentsInGroup) {
            if (args[i].equals("-d"))
            {
                try {
                    date = LocalDate.parse(args[i + 1]);
                }
                catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Could not parse date (" + args[i + 1] + ")");
                }
            }
        }

        return date;
    }
}
