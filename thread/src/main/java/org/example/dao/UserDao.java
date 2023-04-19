package org.example.dao;

import org.example.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection = null;
    public int saveUser(User user){
        String sql = "INSERT INTO userTbl (name, email_address) VALUES (?, ?)";
        connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmaillAddress());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            } else {
                System.out.println("not updated");
            }
            return 1;
        } catch (SQLException e) {
            return 0;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<User> getUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        connection = DBConnection.getConnection();
        String sql = "SELECT * FROM userTbl";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()){
            String id = result.getString(1);
            String name = result.getString(2);
            String emailAddress = result.getString(3);
            list.add(new User()
                    .setId(id)
                    .setName(name)
                    .setEmaillAddress(emailAddress));
        }
        connection.close();
        return list;
    }
}
