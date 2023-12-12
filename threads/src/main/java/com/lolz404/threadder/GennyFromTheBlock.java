package main.java.com.lolz404.threadder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class GennyFromTheBlock {
    private String pathOfFiles;

    public GennyFromTheBlock(String pathOfFiles) {
        this.pathOfFiles = pathOfFiles;
    }

    public void make(int amount) {
        final var ONE_MILLION = 1000000;
        // FIXME: Create folder if not exists

        var listOfFiles = new ArrayList<String>(amount);

        System.out.println("The amount is: " + amount);
        System.out.println(pathOfFiles);

        for (int i = 0; i < amount; i++) {
            var fileName = pathOfFiles + "\\" + fileName() + ".csv";
            // System.out.println("Creating file: " + fileName);

            try {
                var entries = randomNumber(255);
                var fileData = String.valueOf(randomNumber(ONE_MILLION));
                for (int j = 0; j < entries; j++) {
                    fileData += "," + String.valueOf(randomNumber(ONE_MILLION));
                }

                var path = Files.write(Paths.get(fileName), fileData.getBytes());
                listOfFiles.add(path.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        var fileName = pathOfFiles + "\\_list.txt";
        try {
            Files.write(Paths.get(fileName), listOfFiles, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String fileName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    private int randomNumber(int upperBound) {
        var rand = new Random();
        var int_random = rand.nextInt(upperBound);
        return int_random;
    }

}
