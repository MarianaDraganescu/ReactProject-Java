package ro.mariana.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.mariana.ppmtool.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}
