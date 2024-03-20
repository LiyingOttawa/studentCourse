/**
 * File name: DBConnectionTest.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: Assignment2
 * Date: 2024-02-27
 * Professor: Gustavo Adami
 * Purpose: JUnit Test on CSVDataValidator
 */
package org.cst8288Lab2.validation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Glily
 */
public class CSVDataValidatorTest {
    /**
     * Test of getErrorMessages method, of class CSVDataValidator.
     */
    @Test
    public void testGetErrorMessages() {
        System.out.println("getErrorMessages");
        String[] testData = "3457232s8,Ifeoma Dorsey,Quinn Nieves,cmj5170,Nourse Test 2,FALL,2008".split(",");
        CSVDataValidator instance = new CSVDataValidator();
        instance.validate(testData);
        assertEquals(String.format("Invalide StudentId:%s%n", "3457232s8"), instance.getErrorMessages()[0]);
    }

    /**
     * Test of validate method, of class CSVDataValidator.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        String[] testData = "3457232s8,Ifeoma Dorsey,Quinn Nieves,cmj5170,Nourse Test 2,FALL,2008".split(",");
        CSVDataValidator instance = new CSVDataValidator();
        instance.validate(testData);
        assertEquals(1, instance.getErrorMessages().length);
    }
    
}
