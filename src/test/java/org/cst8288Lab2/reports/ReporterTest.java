/**
 * File name: DBConnectionTest.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: Assignment2
 * Date: 2024-02-27
 * Professor: Gustavo Adami
 * Purpose: JUnit Test on Reporter
 */
package org.cst8288Lab2.reports;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Glily
 */
public class ReporterTest {
    @AfterEach
    public void tearDown() {
        Reporter.reset();
    }
    /**
     * Test of getInstance method, of class Reporter.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Reporter expResult = Reporter.getInstance();
        Reporter result = Reporter.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of reportError method, of class Reporter.
     */
    @Test
    public void testReportError() {
        System.out.println("reportError");
        int rowNumber = 0;
        String content = "";
        String[] errors = null;
        Reporter instance = Reporter.getInstance();
        instance.reportError(rowNumber, content, errors);
        
        assertTrue(this.readContent(Reporter.FILEPATH_ERROR).length()>0);
    }

    private String readContent( String filePath) 
    {
                // Read the content of the file
        StringBuilder actualContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                actualContent.append(line).append("\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporterTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReporterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(actualContent.toString());
        return actualContent.toString();
    }
    /**
     * Test of reportSuccess method, of class Reporter.
     */
    @Test
    public void testReportSuccess() {
        System.out.println("reportSuccess");
        int successCountStudent = 0;
        int successCountCourse = 0;
        int successCountStudentCourse = 0;
        Reporter instance = Reporter.getInstance();
        instance.reportSuccess(successCountStudent, successCountCourse, successCountStudentCourse);
        assertTrue(this.readContent(Reporter.FILEPATH_SUCCESS).length()>0);
    }
    
}
