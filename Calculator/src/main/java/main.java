import java.time.LocalDate;

public class main {

    public static void main(String[] args)
    {
        Parser parser = new Parser();

        try {
            Basket basket = parser.ParseBasket(args);
            LocalDate date = parser.ParseDate(args);

            Double price = basket.CalculatePrice(date);

            System.out.println("Total Price: " + price);
        }
        catch (Exception e)
        {
            OutputUsage(e.getMessage());
        }
    }

    static void OutputUsage(String message) {
        System.out.println(message);
        System.out.println();
        System.out.println("options:");
        System.out.println("-a <integer>    Number of apples");
        System.out.println("-b <integer>    Number of loaves of bread");
        System.out.println("-m <integer>    Number of bottles of milk");
        System.out.println("-s <integer>    Number of tins of soup");
        System.out.println("-d <YYY-MM-DD>  Date to price the basket, default is today");
    }
}