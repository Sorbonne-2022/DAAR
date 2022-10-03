package src.main.regex.kmp;

public class KMP {

    private String text;
    private String factor;

    private int[] carryOverTable;

    public KMP() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }


    public KMP(String factor) {
        this.factor = factor;
        this.carryOverTable = new int[this.factor.length() + 1];
    }

    public KMP(String text, String factor) {
        this.text = text;
        this.factor = factor;
        this.carryOverTable = new int[this.factor.length() + 1];
    }

    public String getFactor() {
        return factor;
    }

    public int[] getCarryOverTable() {
        return carryOverTable;
    }

    protected int findLengthOfLargestSuffixThatEqualsPrefix(String input) {
        int maxLen = 0;
        int inputLen = input.length();
        for (int i = 1; i <= inputLen - 1; i++) {
            if (input.substring(0, i).equals(input.substring(inputLen - i, inputLen))) {
                if (i > maxLen) {
                    maxLen = i;
                }
            }
        }
        return maxLen;
    }

    protected void buildCarryOverTableNaif() {
        carryOverTable[0] = -1;
        carryOverTable[carryOverTable.length - 1] = 0;
        for (int i = 1; i < carryOverTable.length - 1; i++) {
            carryOverTable[i] = findLengthOfLargestSuffixThatEqualsPrefix(factor.substring(0, i));
        }
    }

    protected void buildCarryOverTableOptimizationStep1() {
        buildCarryOverTableNaif();
        for (int i = 1; i < carryOverTable.length - 1; i++) {
            if ((factor.charAt(carryOverTable[i]) == (factor.charAt(i)))
                    && carryOverTable[carryOverTable[i]] == -1) {
                carryOverTable[i] = -1;
            }
        }
    }

    protected void buildCarryOverTableOptimizationStep2() {
        buildCarryOverTableNaif();
        buildCarryOverTableOptimizationStep1();
        for (int i = 1; i < carryOverTable.length - 1; i++) {
            if ((carryOverTable[i] != -1
                    && factor.charAt(carryOverTable[i]) == (factor.charAt(i)))
                    && carryOverTable[carryOverTable[i]] != -1) {
                carryOverTable[i] = carryOverTable[carryOverTable[i]];
            }
        }
    }

    public Boolean searchStringInText() {

        buildCarryOverTableOptimizationStep2();

        int textLen = text.length();

        int index = 0;

        for (int i = 0; i < textLen; i++) {
            if (text.charAt(i) == factor.charAt(index)) {
                index++;
                if (index >= factor.length()) {
                    return true;
                }
            } else {
                i -= (carryOverTable[index] + 1);
                index = 0;
            }
        }

        return false;
    }
}
