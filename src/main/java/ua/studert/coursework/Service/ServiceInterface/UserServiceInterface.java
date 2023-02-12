package ua.studert.coursework.Service.ServiceInterface;

import ua.studert.coursework.Entity.UserEntity;
import ua.studert.coursework.Enum.Role;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;

import java.util.List;

public interface UserServiceInterface {
    public List<UserEntity> getAllUsers() throws DBIsEmptyException;
    public UserEntity getUserByUsername(String Username) throws NotFoundException;
    public boolean addUser(String username, String hashPass, Role role, String email) throws AlreadyExistException;

  //  void deleteUsers(List<Long> ids);
}
