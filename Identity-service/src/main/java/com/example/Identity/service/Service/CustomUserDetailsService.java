package com.example.Identity.service.Service;


import com.example.Identity.service.Model.CustomUserDetails;
import com.example.Identity.service.Model.UserCredential;
import com.example.Identity.service.Repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserCredentialRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential user = userRepo.findUserByEmail(username);
        CustomUserDetails userDetails=null;
        if(user!=null){
            userDetails=new CustomUserDetails();
            userDetails.setUser(user);
        }
        else{
            throw new UsernameNotFoundException("User not exist with given username");
        }
        return userDetails;
    }
}
