package src.main.regex.commons;

import static src.main.regex.automata.OperatorEnum.*;

public class RegexUtils {
    public static String valueToString(int value) {
        if (value == CONCAT) return ".";
        if (value == ETOILE) return "*";
        if (value == ALTERN) return "|";
        if (value == DOT) return ".";
        return Character.toString((char) value);
    }

}
