/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import myLib.DBHelper;

/**
 *
 * @author DELL
 */
public class AccountsDAO implements  Serializable{
    public AccountsDTO CheckLogin (String Email, String Password) throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountsDTO result = null;
        try{
            con = DBHelper.getConnection();
            String sql ="select FullName , Role, Status "
                    + "from Accounts "
                    + "where Email = ? and Password = ?";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, Email);
            stm.setString(2, Password);
            
            rs = stm.executeQuery();
            
            if(rs.next()){
                String FullName = rs.getString("FullName");
                String Role = rs.getString("Role");
                boolean Status = rs.getBoolean("Status");
                
                if(Role.equals("admin") && Status == true){
                    result = new AccountsDTO(1, Email, FullName, Password, Role, Status);
                }
            }
        }finally{
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return result;
    }
}
