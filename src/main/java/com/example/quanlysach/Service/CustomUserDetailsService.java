package com.example.quanlysach.Service;


import com.example.quanlysach.Model.Role;
import com.example.quanlysach.Model.User;
import com.example.quanlysach.Repo.RoleRespository;
import com.example.quanlysach.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRespository roleRespository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User u = userRepository.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        Set<GrantedAuthority> authorities = u.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                username,
                u.getPassword(),
                authorities
        );
    }
    public Boolean isUserExist(String email) {
        var u = userRepository.getUserByEmail(email);
        return u != null;
    }
    public User getUserByString(String s) {
        return  userRepository.getUserByUsername(s);
    }
    public void NewUser(User u) {
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRespository.getRoleByName("USER");
            roles.add(userRole);
            u.setRoles(roles);
            System.out.println(u.getUsername());
            userRepository.save(u);
    }
}
