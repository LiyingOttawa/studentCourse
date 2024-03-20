/**
 * File name: TermType.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: lab2
 * Date: 2024-03-03
 * Professor: Gustavo Adami
 * Purpose: define an enum for term field of StudentCourse
 */
package org.cst8288Lab2.enums;

/**
 *
 * @author Glily
 */
public enum TermType {
    WINTER(1),
    SUMMER(2),
    FALL(3);
    private final int intValue;

    TermType(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }

    /***
     * term should be only three options, otherwise, IllegalArgumentException throws
     * @param value
     * @return 
     */
    public static TermType fromString(String value) {
        for (TermType enumConstant : TermType.values()) {
            if (enumConstant.name().equals(value)) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("No enum constant with name: " + value);
    }
}
