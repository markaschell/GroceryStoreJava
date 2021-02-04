import Products.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ParserTests {

    Parser _parser;

    @BeforeEach
    void setup()
    {
        _parser = new Parser();
    }

    @Test
    void ParseBasket_NoArgs_ReturnsZero()
    {
        Basket basket = _parser.ParseBasket(new String[0]);

        Assertions.assertTrue(basket.GetProducts().isEmpty());
    }

    @Test
    void ParseBasket_Apple()
    {
        Basket basket = _parser.ParseBasket(new String[] { "-a", "1" });

        Assertions.assertTrue(basket.GetProducts().containsKey(ProductType.Apple));
    }

    @Test
    void ParseBasket_Count()
    {
        Integer count = 2;
        Basket basket = _parser.ParseBasket(new String[] { "-a", count.toString() });

        Assertions.assertEquals(count, basket.GetProducts().get(ProductType.Apple));
    }

    @Test
    void ParseBasket_Bread()
    {
        Basket basket = _parser.ParseBasket(new String[] { "-b", "1" });

        Assertions.assertTrue(basket.GetProducts().containsKey(ProductType.Bread));
    }

    @Test
    void ParseBasket_Milk()
    {
        Basket basket = _parser.ParseBasket(new String[] { "-m", "1" });

        Assertions.assertTrue(basket.GetProducts().containsKey(ProductType.Milk));
    }

    @Test
    void ParseBasket_Soup()
    {
        Basket basket = _parser.ParseBasket(new String[] { "-s", "1" });

        Assertions.assertTrue(basket.GetProducts().containsKey(ProductType.Soup));
    }

    @Test
    void ParseBasket_MultipleProducts()
    {
        Basket basket = _parser.ParseBasket(new String[] { "-s", "1", "-a", "2" });

        Assertions.assertEquals(1, basket.GetProducts().get(ProductType.Soup));
        Assertions.assertEquals(2, basket.GetProducts().get(ProductType.Apple));
    }

    @Test
    void ParseBasket_DateArgIgnored()
    {
        Basket basket = _parser.ParseBasket(new String[] { "-d", "2000-01-08" });

        Assertions.assertTrue(basket.GetProducts().isEmpty());
    }

    // TODO - move this to Basket?
    @Test
    void ParseBasket_SameProductPassedTwice()
    {
        Basket basket = _parser.ParseBasket(new String[] { "-a", "1", "-a", "2" });

        Assertions.assertEquals(3, basket.GetProducts().get(ProductType.Apple));
    }

    @Test
    void ParseBasket_OddNumberOfArgs_ThrowsError()
    {
        IllegalArgumentException exception =
                Assertions.assertThrows(IllegalArgumentException.class,
                                        () -> _parser.ParseBasket(new String[1]));

        Assertions.assertEquals("Must be an even number of arguments", exception.getMessage());
    }

    @Test
    void ParseBasket_KnownProductType_ThrowsError()
    {
        String unknownArg = "gf";
        IllegalArgumentException exception =
                Assertions.assertThrows(IllegalArgumentException.class,
                () -> _parser.ParseBasket(new String[] { "-a", "1", unknownArg, "1" }));

        Assertions.assertEquals("Unknown option (gf)", exception.getMessage());
    }

    @Test
    void ParseBasket_SecondArgNotANumber_ThrowsError()
    {
        String nonNumberArg = "k";

        IllegalArgumentException exception =
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> _parser.ParseBasket(new String[] { "-a", nonNumberArg }));

        Assertions.assertEquals("Count not an integer (k)", exception.getMessage());
    }

    @Test
    void ParseBasket_SecondArgNotAnInteger_ThrowsError()
    {
        String nonIntegerArg = "5.3";

        IllegalArgumentException exception =
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> _parser.ParseBasket(new String[] { "-a", nonIntegerArg }));

        Assertions.assertEquals("Count not an integer (5.3)", exception.getMessage());
    }

    @Test
    void ParseDate_NoDateArg_ReturnsNull()
    {
        LocalDate date = _parser.ParseDate(new String[0]);

        Assertions.assertNull(date);
    }

    @Test
    void ParseDate_HasDateArgAndDate()
    {
        LocalDate date = _parser.ParseDate(new String[] { "-a", "1", "-d", "2000-01-31" });

        Assertions.assertEquals(  LocalDate.of(2000, 1, 31), date);
    }

    @Test
    void ParseDate_TwoDateArgs_UsesTheLastOne()
    {
        LocalDate expectedDate = LocalDate.of(2000, 8, 8);

        LocalDate date = _parser.ParseDate(new String[] { "-d", "2000-01-31", "-d", expectedDate.toString() });

        Assertions.assertEquals(expectedDate, date);
    }

    @Test
    void ParseDate_OddNumberOfArgs_ThrowsError()
    {
        IllegalArgumentException exception =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> _parser.ParseBasket(new String[1]));

        Assertions.assertEquals("Must be an even number of arguments", exception.getMessage());
    }

    @Test
    void ParseDate_HasDateArgDateCannotBeParsed_ThrowsError()
    {
        String nonDateArg = "k";

        IllegalArgumentException exception =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> _parser.ParseDate(new String[] { "-a", "1", "-d", nonDateArg }));

        Assertions.assertEquals("Could not parse date (k)", exception.getMessage());
    }
}
