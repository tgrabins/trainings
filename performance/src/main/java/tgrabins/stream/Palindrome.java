package tgrabins.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Palindrome {
    public static void main(String[] args) {
        Palindrome f = new Palindrome();
        var result = f.palindrome("Może jeż łka jak łże jeżom");
        System.out.println(result);
    }

    public boolean palindrome(String s) {
        String s1 = s.replaceAll("\\s+", "").toLowerCase();
        String s2 = new StringBuilder(s1).reverse().toString();
        System.out.println(s1);
        System.out.println(s2);

        return s1.equals(s2);
    }
}
