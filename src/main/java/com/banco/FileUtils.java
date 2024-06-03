package com.banco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class FileUtils {
    private static final Logger log = Logger.getLogger(FileUtils.class.getName());

    public static String loadTextFile(final String filename) throws IOException {
        long time = System.currentTimeMillis();
        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            throw new IOException("Arquivo não encontrado: " + filename);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
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
