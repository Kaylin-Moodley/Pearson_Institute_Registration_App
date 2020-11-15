/*
Filename: Client.java         
Author: Kaylin Moodley
Created: 19/10/2020
Operating System: Windows 10
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client extends JFrame implements ActionListener
{
    //Variable Declaration
    JPanel panel;
    JLabel lblAppName, lblDev, lblID,lblName,lblSurname,lblAge,lblCell,lblDegree;
    JTextField txtID,txtName,txtSurname,txtAge,txtCell;
    JComboBox cmbDegree;
    JButton btnRegister;
    String[] degreeArray ={"BSc IT","BSc Computer Science","Higher Certificate in IT","BSc Biomedicine","Bachelor of Commerce","Bachelor of Arts"};
    DefaultListCellRenderer dlcr ; 
    int id,age,cell;
    String name,surname,degree;
  
    Client()
    {
        lblAppName = new JLabel("PIHE Registration App v1.0");
        lblDev = new JLabel("Developer: Kaylin Moodley");
        lblID = new JLabel("ID Number:");
        lblName = new JLabel("First Name:");
        lblSurname = new JLabel("Surname:");
        lblAge = new JLabel("Age:");
        lblCell = new JLabel("Cell Number:");
        lblDegree = new JLabel("Select Degree");
        
        txtID = new JTextField();
        txtName = new JTextField();
        txtSurname = new JTextField();
        txtAge = new JTextField();
        txtCell = new JTextField();
        
        cmbDegree= new JComboBox(degreeArray);
        dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER); 
        cmbDegree.setRenderer(dlcr);  
        
        btnRegister= new JButton("Register");
        
        panel = new JPanel(new GridLayout(0, 2));
        panel.add(lblAppName);
        panel.add(lblDev);
        panel.add(lblID);
        panel.add(txtID);
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblSurname);
        panel.add(txtSurname);
        panel.add(lblAge);
        panel.add(txtAge);
        panel.add(lblCell);
        panel.add(txtCell);
        panel.add(lblDegree);
        panel.add(cmbDegree);
        panel.add(btnRegister);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnRegister.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Pearson Institute Registration App");
        setSize(400, 300);
        setVisible(true);     
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        String source = ae.getActionCommand();
        if (source.equals("Register")) 
        {
            id =Integer.parseInt(txtID.getText());
            name =txtName.getText();
            surname =txtSurname.getText();
            age =Integer.parseInt(txtAge.getText());
            cell =Integer.parseInt(txtCell.getText());
            degree = cmbDegree.getSelectedItem().toString();
            if(name.equals("")||surname.equals("")||degree.equals(""))
            {
                JOptionPane.showMessageDialog(null,"One or more fields are empty","Error",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try
                {
                    Registry registry = LocateRegistry.getRegistry(8080);
                    ConnectInterface stub = (ConnectInterface) registry.lookup("ConnectInterface");
                    stub.insert(id,name,surname,age,cell,degree);
                    JOptionPane.showMessageDialog(null,"Student has been registered successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception e) 
                {
                    System.out.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }

            }
        }
    }
    
    public static void main(String[] args) {
        new Client();
    }
}
