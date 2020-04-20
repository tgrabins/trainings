package tgrabins.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeTest {

    private Palindrome palindrome;

    @BeforeEach
    void setUp(){
        palindrome = new Palindrome();
    }

    @Test
    void whenEmptyStringThrowIllegalExc() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            palindrome.palindrome("");
        });
    }

    @Test
    void whenNoLettersStringThrowIllegalExc() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            palindrome.palindrome("  ,");
        });
    }

    @Test
    void whenNullStringThrowIllegalExc() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            palindrome.palindrome(null);
        });
    }

    @Test
    void whenPalindromeThanReturnTrue() {
        Assertions.assertTrue( palindrome.palindrome("Ada baluje - Jula bada."));
        Assertions.assertTrue( palindrome.palindrome("Ada goła im śmiało gada!"));
        Assertions.assertTrue( palindrome.palindrome("Akta ma w teczce twa matka."));
        Assertions.assertTrue( palindrome.palindrome("Ile Roman ładny dyndał na moreli?"));
        Assertions.assertTrue( palindrome.palindrome("Jego tato idiota, to gej."));
        Assertions.assertTrue( palindrome.palindrome("Marzena pokazała Zakopane z ram."));
    }    @Test
    void whenNotPalindromeThanReturnFalse() {
        Assertions.assertFalse( palindrome.palindrome("Ada baluje - Juasdla bada."));
        Assertions.assertFalse( palindrome.palindrome("Ada balasuje - Juasdla bada."));
        Assertions.assertFalse( palindrome.palindrome("Ada baddluje - Juasdla bada."));
    }

}