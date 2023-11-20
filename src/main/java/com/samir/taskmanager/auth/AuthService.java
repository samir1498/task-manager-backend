package com.samir.taskmanager.auth;

import com.samir.taskmanager.user.UserRepository;
import com.samir.taskmanager.user.model.Role;
import com.samir.taskmanager.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Transactional
    public void addUser(SignupDTO signupDTO){
        Optional<User> optionalUser = userRepository.findByUsername(signupDTO.getUsername());
        if(optionalUser.isPresent()){
            throw  new IllegalStateException("Username not available");
        }else{
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setPassword(encoder.encode(signupDTO.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        }
    }
}
