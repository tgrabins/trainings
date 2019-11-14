package tgrabins.performance.locks;

import java.util.Random;

public class ArrayExample {

    private final boolean[][] table;

    public static void main(String[] args) {
        ArrayExample a = new ArrayExample(15000);
        a.test1();
        a.test2();
    }

    private ArrayExample(int i) {
        this.table = new boolean[i][i];
        Random r = new Random();
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
                table[j][k]=r.nextBoolean();
            }
        }
    }

    private void test1() {
        long start = System.currentTimeMillis();
        int result = 0;
        for (int j = 0; j < this.table.length; j++) {
            for (int k = 0; k < this.table.length; k++) {
                if(table[j][k]){
                    result++;
                } else {
                    result--;
                }
            }
        }
        long duration = System.currentTimeMillis()- start;
        System.out.println("test1, result: " + result + " in " + duration + "ms");
    }

    private void test2() {
        long start = System.currentTimeMillis();
        int result = 0;
        for (int j = 0; j < this.table.length; j++) {
            for (int k = 0; k < this.table.length; k++) {
                if(table[k][j]){
                    result++;
                } else {
                    result--;
                }
            }
        }
        long duration = System.currentTimeMillis()- start;
        System.out.println("test2, result: " + result + " in " + duration + "ms");
    }

}
