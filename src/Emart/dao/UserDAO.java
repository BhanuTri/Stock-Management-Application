/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emart.dao;

import Emart.dbutil.DBConnection;
import Emart.pojo.UserProfile;
import Emart.pojo.Userpojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 91756
 */
public class UserDAO {
    public static boolean validateUser(Userpojo user)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Select * from users where userid=? and password=? and usertype=?");
     ps.setString(1, user.getUserid());
     ps.setString(2, user.getPassword());
     ps.setString(3, user.getUsertype());
     ResultSet rs=ps.executeQuery();
     if(rs.next()){
        
         UserProfile.setUsername(rs.getString(5));
         return true;
     }
     return false;
     
    }
    public static boolean isUserPresent(String empid)throws SQLException{
        
        Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Select 1 from users where empid=?");
     ps.setString(1, empid);
     ResultSet rs=ps.executeQuery();
     return rs.next();
    }
    
}
