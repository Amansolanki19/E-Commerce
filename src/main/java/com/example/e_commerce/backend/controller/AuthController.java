package com.example.e_commerce.backend.controller;

import com.example.e_commerce.backend.Configuration.JwtProvider;
import com.example.e_commerce.backend.entity.User;
import com.example.e_commerce.backend.exception.UserException;
import com.example.e_commerce.backend.repositories.UserRepo;
import com.example.e_commerce.backend.request.LoginRequest;
import com.example.e_commerce.backend.response.AuthResponse;
import com.example.e_commerce.backend.services.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.prefs.BackingStoreException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomService customService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user)throws UserException {
        String email= user.getEmail();
        String password= user.getPassword();
        String firstName= user.getFirstName();
        String lastName= user.getLastName();
        LocalDateTime currentDate=LocalDateTime.now();

        User isExist=userRepo.findByEmail(email);
        if(isExist!=null)   throw new UserException("Email is already associated with other account ");

        User createdUser=new User();
        createdUser.setCreatedAt(currentDate);
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        User newUser=userRepo.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser.getEmail(),
                newUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return new ResponseEntity<AuthResponse>(new AuthResponse(token,"Sign-Up successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request){
        String username= request.getEmail();
        String password= request.getPassword();

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtProvider.generateToken(authentication);
        return new ResponseEntity<AuthResponse>(new AuthResponse(token,"Login successful"),HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password)   {
        UserDetails userDetails = customService.loadUserByUsername(username);
        if(userDetails==null)   throw new BadCredentialsException("No user found");

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Password mismatch...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
