/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import java.util.*;

/**
 *
 * @author usuario
 */
public class Kata5p1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        Class.forName("org.sqlite.JDBC");	
        Connection con = DriverManager.getConnection("jdbc:sqlite:Kata5.db");	
         
        Statement st = con.createStatement();
        
        String query = "SELECT * FROM PEOPLE";
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
        }
        
        query = "CREATE TABLE IF NOT EXISTS MAIL ('Id' INTEGER " +
            "PRIMARY KEY AUTOINCREMENT ,'Mail'	TEXT NOT NULL);"; 
        st.execute(query);
        FileReader fl = null;
    BufferedReader bf = null;
    String line = "C:\\Users\\usuario\\Desktop\\Kata4.txt";
    
    try{
    fl = new FileReader(line);
    bf = new BufferedReader (fl);
    String lineaux;
    while((lineaux = bf.readLine()) != null){
        if(!lineaux.contains("@")){
            continue;
        }
        query =	"INSERT	INTO	MAIL	(Mail)	VALUES	('"+lineaux+"');”";	
        st.execute(query);
    }
    }catch(IOException e){
        e.printStackTrace();
    }
    query = "Select count (*) from MAIL";
    rs = st.executeQuery(query);
    System.out.println("Número de registros de la tabla MAIL: " + rs.getInt(1));
  }
    
}
