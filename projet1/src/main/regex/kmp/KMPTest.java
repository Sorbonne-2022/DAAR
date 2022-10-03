package src.main.regex.kmp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KMPTest {

    KMP kmp;

    @BeforeEach
    public void init() {
        kmp = new KMP();
    }

    @Test
    void testFindLengthOfLargestSuffixThatEqualsPrefix_returnRightValue() {
        // Given
        String input = "mam";
        // When
        int res = kmp.findLengthOfLargestSuffixThatEqualsPrefix(input);
        //Then
        assertEquals(res, 1);
    }

    @Test
    void testFindLengthOfLargestSuffixThatEqualsPrefix_return3() {
        // Given
        String input = "mamam";
        // When
        int res = kmp.findLengthOfLargestSuffixThatEqualsPrefix(input);
        //Then
        assertEquals(res, 3);
    }

    @Test
    void testFindLengthOfLargestSuffixThatEqualsPrefix_return0() {
        // Given
        String input = "ma";
        // When
        int res = kmp.findLengthOfLargestSuffixThatEqualsPrefix(input);
        //Then
        assertEquals(res, 0);
    }

    @Test
    void findLengthOfLargestSuffixThatEqualsPrefix() {
        // Given
        kmp = new KMP("mamamia");
        // When
        kmp.buildCarryOverTableNaif();
        //Then
        assertArrayEquals(kmp.getCarryOverTable(), new int[]{-1, 0, 0, 1, 2, 3, 0, 0});
    }

    @Test
    void buildCarryOverTableOptimizationStep1() {
        // Given
        kmp = new KMP("mamamia");
        // When
        kmp.buildCarryOverTableOptimizationStep1();
        //Then
        assertArrayEquals(kmp.getCarryOverTable(), new int[]{-1, 0, -1, 1, -1, 3, 0, 0});
    }

    @Test
    void buildCarryOverTableOptimizationStep2() {
        // Given
        kmp = new KMP("mamamia");
        // When
        kmp.buildCarryOverTableOptimizationStep2();
        //Then
        assertArrayEquals(kmp.getCarryOverTable(), new int[]{-1, 0, -1, 0, -1, 3, 0, 0});
    }

    @Test
    void searchStringInText() {
        // Given
        kmp = new KMP("maman mame mia ! mm maaah !", "mami");
        //Then
        assertEquals(kmp.searchStringInText(), false);
    }

    @Test
    void searchStringInText_returnResults() {
        // Given
        kmp = new KMP("abxabcabcaby", "abcaby");
        //Then
        assertEquals(kmp.searchStringInText(), true);
    }
}