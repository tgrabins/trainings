package tgrabins.performance.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileExample implements Runnable {

    private String path;

    public FileExample(String path) {
        super();
        this.path = path;
    }

    public void run() {
        LinkedList<File> directories = new LinkedList<File>();
        File parent = new File(path);
        directories.add(parent);

        while (!directories.isEmpty()) {
            File poll = directories.poll();
            System.out.println("folder: " + poll.getAbsolutePath());
            File[] listFiles = poll.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        directories.add(file);
                    } else {
                        if (file.canRead()) {

                            FileInputStream fis;
                            try {
                                fis = new FileInputStream(file);
                                Thread.sleep(100);
                                fis.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        FileExample fc = new FileExample("C:\\");
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(fc);
    }

}