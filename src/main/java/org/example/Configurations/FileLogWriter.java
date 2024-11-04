package org.example.Configurations;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogWriter {
    public void writeRecord(String recordData) {
        try (PrintWriter output = new PrintWriter(new FileWriter("ServerLog.txt", true))) {
            if (recordData != null && !recordData.isEmpty()) {
                output.println(recordData);
            } else {
                System.out.println("Empty data cannot be written.");
            }
        } catch (IOException ioException) {
            System.err.println("Error writing to file.");
        }
    }
}
