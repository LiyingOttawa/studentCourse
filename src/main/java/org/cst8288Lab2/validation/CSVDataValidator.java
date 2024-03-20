
/**
 * File name: CourseDAO.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: lab2
 * Date: 2024-03-03
 * Professor: Gustavo Adami
 * Purpose: validate the dada read from csv file before conduct persistance
 *    studentId - length 9 digits
 *    courseId - length 7 characters - 3 letters followed by 4 digits
 *    term - one of three options - "WINTER", "SUMMER", "FALL" - stored in the db as 1, 2, 3 respectively
 *    year - length 4 digits - minimum founding of Algonquin College to now 
 */
package org.cst8288Lab2.validation;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.cst8288Lab2.enums.TermType;

/**
 *
 * @author Glily
 */
public class CSVDataValidator {
    private final List<String> errorMessages=new ArrayList<>();

    public String[] getErrorMessages() {
        String [] messages= new String[errorMessages.size()];
        for(int i = 0; i<errorMessages.size();i++)
        {
            messages[i]=errorMessages.get(i);
        }
        return messages;
    }
    public void validate(String[]data)
    {
        if(data.length == 7)
        {
            String studentId = data[0].trim();
            data[1] = data[1].trim();//first Name
            data[2] = data[2].trim();//last Name
            String courseId = data[3].trim();
            data[4] = data[4].trim();//course Name
            String term = data[5].trim();
            String year = data[6].trim();
            if(!is9Digits(studentId))
            {
                errorMessages.add(String.format("Invalide StudentId:%s%n", studentId));
            }
            else
            {
                data[0] = studentId;
            }
            
            if(!isValidCourseName(courseId))
            {
               errorMessages.add(String.format("Invalide Course:%s%n", courseId));
            }
            else
            {
                data[3] = courseId;
            }
            
            
            if(!isValidTerm(term))
            {
                errorMessages.add(String.format("Invalide Term:%s%n", term));
            }
            else
            {
                data[5] = term;
            }
            
            if(!isValidYear(year))
            {
                errorMessages.add(String.format("Invalide Year:%s%n", year));
            }
            else
            {
                data[6] = year;
            }
        }
        else
        {
            errorMessages.add(String.format("Invalide data. should be 7 column but %s%n",data.length));
        }
        
    }
        
    /***
     * Use regular expression to check if the string input is exact 9 digits 
     * @param input to be checked
     * @return 
     */
    private static boolean is9Digits(String input)
    {
        return Pattern.matches("\\d{9}",input);
    }
    /***
     * Use regular expression to check if the string input is valid course name
     * 3 letter followed by 4 digits
     * @param input to be checked
     * @return 
     */
    private static boolean isValidCourseName(String input)
    {
        return Pattern.matches("[A-Za-z]{3}\\d{4}",input);
    }
    
    private static boolean isValidTerm(String input)
    {
        for (TermType enumConstant : TermType.values()) {
            if (enumConstant.name().equals(input)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isValidYear(String input)
    {
        if(Pattern.matches("\\d{4}",input))
        {
            int year = Integer.parseInt(input);
            return year>=1967 && year<=Year.now().getValue();
        }
        return false;
    }
}
