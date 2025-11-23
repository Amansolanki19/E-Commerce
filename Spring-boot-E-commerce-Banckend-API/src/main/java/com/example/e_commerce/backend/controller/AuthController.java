//package com.example.e_commerce.backend.controller;
//
//import com.example.e_commerce.backend.entity.User;
//import com.example.e_commerce.backend.exception.UserException;
//import com.example.e_commerce.backend.repositories.UserRepo;
//import com.example.e_commerce.backend.response.AuthResponse;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private JwtProvider jwtProvider;
//    public ResponseEntity<AuthResponse> createUser(@RequestBody User user)throws UserException {
//        String email= user.getEmail();
//        String password= user.getPassword();
//        String firstName= user.getFirstName();
//        String lastName= user.getLastName();
//
//        User isExist=userRepo.findByEmail(email);
//
//        if(isExist!=null)   throw new UserException("Email is already associated with other account ");
//
//        User createdUser=new User();
//        createdUser.setEmail(email);
//        createdUser.setPassword(password);
//        createdUser.setFirstName(firstName);
//        createdUser.setLastName(lastName);
//
//        userRepo.save(createdUser);
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String token = jwtProvider.generateToken(authentication);
//
//        return new ResponseEntity<AuthResponse>(new AuthResponse(token,"Sign-Up successfully"), HttpStatus.CREATED);
//    }
//}
