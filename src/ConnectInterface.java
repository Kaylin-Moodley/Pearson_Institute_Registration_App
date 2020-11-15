/*
Filename: ConnectInterface.java         
Author: Kaylin Moodley
Created: 19/10/2020
Operating System: Windows 10
*/

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectInterface extends Remote
{
    //Insert student details into the database
    public void insert(int id,String name,String surname,int age,int cellNum, String degree) throws RemoteException;
}
