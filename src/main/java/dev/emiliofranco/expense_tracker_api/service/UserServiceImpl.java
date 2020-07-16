package dev.emiliofranco.expense_tracker_api.service;

import dev.emiliofranco.expense_tracker_api.dao.UserRepository;
import dev.emiliofranco.expense_tracker_api.exception.UserAuthException;
import dev.emiliofranco.expense_tracker_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl  implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws UserAuthException {
        return null;
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws UserAuthException {
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email= email.toLowerCase();
        if(!emailPattern.matcher(email).matches())
            throw new UserAuthException("Invalid email format");
        if(userRepository.getCountByEmail(email) > 0)
            throw new UserAuthException("Email already in use");

        int userId = userRepository.create(firstName, lastName, email, password);

        return userRepository.findById(userId);
    }
}
