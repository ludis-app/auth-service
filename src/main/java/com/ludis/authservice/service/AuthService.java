package com.ludis.authservice.service;

import com.ludis.authservice.exceptions.CouldNotCreateTokenException;
import com.ludis.authservice.exceptions.EmailDoesNotExistException;
import com.ludis.authservice.exceptions.NotAuthenticatedException;
import com.ludis.authservice.exceptions.TokenIsEmptyException;
import com.ludis.authservice.model.Login;
import com.ludis.authservice.model.User;
import com.ludis.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepo;

    public String signIn(Login login) throws NotAuthenticatedException, CouldNotCreateTokenException, EmailDoesNotExistException {
        User user;
        try{
            user = userRepo.findByEmail(login.getEmail());
            if (login.getEmail().equals(user.getEmail()) && DecodePassword(login.getPassword(), user.getPassword())) {
                return createToken(user);
            }
            else{
                throw new NotAuthenticatedException();
            }
        } catch (Exception e){
            throw new EmailDoesNotExistException();
        }
    }

    public String createToken(User user) throws CouldNotCreateTokenException {
        String token;
        try {
            token = jwtService.createToken(user);
            if(token.isEmpty()){
                throw new TokenIsEmptyException();
            }
            return token;
        }
        catch(Exception e){
            throw new CouldNotCreateTokenException();
        }
    }

    public String EncodePassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
    }

    public boolean DecodePassword(String loginPassword, String userPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(loginPassword, userPassword);
    }

    //TODO: SignUp & Encoding password
//    public User signUp(Login login) {
//       return User;
//    }
}
