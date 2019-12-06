package tgrabins.performance.mem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Producer implements Runnable {

    private ArrayBlockingQueue<LocalDateTime> communicator;
    private boolean wait;

    public Producer(ArrayBlockingQueue<LocalDateTime> communicator, boolean wait) {
        this.communicator = communicator;
        this.wait = wait;
    }




    @Override
    public void run() {
        while (true){
                try {
                    communicator.offer(LocalDateTime.now());
                    //communicator.put(LocalDateTime.now());
                    if (wait) {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }


    public static void main(String[] args) {
        var communicator = new ArrayBlockingQueue<LocalDateTime>(500);

        var p = new Producer(communicator, true);
        var r = new Reader(communicator, false);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(p);
        executorService.submit(r);




    }
}
