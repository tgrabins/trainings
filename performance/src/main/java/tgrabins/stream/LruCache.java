package tgrabins.stream;

import java.util.LinkedList;
import java.util.Random;

public class LruCache {

    private final int size;
    private LinkedList<Integer> cache;

    public LruCache(int size) {
        this.size = size;
        cache = new LinkedList<Integer>();
    }

    public static void main(String[] args) {
        LruCache cache = new LruCache(10);

        new Random().ints(0,15).limit(50).map(i->cache.get(i)).toArray();
    }

    private int get(int i) {
        if (cache.contains(i)){
            cache.remove(Integer.valueOf(i));
            cache.addFirst(i);
        } else {
            cache.addFirst(i);
            if (cache.size()>size){
            cache.removeLast();
            }
        }
        System.out.println(i +" for "+cache);
        return i;
    }


}
