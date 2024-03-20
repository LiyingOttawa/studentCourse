/**
 * File name: CourseDAOTest.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: Assignment2
 * Date: 2024-02-27
 * Professor: Gustavo Adami
 * Purpose: JUnit Test on CourseDAO
 */
package org.cst8288Lab2.dao;

import java.sql.SQLException;
import org.cst8288Lab2.dto.CourseDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Glily
 */
public class CourseDAOTest {
    static CourseDTO testCourse = new CourseDTO("cmj5170","test course");
    static StudentCourseDAO studentCourseDAO =  new StudentCourseDAOImpl(); 
    static StudentDAO studentDAO=new StudentDAOImpl();
    static CourseDAO courseDAO=new CourseDAOImpl();
    public CourseDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
        studentCourseDAO.deleteAllRecords();
        studentDAO.deleteAllRecords();
        courseDAO.deleteAllRecords();
        courseDAO.createCourse(testCourse);
    }
    
    @AfterAll
    public static void tearDownClass() throws SQLException {
        studentCourseDAO.deleteAllRecords();
        studentDAO.deleteAllRecords();
        courseDAO.deleteAllRecords();
    }

    /**
     * Test of createCourse method, of class CourseDAOImpl.
     */
    @Test
    public void testCreateCourse() throws Exception {
        System.out.println("createCourse");
        CourseDTO newCourse = new CourseDTO("cmj5171","test course2");
        courseDAO.createCourse(newCourse);
        CourseDTO retrivedCourse =courseDAO.retrieveCourse(newCourse.getCourseId());
        assertEquals(newCourse.getCourseName(), retrivedCourse.getCourseName());
    }

    /**
     * Test of retrieveCourse method, of class CourseDAOImpl.
     */
    @Test
    public void testRetrieveCourse() throws Exception {
        System.out.println("retrieveCourse");
        CourseDTO retrivedCourse =courseDAO.retrieveCourse(testCourse.getCourseId());
        assertEquals(testCourse.getCourseName(), retrivedCourse.getCourseName());
    }

    /**
     * Test of updateCourse method, of class CourseDAOImpl.
     */
    @Test
    public void testUpdateCourse() throws Exception {
        System.out.println("updateCourse");
        testCourse.setCourseName("new course name");
        courseDAO.updateCourse(testCourse);
        CourseDTO retrivedCourse =courseDAO.retrieveCourse(testCourse.getCourseId());
        assertEquals(testCourse.getCourseName(), retrivedCourse.getCourseName());
    }
}
