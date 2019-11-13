package tgrabins.performance.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SmartPhilosopher implements Runnable {

        // The forks on either side of this Philosopher
        private ReentrantLock leftFork;
        private ReentrantLock rightFork;

        public SmartPhilosopher(ReentrantLock leftFork, ReentrantLock rightFork) {
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(
                    Thread.currentThread().getName() + " " + action);
            Thread.sleep(((int) (Math.random() * 100)));
        }

        public void run() {
            try {
                while (true) {

                    // thinking
                    doAction(System.nanoTime() + ": Thinking");
                    if(leftFork.tryLock(100, TimeUnit.MILLISECONDS)){
                        doAction(
                                System.nanoTime()
                                        + ": Picked up left fork");
                        if(rightFork.tryLock(100, TimeUnit.MILLISECONDS)){
                            // eating
                            doAction(
                                    System.nanoTime()
                                            + ": Picked up right fork - eating");

                            doAction(
                                    System.nanoTime()
                                            + ": Put down right fork");
                            rightFork.unlock();
                        } else {
                            doAction(
                                    System.nanoTime()
                                            + ": Cannot aquire right fork");
                        }
                        leftFork.unlock();
                        // Back to thinking
                        doAction(
                                System.nanoTime()
                                        + ": Put down left fork. Back to thinking");
                    } else {
                        doAction(
                                System.nanoTime()
                                        + ": Cannot aquire left fork");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }



    }


