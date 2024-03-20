/**
 * File name: DBConnectionTest.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: Assignment2
 * Date: 2024-02-27
 * Professor: Gustavo Adami
 * Purpose: JUnit Test on DBConnection
 */
package org.cst8288Lab2.dao;

import java.sql.Connection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Glily
 */
public class DBConnectionTest {
    /**
     * Test of getInstance method, of class DataSource.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DataSource expResult = DataSource.getInstance();
        DataSource result = DataSource.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConnection method, of class DataSource.
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        Connection expResult = DataSource.getInstance().getConnection();
        Connection result = DataSource.getInstance().getConnection();
        assertEquals(expResult, result);
    }

    /**
     * Test of closeConnection method, of class DataSource.
     */
    @Test
    public void testCloseConnection() throws Exception {
        System.out.println("closeConnection");
        Connection expResult = DataSource.getInstance().getConnection();
        DataSource.getInstance().closeConnection();
        assertNotEquals(expResult, DataSource.getInstance().getConnection());
    }
    
}
