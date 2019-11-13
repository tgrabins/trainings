package tgrabins.performance.hash;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;


class PointTest {

    //private static final int SAMPLE_SIZE = 10000000;
    private static final int SAMPLE_SIZE = 10000;

    @Test
    public void test() throws Exception {
        HashSet<Point> set = new HashSet<Point>(SAMPLE_SIZE);
        for (int i = 0; i < SAMPLE_SIZE; i++) {
            Point a = new Point(i, i);
            set.add(a);
            assertTrue(set.contains(a));
        }
        printHashSet(set);
    }



    @Test
    public void test2() throws Exception {
        HashSet<Point> set = new HashSet<Point>(SAMPLE_SIZE);
        for (int i = 0; i < SAMPLE_SIZE; i++) {
            Point a = new Point(i, i*2);
            set.add(a);
            assertTrue(set.contains(a));
        }
        printHashSet(set);
    }

    @Test
    public void test3() throws Exception {
        HashSet<Point> set = new HashSet<Point>(SAMPLE_SIZE);
        for (int i = 0; i < SAMPLE_SIZE; i++) {
            Point a = new Point(i, i+2);
            set.add(a);
            assertTrue(set.contains(a));
        }
        printHashSet(set);
    }



    private void printHashSet(HashSet<Point> set) throws Exception {
        Field field = set.getClass().getDeclaredField("map");
        field.setAccessible(true);

        // get the internal map
        @SuppressWarnings("unchecked")

        HashMap<Point, Object> interalMap = (HashMap<Point, Object>) (field.get(set));

        //Field tableField = set.getClass().getDeclaredField("table");
        //tableField.setAccessible(true);
        //Object[] buckets = (Object[]) tableField.get(interalMap);

        //System.out.println(buckets.length);
        System.out.println(interalMap.getClass().getSuperclass());
        System.out.println(interalMap.getClass().getSuperclass().getFields().length);
        for (Field field1 : interalMap.getClass().getSuperclass().getFields()) {
            System.out.println(field1.getName() + " " + field1.getType());
        }




    }

}