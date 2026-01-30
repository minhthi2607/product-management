package utils;

import java.util.regex.Pattern;

public class ValidateUtils {

    private static final String NAME_REGEX = "^[A-Za-z0-9 ]{3,50}$";

    public static boolean isValidName(String name) {
        return Pattern.matches(NAME_REGEX, name);
    }

    public static boolean isValidPrice(double price){
        return price > 0;
    }

    public static boolean isValidQuantity(int quantity){
        return quantity >= 0;
    }
}
