import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("tattarrattat"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(" "));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("aabb"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("Aba"));
        assertTrue(palindrome.isPalindrome("AbA"));
        assertTrue(palindrome.isPalindrome("%%"));
    }

    @Test
    public void testIsPalindromeOBO() {
        OffByOne ofb = new OffByOne();
        assertFalse(palindrome.isPalindrome("tattarrattat", ofb));
        assertTrue(palindrome.isPalindrome("", ofb));
        assertTrue(palindrome.isPalindrome("a", ofb));
        assertTrue(palindrome.isPalindrome(" ", ofb));
        assertFalse(palindrome.isPalindrome("cat", ofb));
        assertTrue(palindrome.isPalindrome("aabb", ofb));
        assertTrue(palindrome.isPalindrome("flake", ofb));
        assertFalse(palindrome.isPalindrome("Ab", ofb));
        assertTrue(palindrome.isPalindrome("%&", ofb));
        assertTrue(palindrome.isPalindrome("&%", ofb));
    }

    @Test
    public void testIsPalindromeOBN() {
        OffByN obn = new OffByN(1);
        assertFalse(palindrome.isPalindrome("tattarrattat", obn));
        assertTrue(palindrome.isPalindrome("", obn));
        assertTrue(palindrome.isPalindrome("a", obn));
        assertTrue(palindrome.isPalindrome(" ", obn));
        assertFalse(palindrome.isPalindrome("cat", obn));
        assertTrue(palindrome.isPalindrome("aabb", obn));
        assertTrue(palindrome.isPalindrome("flake", obn));
        assertFalse(palindrome.isPalindrome("Ab", obn));

    }
}
