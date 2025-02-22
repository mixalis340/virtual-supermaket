package api;

import models.Admin;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    private ProductManager productManager;

    public UserManager(){
        this.users = new ArrayList<>();
    }

    public boolean registerUser(User user){
        for(User existinUser: users){
            if(existinUser.getUsername().equals(user.getUsername())){
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public User login(String username, String password){
        for(User existingUser: users){
            if(existingUser.getUsername().equals(username) && existingUser.getPassword().equals(password)){
                System.out.println("You've successfully logged in!");
                return existingUser;
            }
        }
        System.out.println("Wrong credentials.");
        return null;
    }

    public List<User> getUserByRole(String role){
        List<User> result = new ArrayList<>();
        for(User existinUser: users){
            if(existinUser.getRole().equalsIgnoreCase(role)){
                result.add(existinUser);
            }
        }
        return result;
    }
}
