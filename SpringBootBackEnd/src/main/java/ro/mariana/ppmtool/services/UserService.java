package ro.mariana.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.mariana.ppmtool.domain.User;
import ro.mariana.ppmtool.exceptions.UsernameAlreadyExistsException;
import ro.mariana.ppmtool.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            //username has to be unique (custom exception)
            newUser.setUsername(newUser.getUsername());

            //make sure password and confirmPassword match
            //We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        } catch (Exception ex) {
            throw new UsernameAlreadyExistsException("Username " + newUser.getUsername() + " already exists");
        }

    }
}
