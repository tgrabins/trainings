package tgrabins.performance.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileExample implements Runnable {

    private Path path;

    public FileExample(Path path) {
        super();
        this.path = path;
    }

    public void run() {
        try {
            Files.walkFileTree(path, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    System.out.println("pre visit dir:" + dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("visit file: " + file);
                    try(BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)){
                        String currentLine = null;
                        while((currentLine = reader.readLine()) != null){//while there is content on the current line
                            System.out.println(currentLine); // print the current line
                        }
                    }catch(IOException ex){
                        ex.printStackTrace(); //handle an exception here
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("visit file error: " + e.toString());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.out.println("visit file failed: " + file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    System.out.println("post visit directory: " + dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println("visit file error: " + e.toString());
        }

    }

    public static void main(String[] args) {
        FileExample fc = new FileExample(Paths.get("C:\\Program Files"));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(fc);
    }

}