package main.java.com.lolz404.threadder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RunAdder {
    private String listFilePath;
    private List<String> files = new ArrayList<String>(200);

    public RunAdder(String listFilePath) {
        this.listFilePath = listFilePath;
    }

    public void run() {
        System.out.println("Running: " + this.listFilePath);
        try {
            this.files = Files.readAllLines(Paths.get(this.listFilePath));

            files.stream().forEach(path -> {
                try {
                    var nums = Files.readAllLines(Paths.get(path));
                    System.out.println(path);
                    // TODO: Probably should check this better...
                    Adder.add(nums.get(0));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void threaded() {
        throw new UnsupportedOperationException("Unimplemented method 'threaded'");
    }

    public void vthreaded() {
        throw new UnsupportedOperationException("Unimplemented method 'vthreaded'");
    }

}
