/**
 * File name: StudentDTO.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: Lab2
 * Date: 2024-03-03
 * Professor: Gustavo Adami
 * Purpose: database entity to keep student record
 */
package org.cst8288Lab2.dto;

/**
 *
 * @author Glily
 */
public class StudentDTO {

    public StudentDTO(int studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName=firstName;
        this.lastName=lastName;
    }
    private int studentId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    private String firstName;
    private String lastName;
}
