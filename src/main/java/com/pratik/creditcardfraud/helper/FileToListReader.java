package com.pratik.creditcardfraud.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class reads the transactions file and returns a list of String
 */
public class FileToListReader {

    public static List<String> read(String filename) {
        List<String> content = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                content.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
            content.clear();
        }

        return content;
    }
}
