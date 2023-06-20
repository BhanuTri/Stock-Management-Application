/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emart.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 91756
 */
public class DBConnection {
    private static Connection conn;
    static{
        try{
            Class.forName("oracle.jdbc.OracleDriver");

conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-94PAOD2:1521/xe","grocery","grocery");
JOptionPane.showMessageDialog(null,"Connection opened Successfully","Success!",JOptionPane.INFORMATION_MESSAGE);

        }
        catch(ClassNotFoundException ex){
           JOptionPane.showMessageDialog(null,"Error in loading the driver","error!",JOptionPane.ERROR_MESSAGE);
           ex.printStackTrace();
           System.exit(1);
 
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in opening the connection","DB error!",JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
            System.exit(1);
            

        }
    }
    
    public static Connection getConnection(){
        return conn;
    }
    public static void closeConnection(){
        try{
            conn.close();
            JOptionPane.showMessageDialog(null,"Connection  Successfully closed","Success!",JOptionPane.INFORMATION_MESSAGE);

            
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in closing the connection","DB error!",JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
        }
    }
}
