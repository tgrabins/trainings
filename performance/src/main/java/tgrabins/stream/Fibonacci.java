package tgrabins.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        List<Integer> generate = f.generate(50);
        System.out.println(generate);
    }

    public List<Integer> generate(int series) {
        return Stream.iterate(new int[]{0, 1}, i -> new int[]{i[1], i[0] + i[1]})
                .limit(series)
                .map(i -> i[0])
                .collect(Collectors.toList());
    }


}
