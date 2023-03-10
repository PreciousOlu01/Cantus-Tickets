package DaoClass;

import ModelClass.Users;
import Util.ConnectionSingleton;
import org.h2.engine.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDao {
    /*The insertUserDetails will be used to take user details to populate the
    * users table. Here the user will create a new account using a username and password
    * that does not already exist in database.
    * */
    public Users insertUserDetails(Users users){
        Connection conn = ConnectionSingleton.getConnection();

        try{
            String sql = "insert into users(username, password)values(?,?);";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //set preparedStatement
            ps.setString(1, users.getUserName());
            ps.setString(2, users.getPassword());
            ps.executeUpdate();

            ResultSet rsKey = ps.getGeneratedKeys();
            if(rsKey.next()){
                int generatedId = (int)rsKey.getLong(1);
                return new Users(generatedId, users.getUserName(), users.getPassword());
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*getUserById method should be able to get a particular user using user_id*/
    public Users getUserById(int id){
        Connection conn = ConnectionSingleton.getConnection();
        try{
            String sql = "select * from users where user_id=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            //set preparedStatement
            ps.setInt(1, id);

            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Users user = new Users(rs.getInt("user_id"), rs.getString("username"),
                        rs.getString("password"));
                return user;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*getUserNameAndPassword method enable you verify user authenticity, if userName and password
    * are in the database it means the user already registered and should have access to their info*/
    public Users getUserNameAndPassword(Users users){
        Connection conn = ConnectionSingleton.getConnection();
        try{
            String sql = "select * from users where username=? and password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            //set preparedStatement
            ps.setString(1, users.getUserName());
            ps.setString(2, users.getPassword());

            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Users userAccount = new Users(resultSet.getInt("user_id"),
                        resultSet.getString("username"), resultSet.getString("password"));
                return userAccount;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*getAllUsers method should be able to get all the users in the database*/
    public List<Users>getAllUsers(){
        Connection conn = ConnectionSingleton.getConnection();
        List<Users>usersAccount = new ArrayList<>();
        try{
            String sql = "select * from users;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Users userList = new Users(rs.getInt("user_id"), rs.getString("username"),
                        rs.getString("password"));
                usersAccount.add(userList);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return usersAccount;
    }

    public Users getByUserName(String username){
        Connection conn = ConnectionSingleton.getConnection();
        try{
            String sql= "select * from users where username=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Users usersDetails = new Users(rs.getInt("user_id"),rs.getString("username"),
                        rs.getString("password"));
                return usersDetails;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean deleteMethod(Users users){
        Connection conn = ConnectionSingleton.getConnection();
        try{
            String sql = "delete from users where user_id=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, users.getUserId());

            int i = ps.executeUpdate();
            if(i==1)return true;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
