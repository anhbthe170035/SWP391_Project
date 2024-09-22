/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import controller.Encryption;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class UserDAO extends DBContext{

    public User checkLogin(String email, String password) {
        String sql = "select * from [Users] where [username] = ? and [password] = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, Encryption.MD5Encryption(password));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getInt(7),
                        rs.getDate(8)
                );
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean checkEmailExist(String email) {
        String query = "select * from [dbo].[Users] where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }

    public boolean addUser(User user) {
        if (user == null || user.getUsername() == null) {
            throw new IllegalArgumentException("User hoặc username không thể null");
        }

        String query = "INSERT INTO [dbo].[Users] "
                + "([username], [password], [name], [gender], [dob], [email], [phone], [status], [role]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?)";

        try (PreparedStatement st = connection.prepareStatement(query)) {

            st.setString(1, user.getUsername());
            st.setString(2, Encryption.MD5Encryption(user.getPassword()));
            st.setString(3, user.getEmail());

            int rowsAffected = st.executeUpdate();
            System.out.println(1);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(2);
            return false;
        }
    }

    public String getUsernamebyEmail(String email) {
        String username = null;
        String query = "SELECT username FROM Users WHERE email = ?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, email);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    username = rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }
    
}
