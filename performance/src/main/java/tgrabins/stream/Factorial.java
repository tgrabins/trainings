package tgrabins.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Factorial {

    public int factorial(int factor){
        return IntStream.rangeClosed(2, factor).reduce(1, (x, y) -> x * y);
    }
}
