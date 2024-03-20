/**
 * File name: CourseDTO.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: Lab2
 * Date: 2024-03-03
 * Professor: Gustavo Adami
 * Purpose: database entity to keep Course record
 */
package org.cst8288Lab2.dto;

/**
 *
 * @author Glily
 */
public class CourseDTO {

    public CourseDTO(String courseId, String courseName) {
        this.courseId=courseId;
        this.courseName=courseName;
    }
    private String courseId;
    private String courseName;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
