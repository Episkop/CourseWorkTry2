package ua.studert.coursework.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import ua.studert.coursework.Configuration.AppConfig;
import ua.studert.coursework.Entity.UserEntity;
import ua.studert.coursework.Enum.Role;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Repository.UserRepository;
import ua.studert.coursework.Service.ServiceInterface.UserServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserServiceInterface {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> getAllUsers() throws DBIsEmptyException {
        List<UserEntity> list = userRepository.findAll();
        if(list.isEmpty()){
            throw new DBIsEmptyException("Data Base is empty");
        }
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public UserEntity getUserByUsername(String username) throws NotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(username.isEmpty()){
            throw new NotFoundException("User don`t find");
        }
        return user;
    }
    @Transactional
    public boolean registration(UserEntity userEntity) throws AlreadyExistException {
        if(userRepository.existsByUsername(userEntity.getUsername())){
            throw new AlreadyExistException("Such " + userEntity.getUsername() + "is already exist");
        }
        userRepository.save(userEntity);
        return true;
    }
    @Transactional
    @Override
    public boolean addUser(String username, String hashPass, Role role, String email) throws AlreadyExistException {
        if(userRepository.existsByUsername(username)) {
            throw new AlreadyExistException("Such " + username + "is already exist");
        }
        UserEntity user = new UserEntity(username,hashPass,role,email);
         userRepository.save(user);
         return true;
    }
//    @Transactional
//    @Override
//    public void deleteUsers(List<Long> ids) {
//        ids.forEach(id -> {
//            Optional<UserEntity> user = userRepository.findById(id);
//            user.ifPresent(u -> {
//                if ( ! AppConfig.ADMIN.equals(u.getUsername())) {
//                    userRepository.deleteById(u.getId());
//                }
//            });
//        });
//    }

}
