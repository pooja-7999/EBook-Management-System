package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.User;

public class UserDAOImpl {

    private Connection conn;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public User login(String email, String password) {
        User user = null;
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, email);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean userRegister(User user) {
        boolean result = false;
        String query = "INSERT INTO user (name, email, phno, password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPhno());
            pst.setString(4, user.getPassword());

            int rowCount = pst.executeUpdate();
            result = rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkPassword(int id, String password) {
        boolean isValid = false;
        String sql = "SELECT * FROM user WHERE id = ? AND password = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public boolean updateProfile(User us) {
        boolean result = false;
        String query = "UPDATE user SET name=?, email=?, phone=? WHERE id=?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, us.getName());
            pst.setString(2, us.getEmail());
            pst.setString(3, us.getPhno());
            pst.setInt(4, us.getId());

            int rowCount = pst.executeUpdate();
            result = rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean checkUser(String em) {
    	boolean f  = true;;
    	String query = "select * from user where email=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, em);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	f = false;
            }
           

           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return f;
    }
}
