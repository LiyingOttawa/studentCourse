/**
 * File name: client.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: lab2
 * Date: 2024-03-03
 * Professor: Gustavo Adami
 * Purpose: program class to run main funciton
 */
package org.cst8288Lab2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cst8288Lab2.dao.CourseDAO;
import org.cst8288Lab2.dao.CourseDAOImpl;
import org.cst8288Lab2.dao.StudentCourseDAO;
import org.cst8288Lab2.dao.StudentCourseDAOImpl;
import org.cst8288Lab2.dao.StudentDAO;
import org.cst8288Lab2.dao.StudentDAOImpl;
import org.cst8288Lab2.dto.CourseDTO;
import org.cst8288Lab2.dto.StudentDTO;
import org.cst8288Lab2.dto.StudentCourseDTO;
import org.cst8288Lab2.enums.TermType;
import org.cst8288Lab2.reports.Reporter;
import org.cst8288Lab2.validation.CSVDataValidator;


public class App {
    /***
     * This is the entry point for the application
     * The application should connect with a database named 
     * lab2 and support fundamental CRUD (Create, Read, Update, Delete) operations on student, course and studentCourse
     * table within the database.
     * @param args Command line arguments are not used by this program.
     */
    public static void main(String[] args) {
        StudentDAO studentDao = new StudentDAOImpl();
        CourseDAO courseDao = new CourseDAOImpl();
        StudentCourseDAO  studentCourseDao = new StudentCourseDAOImpl();
        try {
            studentDao.deleteAllRecords();
            courseDao.deleteAllRecords();
            studentCourseDao.deleteAllRecords();

        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Preserve this input path
        try (InputStream in = new FileInputStream("./data/bulk-import.csv")){
            try(BufferedReader br = new BufferedReader(new InputStreamReader(in))){
                String out;
                int i=0;
                int successCountStudent = 0,successCountCourse = 0,successCountStudentCourse=0;
                
                while ((out = br.readLine()) != null){
                    if(i==0){
                        i++;//skip first line
                    }
                    else
                    {
                        String[]data=out.split(",");
                        CSVDataValidator validator = new CSVDataValidator();
                        validator.validate(data);
                        if(validator.getErrorMessages().length ==0)
                        {
                            StudentDTO student = new StudentDTO(Integer.parseInt(data[0]),data[1],data[2]);
                            CourseDTO course = new CourseDTO(data[3],data[4]);
                            StudentCourseDTO sc;
                            sc = new StudentCourseDTO(student.getStudentId(),course.getCourseId(),
                                    TermType.fromString(data[5]).getIntValue(),Integer.parseInt(data[6]));
                            try {
                                studentDao.saveStudent(student);
                                successCountStudent++;
                                courseDao.saveCourse(course);
                                successCountCourse++;
                                studentCourseDao.saveStudentCourse(sc);
                                successCountStudentCourse++;
                            } catch (SQLException ex) {
                                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else
                        {
                            Reporter.getInstance().reportError(i, out, validator.getErrorMessages());
                        }
                        i++;
                    }
                }
                
                Reporter.getInstance().reportSuccess(successCountStudent,successCountCourse,successCountStudentCourse);
            }
        } catch (IOException e){
            e.printStackTrace();
        }       
    }
}
