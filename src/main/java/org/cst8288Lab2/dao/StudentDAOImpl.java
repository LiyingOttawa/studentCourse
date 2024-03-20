/**
 * File name: StudentDAO.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: lab2
 * Date: 2024-02-27
 * Professor: Gustavo Adami
 * Purpose: Data Access Object for student table
 */
package org.cst8288Lab2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.cst8288Lab2.dto.StudentDTO;

/**
 * This class is used for creating, retrieving, updating, and 
 deleting a specific StudentDTO object generated by the Client class; 
 * @author Liying Guo
 * @version 1.0
 * @since 2024-March-03
 */
public class StudentDAOImpl implements StudentDAO{
        /**
         * query sql statement
         */
        private  final String SELECT_SQL_QUERY     = "SELECT studentId,firstName,lastName FROM Student WHERE studentId=?";
        /**
         * insert sql statement
         */
        private  final String INSERT_SQL_QUERY     = "INSERT INTO Student(studentId,firstName,lastName) VALUES(?,?,?)";
        /**
         * update sql statement
         */
        private  final String UPDATE_SQL_QUERY     = "UPDATE Student SET firstName=?,lastName=? WHERE studentId=?";
        /**
         * delete sql statement
         */
        private  final String DELETE_ALL_SQL_QUERY = "DELETE FROM Student";

        /***
         * insert student to table
         * @param student to insert
         * @throws SQLException 
         */
        @Override
	public  void createStudent(StudentDTO student) throws SQLException
        {
           Connection con = null;
           PreparedStatement ps = null;
           try
           {
               /**
                * get connection
                */
              con = DataSource.getInstance().getConnection();
              if ( con == null )
              {
                 System.out.println( "Error getting the connection. Please check if the DB server is running" );
                 return;
              }

              con.setAutoCommit( false );
              /**
               * prepare statement
               */
              ps = con.prepareStatement( INSERT_SQL_QUERY );
              /**
               * set parameters
               */
              ps.setInt(1, student.getStudentId() );
              ps.setString(2, student.getFirstName() );
              ps.setString(3, student.getLastName() );

              ps.execute();
              System.out.println( "insertStudent => " + ps.toString() );
              con.commit();

           }
           catch ( SQLException e )
           {
              try
              {
                 if ( con != null )
                 {
                    con.rollback();
                 }
              }
              catch ( SQLException e1 )
              {
                 throw e1;
              }
              throw e;
           }
           finally
           {
              try
              {
                 if(ps!=null)ps.close();
                 DataSource.getInstance().closeConnection();
              }
              catch ( SQLException e )
              {
                 throw e;
              }
           }
        }
        
        /***
         * retrieve student by studentId
         * @param studentId used to query
         * @return
         * @throws SQLException 
         */
        @Override
        public  StudentDTO retrieveStudent(int studentId) throws SQLException
        {
           Connection con = null;
           PreparedStatement ps = null;
           ResultSet rs = null;
           StudentDTO student = null;
           try
           {
              con = DataSource.getInstance().getConnection();
              if ( con == null )
              {
                 System.out.println( "Error getting the connection. Please check if the DB server is running" );
                 return student;
              }
              ps = con.prepareStatement( SELECT_SQL_QUERY );
              ps.setInt(1, studentId );
              rs = ps.executeQuery();
              System.out.println( "retriveStudent => " + ps.toString() );
              if ( rs.next() )
              {
                 student = new StudentDTO(rs.getInt("studentId" ),rs.getString( "firstName" ),rs.getString( "lastName" ));
              }

           }
           catch ( SQLException e )
           {
              throw e;

           }

           finally
           {
              try
              {
                  if(rs!=null)rs.close();
                  if(ps!=null)ps.close();
                  DataSource.getInstance().closeConnection();
              }
              catch ( SQLException e )
              {
                 throw e;
              }
           }
           return student;
        }
        
        /**
         * update student on table
         * @param student to update
         * @throws SQLException 
         */
        @Override
        public  void updateStudent(StudentDTO student) throws SQLException
        {
           Connection con = null;
           PreparedStatement ps = null;

           try
           {
              con = DataSource.getInstance().getConnection();
              if ( con == null )
              {
                 System.out.println( "Error getting the connection. Please check if the DB server is running" );
                 return;
              }
              con.setAutoCommit( false );
              ps = con.prepareStatement( UPDATE_SQL_QUERY );
              ps.setString(1, student.getFirstName() );
              ps.setString(2, student.getLastName() );
              ps.setInt(3, student.getStudentId() );
              ps.execute();
              System.out.println( "updateStudent => " + ps.toString() );
              con.commit();

           }
           catch ( SQLException e )
           {
              try
              {
                 if ( con != null )
                 {
                    con.rollback();
                    throw e;
                 }
              }
              catch ( SQLException e1 )
              {
                 throw e1;
              }
           }
           finally
           {
              try
              {
                 if(ps!=null)ps.close();
                  DataSource.getInstance().closeConnection();
              }
              catch ( SQLException e )
              {
                 throw e;
              }
           }

        }
        
        @Override
        public  void saveStudent(StudentDTO student) throws SQLException
        {
            StudentDTO studentDB = retrieveStudent(student.getStudentId());
            if(studentDB == null)
            {
                createStudent(student);
            }
            else
            {
                updateStudent(student);
            }
        }
        
        public  void deleteAllRecords() throws SQLException
        {
           Connection con = null;
           PreparedStatement ps = null;
           try
           {
              con = DataSource.getInstance().getConnection();
              if ( con == null )
              {
                 System.out.println( "Error getting the connection. Please check if the DB server is running" );
                 return;
              }
              ps = con.prepareStatement( DELETE_ALL_SQL_QUERY );
              ps.execute();
              System.out.println( "deleteAllRecords => " + ps.toString() );
           }
           catch ( SQLException e )
           {
              throw e;

           }

           finally
           {
              try
              {
                  if(ps != null)ps.close();
                  DataSource.getInstance().closeConnection();
              }
              catch ( SQLException e )
              {
                 throw e;
              }
           }
        }
}