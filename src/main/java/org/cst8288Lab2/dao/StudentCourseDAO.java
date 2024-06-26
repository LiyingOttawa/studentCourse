/**
 * File name: StudentCourseDAO.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: lab2
 * Date: 2024-02-27
 * Professor: Gustavo Adami
 * Purpose: Data Access Object for StudentCourse table
 */
package org.cst8288Lab2.dao;
import java.sql.SQLException;
import org.cst8288Lab2.dto.StudentCourseDTO;


/**
 * This interface is used for creating, retrieving, updating, and 
 deleting a specific StudentCourseDTO object generated by the Client class; 
 * @author Liying Guo
 * @version 1.0
 * @since 2024-March-03
 */
public interface StudentCourseDAO {
        /***
         * insert studentcourse to table
         * @param studentCourse to insert
         * @throws SQLException 
         */
	void createStudentCourse(StudentCourseDTO studentCourse) throws SQLException;
        
        /**
         * query studentCourse by studentId and courseId
         * @param studentId for query
         * @param courseId for query
         * @return
         * @throws SQLException 
         */
        StudentCourseDTO retrieveStudentCourse(int studentId, String courseId) throws SQLException;
        /**
         * update table
         * @param studentCourse to update
         * @throws SQLException 
         */
        void updateStudentCourse(StudentCourseDTO studentCourse) throws SQLException;
        void saveStudentCourse(StudentCourseDTO studentCourse) throws SQLException;
        
        void deleteAllRecords() throws SQLException;
}
