package io.aif.gui.helpers;

import java.io.*;

public class FileHelper {

    public static String readAllTextFromFile(final File path) throws IOException {
        try(    final InputStream is = new FileInputStream(path);
                final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            final StringBuilder buff = new StringBuilder();

            String line;

            while((line = reader.readLine()) != null) {
                buff.append(line + System.lineSeparator());
            }

            return buff.toString();
        }
    }

}
