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
        if (s == null ){
            throw new IllegalArgumentException("null argument");
        }
        String s1 = s
                .chars()
                .filter(Character::isLetter)
                .map(Character::toLowerCase)
                .mapToObj(c->String.valueOf((char)c))
                .collect(Collectors.joining());

        if (s1.isEmpty()){
            throw new IllegalArgumentException("Empty String after conversion");
        }

        String s2 = new StringBuilder(s1).reverse().toString();
        System.out.println(s1);
        System.out.println(s2);


        return s1.equals(s2);
    }
}
