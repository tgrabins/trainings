package tgrabins.performance.mem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Reader implements Runnable {

    private ArrayBlockingQueue<LocalDateTime> communicator;
    private final boolean wait;

    public Reader(ArrayBlockingQueue<LocalDateTime> communicator, boolean wait) {

        this.communicator = communicator;
        this.wait = wait;
    }

    @Override
    public void run() {
        while (true){
            var tmpArray = new ArrayList<LocalDateTime>(100);

            communicator.drainTo(tmpArray,100);
            tmpArray.forEach(d-> {
                String formatted = String.format("Communicate received %s", d);
                System.out.println(formatted);
            });
            if (wait) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
