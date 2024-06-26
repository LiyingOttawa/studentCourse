/**
 * File name: CourseDAO.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: lab2
 * Date: 2024-03-03
 * Professor: Gustavo Adami
 * Purpose: Data Access Object for course table
 */
package org.cst8288Lab2.dao;
import java.sql.SQLException;
import org.cst8288Lab2.dto.CourseDTO;

/**
 * This interface is used for creating, retrieving, updating, and 
 deleting a specific CourseDTO object generated by the Client class; 
 * @author Liying Guo
 * @version 1.0
 * @since 2024-March-03
 */
public interface CourseDAO{
        /***
         * insert course to table
         * @param course to insert
         * @throws SQLException 
         */
	void createCourse(CourseDTO course) throws SQLException;
        
        /***
         * query course by id
         * @param courseId courseId used to query
         * @return
         * @throws SQLException 
         */
        CourseDTO retrieveCourse(String courseId) throws SQLException;
        /***
         * update course in table
         * @param course to update
         * @throws SQLException 
         */
        void updateCourse(CourseDTO course) throws SQLException;
        
        void saveCourse(CourseDTO course) throws SQLException;
        void deleteAllRecords() throws SQLException;
}
