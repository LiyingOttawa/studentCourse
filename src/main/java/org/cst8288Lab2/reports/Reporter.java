/**
 * File name: Reporter.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: lab2
 * Date: 2024-03-03
 * Professor: Gustavo Adami
 * Purpose: reporter to report success or error to specific markdown file
 */
package org.cst8288Lab2.reports;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Glily
 */
public class Reporter {
    public static final String FILEPATH_SUCCESS="import-report.md";
    public static final String FILEPATH_ERROR="error-report.md";

    private static Reporter instance;

    public static void reset() {
        try {
            Files.deleteIfExists(Paths.get(FILEPATH_SUCCESS));
            Files.deleteIfExists(Paths.get(FILEPATH_ERROR));
        } catch (IOException ex) {
            Logger.getLogger(Reporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private Reporter()
    {
        
    }
    
    public static Reporter getInstance()
    {
        if(instance == null)
        {
            instance = new Reporter();
            
            reset();
        }
        return instance;
    }

    /***
     *     Error Report: Give details about the items that were not able to be imported into the DB.
     *     *List each row number and row content that was unable to be added to the DB.
     *     *Give details about what items failed to validate.
     * rowNumber
     * @param rowNumber
     * @param content
     * @param errors 
     */
    public void reportError(int rowNumber, String content, String[] errors)
    {
        try {
            Path path = Path.of(FILEPATH_ERROR);
            String message = String.format("Unable to save row %s: %s%n", rowNumber,content);
            Files.writeString(path, message, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            if(errors!=null && errors.length>0)
            {
                for(String error:errors)
                {
                    Files.writeString(path, error, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Reporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * success report
     * @param successCountStudent the count adding records to student table
     * @param successCountCourse the count adding records to course table
     * @param successCountStudentCourse the count adding records to studentcourse table
     */
    public void reportSuccess(int successCountStudent, int successCountCourse, int successCountStudentCourse) {
        try {
            Path path = Path.of(FILEPATH_SUCCESS);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
            
            String message = String.format("%s: %d records is added on Student, "
                    + "%d records is added on Course, "
                    + "%d records is added on StudentCourse%n",
                    currentDateTime.format(formatter),successCountStudent,successCountCourse,successCountStudentCourse);
            Files.writeString(path, message, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(Reporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
