package SercviceClass;

import DaoClass.userDao;
import ModelClass.Users;

import java.util.List;
import java.util.Objects;

public class userService {
    private userDao usersDao;

    public userService(){
        usersDao = new userDao();
    }
    public userService(userDao newUsersDao){
        this.usersDao = newUsersDao;
    }

    /*allUser method should get all the users in the user table*/
    public List<Users>allUsers(){
        return this.usersDao.getAllUsers();
    }

    /*this method persist user details if and only if: -username is not empty,
    * -the password is greater than 8, and the username does not already exist*/
    public Users addUsers(Users users){
        Users usersList = usersDao.getByUserName(users.getUserName());
        String userName = users.getUserName();
        if(userName != "" && users.getPassword().length()>=8 && usersList==null){
            return usersDao.insertUserDetails(users);
        }
        return null;
    }

    public Users userLogin(Users users){
        return usersDao.getUserNameAndPassword(users);
    }

    public boolean dele(Users user){
        if(user.getUserName().length()==0 && user.getPassword().length()==0)return false;
        return usersDao.deleteMethod(user);
    }
}
