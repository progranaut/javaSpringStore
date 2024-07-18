package com.ivlev.javaspringstore.security;

import com.ivlev.javaspringstore.entity.User;
import com.ivlev.javaspringstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .map(AppUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден!"));

    }
}
