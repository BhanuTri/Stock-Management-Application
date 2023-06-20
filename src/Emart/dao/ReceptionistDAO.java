/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emart.dao;

import Emart.dbutil.DBConnection;
import Emart.pojo.Receptionistpojo;
import static Emart.pojo.UserProfile.userid;
import Emart.pojo.Userpojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 91756
 */
public class ReceptionistDAO {

    
    public static Map<String,String> getNonRegisteredReceptionist()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("Select empid,empname from employees where job='Receptionist'and empid not in(select empid from users where usertype='Receptionist')");
        HashMap<String,String> receptionistList=new HashMap<>();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionistList.put(id, name);
        }
        return receptionistList;
        
    }
     public static boolean addReceptionist(Userpojo user)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?,?,?)");
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getEmpid());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getUsertype());
        ps.setString(5, user.getUsername());
       int x=ps.executeUpdate();
       return x==1;
}
     public static  List<Receptionistpojo> getAllReceptionistDetails()throws SQLException{
         Connection con=DBConnection.getConnection();
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery("Select users.empid,empname,userid,job,salary from users,employees where usertype='Receptionist' and users.empid=employees.empid");
         ArrayList<Receptionistpojo> al=new ArrayList<>();
         while(rs.next()){
             Receptionistpojo recep=new Receptionistpojo();
             recep.setEmpid(rs.getString(1));
             recep.setEmpname(rs.getString(2));
             recep.setUserid(rs.getString(3));
             recep.setJob(rs.getString(4));
             recep.setSalary(rs.getDouble(5));
             al.add(recep);
         }
         return al;
     }
     public static Map<String,String> getAllReceptionistId()throws SQLException{
         
         Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("Select userid,username from users where usertype='Receptionist'order by userid");
        HashMap<String,String> receptionistList=new HashMap<>();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionistList.put(id, name);
        }
        return receptionistList;
        
     }
     public static boolean UpdatePassword(String userid,String pwd)throws SQLException{
          Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Update users set password=? where userid=?");
        ps.setString(1, pwd);
        ps.setString(2, userid);
        ps.executeQuery();
        
        
     return ps.executeUpdate()==1;
     }
     public static List<String> getAllReceptionistUserId()throws SQLException{
         Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("Select userid from users where usertype='Receptionist'order by userid");
        List<String> receptionistList=new ArrayList<>();
        while(rs.next()){
            String id=rs.getString(1);
            
            receptionistList.add(id);
     }
        return receptionistList;
     }
     public static boolean deleteReceptionist(String userid)throws SQLException{
         Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete users where userid=?");
        ps.setString(1,userid);
        return ps.executeUpdate()==1;
     }
             
             
             
             }
