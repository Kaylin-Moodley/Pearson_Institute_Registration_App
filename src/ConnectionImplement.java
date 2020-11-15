/*
Filename: ConnectionImplement.java         
Author: Kaylin Moodley
Created: 19/10/2020
Operating System: Windows 10
*/

import java.rmi.RemoteException;
import java.sql.*;

public class ConnectionImplement implements ConnectInterface
{

    //Implement the insert method from the ConnectInterface interface
    @Override
    public void insert(int id, String name, String surname, int age, int cellNum, String degree) throws RemoteException 
    {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            com.mysql.jdbc.Connection conn=(com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/registrants","root","");
            String sql ="INSERT INTO students(idnumber,name,surname,age,cellnumber,degree) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
               
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,surname);
            ps.setInt(4,age);
            ps.setInt(5,cellNum);
            ps.setString(6,degree);
            ps.execute();
            
            ps.close();
            conn.close();

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
