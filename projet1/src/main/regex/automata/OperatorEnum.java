package src.main.regex.automata;

import java.util.ArrayList;
import java.util.Arrays;

public class OperatorEnum {
    public static final int CONCAT = 0xC04CA7;
    public static final int ETOILE = 0xE7011E;
    public static final int ALTERN = 0xA17E54;
    static final int PROTECTION = 0xBADDAD;

    static final int PARENTHESEOUVRANT = 0x16641664;
    static final int PARENTHESEFERMANT = 0x51515151;
    public static final int DOT = 0xD07;

    public static boolean isOperator(Integer x) {
        return new ArrayList<>(Arrays.asList(
                CONCAT,
                ETOILE,
                ALTERN,
                PROTECTION,
                PARENTHESEOUVRANT,
                PARENTHESEFERMANT,
                DOT
        )).contains(x);
    }

}
