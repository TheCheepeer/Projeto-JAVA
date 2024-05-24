package com.banco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class FileUtius {
    private static final Logger log = Logger.getLogger(FileUtius.class.getName());

    public static String loadTextFile(final String filename) throws IOException {
        long time = System.currentTimeMillis();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }

        reader.close();
        time = System.currentTimeMillis() - time;

        log.info("O arquivo " + filename + " foi lido em " + time + " ms");

        return sb.toString();
    }
}
