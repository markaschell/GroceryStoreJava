import java.security.InvalidParameterException;
import java.util.HashMap;

public class Calculator {
    public Double CalculatePrice(HashMap<String, Integer> list) {

        switch (list.keySet().stream().findFirst().get())
        {
            case "apple":
                return 0.1;
            case "milk":
                return 1.3;
            case "bread":
                return 0.8;
            case "soup":
                return 0.65;
            default:
                throw new InvalidParameterException();
        }
    }
}
