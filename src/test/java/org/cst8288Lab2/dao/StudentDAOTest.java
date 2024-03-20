/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.cst8288Lab2.dao;

import java.sql.SQLException;
import static org.cst8288Lab2.dao.CourseDAOTest.courseDAO;
import static org.cst8288Lab2.dao.CourseDAOTest.studentCourseDAO;
import static org.cst8288Lab2.dao.CourseDAOTest.studentDAO;
import org.cst8288Lab2.dto.StudentDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Glily
 */
public class StudentDAOTest {
    static StudentDTO testStudent = new StudentDTO(111111111,"test first name","test last name");
    static StudentCourseDAO studentCourseDAO =  new StudentCourseDAOImpl(); 
    static StudentDAO studentDAO=new StudentDAOImpl();
    static CourseDAO courseDAO=new CourseDAOImpl();
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
        studentCourseDAO.deleteAllRecords();
        studentDAO.deleteAllRecords();
        courseDAO.deleteAllRecords();
        studentDAO.createStudent(testStudent);
    }
    
    @AfterAll
    public static void tearDownClass() throws SQLException {
        studentCourseDAO.deleteAllRecords();
        studentDAO.deleteAllRecords();
        courseDAO.deleteAllRecords();
    }

    /**
     * Test of createStudent method, of class StudentDAOImpl.
     */
    @Test
    public void testCreateStudent() throws Exception {
        System.out.println("createStudent");
        
        StudentDTO student = new StudentDTO(222222222,"test first name xx","test last name xx");
        studentDAO.createStudent(student);
        StudentDTO retrivedStudent =studentDAO.retrieveStudent(student.getStudentId());
        assertEquals(student.getFirstName(), retrivedStudent.getFirstName());
    }

    /**
     * Test of retrieveStudent method, of class StudentDAOImpl.
     */
    @Test
    public void testRetrieveStudent() throws Exception {
        System.out.println("retrieveStudent");
        StudentDTO retrivedStudent =studentDAO.retrieveStudent(testStudent.getStudentId());
        assertEquals(testStudent.getFirstName(), retrivedStudent.getFirstName());
    }

    /**
     * Test of updateStudent method, of class StudentDAOImpl.
     */
    @Test
    public void testUpdateStudent() throws Exception {
        System.out.println("updateStudent");
        testStudent.setFirstName("Test student yy");
        studentDAO.updateStudent(testStudent);
        StudentDTO retrivedStudent =studentDAO.retrieveStudent(testStudent.getStudentId());
        assertEquals(testStudent.getFirstName(), retrivedStudent.getFirstName());
    }
}
