/**
 * File name: StudentCourseDTO.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: Lab2
 * Date: 2024-03-03
 * Professor: Gustavo Adami
 * Purpose: database entity to keep StudentCourse record
 */
package org.cst8288Lab2.dto;

/**
 *
 * @author Glily
 */
public class StudentCourseDTO {

    public StudentCourseDTO(int studentId, String courseId, int term, int year) {
        this.studentId=studentId;
        this.courseId=courseId;
        this.term=term;
        this.year=year;
    }
    private int studentId;
    private String courseId;
    private int term;
    private int year;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
