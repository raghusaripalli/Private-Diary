package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	
	public static User login(String username, String password) {
		try (Connection con = Database.getConnection()) {
            PreparedStatement ps = con.prepareStatement
                ("select * from users where uname = ? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUname(username);
                u.setPassword(password);
                u.setFullname(rs.getString("fullname"));
                u.setEmail(rs.getString("email"));
                u.setMobile(rs.getString("mobile"));
                return u;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("UserDAO-> login() : " + ex.getMessage());
            return null;
        }
	}
	
}
