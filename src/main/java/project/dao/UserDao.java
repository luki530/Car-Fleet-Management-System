package project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.repository.UserRepository;
import project.user.User;

import java.util.List;

@Service
public class UserDao {

    @Autowired
    UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findOne(Integer userId){
        return userRepository.findById(userId).orElse(null);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

}
