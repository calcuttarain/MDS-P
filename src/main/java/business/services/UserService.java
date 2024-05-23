package business.services;

import business.models.User;
import persistance.repos.UserRepo;

import java.sql.SQLException;

public abstract class UserService {
    private UserRepo userRepo;

    public UserService()
    {
        try{
            this.userRepo = new UserRepo();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }




}
