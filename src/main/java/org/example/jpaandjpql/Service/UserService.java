package org.example.jpaandjpql.Service;


import lombok.RequiredArgsConstructor;
import org.example.jpaandjpql.ApiResponse.ApiException;
import org.example.jpaandjpql.Model.User;
import org.example.jpaandjpql.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User oldUser = userRepository.getUserById(id);

        if (oldUser == null){
            throw new ApiException("(USER-UPDATE)user not found");
        }
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setMail(user.getMail());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = userRepository.getUserById(id);
        if (user == null){
            throw new ApiException("(USER-DELETE)user not found");
        }
        userRepository.delete(user);
    }

    public User getUserByMail(String mail){
        User user = userRepository.getUserByMail(mail);
        if (user == null){
            throw new ApiException("(USER-GET-BY-MAIL)user not found");
        }
        return user;
    }

    public List<User> getByRole(String role){
        return userRepository.getUsersByRole(role);
    }

    public List<User> getByAge(Integer range){
        return userRepository.getUsersByAge(range);
    }

    public User checkLogin(String email, String password) {
        User user = userRepository.CheckMailAndPassword(email, password);
        if (user == null) {
            throw new ApiException("Wrong email or password");
        }
        return user;
    }


}
