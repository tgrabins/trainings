package tgrabins.performance.locks;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class SmartLockExample {
    public static void main(String[] args) throws Exception {

        ReentrantLock[] forks = IntStream.range(0,5).boxed().map(i->new ReentrantLock()).toArray(ReentrantLock[]::new);

        //SmartPhilosopher[] philosophers = new SmartPhilosopher[5];

        IntStream.range(0,5).boxed()
                .map(i->new SmartPhilosopher(i,forks[i],forks[(i + 1) % forks.length]))
                .map(p->new Thread(p, "Philosopher " + p.getName()))
                .forEach(Thread::start);

    }
}
