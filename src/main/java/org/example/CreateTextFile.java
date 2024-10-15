package org.example;

import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class CreateTextFile {
    private Formatter output; // Object used to output text to file

    public void openFile() { // Enable user to open file
        try {
            output = new Formatter("Stats.txt");
        } catch (SecurityException securityException) {
            System.err.println("You do not have write access to this file.");
            System.exit(1);
        } catch (FileNotFoundException filesNotFoundException) {
            System.err.println("Error creating file.");
            System.exit(1);
        }
    }

    public void addRecord(String recordData) { // Add a record to the file
        try {
            if (recordData != null && !recordData.isEmpty()) { // Write new record
                output.format("%s\n", recordData);
            } else {
                System.out.println("Empty data cannot be written.");
            }
        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error writing to file.");
        }
    }

    public void closeFile() { // Close the file
        if (output != null) {
            output.close();
        }
    }
}

